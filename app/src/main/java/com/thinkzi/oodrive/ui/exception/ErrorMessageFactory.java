package com.thinkzi.oodrive.ui.exception;

import android.content.Context;

import com.thinkzi.oodrive.data.exception.NetworkConnectionException;
import com.thinkzi.oodrive.ui.R;

/**
 * provide Factory used to create error messages from an Exception as a condition.
 */
public class ErrorMessageFactory {

    private ErrorMessageFactory() { }

    /**
     * creates a String representing an error message.
     */
    public static String create(Context _context, Exception _exception) {

        String _message = _context.getString(R.string.exception_message_generic);

        if (_exception instanceof NetworkConnectionException) {
            _message = _context.getString(R.string.exception_message_no_connection);
        }

        return _message;

    }

}
