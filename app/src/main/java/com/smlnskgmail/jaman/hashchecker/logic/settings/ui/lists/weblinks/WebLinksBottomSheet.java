package com.smlnskgmail.jaman.hashchecker.logic.settings.ui.lists.weblinks;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseListAdapter;
import com.smlnskgmail.jaman.hashchecker.logic.themes.api.ThemeHelper;

import java.util.List;

abstract class WebLinksBottomSheet extends BaseListBottomSheet<WebLink> {

    @NonNull
    @Override
    public BaseListAdapter<WebLink> getItemsAdapter() {
        return new WebLinksListAdapter(
                getLinks(),
                this,
                themeHelper()
        );
    }

    @NonNull
    protected abstract ThemeHelper themeHelper();

    @NonNull
    abstract List<WebLink> getLinks();

}
