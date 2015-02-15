package com.patrickwilson.model.util;

/**
 * Created by pwilson on 2/14/15.
 */
public class InvalidModelSyntaxException extends RuntimeException {
    public InvalidModelSyntaxException(String message) {
        super(message);
    }

    public InvalidModelSyntaxException() {
    }
}
