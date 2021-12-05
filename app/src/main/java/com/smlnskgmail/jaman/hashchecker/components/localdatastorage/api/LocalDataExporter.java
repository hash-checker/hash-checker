package com.smlnskgmail.jaman.hashchecker.components.localdatastorage.api;

import android.content.Context;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class LocalDataExporter {

    public static final String EXPORT_FILE = "hash_checker_user_data.zip";

    public static void exportDatabase(
            @NonNull Context context,
            @NonNull LocalDataStorage localDataStorage
    ) throws IOException {
        localDataStorage.backupCheckpoint();
        String appFolder = getAppFolder(context);

        String databaseFolder = localDataStorage.getDatabaseFolder();
        String databaseName = localDataStorage.getDatabaseFileName();
        String databasePath = appFolder + databaseFolder + databaseName;
        createZip(context, databaseName, new File(databasePath));
    }

    private static void createZip(
            @NonNull Context context,
            @NonNull String databaseName,
            @NonNull File database
    ) throws IOException {
        FileInputStream inputStream = new FileInputStream(database);
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(getUserDataZip(context)));
        zipOutputStream.putNextEntry(new ZipEntry(databaseName));

        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        int count;
        while ((count = inputStream.read(buffer)) > 0) {
            zipOutputStream.write(buffer, 0, count);
        }
        zipOutputStream.close();
        inputStream.close();
    }

    @NonNull
    public static String getUserDataZip(@NonNull Context context) {
        return getAppFolder(context) + EXPORT_FILE;
    }

    @NonNull
    private static String getAppFolder(@NonNull Context context) {
        return context.getApplicationInfo().dataDir + File.separator;
    }

}
