package com.smlnskgmail.jaman.hashchecker.logic.settings.lists.themes;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseListAdapter;

public class ThemesBottomSheet extends BaseListBottomSheet {

    @Override
    public BaseListAdapter getItemsAdapter() {
        return new ThemesListAdapter(getItems(), this);
    }

}
