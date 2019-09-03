package com.thinkzi.oodrive.ui.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.thinkzi.oodrive.data.executor.JobExecutor;
import com.thinkzi.oodrive.data.repository.ItemRepository;
import com.thinkzi.oodrive.domain.executor.PostExecutionThread;
import com.thinkzi.oodrive.domain.executor.ThreadExecutor;
import com.thinkzi.oodrive.domain.repository.IItemRepository;
import com.thinkzi.oodrive.ui.OodriveApplication;
import com.thinkzi.oodrive.ui.UIThread;

/**
 * provide Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {

    private final OodriveApplication _oodriveApplication;

    public ApplicationModule(OodriveApplication _oodriveApplication) {
        this._oodriveApplication = _oodriveApplication;
    }

    /**
     * provide a application context
     */
    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this._oodriveApplication;
    }

    /**
     * provide a executor thread pool
     */
    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor _jobExecutor) {
        return _jobExecutor;
    }

    /**
     * provide a thread created to change context execution
     */
    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread _uiThread) {
        return _uiThread;
    }

    /**
     * provide a photo repository
     */
    @Provides
    @Singleton
    IItemRepository provideIItemRepository(ItemRepository _itemRepository) {
        return _itemRepository;
    }

}
