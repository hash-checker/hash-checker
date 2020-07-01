package com.smlnskgmail.jaman.hashchecker.logic.settings.ui.lists.weblinks;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseListAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseListHolder;
import com.smlnskgmail.jaman.hashchecker.utils.WebUtils;

import java.util.List;

public class WebLinksListAdapter extends BaseListAdapter<WebLink> {

    WebLinksListAdapter(
            @NonNull List<WebLink> items,
            @NonNull BaseListBottomSheet<WebLink> bottomSheet
    ) {
        super(items, bottomSheet);
    }

    @Override
    public BaseListHolder<WebLink> getItemsHolder(
            @NonNull View view,
            @NonNull Context themeContext
    ) {
        return new WebLinksListHolder(
                view,
                themeContext
        );
    }

    private class WebLinksListHolder extends BaseListHolder<WebLink> {

        private WebLink webLink;

        WebLinksListHolder(
                @NonNull View itemView,
                @NonNull Context themeContext
        ) {
            super(itemView, themeContext);
        }

        @Override
        protected void bind(@NonNull WebLink listItem) {
            webLink = listItem;
            super.bind(listItem);
        }

        @Override
        protected void callItemClick() {
            String link = getContext().getString(
                    webLink.getLinkResId()
            );
            WebUtils.openWebLink(
                    getContext(),
                    link
            );
            dismissBottomSheet();
        }

        @Override
        protected boolean getConditionToPrimaryIconVisibleState() {
            return true;
        }

    }

}
