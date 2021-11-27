package com.smlnskgmail.jaman.hashchecker.logic.settings.ui.lists.themes;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smlnskgmail.jaman.hashchecker.App;
import com.smlnskgmail.jaman.hashchecker.components.theme.api.Theme;
import com.smlnskgmail.jaman.hashchecker.components.theme.api.ThemeConfig;
import com.smlnskgmail.jaman.hashchecker.logic.settings.api.SettingsHelper;
import com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.adapter.BaseListAdapter;

import java.util.Arrays;

import javax.inject.Inject;

public class ThemesBottomSheet extends BaseListBottomSheet<Theme> {

    @Inject
    public SettingsHelper settingsHelper;

    @Inject
    public ThemeConfig themeConfig;

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        App.appComponent.inject(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @NonNull
    @Override
    public BaseListAdapter<Theme> getItemsAdapter() {
        return new ThemesListAdapter(
                Arrays.asList(Theme.values()),
                this,
                themeConfig
        );
    }

}
