package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.hashtypes.adapter;

import android.support.annotation.NonNull;
import android.view.View;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListHolder;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.hashtypes.OnHashTypeSelectListener;
import com.smlnskgmail.jaman.hashchecker.generator.HashTypes;
import com.smlnskgmail.jaman.hashchecker.support.preferences.Preferences;

public class HashesBottomSheetListHolder extends BaseBottomSheetListHolder {

    private OnHashTypeSelectListener hashTypeSelectListener;

    HashesBottomSheetListHolder(@NonNull View itemView,
                                @NonNull BaseBottomSheetListAdapter listAdapter,
                                @NonNull OnHashTypeSelectListener hashTypeSelectListener) {
        super(itemView, listAdapter);
        this.hashTypeSelectListener = hashTypeSelectListener;
    }

    @Override
    protected void callItemClick() {
        HashTypes hashType = (HashTypes) getListAdapter().getItems().get(getAdapterPosition());
        boolean visible = itemAdditionalIcon.getVisibility() == View.VISIBLE;
        itemAdditionalIcon.setVisibility(visible ? View.INVISIBLE : View.VISIBLE);
        hashTypeSelectListener.onHashTypeSelect(hashType);
        getListAdapter().getBottomSheet().dismissAllowingStateLoss();
    }

    @Override
    protected boolean getConditionToAdditionalIconVisibleState() {
        int hashNameResId = getListAdapter().getItems().get(getAdapterPosition())
                .getTitleTextResId();
        String hashName = getContext().getString(hashNameResId);
        return Preferences.getLastType(getContext()).endsWith(hashName);
    }

}
