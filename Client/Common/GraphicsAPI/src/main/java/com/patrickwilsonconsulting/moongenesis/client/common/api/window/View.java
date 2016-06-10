package com.patrickwilsonconsulting.moongenesis.client.common.api.window;

/**
 * a platform independent reference to a View.  Views are provided by game developers
 * outside of the context of the engine.  However, the engine knows how to
 * render common view types.
 */
public interface View {

    void activate();
}
