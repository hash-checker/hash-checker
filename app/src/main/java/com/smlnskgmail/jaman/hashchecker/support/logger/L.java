package com.smlnskgmail.jaman.hashchecker.support.logger;

import android.support.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.BuildConfig;

public class L {

    private static final boolean LOG_ENABLED = BuildConfig.DEBUG;

    public static void e(@NonNull Throwable throwable) {
        if (LOG_ENABLED) {
            throwable.printStackTrace();
        }
    }

}
