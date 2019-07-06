package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.sources.adapter;

import android.support.annotation.NonNull;
import android.view.View;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.ListItemMarker;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListHolder;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.selectors.actions.OnUserActionClickListener;

import java.util.List;

public class SourcesBottomSheetListAdapter extends BaseBottomSheetListAdapter {

    private OnUserActionClickListener onUserActionClickListener;

    public SourcesBottomSheetListAdapter(@NonNull List<ListItemMarker> items,
                                         @NonNull BaseListBottomSheet bottomSheet,
                                         @NonNull OnUserActionClickListener onUserActionClickListener) {
        super(items, bottomSheet);
        this.onUserActionClickListener = onUserActionClickListener;
    }

    @Override
    protected BaseBottomSheetListHolder getItemsHolder(@NonNull View view) {
        return new SourcesBottomSheetListHolder(view, this, onUserActionClickListener);
    }

}
