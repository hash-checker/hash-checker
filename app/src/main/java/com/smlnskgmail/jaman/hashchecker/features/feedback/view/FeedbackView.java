package com.smlnskgmail.jaman.hashchecker.features.feedback.view;

import androidx.annotation.NonNull;

public interface FeedbackView {

    void showAppInfo(@NonNull String appInfo);

    void showAndroidVersion(@NonNull String version);

    void showManufacturer(@NonNull String manufacturer);

    void showModel(@NonNull String model);

}
