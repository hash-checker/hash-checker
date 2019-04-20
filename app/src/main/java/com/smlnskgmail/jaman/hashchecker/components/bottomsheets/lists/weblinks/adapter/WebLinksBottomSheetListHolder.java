package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.weblinks.adapter;

import android.support.annotation.NonNull;
import android.view.View;

import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListHolder;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.weblinks.WebLinks;
import com.smlnskgmail.jaman.hashchecker.support.utils.AppUtils;

public class WebLinksBottomSheetListHolder extends BaseBottomSheetListHolder {

    WebLinksBottomSheetListHolder(@NonNull View itemView, @NonNull BaseBottomSheetListAdapter adapter) {
        super(itemView, adapter);
    }

    @Override
    protected void callItemClick() {
        WebLinks webLink = (WebLinks) getListAdapter().getItems().get(getAdapterPosition());
        String linkToSite = getContext().getString(webLink.getLinkResId());
        AppUtils.openWebLink(getContext(), linkToSite);
        getListAdapter().getBottomSheet().dismissAllowingStateLoss();
    }

    @Override
    protected boolean getConditionToPrimaryIconVisibleState() {
        return true;
    }

}
