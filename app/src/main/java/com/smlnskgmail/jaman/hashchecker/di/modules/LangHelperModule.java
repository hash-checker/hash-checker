package com.smlnskgmail.jaman.hashchecker.di.modules;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.components.locale.api.LanguageConfig;

import dagger.Module;
import dagger.Provides;

@Module
public class LangHelperModule {

    private final LanguageConfig languageConfig;

    public LangHelperModule(@NonNull LanguageConfig languageConfig) {
        this.languageConfig = languageConfig;
    }

    @NonNull
    @Provides
    public LanguageConfig langHelper() {
        return languageConfig;
    }

}
