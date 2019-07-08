package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.settings.themes;

import android.content.Context;
import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.ListItemMarker;
import com.smlnskgmail.jaman.hashchecker.support.prefs.PrefsHelper;

public enum Theme implements ListItemMarker {

    LIGHT(R.string.title_theme_light, R.drawable.ic_settings_theme, R.style.AppThemeLight),
    DARK(R.string.title_theme_dark, R.drawable.ic_settings_theme, R.style.AppThemeDark),
    MANGO_BAY(R.string.title_theme_mango_bay, R.drawable.ic_settings_theme, R.style.AppThemeMangoBay),
    MANGO_BAY_DARK(R.string.title_theme_mango_bay_dark, R.drawable.ic_settings_theme, R.style.AppThemeMangoBayDark);

    private int titleResId, iconResId, themeResId;

    Theme(int titleResId, int iconResId, int themeResId) {
        this.titleResId = titleResId;
        this.iconResId = iconResId;
        this.themeResId = themeResId;
    }

    @Override
    public int getTitleTextResId() {
        return titleResId;
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
        String selectedTheme = PrefsHelper.getTheme(context);
        for (Theme theme: values()) {
            if (theme.toString().equals(selectedTheme)) {
                return theme;
            }
        }
        return LIGHT;
    }

}
