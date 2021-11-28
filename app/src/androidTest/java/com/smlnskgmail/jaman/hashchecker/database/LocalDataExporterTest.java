package com.smlnskgmail.jaman.hashchecker.database;

import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.test.InstrumentationRegistry;

import com.smlnskgmail.jaman.hashchecker.components.localdatastorage.api.LocalDataExporter;
import com.smlnskgmail.jaman.hashchecker.components.localdatastorage.impl.ormlite.OrmLiteLocalDataStorage;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class LocalDataExporterTest {

    @Test
    public void runTest() {
        try {
            Context context = InstrumentationRegistry.getTargetContext();
            File databaseCopy = new File(
                    context.getApplicationInfo().dataDir + File.separator + LocalDataExporter.EXPORT_FILE
            );
            if (databaseCopy.exists()) {
                assertTrue(databaseCopy.delete());
            }
            LocalDataExporter.exportDatabase(context, new OrmLiteLocalDataStorage(context));
            assertTrue(databaseCopy.exists());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
