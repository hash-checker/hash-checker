package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.BaseBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetItemsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public abstract class BaseItemsBottomSheet extends BaseBottomSheet {

    @BindView(R.id.bottom_sheet_items)
    protected RecyclerView bottomSheetItems;

    private List<ListItemMarker> listItems = new ArrayList<>();

    @Override
    public void initUI() {
        bottomSheetItems.setLayoutManager(new LinearLayoutManager(getContext()));
        bottomSheetItems.setAdapter(getItemsAdapter());
    }

    public abstract BaseBottomSheetItemsAdapter getItemsAdapter();

    @NonNull
    public List<ListItemMarker> getListItems() {
        return listItems;
    }

    public void setListItems(List<ListItemMarker> listItemMarkers) {
        this.listItems.addAll(listItemMarkers);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.bottom_sheet_list_items;
    }

}
