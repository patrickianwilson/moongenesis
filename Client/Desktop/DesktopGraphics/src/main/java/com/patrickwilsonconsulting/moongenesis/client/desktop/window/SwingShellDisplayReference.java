package com.patrickwilsonconsulting.moongenesis.client.desktop.window;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.*;
import com.patrickwilsonconsulting.moongenesis.client.common.api.window.DisplayReference;


/**
 * Created by pwilson on 3/5/16.
 */
public class SwingShellDisplayReference implements DisplayReference<JFrame> {

    private Map<Enum, JFrame> shellMap = new TreeMap<>();
    private boolean displayFullscreen = false;

    public SwingShellDisplayReference(JFrame mainWindow, boolean displayFullscreen) {
        shellMap.put(BuiltInDisplays.MAIN_WINDOW, mainWindow);
        this.displayFullscreen = displayFullscreen;
    }

    @Override
    public void start() {
        JFrame mainWindow = this.shellMap.get(BuiltInDisplays.MAIN_WINDOW);

        mainWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                SwingShellDisplayReference.this.dispose();
            }
        });


        if (displayFullscreen) {
            //load the engine in fullscreen.
            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(mainWindow);
        } else {
            mainWindow.setBounds(new Rectangle(1024, 768));
        }

        mainWindow.setVisible(true);

    }

    @Override
    public void dispose() {
        for(JFrame frame: shellMap.values()) {
            frame.dispose();
        }

    }

    @Override
    public boolean occupiesMainThread() {
        return false;
    }

    @Override
    public JFrame getDefaultNativeDisplay() {
        return this.shellMap.get(BuiltInDisplays.MAIN_WINDOW);
    }

    @Override
    public JFrame getNamedNativeDisplay(Enum name) {
        return this.shellMap.get(name);
    }

    private enum BuiltInDisplays {
        MAIN_WINDOW;
    }
}
