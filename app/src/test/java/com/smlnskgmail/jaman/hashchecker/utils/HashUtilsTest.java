package com.smlnskgmail.jaman.hashchecker.utils;

import com.smlnskgmail.jaman.hashchecker.calculator.support.HashUtils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HashUtilsTest {

    @Test
    public void convertBytesTest() {
        String input = "String from bytes";
        String validResult = "537472696e672066726f6d206279746573";

        String result = HashUtils.getStringFromBytes(input.getBytes());
        assertEquals(result, validResult);
    }

}
