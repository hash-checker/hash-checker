package com.smlnskgmail.jaman.hashchecker.tools;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.BuildConfig;

public class LogTool {

    public static void e(@NonNull Throwable t) {
        if (BuildConfig.DEBUG) {
            t.printStackTrace();
        }
    }

}
