package com.thinkzi.oodrive.ui.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.thinkzi.oodrive.ui.viewmodel.ItemUIModelListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Allow us to inject dependencies via constructor injection
 */
@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ItemUIModelListViewModel.class)
    abstract ViewModel bindsPhotoUIModelListViewModel(ItemUIModelListViewModel _itemUIModelListViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory _viewModelFactory);
}
