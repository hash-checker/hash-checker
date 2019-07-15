package com.smlnskgmail.jaman.hashchecker.components.fileexplorer.explorer.filesadapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.fileexplorer.entities.FileItem;
import com.smlnskgmail.jaman.hashchecker.components.fileexplorer.explorer.OnFileClickListener;

import java.util.List;

public class FileItemsAdapter extends RecyclerView.Adapter<FileDialogHolder> {

    private OnFileClickListener fileClickListener;
    private List<FileItem> files;

    public FileItemsAdapter(@NonNull List<FileItem> files,
                            @NonNull OnFileClickListener fileClickListener) {
        this.files = files;
        this.fileClickListener = fileClickListener;
    }

    @NonNull
    @Override
    public FileDialogHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FileDialogHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_file, parent, false), fileClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final FileDialogHolder holder, int position) {
        holder.bind(files.get(position));
    }

    @Override
    public int getItemCount() {
        return files.size();
    }

}
