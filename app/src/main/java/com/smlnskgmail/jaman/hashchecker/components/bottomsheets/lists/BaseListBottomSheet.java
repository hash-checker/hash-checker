package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.BaseBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseListAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseListBottomSheet extends BaseBottomSheet {

    private final List<ListItemTarget> items = new ArrayList<>();

    @Override
    public void viewCreated(@NonNull View contentView) {
        RecyclerView bottomSheetItems = contentView.findViewById(R.id.rv_bottom_sheet_list_items);
        bottomSheetItems.setLayoutManager(new LinearLayoutManager(getContext()));
        bottomSheetItems.setAdapter(getItemsAdapter());
    }

    protected abstract BaseListAdapter getItemsAdapter();

    @NonNull
    protected List<ListItemTarget> getItems() {
        return items;
    }

    public void setList(@NonNull List<ListItemTarget> listItemTargets) {
        this.items.addAll(listItemTargets);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.bottom_sheet_list_items;
    }

}
