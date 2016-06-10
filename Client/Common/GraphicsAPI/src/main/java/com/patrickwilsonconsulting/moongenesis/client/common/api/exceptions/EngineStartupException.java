package com.patrickwilsonconsulting.moongenesis.client.common.api.exceptions;

/**
 * Created by pwilson on 3/5/16.
 */
public class EngineStartupException extends Exception {

    public EngineStartupException() {
    }

    public EngineStartupException(String message) {
        super(message);
    }

    public EngineStartupException(String message, Throwable cause) {
        super(message, cause);
    }
}
