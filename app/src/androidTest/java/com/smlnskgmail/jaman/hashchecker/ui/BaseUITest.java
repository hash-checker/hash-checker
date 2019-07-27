package com.smlnskgmail.jaman.hashchecker.ui;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;

import com.smlnskgmail.jaman.hashchecker.support.logger.L;
import com.smlnskgmail.jaman.hashchecker.ui.matchers.TextMatcher;

import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public abstract class BaseUITest {

    protected static final int SECOND_IN_MILLIS = 1000;

    @Test
    public abstract void runTest() throws InterruptedException;

    protected void clickById(int id) {
        onView(withId(id)).perform(click());
    }

    protected void clickByText(@NonNull String text) {
        onView(withText(text)).perform(click());
    }

    protected void textEquals(int textViewId, @NonNull String text) {
        onView(withId(textViewId)).check(matches(TextMatcher.hasStringEqualsTo(text)));
    }

    protected void inRecyclerViewClickOnPosition(int recyclerId, int position) {
        onView(withId(recyclerId)).perform(RecyclerViewActions
                .actionOnItemAtPosition(position, click()));
    }

    protected Context getContext() {
        return InstrumentationRegistry.getTargetContext();
    }

    protected void delayAndBack() {
        secondDelay();
        Espresso.pressBack();
        secondDelay();
    }

    protected void secondDelay() {
        try {
            Thread.sleep(SECOND_IN_MILLIS);
        } catch (InterruptedException e) {
            L.e(e);
        }
    }

}
