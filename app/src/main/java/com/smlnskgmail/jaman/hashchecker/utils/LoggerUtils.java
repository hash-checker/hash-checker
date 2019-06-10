package com.smlnskgmail.jaman.hashchecker.utils;

import android.support.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.BuildConfig;

public class LoggerUtils {

    private static final boolean LOG_ENABLED = BuildConfig.DEBUG;

    public static void error(@NonNull Throwable e) {
        if (LOG_ENABLED) {
            e.printStackTrace();
        }
    }

}
