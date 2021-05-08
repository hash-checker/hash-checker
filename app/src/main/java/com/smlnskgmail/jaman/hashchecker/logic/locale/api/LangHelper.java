package com.smlnskgmail.jaman.hashchecker.logic.locale.api;

import android.content.Context;

import androidx.annotation.NonNull;

public interface LangHelper {

    void setLanguage(@NonNull Language language);

    void applyLanguage(@NonNull Context context);

    @NonNull
    Language currentLanguage();

}
