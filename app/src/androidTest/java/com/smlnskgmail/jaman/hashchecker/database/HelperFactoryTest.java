package com.smlnskgmail.jaman.hashchecker.database;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class HelperFactoryTest {

    @Test
    public void runTest() {
        String helperMark = HelperFactory.getHelper().toString();

        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        HelperFactory.setHelper(context);
        assertNotEquals(
                helperMark,
                HelperFactory.getHelper().toString()
        );

        assertNotNull(HelperFactory.getHelper());
        HelperFactory.releaseHelper();
        assertNull(HelperFactory.getHelper());
    }

}
