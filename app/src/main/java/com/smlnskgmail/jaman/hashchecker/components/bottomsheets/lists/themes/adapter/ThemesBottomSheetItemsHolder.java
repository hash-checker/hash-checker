package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.themes.adapter;

import android.support.annotation.NonNull;
import android.view.View;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetItemsAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetItemsHolder;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.themes.Themes;
import com.smlnskgmail.jaman.hashchecker.support.values.Preferences;

public class ThemesBottomSheetItemsHolder extends BaseBottomSheetItemsHolder {

    ThemesBottomSheetItemsHolder(@NonNull View itemView,
                                 @NonNull BaseBottomSheetItemsAdapter baseBottomSheetItemsAdapter) {
        super(itemView, baseBottomSheetItemsAdapter);
    }

    @Override
    protected void callItemClick() {
        Themes theme = (Themes) getBaseBottomSheetItemsAdapter()
                .getListItemMarkers().get(getAdapterPosition());
        Preferences.saveTheme(getContext(), theme);
        getBaseBottomSheetItemsAdapter().getBaseItemsBottomSheet().dismissAllowingStateLoss();
    }

    @Override
    protected boolean getConditionToPrimaryIconVisibleState() {
        return true;
    }

    @Override
    protected boolean getConditionToAdditionalIconVisibleState() {
        Themes theme = (Themes) getBaseBottomSheetItemsAdapter()
                .getListItemMarkers().get(getAdapterPosition());
        return theme.equals(Themes.getThemeFromPreferences(getContext()));
    }

}
