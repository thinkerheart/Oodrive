package com.thinkzi.oodrive.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.thinkzi.oodrive.domain.exception.DefaultErrorBundle;

/**
 * provide BaseViewModel for operations: save data of View(Activity) for configuration change management,
 * treat actions from View, data binding, live data, observer result sent from UseCase ...
 * */
public abstract class BaseViewModel extends ViewModel {

    // default error bundle to receive error in live data from a result of UseCase
    protected MutableLiveData<DefaultErrorBundle> _defaultErrorBundleMutableLiveData;

    protected BaseViewModel() {
        _defaultErrorBundleMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<DefaultErrorBundle> getDefaultErrorBundleMutableLiveData() {

        return _defaultErrorBundleMutableLiveData;

    }

    /**
     * handle received error in live data from a result of UseCase
     * */
    protected void handleError(DefaultErrorBundle _defaultErrorBundle) {

        _defaultErrorBundleMutableLiveData.setValue(_defaultErrorBundle);

    }

}
