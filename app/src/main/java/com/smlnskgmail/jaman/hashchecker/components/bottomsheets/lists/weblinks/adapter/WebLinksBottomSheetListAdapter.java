package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.weblinks.adapter;

import android.support.annotation.NonNull;
import android.view.View;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.ListItemMarker;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListHolder;

import java.util.List;

public class WebLinksBottomSheetListAdapter extends BaseBottomSheetListAdapter {

    public WebLinksBottomSheetListAdapter(@NonNull List<ListItemMarker> items,
                                          @NonNull BaseListBottomSheet bottomSheet) {
        super(items, bottomSheet);
    }

    @Override
    public BaseBottomSheetListHolder getItemsHolder(@NonNull View view) {
        return new WebLinksBottomSheetListHolder(view, this);
    }

}
