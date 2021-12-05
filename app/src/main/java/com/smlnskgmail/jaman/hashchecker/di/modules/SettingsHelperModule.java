package com.smlnskgmail.jaman.hashchecker.di.modules;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.components.settings.api.Settings;

import dagger.Module;
import dagger.Provides;

@Module
public class SettingsHelperModule {

    private final Settings settings;

    public SettingsHelperModule(@NonNull Settings settings) {
        this.settings = settings;
    }

    @NonNull
    @Provides
    public Settings settingsHelper() {
        return settings;
    }

}
