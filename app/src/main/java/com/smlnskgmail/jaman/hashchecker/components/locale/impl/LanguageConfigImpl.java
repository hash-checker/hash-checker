package com.smlnskgmail.jaman.hashchecker.components.locale.impl;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.components.locale.api.Language;
import com.smlnskgmail.jaman.hashchecker.components.locale.api.LanguageConfig;
import com.smlnskgmail.jaman.hashchecker.components.settings.api.Settings;

import java.util.Locale;

public class LanguageConfigImpl implements LanguageConfig {

    private final Context context;
    private final Settings settings;

    public LanguageConfigImpl(@NonNull Context context, @NonNull Settings settings) {
        this.context = context;
        this.settings = settings;
    }

    @Override
    public void setLanguage(@NonNull Language language) {
        setLanguage(context, language);
        settings.saveLanguage(language);
    }

    private void setLanguage(@NonNull Context context, @NonNull Language language) {
        Locale locale;
        if (language == Language.ZH) {
            locale = Locale.SIMPLIFIED_CHINESE;
        } else if (language == Language.PT) {
            locale = new Locale(language.code(), "BR");
        } else {
            locale = new Locale(language.code(), Locale.getDefault().getCountry());
        }

        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;

        Resources resources = context.getResources();
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    @Override
    public void applyLanguage(@NonNull Context context) {
        setLanguage(context, currentLanguage());
    }

    @NonNull
    @Override
    public Language currentLanguage() {
        return settings.getLanguage();
    }

}
