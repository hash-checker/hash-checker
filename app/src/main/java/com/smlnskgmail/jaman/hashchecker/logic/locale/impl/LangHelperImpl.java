package com.smlnskgmail.jaman.hashchecker.logic.locale.impl;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.logic.locale.api.LangHelper;
import com.smlnskgmail.jaman.hashchecker.logic.locale.api.Language;
import com.smlnskgmail.jaman.hashchecker.logic.settings.api.SettingsHelper;

import java.util.Locale;

public class LangHelperImpl implements LangHelper {

    private final Context context;
    private final SettingsHelper settingsHelper;

    public LangHelperImpl(
            @NonNull Context context,
            @NonNull SettingsHelper settingsHelper
    ) {
        this.context = context;
        this.settingsHelper = settingsHelper;
    }

    @Override
    public void setLanguage(@NonNull Language language) {
        setLanguage(context, language);
        settingsHelper.saveLanguage(language);
    }

    private void setLanguage(
            @NonNull Context context,
            @NonNull Language language
    ) {
        Locale locale;
        if (language != Language.ZH) {
            locale = new Locale(
                    language.code(),
                    Locale.getDefault().getCountry()
            );
        } else {
            locale = Locale.SIMPLIFIED_CHINESE;
        }

        Locale.setDefault(locale);
        Configuration config = context.getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            config.setLocales(
                    new LocaleList(locale)
            );
        } else {
            config.locale = locale;
        }

        Resources resources = context.getResources();
        resources.updateConfiguration(
                config,
                resources.getDisplayMetrics()
        );
    }

    @Override
    public void applyLanguage(@NonNull Context context) {
        setLanguage(context, currentLanguage());
    }

    @NonNull
    @Override
    public Language currentLanguage() {
        return settingsHelper.getLanguage();
    }

}
