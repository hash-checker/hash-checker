package com.smlnskgmail.jaman.hashchecker.fragments.functionality.history.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.fragments.functionality.history.data.HistoryItem;

import java.text.DateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryItemHolder extends RecyclerView.ViewHolder {

    private static final String DEFAULT_TITLE_PATTERN = "%s:";

    @BindView(R.id.iv_item_history_object_type_icon)
    protected ImageView ivHistoryItemObjectIcon;

    @BindView(R.id.tv_item_history_object_title)
    protected TextView tvHistoryItemObjectTitle;

    @BindView(R.id.tv_item_history_object_data)
    protected TextView tvHistoryItemObjectData;

    @BindView(R.id.tv_item_history_hash_type)
    protected TextView tvHistoryItemHashType;

    @BindView(R.id.tv_item_history_hash_data)
    protected TextView tvHistoryItemHashData;

    @BindView(R.id.tv_item_history_date_title)
    protected TextView tvHistoryItemDateTitle;

    @BindView(R.id.tv_item_history_date)
    protected TextView tvHistoryItemDate;

    HistoryItemHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(@NonNull HistoryItem historyItem) {
        Context context = itemView.getContext();
        initializeObjectData(context, historyItem);
        initializeHashType(context, historyItem);
        initializeDate(context, historyItem);
    }


    private void initializeObjectData(@NonNull Context context, @NonNull HistoryItem historyItem) {
        boolean isFile = historyItem.isFile();
        ivHistoryItemObjectIcon.setImageResource(isFile ? R.drawable.ic_file : R.drawable.ic_from_text);
        String objectTitle = String.format(DEFAULT_TITLE_PATTERN, context.getString(isFile
                ? R.string.common_file : R.string.common_text));
        tvHistoryItemObjectTitle.setText(objectTitle);
        tvHistoryItemObjectData.setText(historyItem.getObjectValue());
    }

    private void initializeHashType(@NonNull Context context, @NonNull HistoryItem historyItem) {
        String hashType = String.format(DEFAULT_TITLE_PATTERN, context.getString(historyItem.getHashType()
                .getTitleTextResId()));
        tvHistoryItemHashType.setText(hashType);
        tvHistoryItemHashData.setText(historyItem.getHashValue());
    }

    private void initializeDate(@NonNull Context context, @NonNull HistoryItem historyItem) {
        String dateTitle = String.format(DEFAULT_TITLE_PATTERN, context.getString(R.string.common_date));
        tvHistoryItemDateTitle.setText(dateTitle);
        tvHistoryItemDate.setText(DateFormat.getDateInstance().format(historyItem.getGenerationDate()));
    }

}
