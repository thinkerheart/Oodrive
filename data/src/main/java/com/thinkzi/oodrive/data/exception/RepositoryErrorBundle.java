package com.thinkzi.oodrive.data.exception;

import com.thinkzi.oodrive.domain.exception.ErrorBundle;

/**
 * provide Wrapper around Exceptions used to manage errors in the repository.
 */
public class RepositoryErrorBundle implements ErrorBundle {

    private final Exception _exception;

    public RepositoryErrorBundle(Exception _exception) {
        this._exception = _exception;
    }

    @Override
    public Exception getException() {
        return _exception;
    }

    @Override
    public String getErrorMessage() {
        String _message = "";

        if (this._exception != null) {
            _message = this._exception.getMessage();
        }

        return _message;
    }

}
