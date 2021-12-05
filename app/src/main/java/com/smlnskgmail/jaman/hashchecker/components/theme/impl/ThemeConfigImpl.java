package com.smlnskgmail.jaman.hashchecker.components.theme.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.settings.api.Settings;
import com.smlnskgmail.jaman.hashchecker.components.theme.api.Theme;
import com.smlnskgmail.jaman.hashchecker.components.theme.api.ThemeConfig;

public class ThemeConfigImpl implements ThemeConfig {

    private final Context context;
    private final Settings settings;

    public ThemeConfigImpl(@NonNull Context context, @NonNull Settings settings) {
        this.context = context;
        this.settings = settings;
    }

    @Override
    public void setCurrentTheme(@NonNull Theme theme) {
        settings.saveTheme(theme);
        Resources.Theme appTheme = context.getTheme();
        appTheme.applyStyle(theme.getThemeResId(), true);
    }

    @NonNull
    @Override
    public Theme currentTheme() {
        return settings.getSelectedTheme();
    }

    @Override
    public void applyAccentColorToImage(@NonNull Drawable drawable) {
        drawable.setColorFilter(getAccentColor(), PorterDuff.Mode.SRC_ATOP);
    }

    @SuppressLint("ResourceType")
    @Override
    public int getAccentColor() {
        return getColorFromAttrs(R.attr.colorAccent);
    }

    @SuppressLint("ResourceType")
    @Override
    public int getCommonTextColor() {
        return getColorFromAttrs(R.attr.colorCommonText);
    }

    @SuppressLint("ResourceType")
    @Override
    public int getCommonBackgroundColor() {
        return getColorFromAttrs(R.attr.colorBackground);
    }

    private int getColorFromAttrs(@IdRes int themeColor) {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = context.getTheme();
        theme.resolveAttribute(themeColor, typedValue, true);
        return typedValue.data;
    }

}
