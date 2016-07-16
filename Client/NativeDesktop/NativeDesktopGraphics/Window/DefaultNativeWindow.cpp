//
// Created by Patrick Wilson on 7/16/16.
//
#include <iostream>
#include "mainwindow.h"
#include "DefaultNativeWindow.h"

#include <QtWidgets>


using namespace std;


void DefaultNativeWindow::load() {

    int argcFake = 1;

    char* argv[] = { "sampleApplication" };
    cout << "loaded default Window" << endl;

    QApplication a(argcFake, argv);

    MainWindow mainwindow;

    mainwindow.show();


    int returnValue = a.exec();

    if (returnValue != 0) {
        cout << "QT Application closed with status: " << returnValue << endl;
    }

    return;
}

