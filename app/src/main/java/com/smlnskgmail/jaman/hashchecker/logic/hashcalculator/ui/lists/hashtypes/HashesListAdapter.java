package com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.ui.lists.hashtypes;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseListAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseListHolder;
import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.HashType;
import com.smlnskgmail.jaman.hashchecker.logic.settings.SettingsHelper;

import java.util.List;

public class HashesListAdapter extends BaseListAdapter<HashType> {

    private final HashType selectedHashType;
    private final HashTypeSelectTarget hashTypeSelectListener;

    HashesListAdapter(
            @NonNull List<HashType> items,
            @NonNull BaseListBottomSheet<HashType> bottomSheet,
            @NonNull HashTypeSelectTarget hashTypeSelectListener
    ) {
        super(items, bottomSheet);
        this.hashTypeSelectListener = hashTypeSelectListener;
        selectedHashType = SettingsHelper.getLastHashType(
                getBottomSheet().getContext()
        );
    }

    @Override
    public BaseListHolder<HashType> getItemsHolder(
            @NonNull View view,
            @NonNull Context themeContext
    ) {
        return new HashesListHolder(
                view,
                themeContext
        );
    }

    private class HashesListHolder extends BaseListHolder<HashType> {

        private HashType hashTypeAtPosition;

        HashesListHolder(
                @NonNull View itemView,
                @NonNull Context themeContext
        ) {
            super(itemView, themeContext);
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
