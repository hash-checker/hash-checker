package com.smlnskgmail.jaman.hashchecker.logic.filemanager.ui.support;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileExtensions {

    private static final List<String> VIDEO_EXTENSIONS = new ArrayList<>(Arrays.asList(
            ".3gp",
            ".mp4",
            ".mkv",
            ".webm"
    ));

    private static final List<String> IMAGE_EXTENSIONS = new ArrayList<>(Arrays.asList(
            ".bmp",
            ".gif",
            ".jpg",
            ".png",
            ".webp",
            ".heic",
            ".heif"
    ));

    private static final List<String> SOUND_EXTENSIONS = new ArrayList<>(Arrays.asList(
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
    ));

    public static boolean isVideo(@NonNull String fileName) {
        return VIDEO_EXTENSIONS.contains(getFileExtension(fileName));
    }

    public static boolean isImage(@NonNull String fileName) {
        return IMAGE_EXTENSIONS.contains(getFileExtension(fileName));
    }

    public static boolean isSound(@NonNull String fileName) {
        return SOUND_EXTENSIONS.contains(getFileExtension(fileName));
    }

    private static String getFileExtension(@NonNull String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex != -1) {
            return fileName.substring(lastDotIndex);
        } else {
            return "";
        }
    }

}
