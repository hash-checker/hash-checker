package com.smlnskgmail.jaman.hashchecker.settings;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.test.platform.app.InstrumentationRegistry;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashType;
import com.smlnskgmail.jaman.hashchecker.ui.BaseUITest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public abstract class BaseSettingsTest extends BaseUITest {

    private static final String TEST_TEXT = "TEST";
    protected static final String TEST_RESULT = "033bd94b1168d7e4f0d644c3c95e35bf";

    private static final HashType TEST_HASH_TYPE = HashType.MD5;

    private static final int TEXT_BUTTON_POSITION = 0;
    private static final int GENERATE_BUTTON_POSITION = 0;

    protected void updateBooleanValue(@NonNull String key, boolean value) {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SharedPreferences prefs = context.getSharedPreferences(
                context.getPackageName() + "_preferences",
                Context.MODE_PRIVATE
        );
        prefs.edit().putBoolean(key, value).commit();
    }

    protected void generateHashValueFromText() throws InterruptedException {
        showInputDialog();
        enterText();
        selectHashType();
        generateHashFromText();
    }

    private void showInputDialog() {
        clickById(R.id.btn_generate_from);
        delay();

        inRecyclerViewClickOnPosition(R.id.rv_bottom_sheet_list_items, TEXT_BUTTON_POSITION);
        delay();
    }

    private void enterText() {
        onView(withId(R.id.et_dialog_input_text)).perform(typeText(TEST_TEXT));
        delay();

        clickById(R.id.btn_dialog_input_text_add);
        delay();

        textEquals(TEST_TEXT, R.id.tv_selected_object_name);
    }

    private void selectHashType() {
        clickById(R.id.tv_selected_hash_type);
        delay();

        List<HashType> hashTypes = new ArrayList<>(Arrays.asList(HashType.values()));
        int hashTypePosition = hashTypes.indexOf(TEST_HASH_TYPE);
        assertTrue(hashTypePosition >= 0);
        inRecyclerViewClickOnPosition(R.id.rv_bottom_sheet_list_items, hashTypePosition);
        textEquals(TEST_HASH_TYPE.getTypeAsString(), R.id.tv_selected_hash_type);
    }

    private void generateHashFromText() throws InterruptedException {
        clickById(R.id.btn_hash_actions);
        delay();

        CountDownLatch countDownLatch = new CountDownLatch(1);
        inRecyclerViewClickOnPosition(R.id.rv_bottom_sheet_list_items, GENERATE_BUTTON_POSITION);
        countDownLatch.await(DELAY_IN_MILLIS, TimeUnit.MILLISECONDS);
        delay();
    }

}
