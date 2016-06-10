package com.github.patrickianwilson.moongensis.sample;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import com.jogamp.opengl.GLProfile;
import com.patrickwilsonconsulting.moongenesis.client.common.api.EngineFactory;
import com.patrickwilsonconsulting.moongenesis.client.common.api.exceptions.EngineStartupException;
import com.patrickwilsonconsulting.moongenesis.client.common.api.window.DisplayReference;
import com.patrickwilsonconsulting.moongenesis.client.core.window.PlatformDisplayReferenceUtils;
import com.patrickwilsonconsulting.moongenesis.client.desktop.graphics.Engine3DGraphicsWidget;

/**
 * Created by pwilson on 3/5/16.
 */
public class MainBoot {

    public static void main (String ... args) {

        DisplayReference<JFrame> display = PlatformDisplayReferenceUtils.createPlatformDefaultWindowedDisplay("Sample Game");

        JFrame mainWindow = display.getDefaultNativeDisplay();
        mainWindow.getContentPane().add(new Engine3DGraphicsWidget(), BorderLayout.CENTER);
        mainWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                System.exit(0);
            }
        });
        try {
            EngineFactory.createEngine()
                    .registerDisplayHolder(display)
                    .initializeWithGameFunction(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("Hello Game World");
                        }
                    })
                    .start();
        } catch (EngineStartupException e) {
            e.printStackTrace();
        }

    }
}
