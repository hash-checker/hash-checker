package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.sources.adapter;

import android.support.annotation.NonNull;
import android.view.View;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListHolder;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.sources.Source;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.selectors.actions.OnUserActionClickListener;

class SourcesBottomSheetListHolder extends BaseBottomSheetListHolder {

    private OnUserActionClickListener onUserActionClickListener;

    SourcesBottomSheetListHolder(@NonNull View itemView,
                                 @NonNull BaseBottomSheetListAdapter listAdapter,
                                 @NonNull OnUserActionClickListener onUserActionClickListener) {
        super(itemView, listAdapter);
        this.onUserActionClickListener = onUserActionClickListener;
    }

    @Override
    protected void callItemClick() {
        Source source = (Source) getListAdapter().getItems().get(getAdapterPosition());
        onUserActionClickListener.onUserActionClick(source.getUserActionType());
        getListAdapter().getBottomSheet().dismiss();
    }

    @Override
    protected boolean getConditionToPrimaryIconVisibleState() {
        return true;
    }

}
