package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.main.sources;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.ListItemMarker;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListHolder;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.main.OnUserActionClickListener;

import java.util.List;

public class SourcesBottomSheetListAdapter extends BaseBottomSheetListAdapter {

    private OnUserActionClickListener onUserActionClickListener;

    SourcesBottomSheetListAdapter(@NonNull List<ListItemMarker> items,
                                  @NonNull BaseListBottomSheet bottomSheet,
                                  @NonNull OnUserActionClickListener onUserActionClickListener) {
        super(items, bottomSheet);
        this.onUserActionClickListener = onUserActionClickListener;
    }

    @Override
    protected BaseBottomSheetListHolder getItemsHolder(@NonNull View view, @NonNull Context themeContext) {
        return new SourcesBottomSheetListHolder(view, themeContext, onUserActionClickListener);
    }

    private class SourcesBottomSheetListHolder extends BaseBottomSheetListHolder {

        private OnUserActionClickListener onUserActionClickListener;

        SourcesBottomSheetListHolder(@NonNull View itemView, @NonNull Context themeContext,
                                     @NonNull OnUserActionClickListener onUserActionClickListener) {
            super(itemView, themeContext);
            this.onUserActionClickListener = onUserActionClickListener;
        }

        @Override
        protected void callItemClick() {
            Source source = (Source) getItems().get(getAdapterPosition());
            onUserActionClickListener.onUserActionClick(source.getUserActionType());
            dismissBottomSheet();
        }

        @Override
        protected boolean getConditionToPrimaryIconVisibleState() {
            return true;
        }

    }

}
