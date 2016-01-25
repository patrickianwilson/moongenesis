package com.patrickwilsonconsulting.moongenesis.client.desktop.graphics.runner;

import com.google.common.collect.ImmutableMap;
import com.patrickwilsonconsulting.moongenesis.client.desktop.graphics.DesktopSwtGraphicsManager;
import com.patrickwilsonconsulting.moongenesis.client.internal.api.graphics.GraphicsManager;

/**
 * Created by pwilson on 1/24/16.
 */
public class TestWindowRunner {

    public static void main(String... args) {
        GraphicsManager manager = new DesktopSwtGraphicsManager();

        manager.createMainUI(ImmutableMap.<GraphicsManager.WindowProperties, Object>builder()
                .put(GraphicsManager.WindowProperties.TITLE, "Custom Window Title")
                .build());
    }
}
