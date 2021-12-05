package com.smlnskgmail.jaman.hashchecker.screenshots;

import androidx.test.runner.AndroidJUnit4;

import com.smlnskgmail.jaman.hashchecker.R;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainScreenScreenshotTest extends BaseScreenshotTest {

    @Test
    @Override
    public void runTest() {
        clickById(R.id.tv_selected_object_name);
//        makeScreenshot("1_main_screen");
    }

}
