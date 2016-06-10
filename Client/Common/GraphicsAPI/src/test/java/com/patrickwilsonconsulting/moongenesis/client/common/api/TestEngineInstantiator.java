package com.patrickwilsonconsulting.moongenesis.client.common.api;

/**
 * A threadsafe class for creating Engine objects for testing.
 */
public class TestEngineInstantiator {

    protected Engine newEngine() {
        return new Engine();
    }
}
