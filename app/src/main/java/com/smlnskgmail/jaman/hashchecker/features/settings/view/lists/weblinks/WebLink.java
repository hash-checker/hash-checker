package com.smlnskgmail.jaman.hashchecker.features.settings.view.lists.weblinks;

import android.content.Context;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.ListItem;

import java.util.Arrays;
import java.util.List;

public enum WebLink implements ListItem {

    GITHUB(R.string.title_web_link_github, R.drawable.ic_github, R.string.web_link_github),
    GOOGLE_PLAY(R.string.title_web_link_google_play, R.drawable.ic_google_play, R.string.web_link_my_apps),
    LINKEDIN(R.string.title_web_link_linkedin, R.drawable.ic_linkedin, R.string.web_link_linkedin),
    TWITTER(R.string.title_web_link_twitter, R.drawable.ic_twitter, R.string.web_link_twitter),
    RUBYGEMS(R.string.title_web_link_rubygems, R.drawable.ic_rubygems, R.string.web_link_rubygems),
    STACK_OVERFLOW(
            R.string.title_web_link_stack_overflow,
            R.drawable.ic_stack_oveflow,
            R.string.web_link_stack_overflow
    ),
    PRIVACY_POLICY(
            R.string.settings_title_privacy_policy,
            R.drawable.ic_settings_privacy_policy,
            R.string.web_link_privacy_policy
    ),

    ADAPTIVE_RECYCLER_VIEW(
            R.string.library_name_adaptive_recycler_view,
            R.drawable.ic_adaptiverecyclerview,
            R.string.web_link_adaptive_recycler_view
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
                GITHUB,
                GOOGLE_PLAY,
                LINKEDIN,
                TWITTER,
                RUBYGEMS,
                STACK_OVERFLOW
        );
    }

    @NonNull
    public static List<WebLink> getLibrariesLinks() {
        return Arrays.asList(
                ADAPTIVE_RECYCLER_VIEW,
                ORMLITE
        );
    }

}
