package com.smlnskgmail.jaman.hashchecker.logic.themes.api;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

public interface ThemeHelper {

    void setCurrentTheme(
            @NonNull Theme theme
    );

    @NonNull
    Theme currentTheme();

    void applyAccentColorToImage(
            @NonNull Drawable drawable
    );

    @SuppressLint("ResourceType")
    int getAccentColor();

    @SuppressLint("ResourceType")
    int getCommonTextColor();

    @SuppressLint("ResourceType")
    int getCommonBackgroundColor();

}
