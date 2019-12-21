package com.smlnskgmail.jaman.hashchecker.logic.filemanager.ui.support;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.logic.filemanager.FileItem;

public interface FileSelectTarget {

    void fileSelect(@NonNull FileItem fileItem, int position);

}
