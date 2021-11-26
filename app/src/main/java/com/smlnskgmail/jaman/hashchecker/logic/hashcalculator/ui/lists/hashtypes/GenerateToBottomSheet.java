package com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.ui.lists.hashtypes;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.smlnskgmail.jaman.hashchecker.App;
import com.smlnskgmail.jaman.hashchecker.components.BaseFragment;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseListAdapter;
import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.api.HashType;
import com.smlnskgmail.jaman.hashchecker.logic.settings.api.SettingsHelper;
import com.smlnskgmail.jaman.hashchecker.logic.themes.api.ThemeHelper;

import java.util.Arrays;

import javax.inject.Inject;

public class GenerateToBottomSheet extends BaseListBottomSheet<HashType> {

    @Inject
    public SettingsHelper settingsHelper;

    @Inject
    public ThemeHelper themeHelper;

    // CPD-OFF
    @Override
    public void onAttach(@NonNull Context context) {
        App.appComponent.inject(this);
        super.onAttach(context);
    }
    // CPD-ON

    @NonNull
    @Override
    public BaseListAdapter<HashType> getItemsAdapter() {
        Fragment fragment = getFragmentManager().findFragmentByTag(
                BaseFragment.CURRENT_FRAGMENT_TAG
        );
        return new HashesListAdapter(
                Arrays.asList(HashType.values()),
                this,
                (HashTypeSelectTarget) fragment,
                settingsHelper.getLastHashType(),
                themeHelper
        );
    }

}
