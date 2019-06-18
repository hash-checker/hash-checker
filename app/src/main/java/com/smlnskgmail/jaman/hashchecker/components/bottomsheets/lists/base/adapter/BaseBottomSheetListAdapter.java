package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.ListItemMarker;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseBottomSheetListAdapter extends RecyclerView.Adapter<BaseBottomSheetListHolder> {

    private BaseListBottomSheet bottomSheet;
    private List<ListItemMarker> items = new ArrayList<>();

    protected BaseBottomSheetListAdapter(@NonNull List<ListItemMarker> items,
                                         @NonNull BaseListBottomSheet bottomSheet) {
        this.items.addAll(items);
        this.bottomSheet = bottomSheet;
    }

    @NonNull
    @Override
    public BaseBottomSheetListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getItemsHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bottom_sheet_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseBottomSheetListHolder holder, int position) {
        holder.bind(items.get(position));
    }

    protected abstract BaseBottomSheetListHolder getItemsHolder(@NonNull View view);

    @NonNull
    public BaseListBottomSheet getBottomSheet() {
        return bottomSheet;
    }

    @NonNull
    public List<ListItemMarker> getItems() {
        return items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
