package com.smlnskgmail.jaman.hashchecker.components;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.smlnskgmail.jaman.hashchecker.logic.locale.api.LangHelper;
import com.smlnskgmail.jaman.hashchecker.logic.settings.api.SettingsHelper;
import com.smlnskgmail.jaman.hashchecker.logic.themes.api.ThemeHelper;

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
        theme.applyStyle(
                themeHelper().currentTheme().getThemeResId(),
                true
        );
        return theme;
    }

    @NonNull
    protected abstract SettingsHelper settingsHelper();

    @NonNull
    protected abstract LangHelper langHelper();

    @NonNull
    protected abstract ThemeHelper themeHelper();

    protected abstract void create();

}
