package com.thinkzi.oodrive.domain.usecase;

import com.thinkzi.oodrive.domain.entity.Item;
import com.thinkzi.oodrive.domain.executor.PostExecutionThread;
import com.thinkzi.oodrive.domain.executor.ThreadExecutor;
import com.thinkzi.oodrive.domain.repository.IItemRepository;
import com.thinkzi.oodrive.domain.usecase.base.ObservableUseCase;
import com.thinkzi.oodrive.domain.utility.check.Checker;

import javax.inject.Inject;

import io.reactivex.Observable;

public final class WatchRemoteFolderContentUseCase extends ObservableUseCase<Item, WatchRemoteFolderContentUseCase.Ps> {

    private final IItemRepository _iItemRepository;

    @Inject
    public WatchRemoteFolderContentUseCase(ThreadExecutor _threadExecutor, PostExecutionThread _postExecutionThread, IItemRepository _iItemRepository) {
        super(_threadExecutor, _postExecutionThread);
        this._iItemRepository = _iItemRepository;
    }

    @Override
    protected Observable<Item> buildObservableUseCase(Ps _params) {
        Checker.checkNotNull(_params);
        return _iItemRepository.getRemoteFolderContent(_params._id);
    }

    /**
     * provide parameters to get content of a folder from server
     * */
    public static final class Ps {

        // folder id
        private final String _id;

        private Ps(String _id) {
            this._id = _id;
        }

        public static Ps forPs(String _id) {
            return new Ps(_id);
        }

    }
}
