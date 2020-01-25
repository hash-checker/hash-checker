package com.smlnskgmail.jaman.hashchecker.logic.history.ui.listadapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.dialogs.system.AppSnackbar;
import com.smlnskgmail.jaman.hashchecker.components.vibrator.Vibrator;
import com.smlnskgmail.jaman.hashchecker.logic.history.HistoryItem;
import com.smlnskgmail.jaman.hashchecker.tools.UITools;

import java.text.DateFormat;

class HistoryItemHolder extends RecyclerView.ViewHolder {

    private static final String DEFAULT_TITLE_PATTERN = "%s:";

    private final ImageView ivHistoryItemObjectIcon;

    private final TextView tvHistoryItemObjectTitle;
    private final TextView tvHistoryItemObjectData;
    private final TextView tvHistoryItemHashType;
    private final TextView tvHistoryItemHashData;
    private final TextView tvHistoryItemDateTitle;
    private final TextView tvHistoryItemDate;

    private final View rootView;

    HistoryItemHolder(@NonNull View itemView, @NonNull View rootView) {
        super(itemView);
        this.rootView = rootView;
        ivHistoryItemObjectIcon = itemView.findViewById(R.id.iv_item_history_object_type_icon);
        tvHistoryItemObjectTitle = itemView.findViewById(R.id.tv_item_history_object_title);
        tvHistoryItemObjectData = itemView.findViewById(R.id.tv_item_history_object_data);
        tvHistoryItemHashType = itemView.findViewById(R.id.tv_item_history_hash_type);
        tvHistoryItemHashData = itemView.findViewById(R.id.tv_item_history_hash_data);
        tvHistoryItemDateTitle = itemView.findViewById(R.id.tv_item_history_date_title);
        tvHistoryItemDate = itemView.findViewById(R.id.tv_item_history_date);
    }

    void bind(@NonNull HistoryItem historyItem) {
        Context context = itemView.getContext();
        initializeObjectData(context, historyItem);
        initializeHashType(context, historyItem);
        initializeDate(context, historyItem);
        itemView.setOnClickListener(v -> new AppSnackbar(
                context,
                rootView,
                context.getString(R.string.history_item_click_text),
                context.getString(R.string.common_ok),
                v1 -> copyTextToClipboard(context, historyItem.getHashValue()),
                new Vibrator(context),
                UITools.getAccentColor(context)
        ).show());
    }

    private void copyTextToClipboard(@NonNull Context context, @NonNull String text) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(
                context.getString(R.string.common_app_name),
                text
        );
        if (clipboard != null) {
            clipboard.setPrimaryClip(clip);
        }
    }

    private void initializeObjectData(@NonNull Context context, @NonNull HistoryItem historyItem) {
        boolean isFile = historyItem.isFile();
        ivHistoryItemObjectIcon.setImageResource(isFile ? R.drawable.ic_file : R.drawable.ic_from_text);
        String objectTitle = String.format(
                DEFAULT_TITLE_PATTERN,
                context.getString(isFile ? R.string.common_file : R.string.common_text)
        );
        tvHistoryItemObjectTitle.setText(objectTitle);
        tvHistoryItemObjectData.setText(historyItem.getObjectValue());
    }

    private void initializeHashType(@NonNull Context context, @NonNull HistoryItem historyItem) {
        String hashType = String.format(
                DEFAULT_TITLE_PATTERN,
                historyItem.getHashType().getTitle(context)
        );
        tvHistoryItemHashType.setText(hashType);
        tvHistoryItemHashData.setText(historyItem.getHashValue());
    }

    private void initializeDate(@NonNull Context context, @NonNull HistoryItem historyItem) {
        String dateTitle = String.format(
                DEFAULT_TITLE_PATTERN,
                context.getString(R.string.common_date)
        );
        tvHistoryItemDateTitle.setText(dateTitle);
        tvHistoryItemDate.setText(
                DateFormat.getDateInstance().format(historyItem.getGenerationDate())
        );
    }

}
