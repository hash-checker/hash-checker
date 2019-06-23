package com.smlnskgmail.jaman.hashchecker.hashgenerator.calculator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;

import com.smlnskgmail.jaman.hashchecker.hashgenerator.HashCalculator;
import com.smlnskgmail.jaman.hashchecker.hashgenerator.support.HashType;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class BaseHashCalculatorTest {

    private static final String TESTING_TEXT = "TEST"; // or use "iwrupvqb346386" for testing zero leads
    private static final String TESTING_FILE = "test.zip";

    private HashCalculator hashCalculator;
    private Context context;
    private Context targetContext;

    @Before
    public void initializeResources() {
        context = InstrumentationRegistry.getContext();
        targetContext = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void executeTest() throws Exception {
        initializeHashCalculator();
        testText();
        testFile();
    }

    private void initializeHashCalculator() {
        HashType hashType = getHashType();
        Assert.assertNotNull(hashType);
        hashCalculator = new HashCalculator(hashType.getTypeAsString(targetContext));
    }

    private void testText() {
        String hashFromString = hashCalculator.generateFromString(TESTING_TEXT);
        Assert.assertNotNull(hashFromString);
        String hashValue = getHashValueForTestText();
        Assert.assertNotNull(hashValue);
        Assert.assertEquals(hashFromString, hashValue);
    }

    private void testFile() throws Exception {
        String hashFromFile = hashCalculator.generateFromFile(context.getResources().getAssets()
                .open(TESTING_FILE));
        Assert.assertNotNull(hashFromFile);
        String hashValue = getHashValueForTesFile();
        Assert.assertNotNull(hashValue);
        Assert.assertEquals(hashFromFile, hashValue);
    }

    @NonNull
    protected abstract HashType getHashType();

    @NonNull
    protected abstract String getHashValueForTestText();

    @NonNull
    protected abstract String getHashValueForTesFile();

}