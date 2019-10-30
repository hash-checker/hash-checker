package com.smlnskgmail.jaman.hashchecker.logic.settings.lists.weblinks;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.ListMarker;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseBottomSheetListAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseBottomSheetListHolder;
import com.smlnskgmail.jaman.hashchecker.utils.AppUtils;

import java.util.List;

public class WebLinksBottomSheetListAdapter extends BaseBottomSheetListAdapter {

    WebLinksBottomSheetListAdapter(@NonNull List<ListMarker> items, @NonNull BaseListBottomSheet bottomSheet) {
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
            String link = getContext().getString(webLink.getLinkResId());
            AppUtils.openWebLink(getContext(), link);
            dismissBottomSheet();
        }

        @Override
        protected boolean getConditionToPrimaryIconVisibleState() {
            return true;
        }

    }

}
