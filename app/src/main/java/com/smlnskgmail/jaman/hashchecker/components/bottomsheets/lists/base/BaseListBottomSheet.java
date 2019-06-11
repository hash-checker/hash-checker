package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.BaseBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseListBottomSheet extends BaseBottomSheet {

    private List<ListItemMarker> items = new ArrayList<>();

    @Override
    public void initUI(@NonNull View contentView) {
        RecyclerView bottomSheetItems = contentView.findViewById(R.id.rv_bottom_sheet_list_items);
        bottomSheetItems.setLayoutManager(new LinearLayoutManager(getContext()));
        bottomSheetItems.setAdapter(getItemsAdapter());
    }

    protected abstract BaseBottomSheetListAdapter getItemsAdapter();

    @NonNull
    protected List<ListItemMarker> getItems() {
        return items;
    }

    public void setItems(@NonNull List<ListItemMarker> listItemMarkers) {
        this.items.addAll(listItemMarkers);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.bottom_sheet_list_items;
    }

}
