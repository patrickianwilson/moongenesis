package com.github.patrickianwilson.moongensis.sample;

import com.patrickwilsonconsulting.moongenesis.client.common.api.EngineFactory;
import com.patrickwilsonconsulting.moongenesis.client.common.api.exceptions.EngineStartupException;
import com.patrickwilsonconsulting.moongenesis.client.common.api.window.DisplayReference;
import com.patrickwilsonconsulting.moongenesis.client.core.window.PlatformDisplayReferenceUtils;

/**
 * Created by pwilson on 3/5/16.
 */
public class MainBoot {

    public static void main (String ... args) {
        DisplayReference display = PlatformDisplayReferenceUtils.createPlatformDefaultDisplay("Sample Game");

        try {
            EngineFactory.createEngine()
                    .registerDisplayHolder(display)
                    .initializeWithGameFunction(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("Hello Game World");
                        }
                    })
                    .start();
        } catch (EngineStartupException e) {
            e.printStackTrace();
        }
    }
}
