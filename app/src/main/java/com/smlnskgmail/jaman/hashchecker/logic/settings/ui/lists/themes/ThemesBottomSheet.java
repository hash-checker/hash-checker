package com.smlnskgmail.jaman.hashchecker.logic.settings.ui.lists.themes;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smlnskgmail.jaman.hashchecker.App;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseListAdapter;
import com.smlnskgmail.jaman.hashchecker.logic.settings.api.SettingsHelper;

import java.util.Arrays;

import javax.inject.Inject;

public class ThemesBottomSheet extends BaseListBottomSheet<Theme> {

    @Inject
    SettingsHelper settingsHelper;

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
                settingsHelper.getSelectedTheme()
        );
    }

}
