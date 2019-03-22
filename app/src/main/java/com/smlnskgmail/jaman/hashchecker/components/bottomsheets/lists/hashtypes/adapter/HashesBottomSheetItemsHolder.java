package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.hashtypes.adapter;

import android.support.annotation.NonNull;
import android.view.View;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetItemsAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetItemsHolder;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.hashtypes.OnHashTypeSelectListener;
import com.smlnskgmail.jaman.hashchecker.generator.HashTypes;
import com.smlnskgmail.jaman.hashchecker.support.preferences.Preferences;

public class HashesBottomSheetItemsHolder extends BaseBottomSheetItemsHolder {

    private OnHashTypeSelectListener hashTypeSelectListener;

    HashesBottomSheetItemsHolder(@NonNull View itemView,
                                 @NonNull BaseBottomSheetItemsAdapter itemsAdapter,
                                 @NonNull OnHashTypeSelectListener hashTypeSelectListener) {
        super(itemView, itemsAdapter);
        this.hashTypeSelectListener = hashTypeSelectListener;
    }

    @Override
    protected void callItemClick() {
        HashTypes hashType = (HashTypes) getItemsAdapter().getListItems().get(getAdapterPosition());
        boolean visible = itemAdditionalIcon.getVisibility() == View.VISIBLE;
        itemAdditionalIcon.setVisibility(visible ? View.INVISIBLE : View.VISIBLE);
        hashTypeSelectListener.onHashTypeSelect(hashType);
        getItemsAdapter().getItemsBottomSheet().dismissAllowingStateLoss();
    }

    @Override
    protected boolean getConditionToAdditionalIconVisibleState() {
        int hashNameResId = getItemsAdapter().getListItems().get(getAdapterPosition())
                .getTitleTextResId();
        String hashName = getContext().getString(hashNameResId);
        return Preferences.getLastType(getContext()).endsWith(hashName);
    }

}
