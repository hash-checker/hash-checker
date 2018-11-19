package com.smlnskgmail.jaman.hashchecker.components.selectors.bottomsheets.items.generator;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.selectors.bottomsheets.items.BaseItemsBottomSheet;

@SuppressLint("ValidFragment")
public class GenerateToBottomSheet extends BaseItemsBottomSheet {

    private IHashTypeSelectListener IHashTypeSelectListener;
    private String selectedTypeAsString;

    public GenerateToBottomSheet(@NonNull IHashTypeSelectListener IHashTypeSelectListener,
                                 @NonNull String selectedTypeAsString) {
        this.IHashTypeSelectListener = IHashTypeSelectListener;
        this.selectedTypeAsString = selectedTypeAsString;
    }

    @Nullable
    @Override
    public IHashTypeSelectListener callback() {
        return IHashTypeSelectListener;
    }

    @Nullable
    @Override
    public String getSelectedTypeAsString() {
        return selectedTypeAsString;
    }

    @Override
    public boolean hideMarks() {
        return true;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.bottom_sheet_items;
    }

}
