package com.thinkzi.oodrive.data.exception;

/**
 * provide Exception throw by the application when a there is a network connection exception.
 */
public class NetworkConnectionException extends Exception {

    public NetworkConnectionException() {
        super();
    }

    public NetworkConnectionException(final Throwable _cause) {
        super(_cause);
    }
}
