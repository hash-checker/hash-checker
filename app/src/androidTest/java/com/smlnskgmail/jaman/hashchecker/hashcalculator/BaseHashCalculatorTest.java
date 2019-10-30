package com.smlnskgmail.jaman.hashchecker.hashcalculator;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.test.InstrumentationRegistry;

import com.smlnskgmail.jaman.hashchecker.logic.calculator.functions.HashCalculator;
import com.smlnskgmail.jaman.hashchecker.logic.calculator.functions.support.HashType;

import org.junit.Before;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public abstract class BaseHashCalculatorTest {

    private static final String TESTING_TEXT = "TEST";
    private static final String TESTING_FILE = "test.zip";

    private HashCalculator hashCalculator;
    private Context context;

    @Before
    public void initializeResources() {
        context = InstrumentationRegistry.getContext();
        HashType hashType = getHashType();

        assertNotNull(hashType);

        hashCalculator = new HashCalculator(hashType);
    }

    @Test
    public void checkText() throws NoSuchAlgorithmException {
        String hashValue = getHashValueForTestText();
        assertNotNull(hashValue);

        String hashFromString = hashCalculator.fromString(getTestingText());
        assertNotNull(hashFromString);

        assertEquals(hashFromString, hashValue);
    }

    public String getTestingText() {
        return TESTING_TEXT;
    }

    @Test
    public void checkFile() throws Exception {
        String hashValue = getHashValueForTestFile();
        assertNotNull(hashValue);

        String hashFromFile = hashCalculator.fromFile(context.getResources().getAssets()
                .open(getTestingFile()));
        assertNotNull(hashFromFile);
        assertEquals(hashFromFile, hashValue);
    }

    private String getTestingFile() {
        return TESTING_FILE;
    }

    @NonNull
    protected abstract HashType getHashType();

    @Nullable
    protected abstract String getHashValueForTestText();

    @Nullable
    protected abstract String getHashValueForTestFile();

}