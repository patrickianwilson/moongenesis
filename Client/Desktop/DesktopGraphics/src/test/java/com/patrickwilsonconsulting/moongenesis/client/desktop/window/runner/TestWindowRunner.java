package com.patrickwilsonconsulting.moongenesis.client.desktop.window.runner;


import com.patrickwilsonconsulting.moongenesis.client.desktop.window.SwtShellDisplayReference;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Created by pwilson on 1/24/16.
 */
public class TestWindowRunner {

    public static void main(String... args) {

        Display display = getDisplay();
        Shell shell = new Shell(display);
        shell.setText("Test Game Engine Main Window");
        shell.open();

        // run the event loop as long as the window is open
        while (!shell.isDisposed()) {
            // read the next OS event queue and transfer it to a SWT event
            if (!display.readAndDispatch())
            {
                // if there are currently no other OS event to process
                // sleep until the next OS event is available
                display.sleep();
            }
        }

        // disposes all associated windows and their components
        display.dispose();

    }

    private static Display getDisplay() {

            Display display = new Display();
            Display.setAppName("Test Game Engine");


        return display;
    }
}
