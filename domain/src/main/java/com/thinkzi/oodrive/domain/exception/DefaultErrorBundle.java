package com.thinkzi.oodrive.domain.exception;

/**
* provide Wrapper around Exceptions used to manage default errors
* */
public class DefaultErrorBundle implements ErrorBundle {

    private static final String DEFAULT_ERROR_MSG = "Unknown error";

    private final Exception _exception;

    public DefaultErrorBundle(Exception _exception) {
        this._exception = _exception;
    }

    @Override
    public Exception getException() {
        return _exception;
    }

    @Override
    public String getErrorMessage() {
        return (_exception != null) ? this._exception.getMessage() : DEFAULT_ERROR_MSG;
    }

}
