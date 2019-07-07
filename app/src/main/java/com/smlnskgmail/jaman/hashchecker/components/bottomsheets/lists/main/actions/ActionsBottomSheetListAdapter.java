package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.main.actions;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.ListItemMarker;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListHolder;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.main.actions.types.OnUserActionClickListener;

import java.util.List;

public class ActionsBottomSheetListAdapter extends BaseBottomSheetListAdapter {

    private OnUserActionClickListener onUserActionClickListener;

    ActionsBottomSheetListAdapter(@NonNull List<ListItemMarker> items,
                                  @NonNull BaseListBottomSheet bottomSheet,
                                  @NonNull OnUserActionClickListener onUserActionClickListener) {
        super(items, bottomSheet);
        this.onUserActionClickListener = onUserActionClickListener;
    }

    @Override
    protected BaseBottomSheetListHolder getItemsHolder(@NonNull View view, @NonNull Context themeContext) {
        return new ActionsBottomSheetListHolder(view, themeContext, onUserActionClickListener);
    }

    private class ActionsBottomSheetListHolder extends BaseBottomSheetListHolder {

        private OnUserActionClickListener onUserActionClickListener;

        ActionsBottomSheetListHolder(@NonNull View itemView, @NonNull Context themeContext,
                                     @NonNull OnUserActionClickListener onUserActionClickListener) {
            super(itemView, themeContext);
            this.onUserActionClickListener = onUserActionClickListener;
        }

        @Override
        protected void callItemClick() {
            Action action = (Action) getItems().get(getAdapterPosition());
            onUserActionClickListener.onUserActionClick(action.getUserActionType());
            dismissBottomSheet();
        }

        @Override
        protected boolean getConditionToPrimaryIconVisibleState() {
            return true;
        }

    }

}
