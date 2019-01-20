package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.weblinks;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.BaseItemsBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetItemsAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.weblinks.adapter.WebLinksBottomSheetItemsAdapter;

public class WebLinksBottomSheet extends BaseItemsBottomSheet {

    @Override
    public BaseBottomSheetItemsAdapter getItemsAdapter() {
        return new WebLinksBottomSheetItemsAdapter(getListItems(), this);
    }

}
