package com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.ListItem;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseListAdapter<T extends ListItem>
        extends RecyclerView.Adapter<BaseListHolder<T>> {

    private final BaseListBottomSheet<T> bottomSheet;
    private final List<T> items = new ArrayList<>();

    protected BaseListAdapter(@NonNull List<T> items, @NonNull BaseListBottomSheet<T> bottomSheet) {
        this.items.addAll(items);
        this.bottomSheet = bottomSheet;
    }

    @NonNull
    @Override
    public BaseListHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getItemsHolder(
                parent.getContext(),
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_list,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull BaseListHolder<T> holder, int position) {
        holder.bind(items.get(position));
    }

    @NonNull
    protected abstract BaseListHolder<T> getItemsHolder(@NonNull Context themeContext, @NonNull View view);

    @NonNull
    public BaseListBottomSheet<T> getBottomSheet() {
        return bottomSheet;
    }

    public void dismissBottomSheet() {
        bottomSheet.dismiss();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
