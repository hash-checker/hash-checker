package com.smlnskgmail.jaman.hashchecker.logic.logs;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.BuildConfig;

public class L {

    public static void e(@NonNull Throwable t) {
        if (BuildConfig.DEBUG) {
            t.printStackTrace();
        }
    }

}
