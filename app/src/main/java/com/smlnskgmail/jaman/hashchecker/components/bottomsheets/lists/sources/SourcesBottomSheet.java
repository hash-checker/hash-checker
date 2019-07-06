package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.sources;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.sources.adapter.SourcesBottomSheetListAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.selectors.actions.OnUserActionClickListener;

import java.util.Arrays;

public class SourcesBottomSheet extends BaseListBottomSheet {

    private OnUserActionClickListener onUserActionClickListener;

    public void setOnUserActionClickListener(OnUserActionClickListener onUserActionClickListener) {
        this.onUserActionClickListener = onUserActionClickListener;
    }

    @Override
    protected BaseBottomSheetListAdapter getItemsAdapter() {
        return new SourcesBottomSheetListAdapter(Arrays.asList(Source.TEXT, Source.FILE), this,
                onUserActionClickListener);
    }

}
