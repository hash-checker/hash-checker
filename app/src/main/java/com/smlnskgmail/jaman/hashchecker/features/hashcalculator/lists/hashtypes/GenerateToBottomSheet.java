package com.smlnskgmail.jaman.hashchecker.features.hashcalculator.lists.hashtypes;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.smlnskgmail.jaman.hashchecker.App;
import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashType;
import com.smlnskgmail.jaman.hashchecker.components.settings.api.Settings;
import com.smlnskgmail.jaman.hashchecker.components.theme.api.ThemeConfig;
import com.smlnskgmail.jaman.hashchecker.ui.BaseFragment;
import com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.adapter.BaseListAdapter;

import java.util.Arrays;

import javax.inject.Inject;

public class GenerateToBottomSheet extends BaseListBottomSheet<HashType> {

    @Inject
    public Settings settings;

    @Inject
    public ThemeConfig themeConfig;

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
        Fragment fragment = getFragmentManager().findFragmentByTag(BaseFragment.CURRENT_FRAGMENT_TAG);
        return new HashesListAdapter(
                Arrays.asList(HashType.values()),
                this,
                (HashTypeSelectTarget) fragment,
                settings.getLastHashType(),
                themeConfig
        );
    }

}
