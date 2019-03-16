package com.smlnskgmail.jaman.hashchecker.components.filemanager.selector;

import android.support.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.components.filemanager.data.FileItem;

public interface OnFileClickListener {

    void onFileClick(@NonNull FileItem fileItem, int position);

}
