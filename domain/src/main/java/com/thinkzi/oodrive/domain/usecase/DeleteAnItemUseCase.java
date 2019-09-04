package com.thinkzi.oodrive.domain.usecase;

import com.thinkzi.oodrive.domain.executor.PostExecutionThread;
import com.thinkzi.oodrive.domain.executor.ThreadExecutor;
import com.thinkzi.oodrive.domain.repository.IItemRepository;
import com.thinkzi.oodrive.domain.usecase.base.CompletableUseCase;
import com.thinkzi.oodrive.domain.utility.check.Checker;

import javax.inject.Inject;

import io.reactivex.Completable;

public final class DeleteAnItemUseCase extends CompletableUseCase<DeleteAnItemUseCase.Ps> {

    private final IItemRepository _iItemRepository;

    @Inject
    public DeleteAnItemUseCase(ThreadExecutor _threadExecutor, PostExecutionThread _postExecutionThread, IItemRepository _iItemRepository) {
        super(_threadExecutor, _postExecutionThread);
        this._iItemRepository = _iItemRepository;
    }

    @Override
    protected Completable buildCompletableUseCase(Ps _params) {
        Checker.checkNotNull(_params);
        return _iItemRepository.deleteItem(_params._id);
    }

    /**
     * provide parameters to delete an item from server
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
