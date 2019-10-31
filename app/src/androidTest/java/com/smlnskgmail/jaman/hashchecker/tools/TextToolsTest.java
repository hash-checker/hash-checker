package com.smlnskgmail.jaman.hashchecker.tools;

import android.widget.EditText;

import androidx.test.rule.ActivityTestRule;

import com.smlnskgmail.jaman.hashchecker.MainActivity;
import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.logic.calculator.ui.TextTools;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TextToolsTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    private EditText field;

    @Before
    public void initializeField() {
        field = activityTestRule.getActivity().findViewById(R.id.et_field_custom_hash);
    }

    @Test
    public void fieldIsNotEmpty() {
        assertFalse(TextTools.fieldIsNotEmpty(field));

        onView(withId(R.id.et_field_custom_hash)).perform(typeText("This is test text!"));

        assertTrue(TextTools.fieldIsNotEmpty(field));
    }

    @Test
    public void convertToUpperCase() {
        String text = "green";

        onView(withId(R.id.et_field_custom_hash)).perform(typeText(text));

        TextTools.convertToUpperCase(field);

        assertEquals(text.toUpperCase(), field.getText().toString());
    }

    @Test
    public void convertToLowerCase() {
        String text = "PuRpLe";

        onView(withId(R.id.et_field_custom_hash)).perform(typeText(text));

        TextTools.convertToLowerCase(field);

        assertEquals(text.toLowerCase(), field.getText().toString());
    }

}
