package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.weblinks.adapter;

import android.support.annotation.NonNull;
import android.view.View;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetItemsAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetItemsHolder;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.weblinks.WebLinks;
import com.smlnskgmail.jaman.hashchecker.support.utils.AppUtils;

public class WebLinksBottomSheetItemsHolder extends BaseBottomSheetItemsHolder {

    WebLinksBottomSheetItemsHolder(@NonNull View itemView,
                                   @NonNull BaseBottomSheetItemsAdapter baseBottomSheetItemsAdapter) {
        super(itemView, baseBottomSheetItemsAdapter);
    }

    @Override
    protected void callItemClick() {
        WebLinks webLink = (WebLinks) getBaseBottomSheetItemsAdapter()
                .getListItemMarkers().get(getAdapterPosition());
        String linkToSite = getContext().getString(webLink.getLinkResId());
        AppUtils.openWebLink(getContext(), linkToSite);
        getBaseBottomSheetItemsAdapter().getBaseItemsBottomSheet().dismissAllowingStateLoss();
    }

    @Override
    protected boolean getConditionToPrimaryIconVisibleState() {
        return true;
    }

}
