package com.smlnskgmail.jaman.hashchecker.components;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.smlnskgmail.jaman.hashchecker.utils.UIUtils;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(UIUtils.getThemeResId(this));
        super.onCreate(savedInstanceState);
        initialize();
    }

    public abstract void initialize();

}
