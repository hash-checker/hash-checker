package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.hashtypes.adapter;

import android.support.annotation.NonNull;
import android.view.View;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetItemsAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetItemsHolder;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.hashtypes.OnHashTypeSelectListener;
import com.smlnskgmail.jaman.hashchecker.generator.HashTypes;
import com.smlnskgmail.jaman.hashchecker.support.values.Preferences;

public class HashesBottomSheetItemsHolder extends BaseBottomSheetItemsHolder {

    private OnHashTypeSelectListener onHashTypeSelectListener;

    HashesBottomSheetItemsHolder(@NonNull View itemView,
                                 @NonNull BaseBottomSheetItemsAdapter baseBottomSheetItemsAdapter,
                                 @NonNull OnHashTypeSelectListener onHashTypeSelectListener) {
        super(itemView, baseBottomSheetItemsAdapter);
        this.onHashTypeSelectListener = onHashTypeSelectListener;
    }

    @Override
    protected void callItemClick() {
        HashTypes hashType = (HashTypes) getBaseBottomSheetItemsAdapter()
                .getListItemMarkers().get(getAdapterPosition());
        boolean visible = bottomSheetItemAdditionalIcon.getVisibility() == View.VISIBLE;
        bottomSheetItemAdditionalIcon.setVisibility(visible ? View.INVISIBLE : View.VISIBLE);
        onHashTypeSelectListener.onHashTypeSelect(hashType);
        getBaseBottomSheetItemsAdapter().getBaseItemsBottomSheet().dismissAllowingStateLoss();
    }

    @Override
    protected boolean getConditionToAdditionalIconVisibleState() {
        int hashNameResId = getBaseBottomSheetItemsAdapter()
                .getListItemMarkers().get(getAdapterPosition()).getTitleTextResId();
        String hashName = getContext().getString(hashNameResId);
        return Preferences.getLastType(getContext()).endsWith(hashName);
    }

}
