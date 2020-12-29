package com.smlnskgmail.jaman.hashchecker.screenshots;

import android.os.Environment;

import androidx.test.runner.AndroidJUnit4;

import com.smlnskgmail.jaman.hashchecker.R;

import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.smlnskgmail.jaman.hashchecker.components.matchers.TextMatcher.getText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class CompareHashesScreenshotTest extends BaseScreenshotTest {

    private static final int SELECT_FILE_BUTTON_POSITION = 1;
    private static final int GENERATE_HASH_BUTTON_POSITION = 0;
    private static final int COMPARE_HASHES_BUTTON_POSITION = 1;

    private static final int FIRST_STORAGE_IN_FILE_MANAGER_MENU = 0;
    private static final int DOWNLOAD_FOLDER_POSITION_IN_FILE_MANAGER = 4;
    private static final int TOKENS_FILE_POSITION_IN_FILE_MANAGER = 1;

    @Override
    public void runTest() throws IOException {
        File file = Environment.getExternalStorageDirectory();
        File[] filteredFiles = file.listFiles((dir, name) -> name.equals("Download"));
        assertEquals(
                1,
                filteredFiles.length
        );
        File downloads = filteredFiles[0];
        File tokensFile = new File(downloads.getAbsolutePath() + "/tokens.txt");
        if (tokensFile.exists()) {
            assertTrue(tokensFile.delete());
        }
        assertTrue(tokensFile.createNewFile());
        clickById(R.id.btn_generate_from);
        inRecyclerViewClickOnPosition(
                R.id.rv_bottom_sheet_list_items,
                SELECT_FILE_BUTTON_POSITION
        );
        inRecyclerViewClickOnPosition(
                R.id.rv_file_explorer_list,
                FIRST_STORAGE_IN_FILE_MANAGER_MENU
        );
        inRecyclerViewClickOnPosition(
                R.id.rv_file_explorer_list,
                DOWNLOAD_FOLDER_POSITION_IN_FILE_MANAGER
        );
        inRecyclerViewClickOnPosition(
                R.id.rv_file_explorer_list,
                TOKENS_FILE_POSITION_IN_FILE_MANAGER
        );
        clickById(R.id.btn_hash_actions);
        inRecyclerViewClickOnPosition(
                R.id.rv_bottom_sheet_list_items,
                GENERATE_HASH_BUTTON_POSITION
        );
        onView(withId(R.id.et_field_custom_hash)).perform(replaceText(getText(withId(R.id.et_field_generated_hash))));
        clickById(R.id.btn_hash_actions);
        inRecyclerViewClickOnPosition(
                R.id.rv_bottom_sheet_list_items,
                COMPARE_HASHES_BUTTON_POSITION
        );
        makeScreenshot(
                "5_compare_hashes"
        );
    }

}
