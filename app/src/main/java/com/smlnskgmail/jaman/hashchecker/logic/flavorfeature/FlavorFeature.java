package com.smlnskgmail.jaman.hashchecker.logic.flavorfeature;

import android.app.Activity;

import androidx.annotation.NonNull;

public interface FlavorFeature {

    void bindForActivity(@NonNull Activity activity);

    void bindForOnResumeActivity();

}
