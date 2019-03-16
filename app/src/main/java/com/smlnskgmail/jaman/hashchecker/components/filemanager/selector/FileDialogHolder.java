package com.smlnskgmail.jaman.hashchecker.components.filemanager.selector;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.filemanager.data.FileItem;

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
        int iconResId;
        switch (file.getFileType()) {
            case VIDEO:
                iconResId = R.drawable.ic_video_label;
                break;
            case IMAGE:
                iconResId = R.drawable.ic_image;
                break;
            case MUSIC:
                iconResId = R.drawable.ic_music_note;
                break;
            case FOLDER:
                iconResId = R.drawable.ic_folder;
                break;
            case BACK_FOLDER:
                iconResId = R.drawable.ic_arrow_back;
                break;
            case STORAGE:
                iconResId = R.drawable.ic_settings_file_manager;
                break;
            default:
                iconResId = R.drawable.ic_file;
                break;
        }
        itemFile.setCompoundDrawablesRelativeWithIntrinsicBounds(ContextCompat
                .getDrawable(itemView.getContext(), iconResId), null, null, null);
        itemView.setOnClickListener(v -> {
            onFileClickListener.onFileClick(file, getAdapterPosition());
        });
    }

}
