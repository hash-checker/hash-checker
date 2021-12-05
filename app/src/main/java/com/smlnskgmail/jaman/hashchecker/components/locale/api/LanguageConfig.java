package com.smlnskgmail.jaman.hashchecker.components.locale.api;

import android.content.Context;

import androidx.annotation.NonNull;

public interface LanguageConfig {

    void setLanguage(@NonNull Language language);

    void applyLanguage(@NonNull Context context);

    @NonNull
    Language currentLanguage();

}
