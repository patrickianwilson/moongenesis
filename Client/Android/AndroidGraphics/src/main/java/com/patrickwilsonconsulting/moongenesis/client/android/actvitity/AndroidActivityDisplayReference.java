package com.patrickwilsonconsulting.moongenesis.client.android.actvitity;

import com.patrickwilsonconsulting.moongenesis.client.common.api.window.DisplayReference;

/**
 * Created by pwilson on 3/13/16.
 */
public class AndroidActivityDisplayReference implements DisplayReference {

    private android.app.Activity wrappedActivity;

    @Override
    public void start() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean occupiesMainThread() {
        return false;
    }
}
