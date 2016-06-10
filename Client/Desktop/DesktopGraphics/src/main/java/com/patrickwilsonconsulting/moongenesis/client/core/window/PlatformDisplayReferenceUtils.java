package com.patrickwilsonconsulting.moongenesis.client.core.window;

import java.awt.*;
import javax.swing.*;
import com.patrickwilsonconsulting.moongenesis.client.common.api.window.DisplayReference;
import com.patrickwilsonconsulting.moongenesis.client.desktop.window.SwingShellDisplayReference;


/**
 * Created by pwilson on 3/5/16.
 */
public class PlatformDisplayReferenceUtils {

    public static DisplayReference<JFrame> createPlatformDefaultWindowedDisplay(String windowTitle) {
        JFrame mainWindow = new JFrame(windowTitle);
        mainWindow.setLayout(new BorderLayout());
        DisplayReference result = new SwingShellDisplayReference(mainWindow, false);

        return result;
    }

    public static DisplayReference<JFrame> createPlatformDefaultFullscreenDisplay(String windowTitle) {
        JFrame mainWindow = new JFrame(windowTitle);
        mainWindow.setLayout(new BorderLayout());

        DisplayReference result = new SwingShellDisplayReference(mainWindow, true);

        return result;
    }



}
