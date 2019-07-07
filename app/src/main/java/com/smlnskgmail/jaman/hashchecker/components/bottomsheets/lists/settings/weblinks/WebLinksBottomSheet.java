package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.settings.weblinks;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListAdapter;

public class WebLinksBottomSheet extends BaseListBottomSheet {

    @Override
    public BaseBottomSheetListAdapter getItemsAdapter() {
        return new WebLinksBottomSheetListAdapter(getItems(), this);
    }

}
