package com.smlnskgmail.jaman.hashchecker.components;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.smlnskgmail.jaman.hashchecker.logic.settings.SettingsHelper;
import com.smlnskgmail.jaman.hashchecker.utils.LangUtils;
import com.smlnskgmail.jaman.hashchecker.utils.UIUtils;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LangUtils.setLocale(
                this,
                SettingsHelper.getLanguage(this)
        );
        setTheme(UIUtils.getThemeResId(this));
        super.onCreate(savedInstanceState);
        create();
    }

    protected abstract void create();

}
