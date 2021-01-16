package com.smlnskgmail.jaman.hashchecker.appopen;

import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.test.runner.AndroidJUnit4;

import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class OpenAppWithIntentTest extends BaseOpenAppTest {

    @NonNull
    @Override
    protected Intent getIntentForTest() {
        Intent startIntent = new Intent();
        startIntent.setData(
                Uri.fromFile(
                        getTestFile()
                )
        );
        return startIntent;
    }

}
