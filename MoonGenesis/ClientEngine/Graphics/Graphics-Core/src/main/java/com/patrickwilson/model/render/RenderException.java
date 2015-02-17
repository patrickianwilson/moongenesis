package com.patrickwilson.model.render;

/**
 * Created by pwilson on 2/16/15.
 * thrown if there was an un-expected problem rendering the scene.
 */
public class RenderException extends RuntimeException {
    public RenderException() {
    }

    public RenderException(String message) {
        super(message);
    }

    public RenderException(String message, Throwable cause) {
        super(message, cause);
    }

    public RenderException(Throwable cause) {
        super(cause);
    }

    public RenderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
