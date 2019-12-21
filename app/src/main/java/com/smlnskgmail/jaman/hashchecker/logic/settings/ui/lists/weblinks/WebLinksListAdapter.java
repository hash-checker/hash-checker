package com.smlnskgmail.jaman.hashchecker.logic.settings.ui.lists.weblinks;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.ListItemTarget;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseListAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseListHolder;
import com.smlnskgmail.jaman.hashchecker.tools.WebTools;

import java.util.List;

public class WebLinksListAdapter extends BaseListAdapter {

    WebLinksListAdapter(
            @NonNull List<ListItemTarget> items,
            @NonNull BaseListBottomSheet bottomSheet
    ) {
        super(items, bottomSheet);
    }

    @Override
    public BaseListHolder getItemsHolder(@NonNull View view, @NonNull Context themeContext) {
        return new WebLinksListHolder(view, themeContext);
    }

    private class WebLinksListHolder extends BaseListHolder {

        WebLinksListHolder(@NonNull View itemView, @NonNull Context themeContext) {
            super(itemView, themeContext);
        }

        @Override
        protected void callItemClick() {
            WebLink webLink = (WebLink) getItems().get(getAdapterPosition());
            String link = getContext().getString(webLink.getLinkResId());
            WebTools.openWebLink(getContext(), link);
            dismissBottomSheet();
        }

        @Override
        protected boolean getConditionToPrimaryIconVisibleState() {
            return true;
        }

    }

}
