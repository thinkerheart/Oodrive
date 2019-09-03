package com.thinkzi.oodrive.ui.di;

import javax.inject.Singleton;

import dagger.Component;

import com.thinkzi.oodrive.ui.OodriveApplication;
import com.thinkzi.oodrive.ui.view.activity.BaseActivity;
import com.thinkzi.oodrive.ui.view.activity.ItemUIModelListActivity;

/**
 * provide a component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = {ApplicationModule.class, ViewModelModule.class})
public interface ApplicationComponent {

    /**
     * inject dependency for OodriveApplication
     */
    void inject(OodriveApplication _oodriveApplication);

    /**
     * inject dependency for BaseActivity
     */
    void inject(BaseActivity _baseActivity);

    /**
     * inject dependency for ItemUIModelListActivity
     */
    void inject(ItemUIModelListActivity _itemUIModelListActivity);

}
