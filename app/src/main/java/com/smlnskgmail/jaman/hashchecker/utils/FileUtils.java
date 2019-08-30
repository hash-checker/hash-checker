package com.smlnskgmail.jaman.hashchecker.utils;

import android.os.Environment;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.components.filemanager.entities.FileItem;
import com.smlnskgmail.jaman.hashchecker.components.filemanager.entities.FileType;
import com.smlnskgmail.jaman.hashchecker.support.logs.L;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class FileUtils {

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
        if(lastDotIndex != -1) {
            return fileName.substring(lastDotIndex);
        } else {
            return "";
        }
    }

    public static List<FileItem> getExternalMounts() {
        List<FileItem> storages = new ArrayList<>();
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        String name = Environment.getExternalStorageDirectory().getName();
        storages.add(new FileItem(FileType.STORAGE, path, name));
        try {
            String reg = ".*vold.*(vfat|ntfs|exfat|fat32|ext3|ext4).*rw.*";
            StringBuilder sb = new StringBuilder();
            try {
                Process process = new ProcessBuilder().command("mount")
                        .redirectErrorStream(true).start();
                process.waitFor();
                InputStream is = process.getInputStream();
                byte[] buffer = new byte[1024];
                while (is.read(buffer) != -1) {
                    sb.append(new String(buffer));
                }
                is.close();
            } catch (Exception e) {
                L.e(e);
                return storages;
            }

            String[] lines = sb.toString().split("\n");
            for (String line: lines) {
                if (!line.toLowerCase(Locale.ENGLISH).contains("asec")) {
                    if (line.matches(reg)) {
                        String[] parts = line.split(" ");
                        for (String part: parts) {
                            if (part.startsWith("/"))
                                if (!part.toLowerCase(Locale.ENGLISH).contains("vold")) {
                                    String storageName;
                                    int counter = 0;
                                    StringBuilder tempBuilder = new StringBuilder();
                                    for (int i = part.length() - 1; i >= 0; i--) {
                                        if (counter == 0) {
                                            if (part.charAt(i) == '/') counter = 1;
                                            else tempBuilder.append(part.charAt(i));
                                        }
                                    }
                                    storageName = tempBuilder.toString();
                                    tempBuilder.delete(0, tempBuilder.length());
                                    for (int i = storageName.length() - 1; i >= 0; i--) {
                                        tempBuilder.append(storageName.charAt(i));
                                    }
                                    storageName = tempBuilder.toString();
                                    boolean equalState = false;
                                    for (FileItem storage: storages) {
                                        if (("/storage/" + storageName)
                                                .equals(storage.getFileName())) {
                                            equalState = true;
                                        }
                                    }
                                    if (!equalState) {
                                        String storagePath = "/storage/" + storageName;
                                        if (new File(storagePath).isDirectory()) {
                                            storages.add(new FileItem(FileType.STORAGE, storagePath,
                                                    storageName));
                                        }
                                    }
                                }
                        }
                    }
                }
            }
        } catch (Exception e) {
            L.e(e);
            return storages;
        }
        return storages;
    }

}
