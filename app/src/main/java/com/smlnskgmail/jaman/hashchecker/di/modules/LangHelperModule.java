package com.smlnskgmail.jaman.hashchecker.di.modules;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.logic.locale.api.LangHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class LangHelperModule {

    private final LangHelper langHelper;

    public LangHelperModule(
            @NonNull LangHelper langHelper
    ) {
        this.langHelper = langHelper;
    }

    @Provides
    public LangHelper langHelper() {
        return langHelper;
    }

}
