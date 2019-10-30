package com.smlnskgmail.jaman.hashchecker.tools;

import com.smlnskgmail.jaman.hashchecker.logic.calculator.functions.support.HashTools;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HashToolsTest {

    @Test
    public void convertBytesTest() {
        String input = "String from bytes";
        String validResult = "537472696e672066726f6d206279746573";

        String result = HashTools.getStringFromByteArray(input.getBytes());
        assertEquals(result, validResult);
    }

}
