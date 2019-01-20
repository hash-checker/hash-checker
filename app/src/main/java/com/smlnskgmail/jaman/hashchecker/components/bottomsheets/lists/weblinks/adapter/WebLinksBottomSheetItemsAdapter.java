package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.weblinks.adapter;

import android.support.annotation.NonNull;
import android.view.View;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.BaseItemsBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.ListItemMarker;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetItemsAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetItemsHolder;

import java.util.List;

public class WebLinksBottomSheetItemsAdapter extends BaseBottomSheetItemsAdapter {

    public WebLinksBottomSheetItemsAdapter(@NonNull List<ListItemMarker> bottomSheetItems, @NonNull BaseItemsBottomSheet baseItemsBottomSheet) {
        super(bottomSheetItems, baseItemsBottomSheet);
    }

    @Override
    public BaseBottomSheetItemsHolder getItemsHolder(@NonNull View view) {
        return new WebLinksBottomSheetItemsHolder(view, this);
    }

}
