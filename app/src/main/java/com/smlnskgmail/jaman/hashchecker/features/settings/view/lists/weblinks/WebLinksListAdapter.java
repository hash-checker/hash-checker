package com.smlnskgmail.jaman.hashchecker.features.settings.view.lists.weblinks;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.components.theme.api.ThemeConfig;
import com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.adapter.BaseListAdapter;
import com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.adapter.BaseListHolder;
import com.smlnskgmail.jaman.hashchecker.utils.WebUtils;

import java.util.List;

public class WebLinksListAdapter extends BaseListAdapter<WebLink> {

    private final ThemeConfig themeConfig;

    WebLinksListAdapter(
            @NonNull List<WebLink> items,
            @NonNull BaseListBottomSheet<WebLink> bottomSheet,
            @NonNull ThemeConfig themeConfig
    ) {
        super(items, bottomSheet);
        this.themeConfig = themeConfig;
    }

    @NonNull
    @Override
    public BaseListHolder<WebLink> getItemsHolder(@NonNull Context themeContext, @NonNull View view) {
        return new WebLinksListHolder(themeContext, view);
    }

    private class WebLinksListHolder extends BaseListHolder<WebLink> {

        private WebLink webLink;

        WebLinksListHolder(@NonNull Context themeContext, @NonNull View itemView) {
            super(themeContext, itemView, themeConfig);
        }

        @Override
        protected void bind(@NonNull WebLink listItem) {
            webLink = listItem;
            super.bind(listItem);
        }

        @Override
        protected void callItemClick() {
            String link = getContext().getString(webLink.getLinkResId());
            WebUtils.openWebLink(getContext(), link);
            dismissBottomSheet();
        }

        @Override
        protected boolean getConditionToPrimaryIconVisibleState() {
            return true;
        }

    }

}
