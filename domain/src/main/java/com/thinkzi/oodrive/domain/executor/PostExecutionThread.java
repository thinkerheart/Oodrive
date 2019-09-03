package com.thinkzi.oodrive.domain.executor;

import io.reactivex.Scheduler;

/**
 * provide Thread abstraction created to change the execution context from any thread to any other thread.
 * Useful to encapsulate a UI Thread for example, since some job will be done in background, an
 * implementation of this interface will change context and update the UI.
 */
public interface PostExecutionThread {
    Scheduler getScheduler();
}