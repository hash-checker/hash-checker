package com.smlnskgmail.jaman.hashchecker.utils;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.BuildConfig;

public class LogUtils {

    public static void e(@NonNull Throwable t) {
        if (BuildConfig.DEBUG) {
            t.printStackTrace();
        }
    }

}
