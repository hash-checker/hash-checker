package com.smlnskgmail.jaman.hashchecker.logic.feedback;

import android.os.Build;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.BuildConfig;

public class Feedback {

    private final String appVersionName = BuildConfig.VERSION_NAME;
    private final int appVersionCode = BuildConfig.VERSION_CODE;

    private final String osVersion = Build.VERSION.RELEASE;
    private final String manufacturer = Build.MANUFACTURER;
    private final String model = Build.MODEL;

    public String getAppInfo() {
        return String.format("%s (%s)", appVersionName, appVersionCode);
    }

    public String getOsVersion() {
        return osVersion;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public String getConfiguredMessage(@NonNull String feedback) {
        return String.format("%s" +
                "\n\n\n%s (%s)" +
                "\nAndroid %s" +
                "\n%s" +
                "\n%s",
                feedback,
                appVersionName,
                appVersionCode,
                osVersion,
                manufacturer,
                model
        );
    }

}
