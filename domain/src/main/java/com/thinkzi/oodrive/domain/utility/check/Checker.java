package com.thinkzi.oodrive.domain.utility.check;

/**
 * provide some data checks
 * */
public final class Checker {

    /**
    * check not null T object
    * */
    public static <T> T checkNotNull(T _objectReference) {

        if (_objectReference == null) {

            throw new NullPointerException();

        }

        return _objectReference;

    }

}
