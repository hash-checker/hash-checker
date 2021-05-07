package com.smlnskgmail.jaman.hashchecker.logic.locale.impl;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.logic.locale.api.LangHelper;
import com.smlnskgmail.jaman.hashchecker.logic.locale.api.Language;

import java.util.Locale;

public class LangHelperImpl implements LangHelper {

    private final Context context;

    public LangHelperImpl(
            @NonNull Context context
    ) {
        this.context = context;
    }

    @Override
    public void setLocale(@NonNull Language language) {
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

}
