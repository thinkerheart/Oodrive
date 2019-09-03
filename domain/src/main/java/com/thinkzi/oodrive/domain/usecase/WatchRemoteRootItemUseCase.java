package com.thinkzi.oodrive.domain.usecase;

import com.thinkzi.oodrive.domain.entity.Item;
import com.thinkzi.oodrive.domain.executor.PostExecutionThread;
import com.thinkzi.oodrive.domain.executor.ThreadExecutor;
import com.thinkzi.oodrive.domain.repository.IItemRepository;
import com.thinkzi.oodrive.domain.usecase.base.SingleUseCase;

import javax.inject.Inject;
import io.reactivex.Single;

/**
 * provide UseCase(Clean Architecture) to watch root item from server
 * */
public final class WatchRemoteRootItemUseCase extends SingleUseCase<Item, Void> {

    private final IItemRepository _iItemRepository;

    @Inject
    public WatchRemoteRootItemUseCase(ThreadExecutor _threadExecutor, PostExecutionThread _postExecutionThread, IItemRepository _iItemRepository) {
        super(_threadExecutor, _postExecutionThread);
        this._iItemRepository = _iItemRepository;
    }

    @Override
    protected Single<Item> buildSingleUseCase(Void _params) {
        return _iItemRepository.getRemoteRootItem();
    }
}
