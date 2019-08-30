package com.smlnskgmail.jaman.hashchecker.db.utils;

import android.content.Context;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.db.HelperFactory;
import com.smlnskgmail.jaman.hashchecker.db.helper.DatabaseHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DatabaseExporter {

    public static final String EXPORT_FILE = "hash_checker_user_data.zip";

    public static void exportDatabase(@NonNull Context context) throws IOException {
        HelperFactory.getHelper().checkpoint();
        String appFolder = getAppFolder(context);
        String databasePath = appFolder + DatabaseHelper.DATABASE_FOLDER + DatabaseHelper.DATABASE_NAME;
        createZip(context, new File(databasePath));
    }

    private static void createZip(@NonNull Context context, @NonNull File database) throws IOException {
        FileInputStream inputStream = new FileInputStream(database);
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(getUserDataZip(context)));
        zipOutputStream.putNextEntry(new ZipEntry(DatabaseHelper.DATABASE_NAME));

        byte[] buffer = new byte[1024];
        int count;
        while ((count = inputStream.read(buffer)) > 0) {
            zipOutputStream.write(buffer, 0, count);
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
