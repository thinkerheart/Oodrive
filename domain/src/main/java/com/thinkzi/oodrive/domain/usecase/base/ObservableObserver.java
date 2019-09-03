package com.thinkzi.oodrive.domain.usecase.base;

import io.reactivex.observers.DisposableObserver;

/**
 * provide a observer for the work result (T object or complete or error)
 * */
public class ObservableObserver<T> extends DisposableObserver<T> {

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

}
