package com.smlnskgmail.jaman.hashchecker.navigation.history.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.navigation.history.data.HistoryItem;

import java.util.ArrayList;
import java.util.List;

public class HistoryItemsAdapter extends RecyclerView.Adapter<HistoryItemHolder> {

    private View rootView;

    private List<HistoryItem> historyItems = new ArrayList<>();

    public HistoryItemsAdapter(@NonNull View rootView) {
        this.rootView = rootView;
    }

    public void addHistoryItems(List<HistoryItem> historyItems) {
        this.historyItems.addAll(historyItems);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryItemHolder historyItemHolder, int position) {
        historyItemHolder.bind(historyItems.get(position));
    }

    @NonNull
    @Override
    public HistoryItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new HistoryItemHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_history_data, viewGroup, false), rootView);
    }

    @Override
    public int getItemCount() {
        return historyItems.size();
    }

}
