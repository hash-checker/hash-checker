package com.smlnskgmail.jaman.hashchecker.calculator;

import com.smlnskgmail.jaman.hashchecker.logic.calculator.functions.HashCalculatorDigest;
import com.smlnskgmail.jaman.hashchecker.logic.calculator.functions.HashType;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;

public class HashCalculatorDigestTest {

    private byte[] input = "Test".getBytes();

    @Test
    public void md5HashDigest() throws NoSuchAlgorithmException {
        HashCalculatorDigest hashCalculatorDigest = HashCalculatorDigest.newInstance(HashType.MD5);
        hashCalculatorDigest.update(input);

        assertEquals("0cbc6611f5540bd0809a388dc95a615b", hashCalculatorDigest.result());
    }

    @Test
    public void crc32HashDigest() throws NoSuchAlgorithmException {
        HashCalculatorDigest hashCalculatorDigest = HashCalculatorDigest.newInstance(HashType.CRC_32);
        hashCalculatorDigest.update(input);

        assertEquals("784dd132", hashCalculatorDigest.result());
    }

}
