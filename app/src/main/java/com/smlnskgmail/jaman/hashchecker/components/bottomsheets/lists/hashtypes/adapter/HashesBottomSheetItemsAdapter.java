package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.hashtypes.adapter;

import android.support.annotation.NonNull;
import android.view.View;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.BaseItemsBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.ListItemMarker;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetItemsAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetItemsHolder;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.hashtypes.OnHashTypeSelectListener;

import java.util.List;

public class HashesBottomSheetItemsAdapter extends BaseBottomSheetItemsAdapter {

    private OnHashTypeSelectListener onHashTypeSelectListener;

    public HashesBottomSheetItemsAdapter(@NonNull List<ListItemMarker> bottomSheetItems,
                                         @NonNull BaseItemsBottomSheet baseItemsBottomSheet,
                                         @NonNull OnHashTypeSelectListener onHashTypeSelectListener) {
        super(bottomSheetItems, baseItemsBottomSheet);
        this.onHashTypeSelectListener = onHashTypeSelectListener;
    }

    @Override
    public BaseBottomSheetItemsHolder getItemsHolder(@NonNull View view) {
        return new HashesBottomSheetItemsHolder(view, this,
                onHashTypeSelectListener);
    }

}
