package com.smlnskgmail.jaman.hashchecker.logic.filemanager.ui.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.logic.filemanager.FileItem;
import com.smlnskgmail.jaman.hashchecker.logic.filemanager.ui.FileSelectTarget;

import java.util.List;

public class FileItemsAdapter extends RecyclerView.Adapter<FileDialogHolder> {

    private final FileSelectTarget fileClickListener;
    private final List<FileItem> files;

    public FileItemsAdapter(
            @NonNull List<FileItem> files,
            @NonNull FileSelectTarget fileClickListener
    ) {
        this.files = files;
        this.fileClickListener = fileClickListener;
    }

    @NonNull
    @Override
    public FileDialogHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {
        return new FileDialogHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_file,
                        parent,
                        false
                ),
                fileClickListener
        );
    }

    @Override
    public void onBindViewHolder(
            @NonNull final FileDialogHolder holder,
            int position
    ) {
        holder.bind(files.get(position));
    }

    @Override
    public int getItemCount() {
        return files.size();
    }

}
