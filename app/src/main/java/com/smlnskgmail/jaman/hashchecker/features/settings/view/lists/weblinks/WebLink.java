package com.smlnskgmail.jaman.hashchecker.features.settings.view.lists.weblinks;

import android.content.Context;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.ListItem;

import java.util.Arrays;
import java.util.List;

public enum WebLink implements ListItem {

    SOURCE_CODE(R.string.title_web_link_github, R.drawable.ic_github, R.string.web_link_source_code),
    GOOGLE_PLAY(R.string.title_web_link_google_play, R.drawable.ic_google_play, R.string.web_link_my_apps),
    PRIVACY_POLICY(
            R.string.settings_title_privacy_policy,
            R.drawable.ic_settings_privacy_policy,
            R.string.web_link_privacy_policy
    ),

    ADAPTIVERECYCLERVIEW(
            R.string.library_name_adaptiverecyclerview,
            R.drawable.ic_adaptiverecyclerview,
            R.string.web_link_adaptiverecyclerview
    ),
    ORMLITE(R.string.library_name_ormlite, R.drawable.ic_ormlite, R.string.web_link_ormlite);

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

    @NonNull
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

    @NonNull
    public static List<WebLink> getAuthorLinks() {
        return Arrays.asList(
                SOURCE_CODE,
                GOOGLE_PLAY
        );
    }

    @NonNull
    public static List<WebLink> getLibrariesLinks() {
        return Arrays.asList(
                ADAPTIVERECYCLERVIEW,
                ORMLITE
        );
    }

}

