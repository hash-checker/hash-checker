package com.smlnskgmail.jaman.hashchecker.logic.filemanager.manager.support;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.logic.filemanager.entities.FileItem;

public interface FileSelectTarget {

    void fileSelect(@NonNull FileItem fileItem, int position);

}
