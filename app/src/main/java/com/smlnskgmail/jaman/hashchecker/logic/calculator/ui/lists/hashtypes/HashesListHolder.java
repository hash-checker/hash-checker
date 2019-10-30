package com.smlnskgmail.jaman.hashchecker.logic.calculator.ui.lists.hashtypes;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.ListItemTarget;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseListHolder;
import com.smlnskgmail.jaman.hashchecker.logic.calculator.functions.HashType;

class HashesListHolder extends BaseListHolder {

    private final HashesListAdapter hashesListAdapter;
    private final HashTypeSelectTarget hashTypeSelectListener;

    private HashType hashTypeAtPosition;

    HashesListHolder(HashesListAdapter hashesListAdapter, @NonNull View itemView, @NonNull Context themeContext,
                     @NonNull HashTypeSelectTarget hashTypeSelectListener) {
        super(itemView, themeContext);
        this.hashesListAdapter = hashesListAdapter;
        this.hashTypeSelectListener = hashTypeSelectListener;
    }

    @Override
    protected void bind(@NonNull ListItemTarget listItemTarget) {
        hashTypeAtPosition = (HashType) listItemTarget;
        super.bind(listItemTarget);
    }

    @Override
    protected void callItemClick() {
        ImageView ivAdditionalIcon = getIvItemAdditionalIcon();
        boolean visible = ivAdditionalIcon.getVisibility() == View.VISIBLE;
        ivAdditionalIcon.setVisibility(visible ? View.INVISIBLE : View.VISIBLE);

        hashTypeSelectListener.hashTypeSelect(hashTypeAtPosition);
        hashesListAdapter.dismissBottomSheet();
    }

    @Override
    protected boolean getConditionToAdditionalIconVisibleState() {
        return hashTypeAtPosition == hashesListAdapter.getSelectedHashType();
    }

}
