package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.themes;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.BaseItemsBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetItemsAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.themes.adapter.ThemesBottomSheetItemsAdapter;

public class ThemesBottomSheet extends BaseItemsBottomSheet {

    @Override
    public BaseBottomSheetItemsAdapter getItemsAdapter() {
        return new ThemesBottomSheetItemsAdapter(getListItems(),
                this, getActivity());
    }

}
