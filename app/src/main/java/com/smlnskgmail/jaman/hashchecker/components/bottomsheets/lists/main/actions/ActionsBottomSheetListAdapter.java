package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.main.actions;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.ListMarker;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListHolder;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.main.actions.types.UserActionTarget;

import java.util.List;

public class ActionsBottomSheetListAdapter extends BaseBottomSheetListAdapter {

    private final UserActionTarget userActionTarget;

    ActionsBottomSheetListAdapter(@NonNull List<ListMarker> items, @NonNull BaseListBottomSheet bottomSheet,
                                  @NonNull UserActionTarget userActionTarget) {
        super(items, bottomSheet);
        this.userActionTarget = userActionTarget;
    }

    @Override
    protected BaseBottomSheetListHolder getItemsHolder(@NonNull View view, @NonNull Context themeContext) {
        return new ActionsBottomSheetListHolder(view, themeContext, userActionTarget);
    }

    private class ActionsBottomSheetListHolder extends BaseBottomSheetListHolder {

        private final UserActionTarget userActionTarget;

        ActionsBottomSheetListHolder(@NonNull View itemView, @NonNull Context themeContext,
                                     @NonNull UserActionTarget userActionTarget) {
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
