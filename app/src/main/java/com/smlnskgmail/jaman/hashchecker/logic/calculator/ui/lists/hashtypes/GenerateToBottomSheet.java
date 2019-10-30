package com.smlnskgmail.jaman.hashchecker.logic.calculator.ui.lists.hashtypes;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseListAdapter;

public class GenerateToBottomSheet extends BaseListBottomSheet {

    private HashTypeSelectTarget hashTypeSelectListener;

    @Override
    public BaseListAdapter getItemsAdapter() {
        return new HashesListAdapter(getItems(), this, hashTypeSelectListener);
    }

    public void setHashTypeSelectListener(@NonNull HashTypeSelectTarget hashTypeSelectListener) {
        this.hashTypeSelectListener = hashTypeSelectListener;
    }

}
