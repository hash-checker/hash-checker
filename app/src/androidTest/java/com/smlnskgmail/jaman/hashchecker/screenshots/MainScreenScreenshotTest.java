package com.smlnskgmail.jaman.hashchecker.screenshots;

import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainScreenScreenshotTest extends BaseScreenshotTest {

    @Test
    public void makeMainScreenScreenshot() {
        makeScreenshot(
                "1_main_screen"
        );
    }

}
