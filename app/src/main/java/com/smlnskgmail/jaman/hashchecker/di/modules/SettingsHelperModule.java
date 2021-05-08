package com.smlnskgmail.jaman.hashchecker.di.modules;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.logic.settings.api.SettingsHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class SettingsHelperModule {

    private final SettingsHelper settingsHelper;

    public SettingsHelperModule(
            @NonNull SettingsHelper settingsHelper
    ) {
        this.settingsHelper = settingsHelper;
    }

    @NonNull
    @Provides
    public SettingsHelper settingsHelper() {
        return settingsHelper;
    }

}
