package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.main.hashtypes;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.calculator.support.HashType;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.ListMarker;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListHolder;
import com.smlnskgmail.jaman.hashchecker.support.prefs.SettingsHelper;

import java.util.List;

public class HashesBottomSheetListAdapter extends BaseBottomSheetListAdapter {

    private final HashType selectedHashType;
    private final HashTypeSelectTarget hashTypeSelectListener;

    HashesBottomSheetListAdapter(@NonNull List<ListMarker> items,
                                 @NonNull BaseListBottomSheet bottomSheet,
                                 @NonNull HashTypeSelectTarget hashTypeSelectListener) {
        super(items, bottomSheet);
        this.hashTypeSelectListener = hashTypeSelectListener;
        selectedHashType = SettingsHelper.getLastHashType(getBottomSheet().getContext());
    }

    @Override
    public BaseBottomSheetListHolder getItemsHolder(@NonNull View view, @NonNull Context themeContext) {
        return new HashesBottomSheetListHolder(view, themeContext, hashTypeSelectListener);
    }

    private class HashesBottomSheetListHolder extends BaseBottomSheetListHolder {

        private HashType hashTypeAtPosition;
        private final HashTypeSelectTarget hashTypeSelectListener;

        HashesBottomSheetListHolder(@NonNull View itemView, @NonNull Context themeContext,
                                    @NonNull HashTypeSelectTarget hashTypeSelectListener) {
            super(itemView, themeContext);
            this.hashTypeSelectListener = hashTypeSelectListener;
        }

        @Override
        protected void bind(@NonNull ListMarker listMarker) {
            hashTypeAtPosition = (HashType) listMarker;
            super.bind(listMarker);
        }

        @Override
        protected void callItemClick() {
            ImageView ivAdditionalIcon = getIvItemAdditionalIcon();
            boolean visible = ivAdditionalIcon.getVisibility() == View.VISIBLE;
            ivAdditionalIcon.setVisibility(visible ? View.INVISIBLE : View.VISIBLE);
            hashTypeSelectListener.onHashTypeSelect(hashTypeAtPosition);
            dismissBottomSheet();
        }

        @Override
        protected boolean getConditionToAdditionalIconVisibleState() {
            return hashTypeAtPosition == selectedHashType;
        }

    }

}
