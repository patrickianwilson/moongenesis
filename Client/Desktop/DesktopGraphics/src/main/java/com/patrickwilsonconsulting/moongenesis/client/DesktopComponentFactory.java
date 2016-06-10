package com.patrickwilsonconsulting.moongenesis.client;

import java.util.Map;
import com.google.common.collect.ImmutableMap;
import com.patrickwilsonconsulting.moongenesis.client.common.api.Component;
import com.patrickwilsonconsulting.moongenesis.client.desktop.graphics.DesktopGraphicsRenderer;

/**
 * Created by pwilson on 6/10/16.
 */
public class DesktopComponentFactory {

    private static final DesktopComponentFactory instance = new DesktopComponentFactory();

    private static final Map<Class<? extends Component>, Component> componentMap = ImmutableMap.<Class<? extends Component>, Component>builder()
            .put(DesktopGraphicsRenderer.class, new DesktopGraphicsRenderer())
            .build();

    public static DesktopComponentFactory getDefaultInstance() {
        return instance;
    }

    public DesktopGraphicsRenderer getDefaultGraphicsRenderer() {
        return (DesktopGraphicsRenderer) componentMap.get(DesktopGraphicsRenderer.class);
    }


}
