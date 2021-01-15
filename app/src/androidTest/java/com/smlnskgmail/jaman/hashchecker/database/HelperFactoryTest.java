package com.smlnskgmail.jaman.hashchecker.database;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.smlnskgmail.jaman.hashchecker.logic.database.HelperFactory;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class HelperFactoryTest {

    @Test
    public void runTest() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        HelperFactory.setHelper(context);

        String helperMark = HelperFactory.getHelper().toString();
        assertNotEquals(
                helperMark,
                HelperFactory.getHelper().toString()
        );

        assertNotNull(HelperFactory.getHelper());
        HelperFactory.releaseHelper();
        assertNull(HelperFactory.getHelper());
    }

}
