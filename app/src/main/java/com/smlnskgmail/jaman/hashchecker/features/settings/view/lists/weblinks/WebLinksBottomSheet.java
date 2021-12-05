package com.smlnskgmail.jaman.hashchecker.features.settings.view.lists.weblinks;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.components.theme.api.ThemeConfig;
import com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.adapter.BaseListAdapter;

import java.util.List;

abstract class WebLinksBottomSheet extends BaseListBottomSheet<WebLink> {

    @NonNull
    @Override
    public BaseListAdapter<WebLink> getItemsAdapter() {
        return new WebLinksListAdapter(getLinks(), this, themeHelper());
    }

    @NonNull
    protected abstract ThemeConfig themeHelper();

    @NonNull
    protected abstract List<WebLink> getLinks();

}
