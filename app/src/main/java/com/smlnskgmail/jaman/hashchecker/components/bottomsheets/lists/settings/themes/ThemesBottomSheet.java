package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.settings.themes;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListAdapter;

public class ThemesBottomSheet extends BaseListBottomSheet {

    @Override
    public BaseBottomSheetListAdapter getItemsAdapter() {
        return new ThemesBottomSheetListAdapter(getItems(), this);
    }

}
