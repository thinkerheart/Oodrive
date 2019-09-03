package com.thinkzi.oodrive.domain.usecase.base;

import io.reactivex.observers.DisposableCompletableObserver;

/**
 * provide a observer for the work result (complete or error)
 * */
public class CompletableObserver extends DisposableCompletableObserver {

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {

    }

}
