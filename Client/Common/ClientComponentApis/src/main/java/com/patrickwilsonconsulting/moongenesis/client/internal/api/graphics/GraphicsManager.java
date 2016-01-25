package com.patrickwilsonconsulting.moongenesis.client.internal.api.graphics;

import java.util.Map;

/**
 * Created by pwilson on 1/24/16.
 */
public interface GraphicsManager {
    void createMainUI(Map<WindowProperties, Object> props);

    void createLoggerUI();

    enum WindowProperties {
        TITLE;
    }

}
