package com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.ui.lists.hashtypes;

import androidx.fragment.app.Fragment;

import com.smlnskgmail.jaman.hashchecker.components.BaseFragment;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseListAdapter;
import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.HashType;

import java.util.Arrays;

public class GenerateToBottomSheet extends BaseListBottomSheet<HashType> {

    @Override
    public BaseListAdapter<HashType> getItemsAdapter() {
        Fragment fragment = getFragmentManager().findFragmentByTag(
                BaseFragment.CURRENT_FRAGMENT_TAG
        );
        return new HashesListAdapter(
                Arrays.asList(HashType.values()),
                this,
                (HashTypeSelectTarget) fragment
        );
    }

}
