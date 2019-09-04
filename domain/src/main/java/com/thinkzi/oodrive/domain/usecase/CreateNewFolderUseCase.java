package com.thinkzi.oodrive.domain.usecase;

import com.thinkzi.oodrive.domain.entity.Item;
import com.thinkzi.oodrive.domain.executor.PostExecutionThread;
import com.thinkzi.oodrive.domain.executor.ThreadExecutor;
import com.thinkzi.oodrive.domain.repository.IItemRepository;
import com.thinkzi.oodrive.domain.usecase.base.SingleUseCase;
import com.thinkzi.oodrive.domain.utility.check.Checker;

import javax.inject.Inject;

import io.reactivex.Single;

public final class CreateNewFolderUseCase extends SingleUseCase<Item, CreateNewFolderUseCase.Ps> {

    private final IItemRepository _iItemRepository;

    @Inject
    public CreateNewFolderUseCase(ThreadExecutor _threadExecutor, PostExecutionThread _postExecutionThread, IItemRepository _iItemRepository) {
        super(_threadExecutor, _postExecutionThread);
        this._iItemRepository = _iItemRepository;
    }

    @Override
    protected Single<Item> buildSingleUseCase(Ps _params) {
        Checker.checkNotNull(_params);
        return _iItemRepository.createNewFolder(_params._id, _params._name);
    }

    /**
     * provide parameters to get create a new folder
     * */
    public static final class Ps {

        //folder id identified to create new folder
        private final String _id;

        //new folder name
        private final String _name;

        private Ps(String _id, String _name) {
            this._id = _id;
            this._name = _name;
        }

        public static Ps forPs(String _id, String _name) {
            return new Ps(_id, _name);
        }

    }
}
