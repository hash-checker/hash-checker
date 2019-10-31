package com.smlnskgmail.jaman.hashchecker.calculator;

import com.smlnskgmail.jaman.hashchecker.logic.calculator.functions.HashCalculator;
import com.smlnskgmail.jaman.hashchecker.logic.calculator.functions.HashType;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;

public class HashCalculatorTest {

    private final String inputText = "Test";

    @Test
    public void md5FromString() throws NoSuchAlgorithmException {
        assertEquals(
                "0cbc6611f5540bd0809a388dc95a615b",
                HashCalculator.newInstance(HashType.MD5).fromString(inputText)
        );
    }

    @Test
    public void sha1FromString() throws NoSuchAlgorithmException {
        assertEquals(
                "640ab2bae07bedc4c163f679a746f7ab7fb5d1fa",
                HashCalculator.newInstance(HashType.SHA_1).fromString(inputText)
        );
    }

    @Test
    public void sha224FromString() throws NoSuchAlgorithmException {
        assertEquals(
                "3606346815fd4d491a92649905a40da025d8cf15f095136b19f37923",
                HashCalculator.newInstance(HashType.SHA_224).fromString(inputText)
        );
    }

    @Test
    public void sha256FromString() throws NoSuchAlgorithmException {
        assertEquals(
                "532eaabd9574880dbf76b9b8cc00832c20a6ec113d682299550d7a6e0f345e25",
                HashCalculator.newInstance(HashType.SHA_256).fromString(inputText)
        );
    }

    @Test
    public void sha384FromString() throws NoSuchAlgorithmException {
        assertEquals(
                "7b8f4654076b80eb963911f19cfad1aaf4285ed48e826f6cde1b01a79aa73fadb5446e667fc4f90417782c91270540f3",
                HashCalculator.newInstance(HashType.SHA_384).fromString(inputText)
        );
    }

    @Test
    public void sha512FromString() throws NoSuchAlgorithmException {
        assertEquals(
                "c6ee9e33cf5c6715a1d148fd73f7318884b41adcb916021e2bc0e800a5c5dd97f5142178f6ae88c8fdd98e1afb0ce4c8d2c54b5f37b30b7da1997bb33b0b8a31",
                HashCalculator.newInstance(HashType.SHA_512).fromString(inputText)
        );
    }

    @Test
    public void crc32FromString() throws NoSuchAlgorithmException {
        assertEquals(
                "784dd132",
                HashCalculator.newInstance(HashType.CRC_32).fromString(inputText)
        );
    }

}
