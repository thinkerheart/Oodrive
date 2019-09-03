package com.thinkzi.oodrive.domain.usecase.base;

import io.reactivex.observers.DisposableSingleObserver;

/**
 * provide a observer for the work result (returned only T object or error)
 * */
public class SingleObserver<T> extends DisposableSingleObserver<T> {

    @Override
    public void onSuccess(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }

}
