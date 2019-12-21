package com.smlnskgmail.jaman.hashchecker.logic.settings.ui.lists.weblinks;

import android.content.Context;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.ListItemTarget;

import java.util.Arrays;
import java.util.List;

public enum WebLink implements ListItemTarget {

    SOURCE_CODE(
            R.string.title_web_link_github,
            R.drawable.ic_github,
            R.string.web_link_source_code
    ),
    GOOGLE_PLAY(
            R.string.title_web_link_google_play,
            R.drawable.ic_google_play,
            R.string.web_link_my_apps
    );

    private final int titleResId;
    private final int iconResId;
    private final int linkResId;

    WebLink(int titleResId, int iconResId, int linkResId) {
        this.titleResId = titleResId;
        this.iconResId = iconResId;
        this.linkResId = linkResId;
    }

    public int getLinkResId() {
        return linkResId;
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
        return DEFAULT_ICON_VALUE;
    }

    public static List<ListItemTarget> getAuthorLinks() {
        return Arrays.asList(SOURCE_CODE, GOOGLE_PLAY);
    }

}

