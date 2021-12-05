package com.smlnskgmail.jaman.hashchecker.features.hashcalculator.lists.hashtypes;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashType;
import com.smlnskgmail.jaman.hashchecker.components.theme.api.ThemeConfig;
import com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.adapter.BaseListAdapter;
import com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.adapter.BaseListHolder;

import java.util.List;

public class HashesListAdapter extends BaseListAdapter<HashType> {

    private final HashType selectedHashType;
    private final HashTypeSelectTarget hashTypeSelectListener;
    private final ThemeConfig themeConfig;

    HashesListAdapter(
            @NonNull List<HashType> items,
            @NonNull BaseListBottomSheet<HashType> bottomSheet,
            @NonNull HashTypeSelectTarget hashTypeSelectListener,
            @NonNull HashType selectedHashType,
            @NonNull ThemeConfig themeConfig
    ) {
        super(items, bottomSheet);
        this.hashTypeSelectListener = hashTypeSelectListener;
        this.selectedHashType = selectedHashType;
        this.themeConfig = themeConfig;
    }

    @NonNull
    @Override
    public BaseListHolder<HashType> getItemsHolder(@NonNull Context themeContext, @NonNull View view) {
        return new HashesListHolder(themeContext, view);
    }

    private class HashesListHolder extends BaseListHolder<HashType> {

        private HashType hashTypeAtPosition;

        HashesListHolder(@NonNull Context themeContext, @NonNull View itemView) {
            super(themeContext, itemView, themeConfig);
        }

        @Override
        protected void bind(@NonNull HashType listItem) {
            hashTypeAtPosition = listItem;
            super.bind(listItem);
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
