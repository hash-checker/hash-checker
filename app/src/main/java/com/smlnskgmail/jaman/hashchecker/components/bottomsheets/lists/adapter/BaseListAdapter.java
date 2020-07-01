package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.ListItem;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseListAdapter<T extends ListItem>
        extends RecyclerView.Adapter<BaseListHolder<T>> {

    private final BaseListBottomSheet bottomSheet;
    private final List<T> items = new ArrayList<>();

    protected BaseListAdapter(
            @NonNull List<T> items,
            @NonNull BaseListBottomSheet bottomSheet
    ) {
        this.items.addAll(items);
        this.bottomSheet = bottomSheet;
    }

    @NonNull
    @Override
    public BaseListHolder<T> onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {
        return getItemsHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_list,
                        parent,
                        false
                ),
                getBottomSheet().getContext()
        );
    }

    @Override
    public void onBindViewHolder(
            @NonNull BaseListHolder<T> holder,
            int position
    ) {
        holder.bind(items.get(position));
    }

    protected abstract BaseListHolder<T> getItemsHolder(
            @NonNull View view,
            @NonNull Context themeContext
    );

    @NonNull
    public BaseListBottomSheet getBottomSheet() {
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
