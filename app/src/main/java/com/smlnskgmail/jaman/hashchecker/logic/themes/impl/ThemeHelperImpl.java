package com.smlnskgmail.jaman.hashchecker.logic.themes.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.logic.settings.api.SettingsHelper;
import com.smlnskgmail.jaman.hashchecker.logic.themes.api.Theme;
import com.smlnskgmail.jaman.hashchecker.logic.themes.api.ThemeHelper;

public class ThemeHelperImpl implements ThemeHelper {

    private final Context context;
    private final SettingsHelper settingsHelper;

    public ThemeHelperImpl(
            @NonNull Context context,
            @NonNull SettingsHelper settingsHelper
    ) {
        this.context = context;
        this.settingsHelper = settingsHelper;
    }

    @Override
    public void setCurrentTheme(
            @NonNull Theme theme
    ) {
        settingsHelper.saveTheme(theme);
        Resources.Theme appTheme = context.getTheme();
        appTheme.applyStyle(
                theme.getThemeResId(),
                true
        );
    }

    @NonNull
    @Override
    public Theme currentTheme() {
        return settingsHelper.getSelectedTheme();
    }

    @Override
    public void applyAccentColorToImage(
            @NonNull Drawable drawable
    ) {
        drawable.setColorFilter(
                getAccentColor(),
                PorterDuff.Mode.SRC_ATOP
        );
    }

    @SuppressLint("ResourceType")
    @Override
    public int getAccentColor() {
        return getColorFromAttrs(
                R.attr.colorAccent
        );
    }

    @SuppressLint("ResourceType")
    @Override
    public int getCommonTextColor() {
        return getColorFromAttrs(
                R.attr.colorCommonText
        );
    }

    @SuppressLint("ResourceType")
    @Override
    public int getCommonBackgroundColor() {
        return getColorFromAttrs(
                R.attr.colorBackground
        );
    }

    private int getColorFromAttrs(
            @IdRes int themeColor
    ) {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = context.getTheme();
        theme.resolveAttribute(
                themeColor,
                typedValue,
                true
        );
        return typedValue.data;
    }

}
