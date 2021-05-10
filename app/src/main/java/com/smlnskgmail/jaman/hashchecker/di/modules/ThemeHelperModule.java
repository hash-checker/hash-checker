package com.smlnskgmail.jaman.hashchecker.di.modules;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.logic.themes.api.ThemeHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class ThemeHelperModule {

    private final ThemeHelper themeHelper;

    public ThemeHelperModule(
            @NonNull ThemeHelper themeHelper
    ) {
        this.themeHelper = themeHelper;
    }

    @NonNull
    @Provides
    public ThemeHelper themeHelper() {
        return themeHelper;
    }

}
