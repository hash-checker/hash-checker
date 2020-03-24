package com.smlnskgmail.jaman.hashchecker.screenshots;

import androidx.annotation.NonNull;
import androidx.test.rule.ActivityTestRule;

import com.smlnskgmail.jaman.hashchecker.MainActivity;

import org.junit.Before;
import org.junit.Rule;

import tools.fastlane.screengrab.FalconScreenshotStrategy;
import tools.fastlane.screengrab.Screengrab;

public abstract class BaseScreenshotTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup() {
        Screengrab.setDefaultScreenshotStrategy(
                new FalconScreenshotStrategy(
                        activityTestRule.getActivity()
                )
        );
    }

    public void makeScreenshot(
            @NonNull String screenshotName
    ) {
        Screengrab.screenshot(
                screenshotName
        );
    }

}
