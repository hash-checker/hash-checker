package com.smlnskgmail.jaman.hashchecker.utils;

import com.smlnskgmail.jaman.hashchecker.MainActivity;

public class MainActivitySingletone {
    private static MainActivity instance;

    public static void setInstance(MainActivity activity) {
        instance = activity;
    }

    public static MainActivity getInstance() {
        return instance;
    }
}
