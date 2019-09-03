package com.thinkzi.oodrive.ui.navigation;

import android.content.Context;
import android.content.Intent;

import com.thinkzi.oodrive.ui.view.activity.ItemUIModelListActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * provide a navigator used to navigate through the application.
 */
@Singleton
public class Navigator {

    @Inject
    public Navigator() { }

    /**
     * navigate to ItemUIModelListActivity
     * */
    public void navigateToItemUIModelListActivity(Context _context) {

        if (_context != null) {
            Intent _intent = ItemUIModelListActivity.getCallingIntent(_context);
            _context.startActivity(_intent);
        }

    }

}
