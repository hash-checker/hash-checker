package com.smlnskgmail.jaman.hashchecker.appopen;

import android.content.Intent;
import android.os.Environment;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.ui.BaseUITest;
import com.smlnskgmail.jaman.hashchecker.ui.TestFileUtils;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public abstract class BaseOpenAppTest extends BaseUITest {

    private static final int GENERATE_HASH_BUTTON_POSITION = 0;

    @Override
    public void startActivity() {
        try {
            TestFileUtils.createTestFileInDownloadFolder();
        } catch (IOException e) {
            e.printStackTrace();
        }
        activityTestRule.launchActivity(
                getIntentForTest()
        );
    }

    @NonNull
    protected abstract Intent getIntentForTest();

    @NonNull
    protected File getTestFile() {
        File file = Environment.getExternalStorageDirectory();
        File[] filteredFiles = file.listFiles(
                (dir, name) -> name.equals(TestFileUtils.DOWNLOAD_DIRECTORY)
        );
        assertEquals(
                1,
                filteredFiles.length
        );
        File downloads = filteredFiles[0];
        return new File(
                downloads.getAbsolutePath() + "/" + TestFileUtils.TEST_FILE_NAME
        );
    }

    @Override
    public void runTest() {
        textEquals(
                TestFileUtils.TEST_FILE_NAME,
                R.id.tv_selected_object_name
        );
        textEquals(
                activityTestRule.getActivity().getString(R.string.common_file),
                R.id.btn_generate_from
        );
        clickById(R.id.btn_hash_actions);
        inRecyclerViewClickOnPosition(
                R.id.rv_bottom_sheet_list_items,
                GENERATE_HASH_BUTTON_POSITION
        );
    }

}
