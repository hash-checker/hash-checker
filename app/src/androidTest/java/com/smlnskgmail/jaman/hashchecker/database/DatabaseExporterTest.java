package com.smlnskgmail.jaman.hashchecker.database;

import android.content.Context;

import androidx.test.InstrumentationRegistry;

import com.smlnskgmail.jaman.hashchecker.logic.database.DatabaseExporter;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class DatabaseExporterTest {

    @Test
    public void runTest() {
        try {
            Context context = InstrumentationRegistry.getTargetContext();
            File databaseCopy = new File(
                    context.getApplicationInfo().dataDir + File.separator + DatabaseExporter.EXPORT_FILE
            );
            if (databaseCopy.exists()) {
                assertTrue(databaseCopy.delete());
            }
            DatabaseExporter.exportDatabase(context);
            assertTrue(databaseCopy.exists());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
