package com.thinkzi.oodrive.domain.usecase.base;

import com.thinkzi.oodrive.domain.executor.PostExecutionThread;
import com.thinkzi.oodrive.domain.executor.ThreadExecutor;
import com.thinkzi.oodrive.domain.utility.check.Checker;
import io.reactivex.Completable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * provide common abstract execution of all reactive asynchronous UseCase(Clean Architecture) that have only 2 results (complete or error)
 * */
public abstract class CompletableUseCase<Ps> extends AsynchronousRxUseCase {

    protected CompletableUseCase(ThreadExecutor _threadExecutor, PostExecutionThread _postExecutionThread) {

        super(_threadExecutor, _postExecutionThread);

    }

    /**
     * build work execution of UseCase
     * */
    protected abstract Completable buildCompletableUseCase(Ps _params);

    /**
     * add observer for result and execute UseCase with input parameter
     * */
    public void execute(DisposableCompletableObserver _observer, Ps _params) {

        Checker.checkNotNull(_observer);

        final Completable _completable = this.buildCompletableUseCase(_params)
                .subscribeOn(Schedulers.from(_threadExecutor))
                .observeOn(_postExecutionThread.getScheduler());

        addDisposable(_completable.subscribeWith(_observer));

    }
}
