package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.BaseBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseListBottomSheet extends BaseBottomSheet {

    private List<ListMarker> items = new ArrayList<>();

    @Override
    public void initUI(@NonNull View contentView) {
        RecyclerView bottomSheetItems = contentView.findViewById(R.id.rv_bottom_sheet_list_items);
        bottomSheetItems.setLayoutManager(new LinearLayoutManager(getContext()));
        bottomSheetItems.setAdapter(getItemsAdapter());
    }

    protected abstract BaseBottomSheetListAdapter getItemsAdapter();

    @NonNull
    protected List<ListMarker> getItems() {
        return items;
    }

    public void setItems(@NonNull List<ListMarker> listMarkers) {
        this.items.addAll(listMarkers);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.bottom_sheet_list_items;
    }

}
