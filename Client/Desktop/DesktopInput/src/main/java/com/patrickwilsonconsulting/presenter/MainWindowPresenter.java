package com.patrickwilsonconsulting.presenter;

import com.patrickwilsonconsulting.model.FormModel;
import org.eclipse.swt.widgets.Display;


/**
 * Created by pwilson on 1/24/16.
 */
public class MainWindowPresenter {

    private FormModel model;
    private Display dHandle;

    public MainWindowPresenter(Display dHandle, FormModel model) {
        this.model = model;
        this.dHandle = dHandle;
    }

    public void submit() {

        System.out.println(String.format("submitting name=%s, and email=%s", model.getName(), model.getEmail()));

    }

    public void kill() {
        dHandle.dispose();
    }

    public FormModel getModel() {
        return model;
    }
}
