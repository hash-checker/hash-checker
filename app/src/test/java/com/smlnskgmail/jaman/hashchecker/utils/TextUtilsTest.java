package com.smlnskgmail.jaman.hashchecker.utils;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TextUtilsTest {

    @Test
    public void validateEqualsStringsTest() {
        String singleString = "equals";

        assertTrue(TextUtils.compareText(singleString, singleString));
    }

    @Test
    public void validateEqualsStringsWithDifferentCasesTest() {
        String firstString = "Blob";
        String secondString = "BLOB";

        assertTrue(TextUtils.compareText(firstString, secondString));
    }

    @Test
    public void validateNotEqualsStringsTest() {
        String firstString = "equals";
        String secondString = "blob";

        assertFalse(TextUtils.compareText(firstString, secondString));
    }

}
