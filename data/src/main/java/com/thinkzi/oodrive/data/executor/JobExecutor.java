package com.thinkzi.oodrive.data.executor;

import androidx.annotation.NonNull;

import com.thinkzi.oodrive.domain.executor.ThreadExecutor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * provide an implementation of ThreadExecutor for thread pool to execute works
 */
@Singleton
public class JobExecutor implements ThreadExecutor {

    private final ThreadPoolExecutor _threadPoolExecutor;

    @Inject
    JobExecutor() {
        this._threadPoolExecutor = new ThreadPoolExecutor(3, 5, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new JobThreadFactory());
    }

    @Override
    public void execute(@NonNull Runnable command) {
        this._threadPoolExecutor.execute(command);
    }

    private static class JobThreadFactory implements ThreadFactory {
        private int counter = 0;


        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "android_" + counter++);
        }
    }

}
