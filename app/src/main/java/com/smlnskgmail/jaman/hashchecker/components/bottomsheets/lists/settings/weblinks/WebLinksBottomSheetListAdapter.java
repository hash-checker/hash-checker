package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.settings.weblinks;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.ListItemMarker;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListHolder;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.settings.weblinks.WebLink;
import com.smlnskgmail.jaman.hashchecker.utils.AppUtils;

import java.util.List;

public class WebLinksBottomSheetListAdapter extends BaseBottomSheetListAdapter {

    WebLinksBottomSheetListAdapter(@NonNull List<ListItemMarker> items,
                                   @NonNull BaseListBottomSheet bottomSheet) {
        super(items, bottomSheet);
    }

    @Override
    public BaseBottomSheetListHolder getItemsHolder(@NonNull View view, @NonNull Context themeContext) {
        return new WebLinksBottomSheetListHolder(view, themeContext);
    }

    private class WebLinksBottomSheetListHolder extends BaseBottomSheetListHolder {

        WebLinksBottomSheetListHolder(@NonNull View itemView, @NonNull Context themeContext) {
            super(itemView, themeContext);
        }

        @Override
        protected void callItemClick() {
            WebLink webLink = (WebLink) getItems().get(getAdapterPosition());
            String linkToSite = getContext().getString(webLink.getLinkResId());
            AppUtils.openWebLink(getContext(), linkToSite);
            dismissBottomSheet();
        }

        @Override
        protected boolean getConditionToPrimaryIconVisibleState() {
            return true;
        }

    }

}
