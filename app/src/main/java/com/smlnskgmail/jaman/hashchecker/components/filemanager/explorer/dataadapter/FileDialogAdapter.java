package com.smlnskgmail.jaman.hashchecker.components.filemanager.explorer.dataadapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.filemanager.data.FileItem;
import com.smlnskgmail.jaman.hashchecker.components.filemanager.explorer.OnFileClickListener;

import java.util.List;

public class FileDialogAdapter extends RecyclerView.Adapter<FileDialogHolder> {

    private OnFileClickListener onFileClickListener;
    private List<FileItem> fileItems;

    public FileDialogAdapter(@NonNull List<FileItem> fileElements,
                             @NonNull OnFileClickListener onFileClickListener) {
        this.fileItems = fileElements;
        this.onFileClickListener = onFileClickListener;
    }

    @NonNull
    @Override
    public FileDialogHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FileDialogHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_file, parent, false), onFileClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final FileDialogHolder holder, int position) {
        holder.bind(fileItems.get(position));
    }

    @Override
    public int getItemCount() {
        return fileItems.size();
    }

}
