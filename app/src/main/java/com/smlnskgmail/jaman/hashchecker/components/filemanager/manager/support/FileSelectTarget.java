package com.smlnskgmail.jaman.hashchecker.components.filemanager.manager.support;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.components.filemanager.entities.FileItem;

public interface FileSelectTarget {

    void onFileSelect(@NonNull FileItem fileItem, int position);

}
