package com.patrickwilsonconsulting.moongenesis.client.common.api.window;

/**
 * This is a platform independent way to identify a window/display/widget that will hold
 * the engine and the game view port.  On the desktop this is probably
 * a Window (Frame), on mobile this is an activity, on the web this is a html reference.
 *
 */
public interface DisplayReference {

    /**
     * called by the engine during initialization.  Usually this can be left empty.
     * On some platforms this may occupy the main thread indefinitely.
     *
     */
    void start();


    /**
     * will be called by the engine when the main window/device is no longer
     * needed.  Put any resource cleanup logic here.
     */
    void dispose();

    boolean occupiesMainThread();

}
