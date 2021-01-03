package com.smlnskgmail.jaman.hashchecker.calculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import androidx.test.InstrumentationRegistry;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.BaseUITest;

import java.io.File;
import java.io.IOException;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.smlnskgmail.jaman.hashchecker.components.matchers.TextMatcher.getText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GenerateHashFromFileTest extends BaseUITest {

    private static final int SELECT_FILE_BUTTON_POSITION = 1;
    private static final int GENERATE_HASH_BUTTON_POSITION = 0;
    private static final int COMPARE_HASHES_BUTTON_POSITION = 1;

    private static final int FIRST_STORAGE_IN_FILE_MANAGER_MENU = 0;
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

        Context context = InstrumentationRegistry.getTargetContext();
        SharedPreferences prefs = context.getSharedPreferences(
                context.getPackageName() + "_preferences",
                Context.MODE_PRIVATE
        );
        prefs.edit().putBoolean(
                context.getString(R.string.key_inner_file_manager),
                true
        ).commit();

        clickById(R.id.btn_generate_from);
        inRecyclerViewClickOnPosition(
                R.id.rv_bottom_sheet_list_items,
                SELECT_FILE_BUTTON_POSITION
        );
        inRecyclerViewClickOnPosition(
                R.id.rv_file_explorer_list,
                FIRST_STORAGE_IN_FILE_MANAGER_MENU
        );
        onView(withText("Download")).perform(click());
        inRecyclerViewClickOnPosition(
                R.id.rv_file_explorer_list,
                TOKENS_FILE_POSITION_IN_FILE_MANAGER
        );
        clickById(R.id.btn_hash_actions);
        inRecyclerViewClickOnPosition(
                R.id.rv_bottom_sheet_list_items,
                GENERATE_HASH_BUTTON_POSITION
        );
        onView(withId(R.id.et_field_custom_hash)).perform(
                replaceText(getText(withId(R.id.et_field_generated_hash)))
        );
        clickById(R.id.btn_hash_actions);
        inRecyclerViewClickOnPosition(
                R.id.rv_bottom_sheet_list_items,
                COMPARE_HASHES_BUTTON_POSITION
        );
    }

}
