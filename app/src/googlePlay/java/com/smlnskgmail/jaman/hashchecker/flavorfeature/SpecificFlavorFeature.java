package com.smlnskgmail.jaman.hashchecker.flavorfeature;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.logic.flavorfeature.FlavorFeature;

public class SpecificFlavorFeature implements FlavorFeature {

    private InAppUpdateFeature inAppUpdateFeature;

    @Override
    public void bindForActivity(@NonNull Activity activity) {
        inAppUpdateFeature = new InAppUpdateFeature(activity);
        inAppUpdateFeature.bind();
    }

    @Override
    public void bindForOnResumeActivity() {
        inAppUpdateFeature.resume();
    }

}
