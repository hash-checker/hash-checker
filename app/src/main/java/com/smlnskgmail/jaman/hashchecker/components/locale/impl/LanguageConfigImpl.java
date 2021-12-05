package com.smlnskgmail.jaman.hashchecker.components.locale.impl;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;

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
        if (language != Language.ZH) {
            locale = new Locale(language.code(), Locale.getDefault().getCountry());
        } else {
            locale = Locale.SIMPLIFIED_CHINESE;
        }

        Locale.setDefault(locale);
        Configuration config = context.getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            config.setLocales(new LocaleList(locale));
        } else {
            config.locale = locale;
        }

        Resources resources = context.getResources();
        resources.updateConfiguration(config, resources.getDisplayMetrics());
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
