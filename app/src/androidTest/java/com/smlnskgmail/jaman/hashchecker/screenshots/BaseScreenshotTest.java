package com.smlnskgmail.jaman.hashchecker.screenshots;

import androidx.annotation.NonNull;
import androidx.test.rule.ActivityTestRule;

import com.smlnskgmail.jaman.hashchecker.MainActivity;
import com.smlnskgmail.jaman.hashchecker.components.BaseUITest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import tools.fastlane.screengrab.FalconScreenshotStrategy;
import tools.fastlane.screengrab.Screengrab;
import tools.fastlane.screengrab.UiAutomatorScreenshotStrategy;

public abstract class BaseScreenshotTest extends BaseUITest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup() {
        Screengrab.setDefaultScreenshotStrategy(
                new UiAutomatorScreenshotStrategy()
        );
    }

    public void makeScreenshot(
            @NonNull String screenshotName
    ) {
        secondDelay();
        Screengrab.screenshot(
                screenshotName
        );
    }

}
