package com.smlnskgmail.jaman.hashchecker.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.annotation.NonNull;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.smlnskgmail.jaman.hashchecker.MainActivity;
import com.smlnskgmail.jaman.hashchecker.ui.matchers.TextMatcher;
import com.smlnskgmail.jaman.hashchecker.utils.LogUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public abstract class BaseUITest {

    protected static final int DELAY_IN_MILLIS = 5000;

    @Rule
    public final ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(
            MainActivity.class,
            false,
            false
    );

    @Before
    public void startActivity() {
        activityTestRule.launchActivity(null);
    }

    @Test
    public abstract void runTest() throws Exception;

    protected void clickById(int id) {
        onView(withId(id)).perform(click());
    }

    protected void textEquals(@NonNull String text, int textViewId) {
        onView(withId(textViewId)).check(matches(TextMatcher.hasStringEqualsTo(text)));
    }

    @SuppressWarnings("SameParameterValue")
    protected void inRecyclerViewClickOnPosition(int recyclerId, int position) {
        onView(withId(recyclerId)).perform(
                RecyclerViewActions.actionOnItemAtPosition(
                        position,
                        click()
                )
        );
    }

    protected void delayAndBack() {
        delay();
        pressBack();
        delay();
    }

    protected void delay() {
        try {
            Thread.sleep(DELAY_IN_MILLIS);
        } catch (InterruptedException e) {
            LogUtils.e(e);
        }
    }

}
