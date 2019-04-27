package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.hashtypes.adapter;

import android.support.annotation.NonNull;
import android.view.View;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.ListItemMarker;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListHolder;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.hashtypes.OnHashTypeSelectListener;
import com.smlnskgmail.jaman.hashchecker.generator.HashTypes;
import com.smlnskgmail.jaman.hashchecker.support.preferences.Preferences;

public class HashesBottomSheetListHolder extends BaseBottomSheetListHolder {

    private HashTypes hashTypeAtPosition, selectedHashType;
    private OnHashTypeSelectListener hashTypeSelectListener;

    HashesBottomSheetListHolder(@NonNull View itemView, @NonNull BaseBottomSheetListAdapter listAdapter,
                                @NonNull OnHashTypeSelectListener hashTypeSelectListener,
                                @NonNull HashTypes selectedHashType) {
        super(itemView, listAdapter);
        this.hashTypeSelectListener = hashTypeSelectListener;
        this.selectedHashType = selectedHashType;
    }

    @Override
    protected void bind(@NonNull ListItemMarker listItemMarker) {
        hashTypeAtPosition = (HashTypes) listItemMarker;
        super.bind(listItemMarker);
    }

    @Override
    protected void callItemClick() {
        boolean visible = itemAdditionalIcon.getVisibility() == View.VISIBLE;
        itemAdditionalIcon.setVisibility(visible ? View.INVISIBLE : View.VISIBLE);
        hashTypeSelectListener.onHashTypeSelect(hashTypeAtPosition);
        getListAdapter().getBottomSheet().dismissAllowingStateLoss();
    }

    @Override
    protected boolean getConditionToAdditionalIconVisibleState() {
        return hashTypeAtPosition == selectedHashType;
    }

}
