package com.smlnskgmail.jaman.hashchecker.components.fileexplorer.data;

import android.support.annotation.NonNull;

public class FileItem {

    private FileType fileType;
    private String filePath;
    private String fileName;

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
