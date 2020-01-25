package com.smlnskgmail.jaman.hashchecker.logic.settings.ui.lists.languages;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseListAdapter;
import com.smlnskgmail.jaman.hashchecker.logic.settings.SettingsHelper;

public class LanguagesBottomSheet extends BaseListBottomSheet {

    @Override
    protected BaseListAdapter getItemsAdapter() {
        return new LanguagesListAdapter(
                getItems(),
                this,
                SettingsHelper.getLanguage(getContext())
        );
    }

}
