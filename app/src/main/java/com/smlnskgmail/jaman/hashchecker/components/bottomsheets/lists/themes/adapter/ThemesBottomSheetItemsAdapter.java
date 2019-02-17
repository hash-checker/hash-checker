package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.themes.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.BaseItemsBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.ListItemMarker;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetItemsAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetItemsHolder;

import java.util.List;

public class ThemesBottomSheetItemsAdapter extends BaseBottomSheetItemsAdapter {

    private Activity activity;

    public ThemesBottomSheetItemsAdapter(@NonNull List<ListItemMarker> bottomSheetItems,
                                         @NonNull BaseItemsBottomSheet baseItemsBottomSheet,
                                         @NonNull Activity activity) {
        super(bottomSheetItems, baseItemsBottomSheet);
        this.activity = activity;
    }

    @Override
    public BaseBottomSheetItemsHolder getItemsHolder(@NonNull View view) {
        return new ThemesBottomSheetItemsHolder(view, this, activity);
    }

}

