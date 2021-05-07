package com.smlnskgmail.jaman.hashchecker.components;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.smlnskgmail.jaman.hashchecker.logic.locale.LangUtils;
import com.smlnskgmail.jaman.hashchecker.logic.settings.api.SettingsHelper;
import com.smlnskgmail.jaman.hashchecker.utils.UIUtils;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LangUtils.setLocale(
                this,
                settingsHelper().getLanguage()
        );
        setTheme(UIUtils.getThemeResId(this));
        super.onCreate(savedInstanceState);
        create();
    }

    @NonNull
    protected abstract SettingsHelper settingsHelper();

    protected abstract void create();

}
