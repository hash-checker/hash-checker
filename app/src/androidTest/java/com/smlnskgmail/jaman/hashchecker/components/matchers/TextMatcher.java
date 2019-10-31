package com.smlnskgmail.jaman.hashchecker.components.matchers;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class TextMatcher {

    public static Matcher<View> hasStringEqualsTo(String content) {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                if (item instanceof EditText) {
                    return ((EditText) item).getText().toString().equals(content);
                }
                if (item instanceof TextView) {
                    return ((TextView) item).getText().toString().equals(content);
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }

}
