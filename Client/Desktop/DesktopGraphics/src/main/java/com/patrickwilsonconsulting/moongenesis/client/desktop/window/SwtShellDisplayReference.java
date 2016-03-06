package com.patrickwilsonconsulting.moongenesis.client.desktop.window;

import com.patrickwilsonconsulting.moongenesis.client.common.api.window.DisplayReference;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Created by pwilson on 3/5/16.
 */
public class SwtShellDisplayReference implements DisplayReference {

    private Shell mainWindow;
    private Display root;

    public SwtShellDisplayReference(Shell mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void start() {
        root = mainWindow.getDisplay();
        // run the event loop as long as the window is open
        while (!mainWindow.isDisposed()) {
            // read the next OS event queue and transfer it to a SWT event
            if (!root.readAndDispatch())
            {
                // if there are currently no other OS event to process
                // sleep until the next OS event is available
                root.sleep();
            }
        }

    }

    @Override
    public void dispose() {
        root.dispose();
    }

    @Override
    public boolean occupiesMainThread() {
        return true;
    }
}
