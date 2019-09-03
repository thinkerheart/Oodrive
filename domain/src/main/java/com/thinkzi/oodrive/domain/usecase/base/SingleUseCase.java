package com.thinkzi.oodrive.domain.usecase.base;

import com.thinkzi.oodrive.domain.executor.PostExecutionThread;
import com.thinkzi.oodrive.domain.executor.ThreadExecutor;
import com.thinkzi.oodrive.domain.utility.check.Checker;
import io.reactivex.Single;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * provide common abstract execution of all reactive asynchronous UseCase(Clean Architecture) that have only results (return only Rs object or error)
 * */
public abstract class SingleUseCase<Rs, Ps> extends AsynchronousRxUseCase {

    protected SingleUseCase(ThreadExecutor _threadExecutor, PostExecutionThread _postExecutionThread) {

        super(_threadExecutor, _postExecutionThread);

    }

    /**
     * build work execution of UseCase
     * */
    protected abstract Single<Rs> buildSingleUseCase(Ps _params);

    /**
     * add observer for result and execute UseCase with input parameter
     * */
    public void execute(DisposableSingleObserver<Rs> _observer, Ps _params) {

        Checker.checkNotNull(_observer);

        final Single<Rs> _single = this.buildSingleUseCase(_params)
                .subscribeOn(Schedulers.from(_threadExecutor))
                .observeOn(_postExecutionThread.getScheduler());

        addDisposable(_single.subscribeWith(_observer));

    }
}
