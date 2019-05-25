package com.smlnskgmail.jaman.hashchecker.fragments.functionality.history.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.fragments.functionality.history.data.HistoryItem;

public class HistoryItemHolder extends RecyclerView.ViewHolder {

    private ImageView ivHistoryItemObjectIcon;
    private TextView tvHistoryItemHashValue;

    HistoryItemHolder(@NonNull View itemView) {
        super(itemView);
        ivHistoryItemObjectIcon = itemView.findViewById(R.id.iv_item_history_object_type_icon);
        tvHistoryItemHashValue = itemView.findViewById(R.id.tv_item_history_hash_value);
    }

    public void bind(@NonNull HistoryItem historyItem) {
        ivHistoryItemObjectIcon.setImageResource(historyItem.isFile()
                ? R.drawable.ic_file : R.drawable.ic_from_text);
        tvHistoryItemHashValue.setText(historyItem.getHashValue());
    }

}
