package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.themes;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.themes.adapter.ThemesBottomSheetListAdapter;

public class ThemesBottomSheet extends BaseListBottomSheet {

    @Override
    public BaseBottomSheetListAdapter getItemsAdapter() {
        return new ThemesBottomSheetListAdapter(getItems(), this, getActivity());
    }

}
