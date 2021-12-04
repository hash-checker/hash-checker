package com.smlnskgmail.jaman.hashchecker.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import android.os.Environment;

import java.io.File;
import java.io.IOException;

public class TestFileUtils {

    public static final String DOWNLOAD_DIRECTORY = "Download";
    public static final String TEST_FILE_NAME = "tokens.txt";

    public static void createTestFileInDownloadFolder() throws IOException {
        File file = Environment.getExternalStorageDirectory();
        File[] filteredFiles = file.listFiles((dir, name) -> name.equals(DOWNLOAD_DIRECTORY));
        assertNotNull(filteredFiles);
        assertEquals(
                1,
                filteredFiles.length
        );
        File downloads = filteredFiles[0];
        File tokensFile = new File(downloads.getAbsolutePath() + "/" + TEST_FILE_NAME);
        if (tokensFile.exists()) {
            assertTrue(tokensFile.delete());
        }
        assertTrue(tokensFile.createNewFile());
    }

}
