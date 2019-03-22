package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.weblinks.adapter;

import android.support.annotation.NonNull;
import android.view.View;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetItemsAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetItemsHolder;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.weblinks.WebLinks;
import com.smlnskgmail.jaman.hashchecker.support.utils.AppUtils;

public class WebLinksBottomSheetItemsHolder extends BaseBottomSheetItemsHolder {

    WebLinksBottomSheetItemsHolder(@NonNull View itemView,
                                   @NonNull BaseBottomSheetItemsAdapter itemsAdapter) {
        super(itemView, itemsAdapter);
    }

    @Override
    protected void callItemClick() {
        WebLinks webLink = (WebLinks) getItemsAdapter().getListItems().get(getAdapterPosition());
        String linkToSite = getContext().getString(webLink.getLinkResId());
        AppUtils.openWebLink(getContext(), linkToSite);
        getItemsAdapter().getItemsBottomSheet().dismissAllowingStateLoss();
    }

    @Override
    protected boolean getConditionToPrimaryIconVisibleState() {
        return true;
    }

}
