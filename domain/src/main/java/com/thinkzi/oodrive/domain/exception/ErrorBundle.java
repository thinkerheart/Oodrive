package com.thinkzi.oodrive.domain.exception;

/**
* provide Interface to represent a wrapper around an Exception to manage errors
* */
public interface ErrorBundle {

    Exception getException();

    String getErrorMessage();

}
