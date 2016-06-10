package com.patrickwilsonconsulting.moongenesis.client.common.api;

/**
 * A factory to create platform appropriate game engine objects.
 * This class is not useful for unit testing.  Use the provided UnitTestEngineFactory
 * instead.
 */
public final class EngineFactory {
    private EngineFactory() {

    }

    public static Engine createEngine() {
        return new Engine();
    }


}
