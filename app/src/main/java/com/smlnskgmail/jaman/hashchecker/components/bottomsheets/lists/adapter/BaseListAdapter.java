package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.ListItemTarget;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseListAdapter extends RecyclerView.Adapter<BaseListHolder> {

    private final BaseListBottomSheet bottomSheet;
    private final List<ListItemTarget> items = new ArrayList<>();

    protected BaseListAdapter(@NonNull List<ListItemTarget> items, @NonNull BaseListBottomSheet bottomSheet) {
        this.items.addAll(items);
        this.bottomSheet = bottomSheet;
    }

    @NonNull
    @Override
    public BaseListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getItemsHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false), getBottomSheet().getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull BaseListHolder holder, int position) {
        holder.bind(items.get(position));
    }

    protected abstract BaseListHolder getItemsHolder(@NonNull View view, @NonNull Context themeContext);

    @NonNull
    protected BaseListBottomSheet getBottomSheet() {
        return bottomSheet;
    }

    @NonNull
    protected List<ListItemTarget> getItems() {
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
