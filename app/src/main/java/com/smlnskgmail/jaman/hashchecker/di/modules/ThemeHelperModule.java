package com.smlnskgmail.jaman.hashchecker.di.modules;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.components.theme.api.ThemeConfig;

import dagger.Module;
import dagger.Provides;

@Module
public class ThemeHelperModule {

    private final ThemeConfig themeConfig;

    public ThemeHelperModule(@NonNull ThemeConfig themeConfig) {
        this.themeConfig = themeConfig;
    }

    @NonNull
    @Provides
    public ThemeConfig themeHelper() {
        return themeConfig;
    }

}
