package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.main.hashtypes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.ListItemMarker;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListHolder;
import com.smlnskgmail.jaman.hashchecker.hashgenerator.support.HashType;
import com.smlnskgmail.jaman.hashchecker.support.prefs.PrefsHelper;

import java.util.List;

public class HashesBottomSheetListAdapter extends BaseBottomSheetListAdapter {

    private HashType selectedHashType;
    private OnHashTypeSelectListener hashTypeSelectListener;

    HashesBottomSheetListAdapter(@NonNull List<ListItemMarker> items,
                                 @NonNull BaseListBottomSheet bottomSheet,
                                 @NonNull OnHashTypeSelectListener hashTypeSelectListener) {
        super(items, bottomSheet);
        this.hashTypeSelectListener = hashTypeSelectListener;
        selectedHashType = PrefsHelper.getLastHashType(getBottomSheet().getContext());
    }

    @Override
    public BaseBottomSheetListHolder getItemsHolder(@NonNull View view, @NonNull Context themeContext) {
        return new HashesBottomSheetListHolder(view, themeContext, hashTypeSelectListener);
    }

    private class HashesBottomSheetListHolder extends BaseBottomSheetListHolder {

        private HashType hashTypeAtPosition;
        private OnHashTypeSelectListener hashTypeSelectListener;

        HashesBottomSheetListHolder(@NonNull View itemView, @NonNull Context themeContext,
                                    @NonNull OnHashTypeSelectListener hashTypeSelectListener) {
            super(itemView, themeContext);
            this.hashTypeSelectListener = hashTypeSelectListener;
        }

        @Override
        protected void bind(@NonNull ListItemMarker listItemMarker) {
            hashTypeAtPosition = (HashType) listItemMarker;
            super.bind(listItemMarker);
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
