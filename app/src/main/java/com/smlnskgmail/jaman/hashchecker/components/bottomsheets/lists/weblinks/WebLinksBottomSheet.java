package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.weblinks;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.weblinks.adapter.WebLinksBottomSheetListAdapter;

public class WebLinksBottomSheet extends BaseListBottomSheet {

    @Override
    public BaseBottomSheetListAdapter getItemsAdapter() {
        return new WebLinksBottomSheetListAdapter(getItems(), this);
    }

}
