package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.BaseItemsBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.ListItemMarker;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseBottomSheetItemsAdapter
        extends RecyclerView.Adapter<BaseBottomSheetItemsHolder> {

    private BaseItemsBottomSheet itemsBottomSheet;
    private List<ListItemMarker> listItems = new ArrayList<>();

    public BaseBottomSheetItemsAdapter(@NonNull List<ListItemMarker> listItems,
                                       @NonNull BaseItemsBottomSheet itemsBottomSheet) {
        this.listItems.addAll(listItems);
        this.itemsBottomSheet = itemsBottomSheet;
    }

    @NonNull
    @Override
    public BaseBottomSheetItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getItemsHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bottom_sheet_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseBottomSheetItemsHolder holder, int position) {
        holder.bind(listItems.get(position));
    }

    public abstract BaseBottomSheetItemsHolder getItemsHolder(@NonNull View view);

    @NonNull
    public BaseItemsBottomSheet getItemsBottomSheet() {
        return itemsBottomSheet;
    }

    @NonNull
    public List<ListItemMarker> getListItems() {
        return listItems;
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

}
