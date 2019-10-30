package com.smlnskgmail.jaman.hashchecker.logic.settings.lists.themes;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseBottomSheetListAdapter;

public class ThemesBottomSheet extends BaseListBottomSheet {

    @Override
    public BaseBottomSheetListAdapter getItemsAdapter() {
        return new ThemesBottomSheetListAdapter(getItems(), this);
    }

}
