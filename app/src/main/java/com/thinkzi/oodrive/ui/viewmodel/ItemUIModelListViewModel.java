package com.thinkzi.oodrive.ui.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.thinkzi.oodrive.domain.entity.Item;
import com.thinkzi.oodrive.domain.entity.User;
import com.thinkzi.oodrive.domain.exception.DefaultErrorBundle;
import com.thinkzi.oodrive.domain.usecase.WatchRemoteCurrentUserUseCase;
import com.thinkzi.oodrive.domain.usecase.WatchRemoteFolderContentUseCase;
import com.thinkzi.oodrive.domain.usecase.base.ObservableObserver;
import com.thinkzi.oodrive.domain.usecase.base.SingleObserver;
import com.thinkzi.oodrive.ui.mapper.ItemUIModelMapper;
import com.thinkzi.oodrive.ui.mapper.UserUIModelMapper;
import com.thinkzi.oodrive.ui.model.ItemUIModel;
import com.thinkzi.oodrive.ui.model.UserUIModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * provide ItemUIModelListViewModel for operations on ItemUIModelListActivity: save data of View(Activity) for configuration change management,
 * treat actions from View, data binding, live data, observer result sent from UseCase ...
 * */
@Singleton
public class ItemUIModelListViewModel extends BaseViewModel {

    // UseCase(Clean Architecture) to watch current user and retrieve the root item from server from server
    private final WatchRemoteCurrentUserUseCase _watchRemoteCurrentUserUseCase;

    // UseCase(Clean Architecture) to watch content of a folder from server
    private final WatchRemoteFolderContentUseCase _watchRemoteFolderContentUseCase;

    // a mapper to map data between 2 layers domain and app(ui)
    private final ItemUIModelMapper _itemUIModelMapper;

    // a mapper to map data between 2 layers domain and app(ui)
    private final UserUIModelMapper _userUIModelMapper;

    // a live data list of ItemUIModel
    private final MutableLiveData<List<ItemUIModel>> _itemUIModelListMutableLiveData;

    // a live data UserUIModel
    private final MutableLiveData<UserUIModel> _userUIModelMutableLiveData;

    // application context
    private final Context _context;

    @Inject
    public ItemUIModelListViewModel(WatchRemoteCurrentUserUseCase _watchRemoteCurrentUserUseCase, WatchRemoteFolderContentUseCase _watchRemoteFolderContentUseCase,
                                    ItemUIModelMapper _itemUIModelMapper, UserUIModelMapper _userUIModelMapper, Context _context) {
        super();
        this._watchRemoteCurrentUserUseCase = _watchRemoteCurrentUserUseCase;
        this._watchRemoteFolderContentUseCase = _watchRemoteFolderContentUseCase;
        this._itemUIModelMapper = _itemUIModelMapper;
        this._userUIModelMapper = _userUIModelMapper;
        this._itemUIModelListMutableLiveData = new MutableLiveData<>();
        this._itemUIModelListMutableLiveData.setValue(new ArrayList<ItemUIModel>());
        this._userUIModelMutableLiveData = new MutableLiveData<>();
        this._userUIModelMutableLiveData.setValue(new UserUIModel());
        this._context = _context;

        getRemoteCurrentUser();
    }

    public MutableLiveData<List<ItemUIModel>> getItemUIModelListMutableLiveData() {
        return _itemUIModelListMutableLiveData;
    }

    public MutableLiveData<UserUIModel> getUserUIModelMutableLiveData() {
        return _userUIModelMutableLiveData;
    }

    /**
     * get current user and retrieve the root item from server
     * */
    private void getRemoteCurrentUser() {
        this._watchRemoteCurrentUserUseCase.execute(new WatchRemoteCurrentUserObserver(), null);
    }

    /**
     * handle received current user from WatchRemoteCurrentUserUseCase
     * */
    private void handleUser(User _user) {
        _userUIModelMutableLiveData.setValue(_userUIModelMapper.transform(_user));
        _watchRemoteFolderContentUseCase.execute(new WatchRemoteFolderContentObserver(), WatchRemoteFolderContentUseCase.Ps.forPs(_userUIModelMutableLiveData.getValue().getRootItem().getId()));
    }

    /**
     * handle received content of a folder from WatchRemoteFolderContentUseCase
     * */
    private void handleItem(Item _item) {
        List<ItemUIModel> _itemUIModelList = _itemUIModelListMutableLiveData.getValue();
        _itemUIModelList.add(_itemUIModelMapper.transform(_item));
        _itemUIModelListMutableLiveData.setValue(_itemUIModelList);
    }

    /**
     * provide a observer to treat current user and root item sent from server
     * */
    private final class WatchRemoteCurrentUserObserver extends SingleObserver<User> {

        @Override
        public void onSuccess(User _user) {
            handleUser(_user);
            _watchRemoteCurrentUserUseCase.dispose();
        }

        @Override
        public void onError(Throwable e) {
            handleError(new DefaultErrorBundle((Exception) e));
        }

    }

    /**
     * provide a observer to treat content of a folder from server
     * */
    private final class WatchRemoteFolderContentObserver extends ObservableObserver<Item> {

        @Override
        public void onNext(Item _item) {
            handleItem(_item);
        }

        @Override
        public void onError(Throwable e) {
            handleError(new DefaultErrorBundle((Exception) e));
        }

        @Override
        public void onComplete() {
            _watchRemoteFolderContentUseCase.dispose();
        }

    }

}
