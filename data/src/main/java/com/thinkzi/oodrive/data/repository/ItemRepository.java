package com.thinkzi.oodrive.data.repository;

import android.content.Context;
import com.thinkzi.oodrive.data.mapper.ItemDataModelMapper;
import com.thinkzi.oodrive.domain.entity.Item;
import com.thinkzi.oodrive.domain.executor.PostExecutionThread;
import com.thinkzi.oodrive.domain.executor.ThreadExecutor;
import com.thinkzi.oodrive.domain.repository.IItemRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * provide implementation for item data repository
 * */
@Singleton
public class ItemRepository implements IItemRepository {

    // mapper to map data between 2 layers data and domain
    private final ItemDataModelMapper _itemDataModelMapper;

    // application conext
    private final Context _context;

    // executor thread pool to execute work
    private final ThreadExecutor _threadExecutor;

    // thread created to change the execution context
    private final PostExecutionThread _postExecutionThread;

    @Inject
    public ItemRepository(ItemDataModelMapper _itemDataModelMapper, Context _context, ThreadExecutor _threadExecutor, PostExecutionThread _postExecutionThread) {

        this._itemDataModelMapper = _itemDataModelMapper;
        this._context = _context;
        this._threadExecutor = _threadExecutor;
        this._postExecutionThread = _postExecutionThread;

    }

    @Override
    public Single<Item> getRemoteRootItem() {
        return null;
    }

    @Override
    public Observable<Item> getRemoteFolderContent(String _id) {
        return null;
    }
}
