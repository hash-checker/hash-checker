package com.smlnskgmail.jaman.hashchecker.components.fileexplorer.explorer;

import android.support.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.components.fileexplorer.entities.FileItem;

public interface OnFileClickListener {

    void onFileClick(@NonNull FileItem fileItem, int position);

}
