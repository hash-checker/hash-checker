package com.smlnskgmail.jaman.hashchecker.utils;

import com.smlnskgmail.jaman.hashchecker.utils.TextUtils;

import org.junit.Assert;
import org.junit.Test;

public class TextUtilsTest {

    @Test
    public void validateEqualsStringsTest() {
        String singleString = "equals";
        Assert.assertTrue(TextUtils.compareText(singleString, singleString));
    }

    @Test
    public void validateEqualsStringsWithDifferentCasesTest() {
        String firstString = "Blob";
        String secondString = "BLOB";
        Assert.assertTrue(TextUtils.compareText(firstString, secondString));
    }

    @Test
    public void validateNotEqualsStringsTest() {
        String firstString = "equals";
        String secondString = "blob";
        Assert.assertFalse(TextUtils.compareText(firstString, secondString));
    }

}
