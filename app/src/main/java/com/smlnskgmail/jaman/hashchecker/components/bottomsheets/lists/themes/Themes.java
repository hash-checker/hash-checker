package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.themes;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.ListItemMarker;

public enum Themes implements ListItemMarker {

    LIGHT(R.string.title_theme_light, R.drawable.ic_settings_theme),
    DARK(R.string.title_theme_dark, R.drawable.ic_settings_theme);

    private int titleResId, iconResId;

    Themes(int titleResId, int iconResId) {
        this.titleResId = titleResId;
        this.iconResId = iconResId;
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
        return -1;
    }

}
