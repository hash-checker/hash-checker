package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.main.sources;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.main.OnUserActionClickListener;

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
