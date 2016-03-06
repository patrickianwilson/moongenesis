package com.patrickwilsonconsulting.moongenesis.client.common.api;

import com.patrickwilsonconsulting.moongenesis.client.common.api.exceptions.EngineStartupException;
import com.patrickwilsonconsulting.moongenesis.client.common.api.window.DisplayReference;
import com.patrickwilsonconsulting.moongenesis.client.common.api.window.View;

import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

/**
 * Created by pwilson on 3/5/16.
 */
public class Engine {

    private Properties config = new Properties();
    private DisplayReference rootDisplayHolder;
    private Runnable gameInitFunction;
    private Thread gameThread;

    private Map<String, View> viewMap = new TreeMap<>();


    protected Engine () {
        //private as this object is only created by a factory.
    }
    public Engine withConfigProperties(Properties properties ) {
        config.putAll(properties);

        return this;
    }

    public Engine registerDisplayHolder(DisplayReference display) {
        this.rootDisplayHolder = display;
        return this;
    }

    public Engine registerView(String viewId, View view) {
        this.viewMap.put(viewId, view);
        return this;
    }

    public Engine initializeWithGameFunction(Runnable init) {
        this.gameInitFunction = init;
        return this;
    }

    public Engine start() throws EngineStartupException {
        if (this.gameInitFunction == null) {
            throw new EngineStartupException("No game initialization function was provided.");
        }

        this.gameThread = new Thread(this.gameInitFunction, "MainGameThread");
        this.gameThread.start();
        this.getRootDisplayHolder().start();
        //the above call will have blocked the main thread in an event while loop.  shutdown now.
        if (this.getRootDisplayHolder().occupiesMainThread())
                this.getRootDisplayHolder().dispose();

        return this;
    }

    protected DisplayReference getRootDisplayHolder() {
        return this.rootDisplayHolder;
    }



    protected Map<String, View> getViewMap() {
        return this.viewMap;
    }


}
