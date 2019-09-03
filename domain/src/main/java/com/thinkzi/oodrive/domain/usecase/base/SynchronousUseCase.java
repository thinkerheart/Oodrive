package com.thinkzi.oodrive.domain.usecase.base;

/**
 * provide common execution interface of all synchronous UseCase(Clean Architecture)
 * */
public interface SynchronousUseCase<Rs, Ps> {

    interface Callback<Rs> {

        void onSuccess(Rs _return);

        void onError(Throwable _throwable);

    }

    void execute(Ps _parameter, Callback<Rs> _callback);

}
