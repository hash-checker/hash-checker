package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.themes.adapter;

import android.support.annotation.NonNull;
import android.view.View;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.ListItemMarker;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListHolder;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.themes.Theme;

import java.util.List;

public class ThemesBottomSheetListAdapter extends BaseBottomSheetListAdapter {

    private Theme selectedTheme;

    public ThemesBottomSheetListAdapter(@NonNull List<ListItemMarker> items,
                                        @NonNull BaseListBottomSheet bottomSheet) {
        super(items, bottomSheet);
        selectedTheme = Theme.getThemeFromPreferences(getBottomSheet().getContext());
    }

    @Override
    public BaseBottomSheetListHolder getItemsHolder(@NonNull View view) {
        return new ThemesBottomSheetListHolder(view, this);
    }

    public Theme getSelectedTheme() {
        return selectedTheme;
    }

}

