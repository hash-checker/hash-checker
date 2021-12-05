package com.smlnskgmail.jaman.hashchecker.components.theme.api;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

public interface ThemeConfig {

    void setCurrentTheme(@NonNull Theme theme);

    @NonNull
    Theme currentTheme();

    void applyAccentColorToImage(@NonNull Drawable drawable);

    @SuppressLint("ResourceType")
    int getAccentColor();

    @SuppressLint("ResourceType")
    int getCommonTextColor();

    @SuppressLint("ResourceType")
    int getCommonBackgroundColor();

}
