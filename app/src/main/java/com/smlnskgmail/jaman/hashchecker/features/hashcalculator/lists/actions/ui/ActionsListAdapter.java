package com.smlnskgmail.jaman.hashchecker.features.hashcalculator.lists.actions.ui;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smlnskgmail.jaman.hashchecker.components.theme.api.ThemeConfig;
import com.smlnskgmail.jaman.hashchecker.features.hashcalculator.lists.actions.Action;
import com.smlnskgmail.jaman.hashchecker.features.hashcalculator.lists.actions.types.UserActionTarget;
import com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.adapter.BaseListAdapter;
import com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.adapter.BaseListHolder;

import java.util.List;

public class ActionsListAdapter extends BaseListAdapter<Action> {

    private final UserActionTarget userActionTarget;
    private final ThemeConfig themeConfig;

    ActionsListAdapter(
            @NonNull List<Action> items,
            @NonNull BaseListBottomSheet<Action> bottomSheet,
            @Nullable UserActionTarget userActionTarget,
            @NonNull ThemeConfig themeConfig
    ) {
        super(items, bottomSheet);
        this.userActionTarget = userActionTarget;
        this.themeConfig = themeConfig;
    }

    @NonNull
    @Override
    protected BaseListHolder<Action> getItemsHolder(@NonNull Context themeContext, @NonNull View view) {
        return new ActionHolder(themeContext, view);
    }

    private class ActionHolder extends BaseListHolder<Action> {

        private Action action;

        ActionHolder(@NonNull Context themeContext, @NonNull View itemView) {
            super(themeContext, itemView, themeConfig);
        }

        @Override
        protected void bind(@NonNull Action listItem) {
            action = listItem;
            super.bind(listItem);
        }

        @Override
        protected void callItemClick() {
            if (userActionTarget != null) {
                userActionTarget.userActionSelect(action.getUserActionType());
            }
            dismissBottomSheet();
        }

        @Override
        protected boolean getConditionToPrimaryIconVisibleState() {
            return true;
        }

    }

}
