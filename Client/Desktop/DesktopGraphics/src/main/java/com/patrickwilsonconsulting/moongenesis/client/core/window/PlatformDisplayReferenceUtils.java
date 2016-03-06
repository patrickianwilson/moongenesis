package com.patrickwilsonconsulting.moongenesis.client.core.window;

import com.patrickwilsonconsulting.moongenesis.client.common.api.window.DisplayReference;
import com.patrickwilsonconsulting.moongenesis.client.desktop.window.SwtShellDisplayReference;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Created by pwilson on 3/5/16.
 */
public class PlatformDisplayReferenceUtils {

    public static DisplayReference createPlatformDefaultDisplay(String windowTitle) {
        Display display = getDisplay(windowTitle);
        Shell shell = new Shell(display);
        shell.setText(windowTitle);
        shell.open();

        DisplayReference result = new SwtShellDisplayReference(shell);

        return result;
    }

    private static Display getDisplay(String appName) {

        Display display = new Display();
        Display.setAppName(appName);


        return display;
    }
}
