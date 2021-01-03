package com.smlnskgmail.jaman.hashchecker.logic.settings.ui.lists.weblinks;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseListAdapter;

import java.util.List;

abstract class WebLinksBottomSheet extends BaseListBottomSheet<WebLink> {

    @Override
    public @androidx.annotation.NonNull BaseListAdapter<WebLink> getItemsAdapter() {
        return new WebLinksListAdapter(
                getLinks(),
                this
        );
    }

    @NonNull
    abstract List<WebLink> getLinks();

}
