//
// Created by Patrick Wilson on 7/16/16.
//
#include <iostream>
#include "../Window/DefaultNativeWindow.h"

using namespace std;

int main() {
    DefaultNativeWindow* win = new DefaultNativeWindow();

    win -> load();
}