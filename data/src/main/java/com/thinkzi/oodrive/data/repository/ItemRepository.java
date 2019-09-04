package com.thinkzi.oodrive.data.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.thinkzi.oodrive.data.exception.NetworkConnectionException;
import com.thinkzi.oodrive.data.mapper.ItemDataModelMapper;
import com.thinkzi.oodrive.data.mapper.UserDataModelMapper;
import com.thinkzi.oodrive.data.model.CreateNewFolderPOSTRequestBodyDataModel;
import com.thinkzi.oodrive.data.model.ItemDataModel;
import com.thinkzi.oodrive.data.model.UserDataModel;
import com.thinkzi.oodrive.data.net.APIConnection;
import com.thinkzi.oodrive.domain.entity.Item;
import com.thinkzi.oodrive.domain.entity.User;
import com.thinkzi.oodrive.domain.repository.IItemRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;

/**
 * provide implementation for item data repository
 * */
@Singleton
public class ItemRepository implements IItemRepository {

    // mapper to map data between 2 layers data and domain
    private final ItemDataModelMapper _itemDataModelMapper;

    // mapper to map data between 2 layers data and domain
    private final UserDataModelMapper _userDataModelMapper;

    @Inject
    public ItemRepository(ItemDataModelMapper _itemDataModelMapper, UserDataModelMapper _userDataModelMapper) {

        this._itemDataModelMapper = _itemDataModelMapper;
        this._userDataModelMapper = _userDataModelMapper;

    }

    @Override
    public Single<User> getRemoteCurrentUser() {
        return APIConnection.getInstance().getRemoteCurrentUser().flatMap(new Function<UserDataModel, SingleSource<User>>() {

            @Override
            public SingleSource<User> apply(final UserDataModel userDataModel) throws Exception {

                return Single.create(new SingleOnSubscribe<User>() {

                    @Override
                    public void subscribe(SingleEmitter<User> emitter) throws Exception {
                        try {
                            // send user data object as a stream to observers
                            emitter.onSuccess(_userDataModelMapper.transform(userDataModel));
                        } catch (Exception e) {
                            // send a error as result to observers
                            emitter.onError(new NetworkConnectionException(e.getCause()));
                        }
                    }

                });

            }

        });
    }

    @Override
    public Observable<Item> getRemoteFolderContent(String _id) {
        return APIConnection.getInstance().getRemoteFolderContent(_id).flatMapObservable(new Function<ResponseBody, ObservableSource<Item>>() {

            @Override
            public ObservableSource<Item> apply(final ResponseBody responseBody) throws Exception {

                return Observable.create(new ObservableOnSubscribe<Item>() {

                    @Override
                    public void subscribe(ObservableEmitter<Item> emitter) throws Exception {

                        try {

                            // use Gson stream to treat Json result. Idea: not put all json objects in memory
                            Gson _gson = new GsonBuilder().create();
                            JsonReader _reader = _gson.newJsonReader(responseBody.charStream());

                            // start reading Json data array
                            _reader.beginArray();

                            // while Json data array has next item
                            while (_reader.hasNext() && _reader.peek() != JsonToken.END_ARRAY) {

                                // read a Item data object from Json data
                                ItemDataModel _itemDataModel = _gson.fromJson(_reader, ItemDataModel.class);

                                // send Item data object as a stream to observers
                                emitter.onNext(_itemDataModelMapper.transform(_itemDataModel));

                            }

                            // send work complete result to observers
                            emitter.onComplete();

                        } catch (Exception e) {
                            // send a error as result to observers
                            emitter.onError(new NetworkConnectionException(e.getCause()));
                        }

                    }

                });

            }

        });
    }

    @Override
    public Single<Item> createNewFolder(String _id, String _name) {
        return APIConnection.getInstance().createNewFolder(_id, new CreateNewFolderPOSTRequestBodyDataModel(_name)).flatMap(new Function<ItemDataModel, SingleSource<Item>>() {
            @Override
            public SingleSource<Item> apply(final ItemDataModel itemDataModel) throws Exception {

                return Single.create(new SingleOnSubscribe<Item>() {
                    @Override
                    public void subscribe(SingleEmitter<Item> emitter) throws Exception {

                        try {
                            // send new item data object as a stream to observers
                            emitter.onSuccess(_itemDataModelMapper.transform(itemDataModel));
                        } catch (Exception e) {
                            // send a error as result to observers
                            emitter.onError(new NetworkConnectionException(e.getCause()));
                        }

                    }
                });

            }
        });
    }

    @Override
    public Completable deleteItem(String _id) {
        return APIConnection.getInstance().deleteItem(_id);
    }
}
