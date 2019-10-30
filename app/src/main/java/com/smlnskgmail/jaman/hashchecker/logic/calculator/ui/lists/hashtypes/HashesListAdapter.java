package com.smlnskgmail.jaman.hashchecker.logic.calculator.ui.lists.hashtypes;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.ListItemTarget;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseListAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseListHolder;
import com.smlnskgmail.jaman.hashchecker.logic.calculator.functions.HashType;
import com.smlnskgmail.jaman.hashchecker.logic.settings.SettingsHelper;

import java.util.List;

public class HashesListAdapter extends BaseListAdapter {

    private final HashType selectedHashType;
    private final HashTypeSelectTarget hashTypeSelectListener;

    HashesListAdapter(@NonNull List<ListItemTarget> items, @NonNull BaseListBottomSheet bottomSheet,
                      @NonNull HashTypeSelectTarget hashTypeSelectListener) {
        super(items, bottomSheet);
        this.hashTypeSelectListener = hashTypeSelectListener;
        selectedHashType = SettingsHelper.getLastHashType(getBottomSheet().getContext());
    }

    @Override
    public BaseListHolder getItemsHolder(@NonNull View view, @NonNull Context themeContext) {
        return new HashesListHolder(this, view, themeContext, hashTypeSelectListener);
    }

    public HashType getSelectedHashType() {
        return selectedHashType;
    }

}
