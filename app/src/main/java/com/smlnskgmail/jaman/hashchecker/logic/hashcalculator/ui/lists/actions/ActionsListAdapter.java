package com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.ui.lists.actions;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.ListItemTarget;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseListAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseListHolder;
import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.ui.lists.actions.types.UserActionTarget;

import java.util.List;

public class ActionsListAdapter extends BaseListAdapter {

    private final UserActionTarget userActionTarget;

    ActionsListAdapter(
            @NonNull List<ListItemTarget> items,
            @NonNull BaseListBottomSheet bottomSheet,
            @NonNull UserActionTarget userActionTarget
    ) {
        super(items, bottomSheet);
        this.userActionTarget = userActionTarget;
    }

    @Override
    protected BaseListHolder getItemsHolder(@NonNull View view, @NonNull Context themeContext) {
        return new ActionsListHolder(view, themeContext, userActionTarget);
    }

    private class ActionsListHolder extends BaseListHolder {

        private final UserActionTarget userActionTarget;

        ActionsListHolder(
                @NonNull View itemView,
                @NonNull Context themeContext,
                @NonNull UserActionTarget userActionTarget
        ) {
            super(itemView, themeContext);
            this.userActionTarget = userActionTarget;
        }

        @Override
        protected void callItemClick() {
            Action action = (Action) getItems().get(getAdapterPosition());
            userActionTarget.userActionSelect(action.getUserActionType());
            dismissBottomSheet();
        }

        @Override
        protected boolean getConditionToPrimaryIconVisibleState() {
            return true;
        }

    }

}
