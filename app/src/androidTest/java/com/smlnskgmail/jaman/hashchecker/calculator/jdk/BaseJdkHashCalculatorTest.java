package com.smlnskgmail.jaman.hashchecker.calculator.jdk;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.test.InstrumentationRegistry;

import com.github.aelstad.keccakj.provider.KeccakjProvider;
import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashType;
import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.jdk.JdkHashCalculator;

import org.junit.Before;
import org.junit.Test;

import java.security.Security;

public abstract class BaseJdkHashCalculatorTest {

    private static final String TESTING_TEXT = "TEST";
    private static final String TESTING_FILE = "test.zip";

    private JdkHashCalculator jdkHashCalculator;
    private Context context;

    @Before
    public void initializeResources() {
        Security.addProvider(new KeccakjProvider());
        context = InstrumentationRegistry.getContext();

        HashType hashType = getHashType();
        assertNotNull(hashType);

        jdkHashCalculator = new JdkHashCalculator();
        jdkHashCalculator.setHashType(hashType);
    }

    @Test
    public void checkText() {
        String hashValue = getHashValueForTestText();
        assertNotNull(hashValue);

        String hashFromString = jdkHashCalculator.fromString(getTestingText());
        assertNotNull(hashFromString);
        assertEquals(hashValue, hashFromString);
    }

    public String getTestingText() {
        return TESTING_TEXT;
    }

    @Test
    public void checkFile() throws Exception {
        String hashValue = getHashValueForTestFile();
        assertNotNull(hashValue);

        String hashFromFile = jdkHashCalculator.fromFile(context.getResources().getAssets().open(TESTING_FILE));
        assertNotNull(hashFromFile);
        assertEquals(hashValue, hashFromFile);
    }

    @NonNull
    protected abstract HashType getHashType();

    @Nullable
    protected abstract String getHashValueForTestText();

    @Nullable
    protected abstract String getHashValueForTestFile();

}