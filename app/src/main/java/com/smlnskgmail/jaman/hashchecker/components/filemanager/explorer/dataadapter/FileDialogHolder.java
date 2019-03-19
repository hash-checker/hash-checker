package com.smlnskgmail.jaman.hashchecker.components.filemanager.explorer.dataadapter;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.filemanager.data.FileItem;
import com.smlnskgmail.jaman.hashchecker.components.filemanager.explorer.OnFileClickListener;

class FileDialogHolder extends RecyclerView.ViewHolder {

    private OnFileClickListener onFileClickListener;
    private TextView itemFile;

    FileDialogHolder(@NonNull View itemView, @NonNull OnFileClickListener onFileClickListener) {
        super(itemView);
        this.onFileClickListener = onFileClickListener;
        itemFile = itemView.findViewById(R.id.item_file);
    }

    void bind(@NonNull FileItem file) {
        itemFile.setText(file.getFileName());
        itemFile.setCompoundDrawablesRelativeWithIntrinsicBounds(ContextCompat
                .getDrawable(itemView.getContext(), file.getFileType().getIconResId()),
                null, null, null);
        itemView.setOnClickListener(v -> {
            onFileClickListener.onFileClick(file, getAdapterPosition());
        });
    }

}
