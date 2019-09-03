package com.thinkzi.oodrive.ui;

import android.app.Application;

import com.thinkzi.oodrive.ui.di.ApplicationComponent;
import com.thinkzi.oodrive.ui.di.ApplicationModule;
import com.thinkzi.oodrive.ui.di.DaggerApplicationComponent;

/**
 * provide OodriveApplication object
 * */
public class OodriveApplication extends Application {

    private ApplicationComponent _applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    /**
     * initialize injector
     * */
    private void initializeInjector() {
        this._applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    /**
     * provide ApplicationComponent
     * */
    public ApplicationComponent getApplicationComponent() {
        return this._applicationComponent;
    }

}
