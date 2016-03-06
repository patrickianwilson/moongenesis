package com.patrickwilsonconsulting.moongenesis.client.desktop.window;

//import com.patrickwilsonconsulting.moongenesis.client.internal.api.graphics.GraphicsManager;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.util.Map;
import java.util.TreeMap;


/**
 * Created by pwilson on 1/24/16.
 */
public class DesktopSwtGraphicsManager {

    public enum DisplayComponents {
        DISPLAY_DEVICE;
    }

    private Map<DisplayComponents, Object> displayComponentHandles = new TreeMap<>();


    public void createMainUI() {

        Shell mainUI = new Shell(getDisplay());
//        if (props.containsKey(WindowProperties.TITLE))
//            mainUI.setText((String) props.get(WindowProperties.TITLE));


        mainUI.setFullScreen(true);
        mainUI.setLayout(new GridLayout());


    }

    private Display getDisplay() {
        if (!displayComponentHandles.containsKey(DisplayComponents.DISPLAY_DEVICE)) {
            Display display = new Display();
            displayComponentHandles.put(DisplayComponents.DISPLAY_DEVICE, display);
            Display.setAppName("Unified Game Engine");
        }

        return (Display) displayComponentHandles.get(DisplayComponents.DISPLAY_DEVICE);
    }



    public Object getInnerComponent(DisplayComponents comp) {
        return this.displayComponentHandles.get(comp);
    }
}
