package com.smlnskgmail.jaman.hashchecker.logic.filemanager;

import androidx.annotation.NonNull;

import java.util.Objects;

public class FileItem {

    private final FileType fileType;
    private final String filePath;
    private final String fileName;

    public FileItem(
            @NonNull FileType fileType,
            @NonNull String filePath,
            @NonNull String fileName
    ) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileItem fileItem = (FileItem) o;
        return fileType == fileItem.fileType
                && Objects.equals(filePath, fileItem.filePath)
                && Objects.equals(fileName, fileItem.fileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileType, filePath, fileName);
    }

}
