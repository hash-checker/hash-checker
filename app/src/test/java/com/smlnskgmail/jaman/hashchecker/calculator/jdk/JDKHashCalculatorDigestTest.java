package com.smlnskgmail.jaman.hashchecker.calculator.jdk;

import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.HashType;
import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.jdk.JDKHashCalculatorDigest;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;

public class JDKHashCalculatorDigestTest {

    private final byte[] input = "Test".getBytes();

    @Test
    public void md5HashDigest() throws NoSuchAlgorithmException {
        JDKHashCalculatorDigest jdkHashCalculatorDigest = JDKHashCalculatorDigest.newInstance(HashType.MD5);
        jdkHashCalculatorDigest.update(input);

        assertEquals(
                "0cbc6611f5540bd0809a388dc95a615b",
                jdkHashCalculatorDigest.result()
        );
    }

    @Test
    public void crc32HashDigest() throws NoSuchAlgorithmException {
        JDKHashCalculatorDigest jdkHashCalculatorDigest = JDKHashCalculatorDigest.newInstance(HashType.CRC_32);
        jdkHashCalculatorDigest.update(input);

        assertEquals(
                "784dd132",
                jdkHashCalculatorDigest.result()
        );
    }

}
