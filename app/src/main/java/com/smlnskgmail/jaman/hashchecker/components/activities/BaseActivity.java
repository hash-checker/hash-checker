package com.smlnskgmail.jaman.hashchecker.components.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.smlnskgmail.jaman.hashchecker.logic.settings.SettingsHelper;
import com.smlnskgmail.jaman.hashchecker.tools.LanguageTool;
import com.smlnskgmail.jaman.hashchecker.tools.UITools;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LanguageTool.setLocale(
                this,
                SettingsHelper.getLanguage(this)
        );
        setTheme(UITools.getThemeResId(this));
        super.onCreate(savedInstanceState);
        create();
    }

    protected abstract void create();

}
