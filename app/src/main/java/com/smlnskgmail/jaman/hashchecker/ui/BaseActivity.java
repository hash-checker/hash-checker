package com.smlnskgmail.jaman.hashchecker.ui;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.smlnskgmail.jaman.hashchecker.components.locale.api.LanguageConfig;
import com.smlnskgmail.jaman.hashchecker.components.theme.api.ThemeConfig;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        langHelper().applyLanguage(this);
        super.onCreate(savedInstanceState);
        create();
    }

    @NonNull
    @Override
    public Resources.Theme getTheme() {
        Resources.Theme theme = super.getTheme();
        theme.applyStyle(themeHelper().currentTheme().getThemeResId(), true);
        return theme;
    }

    @NonNull
    protected abstract LanguageConfig langHelper();

    @NonNull
    protected abstract ThemeConfig themeHelper();

    protected abstract void create();

}
