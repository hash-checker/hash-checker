package com.smlnskgmail.jaman.hashchecker.logic.settings.lists.themes;

import android.content.Context;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.ListItemTarget;
import com.smlnskgmail.jaman.hashchecker.logic.settings.SettingsHelper;

public enum Theme implements ListItemTarget {

    LIGHT(R.string.title_theme_light, R.drawable.ic_settings_theme, R.style.AppThemeLight),
    DARK(R.string.title_theme_dark, R.drawable.ic_settings_theme, R.style.AppThemeDark);

    private final int titleResId;
    private final int iconResId;
    private final int themeResId;

    Theme(int titleResId, int iconResId, int themeResId) {
        this.titleResId = titleResId;
        this.iconResId = iconResId;
        this.themeResId = themeResId;
    }

    @Override
    public String getTitle(@NonNull Context context) {
        return context.getString(titleResId);
    }

    @Override
    public int getPrimaryIconResId() {
        return iconResId;
    }

    @Override
    public int getAdditionalIconResId() {
        return R.drawable.ic_done;
    }

    public int getThemeResId() {
        return themeResId;
    }

    public static Theme getThemeFromPreferences(@NonNull Context context) {
        String selectedTheme = SettingsHelper.getTheme(context);
        for (Theme theme: values()) {
            if (theme.toString().equals(selectedTheme)) {
                return theme;
            }
        }
        return LIGHT;
    }

}
