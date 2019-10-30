package com.smlnskgmail.jaman.hashchecker.logic.settings.lists.weblinks;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseListAdapter;

public class WebLinksBottomSheet extends BaseListBottomSheet {

    @Override
    public BaseListAdapter getItemsAdapter() {
        return new WebLinksListAdapter(getItems(), this);
    }

}
