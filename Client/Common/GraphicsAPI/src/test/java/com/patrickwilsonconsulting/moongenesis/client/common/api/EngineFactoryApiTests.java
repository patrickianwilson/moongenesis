package com.patrickwilsonconsulting.moongenesis.client.common.api;

import com.patrickwilsonconsulting.moongenesis.client.common.api.exceptions.EngineStartupException;
import com.patrickwilsonconsulting.moongenesis.client.common.api.window.DisplayReference;
import com.patrickwilsonconsulting.moongenesis.client.common.api.window.View;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test (mostly to visually verify the Engine API for
 * usability problems.
 */
public class EngineFactoryApiTests {

    @Test
    public void doesTheAPIVisuallyLookOK() {

        Engine engine = null;
        try {
            engine = EngineFactory.createEngine()
                    .registerDisplayHolder(new ConsoleDisplayHolder())
                    .registerView("login", new LoginView())
                    .registerView("main", new MainGameView())
                    .start();
        } catch (EngineStartupException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(2, engine.getViewMap().size());
        Assert.assertEquals(ConsoleDisplayHolder.class, engine.getRootDisplayHolder().getClass());

    }

    private class LoginView implements View {
        @Override
        public void activate() {
            System.out.println("Do A Login!");
        }
    }

    private class MainGameView implements View {
        @Override
        public void activate() {
            System.out.println("Loading The Game!");
        }
    }

    private class ConsoleDisplayHolder implements DisplayReference {
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

}
