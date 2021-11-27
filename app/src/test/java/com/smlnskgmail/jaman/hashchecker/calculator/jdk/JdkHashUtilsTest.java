package com.smlnskgmail.jaman.hashchecker.calculator.jdk;

import static org.junit.Assert.assertEquals;

import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.jdk.JdkHashUtils;

import org.junit.Test;

public class JdkHashUtilsTest {

    @Test
    public void stringFromBytesArray() {
        // Text: Test (one word)
        byte[] input = new byte[]{
                12,
                -68,
                102,
                17,
                -11,
                84,
                11,
                -48,
                -128,
                -102,
                56,
                -115,
                -55,
                90,
                97,
                91
        };

        assertEquals(
                "0cbc6611f5540bd0809a388dc95a615b",
                JdkHashUtils.getStringFromByteArray(input)
        );
    }

    @Test
    public void stringFromLong() {
        // Text: Test (one word)
        assertEquals(
                "784dd132",
                JdkHashUtils.getStringFromLong(2018365746)
        );
    }

}
