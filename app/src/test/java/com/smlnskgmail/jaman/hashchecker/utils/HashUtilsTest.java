package com.smlnskgmail.jaman.hashchecker.utils;

import org.junit.Assert;
import org.junit.Test;

public class HashUtilsTest {

    private String testString = "String from bytes";
    private String testStringValidResult = "537472696e672066726f6d206279746573";

    @Test
    public void convertBytesTest() {
        String result = HashUtils.getStringFromBytes(testString.getBytes());
        Assert.assertEquals(result, testStringValidResult);
    }

}
