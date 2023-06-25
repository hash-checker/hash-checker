package com.smlnskgmail.jaman.hashchecker.calculator;

import static org.junit.Assert.assertNull;

import android.content.Context;
import android.net.Uri;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashCalculatorTask;
import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashType;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RunWith(AndroidJUnit4.class)
public class HashCalculatorTaskExceptionTest {

    private static final int SECOND_IN_MILLIS = 1000;

    @Test
    public void runTest() throws InterruptedException {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        final String[] hashValueToAssert = {""};
        HashCalculatorTask hashCalculatorTask = new HashCalculatorTask(
                context,
                HashType.MD5,
                Uri.fromFile(new File("")),
                Uri.fromFile(new File("")),
                hashValue -> hashValueToAssert[0] = hashValue
        );
        hashCalculatorTask.execute();

        CountDownLatch countDownLatch = new CountDownLatch(1);
        countDownLatch.await(
                SECOND_IN_MILLIS,
                TimeUnit.MILLISECONDS
        );
        assertNull(hashValueToAssert[0]);
    }

}
