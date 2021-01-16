package com.smlnskgmail.jaman.hashchecker.appopen;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;

public class OpenAppWithClipDataTest extends BaseOpenAppTest {

    @NonNull
    @Override
    protected Intent getIntentForTest() {
        Intent startIntent = new Intent();
        ClipData clipData = new ClipData(
                new ClipDescription(
                        "ClipData with the path to a test file",
                        new String[] { "*/*" }
                ),
                new ClipData.Item(
                        Uri.fromFile(
                                getTestFile()
                        )
                )
        );
        startIntent.setClipData(
               clipData
        );
        return startIntent;
    }

}
