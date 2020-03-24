package com.smlnskgmail.jaman.hashchecker.logic.filemanager;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.List;
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
        return Objects.hash(
                fileType,
                filePath,
                fileName
        );
    }

    public interface Extensions {

        List<String> VIDEO_EXTENSIONS = Arrays.asList(
                ".3gp",
                ".mp4",
                ".mkv",
                ".webm"
        );

        List<String> IMAGE_EXTENSIONS = Arrays.asList(
                ".bmp",
                ".gif",
                ".jpg",
                ".png",
                ".webp",
                ".heic",
                ".heif"
        );

        List<String> SOUND_EXTENSIONS = Arrays.asList(
                ".m4a",
                ".aac",
                ".tc",
                ".flac",
                ".gsm",
                ".mid",
                ".xmf",
                ".mxmf",
                ".rtttl",
                ".rtx",
                ".ota",
                ".imy",
                ".mp3",
                ".wav",
                ".ogg"
        );

        static boolean isVideo(@NonNull String fileName) {
            return VIDEO_EXTENSIONS.contains(
                    getFileExtension(fileName)
            );
        }

        static boolean isImage(@NonNull String fileName) {
            return IMAGE_EXTENSIONS.contains(
                    getFileExtension(fileName)
            );
        }

        static boolean isSound(@NonNull String fileName) {
            return SOUND_EXTENSIONS.contains(
                    getFileExtension(fileName)
            );
        }

        static String getFileExtension(@NonNull String fileName) {
            int lastDotIndex = fileName.lastIndexOf('.');
            if (lastDotIndex != -1) {
                return fileName.substring(lastDotIndex);
            } else {
                return "";
            }
        }

    }

}
