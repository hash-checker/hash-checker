package com.smlnskgmail.jaman.hashchecker.history;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.BaseUITest;
import com.smlnskgmail.jaman.hashchecker.components.matchers.RecyclerViewItemCountAssertion;
import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.HashType;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HistoryTest extends BaseUITest {

    private static final String TEST_TEXT = "TEST";
    private static final String TEST_RESULT = "033bd94b1168d7e4f0d644c3c95e35bf";

    private static final HashType TEST_HASH_TYPE = HashType.MD5;

    private static final int TEXT_BUTTON_POSITION = 0;
    private static final int GENERATE_BUTTON_POSITION = 0;

    @Override
    public void runTest() throws InterruptedException {
        openHistory();
        clearHistory();

        showInputDialog();
        enterText();
        selectHashType();
        generateHashFromText();

        openHistory();
        checkGeneratedHashInHistory();
        copyHashValueToClipboard();
        clearHistory();
    }

    private void openHistory() {
        clickById(R.id.menu_main_section_history);
    }

    private void clearHistory() {
        clickById(R.id.menu_item_clean_history);
        onView(withText(R.string.common_ok)).perform(click());

        onView(withId(R.id.rv_history_items)).check(new RecyclerViewItemCountAssertion(0));

        delayAndBack();
    }

    private void showInputDialog() {
        clickById(R.id.btn_generate_from);
        secondDelay();

        inRecyclerViewClickOnPosition(
                R.id.rv_bottom_sheet_list_items,
                TEXT_BUTTON_POSITION
        );
        secondDelay();
    }

    private void enterText() {
        onView(withId(R.id.et_dialog_input_text)).perform(typeText(TEST_TEXT));
        secondDelay();

        clickById(R.id.btn_dialog_input_text_add);
        secondDelay();

        textEquals(
                TEST_TEXT,
                R.id.tv_selected_object_name
        );
    }

    private void selectHashType() {
        clickById(R.id.tv_selected_hash_type);
        secondDelay();

        List<HashType> hashTypes = new ArrayList<>(
                Arrays.asList(HashType.values())
        );
        int hashTypePosition = hashTypes.indexOf(TEST_HASH_TYPE);
        assertTrue(hashTypePosition >= 0);

        inRecyclerViewClickOnPosition(
                R.id.rv_bottom_sheet_list_items,
                hashTypePosition
        );

        textEquals(
                TEST_HASH_TYPE.getTypeAsString(),
                R.id.tv_selected_hash_type
        );
    }

    private void generateHashFromText() throws InterruptedException {
        clickById(R.id.btn_hash_actions);
        secondDelay();

        CountDownLatch countDownLatch = new CountDownLatch(1);
        inRecyclerViewClickOnPosition(
                R.id.rv_bottom_sheet_list_items,
                GENERATE_BUTTON_POSITION
        );

        countDownLatch.await(
                SECOND_IN_MILLIS,
                TimeUnit.MILLISECONDS
        );
        secondDelay();
    }

    private void checkGeneratedHashInHistory() {
        onView(withId(R.id.rv_history_items)).check(new RecyclerViewItemCountAssertion(1));

        textEquals(
                TEST_TEXT,
                R.id.tv_item_history_object_data
        );
        textEquals(
                TEST_HASH_TYPE.getHashName() + ":",
                R.id.tv_item_history_hash_type
        );
        textEquals(
                TEST_RESULT,
                R.id.tv_item_history_hash_data
        );
        textEquals(
                DateFormat.getDateInstance().format(
                        Calendar.getInstance().getTime()
                ),
                R.id.tv_item_history_date
        );
    }

    private void copyHashValueToClipboard() {
        inRecyclerViewClickOnPosition(
                R.id.rv_history_items,
                0
        );
        InstrumentationRegistry.getInstrumentation().runOnMainSync(
                () -> {
                    Activity activity = activityTestRule.getActivity();
                    ClipboardManager clipboardManager = (ClipboardManager) activity.getSystemService(
                            Context.CLIPBOARD_SERVICE
                    );
                    assertEquals(
                            clipboardManager.getPrimaryClip().getItemAt(0).coerceToText(
                                    activity
                            ),
                            TEST_RESULT
                    );
                    onView(withText(R.string.common_ok)).perform(click());
                }
        );
    }

}
