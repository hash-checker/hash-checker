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

public abstract class BaseBottomSheetItemsAdapter extends RecyclerView.Adapter<BaseBottomSheetItemsHolder> {

    private BaseItemsBottomSheet baseItemsBottomSheet;
    private List<ListItemMarker> listItemMarkers = new ArrayList<>();

    public BaseBottomSheetItemsAdapter(@NonNull List<ListItemMarker> bottomSheetItems,
                                       @NonNull BaseItemsBottomSheet baseItemsBottomSheet) {
        this.listItemMarkers.addAll(bottomSheetItems);
        this.baseItemsBottomSheet = baseItemsBottomSheet;
    }

    @NonNull
    @Override
    public BaseBottomSheetItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getItemsHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bottom_sheet_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseBottomSheetItemsHolder holder, int position) {
        holder.bind(listItemMarkers.get(position));
    }

    public abstract BaseBottomSheetItemsHolder getItemsHolder(@NonNull View view);

    @NonNull
    public BaseItemsBottomSheet getBaseItemsBottomSheet() {
        return baseItemsBottomSheet;
    }

    @NonNull
    public List<ListItemMarker> getListItemMarkers() {
        return listItemMarkers;
    }

    @Override
    public int getItemCount() {
        return listItemMarkers.size();
    }

}
