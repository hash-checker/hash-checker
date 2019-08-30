package com.smlnskgmail.jaman.hashchecker.components.filemanager.entities;

import androidx.annotation.NonNull;

public class FileItem {

    private final FileType fileType;
    private final String filePath;
    private final String fileName;

    public FileItem(@NonNull FileType fileType, @NonNull String filePath, @NonNull String fileName) {
        this.fileType = fileType;
        this.filePath = filePath;
        this.fileName = fileName;
    }

    @NonNull
    public FileType getFileType() {
        return fileType;
    }

    @NonNull
    public String getFilePath() {
        return filePath;
    }

    @NonNull
    public String getFileName() {
        return fileName;
    }

}
