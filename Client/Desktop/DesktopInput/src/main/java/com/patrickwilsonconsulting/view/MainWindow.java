package com.patrickwilsonconsulting.view;

import com.patrickwilsonconsulting.model.FormModel;
import com.patrickwilsonconsulting.presenter.MainWindowPresenter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

/**
 * Created by pwilson on 1/24/16.
 */
public class MainWindow {

    private static Display display = new Display();
    private MainWindowPresenter presenter;

    public MainWindow(MainWindowPresenter presenter) {
        this.presenter = presenter;
    }

    public void init() {

        final Shell shell = new Shell();

        shell.setLayout(new RowLayout());

        Button hello = new Button(shell, SWT.PUSH);
        hello.setText("Hello World - click to close...");
        hello.addMouseListener(new MouseListener() {
            @Override
            public void mouseDoubleClick(MouseEvent e) {

            }

            @Override
            public void mouseDown(MouseEvent e) {
                presenter.kill();
            }

            @Override
            public void mouseUp(MouseEvent e) {

            }
        });

        hello.pack();


        Label label = new Label(shell, SWT.PUSH);
        label.setText("Please Enter Your Name");

        label.pack();

        final Text nameText = new Text(shell, SWT.SINGLE);
        nameText.addModifyListener(new ModifyListener() {
            @Override
            public void modifyText(ModifyEvent e) {
                MainWindow.this.presenter.getModel().setName(nameText.getText());
            }
        });
        nameText.setEnabled(true);
        nameText.pack();

        Button submit = new Button(shell, SWT.PUSH);
        submit.setText("Submit Button");
        submit.addMouseListener(new MouseListener() {
            @Override
            public void mouseDoubleClick(MouseEvent e) {

            }

            @Override
            public void mouseDown(MouseEvent e) {
                presenter.submit();
            }

            @Override
            public void mouseUp(MouseEvent e) {

            }
        });

        submit.pack();

        shell.setFullScreen(true);



        shell.pack();
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) display.sleep();
        }

        display.dispose();

    }

    public static void main(String... args) {


        MainWindow win = new MainWindow(new MainWindowPresenter(display, new FormModel()));


        win.init();




    }
}
