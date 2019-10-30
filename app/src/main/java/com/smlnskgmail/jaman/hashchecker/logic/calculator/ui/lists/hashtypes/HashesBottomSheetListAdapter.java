package com.smlnskgmail.jaman.hashchecker.logic.calculator.ui.lists.hashtypes;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.ListMarker;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseBottomSheetListAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseBottomSheetListHolder;
import com.smlnskgmail.jaman.hashchecker.logic.calculator.functions.support.HashType;
import com.smlnskgmail.jaman.hashchecker.logic.settings.SettingsHelper;

import java.util.List;

public class HashesBottomSheetListAdapter extends BaseBottomSheetListAdapter {

    private final HashType selectedHashType;
    private final HashTypeSelectTarget hashTypeSelectListener;

    HashesBottomSheetListAdapter(@NonNull List<ListMarker> items, @NonNull BaseListBottomSheet bottomSheet,
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
            hashTypeSelectListener.hashTypeSelect(hashTypeAtPosition);
            dismissBottomSheet();
        }

        @Override
        protected boolean getConditionToAdditionalIconVisibleState() {
            return hashTypeAtPosition == selectedHashType;
        }

    }

}
