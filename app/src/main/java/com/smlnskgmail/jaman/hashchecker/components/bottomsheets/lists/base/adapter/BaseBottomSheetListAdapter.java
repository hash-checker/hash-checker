package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.ListMarker;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseBottomSheetListAdapter extends RecyclerView.Adapter<BaseBottomSheetListHolder> {

    private final BaseListBottomSheet bottomSheet;
    private final List<ListMarker> items = new ArrayList<>();

    protected BaseBottomSheetListAdapter(@NonNull List<ListMarker> items,
                                         @NonNull BaseListBottomSheet bottomSheet) {
        this.items.addAll(items);
        this.bottomSheet = bottomSheet;
    }

    @NonNull
    @Override
    public BaseBottomSheetListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getItemsHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false),
                getBottomSheet().getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull BaseBottomSheetListHolder holder, int position) {
        holder.bind(items.get(position));
    }

    protected abstract BaseBottomSheetListHolder getItemsHolder(@NonNull View view,
                                                                @NonNull Context themeContext);

    @NonNull
    public BaseListBottomSheet getBottomSheet() {
        return bottomSheet;
    }

    @NonNull
    public List<ListMarker> getItems() {
        return items;
    }

    public void dismissBottomSheet() {
        bottomSheet.dismiss();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
