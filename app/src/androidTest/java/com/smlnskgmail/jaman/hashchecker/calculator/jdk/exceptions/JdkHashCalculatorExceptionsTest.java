package com.smlnskgmail.jaman.hashchecker.calculator.jdk.exceptions;

import android.content.Context;
import android.net.Uri;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.HashType;
import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.jdk.JdkHashCalculator;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class JdkHashCalculatorExceptionsTest {

    @Test
    public void runTest() throws NoSuchAlgorithmException {
        JdkHashCalculator jdkHashCalculator = new JdkHashCalculator();
        jdkHashCalculator.setHashType(HashType.MD5);
        assertNull(
                jdkHashCalculator.fromFile(
                        new InputStreamMock()
                )
        );
        assertNull(jdkHashCalculator.fromFile(null));
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertNull(
                jdkHashCalculator.fromFile(
                        context,
                        Uri.fromFile(new File(""))
                )
        );
    }

}

class InputStreamMock extends InputStream {
    @Override
    public int read() throws IOException {
        throw new IOException();
    }
}
