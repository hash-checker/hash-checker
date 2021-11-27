package com.smlnskgmail.jaman.hashchecker.features.feedback;

import androidx.annotation.NonNull;

public class Feedback {

    private final String appVersionName;
    private final int appVersionCode;

    private final String osVersion;
    private final String manufacturer;
    private final String model;

    public Feedback(
            @NonNull String appVersionName,
            int appVersionCode,
            @NonNull String osVersion,
            @NonNull String manufacturer,
            @NonNull String model
    ) {
        this.appVersionName = appVersionName;
        this.appVersionCode = appVersionCode;
        this.osVersion = osVersion;
        this.manufacturer = manufacturer;
        this.model = model;
    }

    @NonNull
    public String osVersion() {
        return osVersion;
    }

    @NonNull
    public String manufacturer() {
        return manufacturer;
    }

    @NonNull
    public String model() {
        return model;
    }

    @NonNull
    public String getAppInfo() {
        return String.format(
                "%s (%s)",
                appVersionName,
                appVersionCode
        );
    }

    @NonNull
    public String getConfiguredMessage(@NonNull String feedback) {
        return String.format(
                "%s" +
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
