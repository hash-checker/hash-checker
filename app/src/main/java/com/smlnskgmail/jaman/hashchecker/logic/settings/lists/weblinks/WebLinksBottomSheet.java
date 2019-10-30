package com.smlnskgmail.jaman.hashchecker.logic.settings.lists.weblinks;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseBottomSheetListAdapter;

public class WebLinksBottomSheet extends BaseListBottomSheet {

    @Override
    public BaseBottomSheetListAdapter getItemsAdapter() {
        return new WebLinksBottomSheetListAdapter(getItems(), this);
    }

}
