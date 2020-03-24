package com.smlnskgmail.jaman.hashchecker.logic.database;

import android.content.Context;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DatabaseExporter {

    public static final String EXPORT_FILE = "hash_checker_user_data.zip";

    public static void exportDatabase(
            @NonNull Context context
    ) throws IOException {
        HelperFactory.getHelper().backupCheckpoint();
        String appFolder = getAppFolder(context);

        String databaseFolder = HelperFactory.getHelper().getDatabaseFolder();
        String databaseName = HelperFactory.getHelper().getDatabaseFileName();
        String databasePath = appFolder + databaseFolder + databaseName;
        createZip(
                context,
                databaseName,
                new File(databasePath)
        );
    }

    private static void createZip(
            @NonNull Context context,
            @NonNull String databaseName,
            @NonNull File database
    ) throws IOException {
        FileInputStream inputStream = new FileInputStream(database);
        ZipOutputStream zipOutputStream = new ZipOutputStream(
                new FileOutputStream(
                        getUserDataZip(context)
                )
        );
        zipOutputStream.putNextEntry(
                new ZipEntry(databaseName)
        );

        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        int count;
        while ((count = inputStream.read(buffer)) > 0) {
            zipOutputStream.write(
                    buffer,
                    0,
                    count
            );
        }
        zipOutputStream.close();
        inputStream.close();
    }

    public static String getUserDataZip(@NonNull Context context) {
        return getAppFolder(context) + EXPORT_FILE;
    }

    private static String getAppFolder(@NonNull Context context) {
        return context.getApplicationInfo().dataDir + File.separator;
    }

}
