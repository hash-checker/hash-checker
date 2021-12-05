package com.smlnskgmail.jaman.hashchecker.calculator;

import static org.junit.Assert.assertEquals;

import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashType;

import org.junit.Test;

public class HashTypeTest {

    @Test
    public void parseMD5FromString() {
        assertEquals(
                HashType.MD5,
                HashType.getHashTypeFromString("MD5")
        );
    }

    @Test
    public void parseSHA1FromString() {
        assertEquals(
                HashType.SHA_1,
                HashType.getHashTypeFromString("SHA-1")
        );
    }

    @Test
    public void parseSHA224FromString() {
        assertEquals(
                HashType.SHA_224,
                HashType.getHashTypeFromString("SHA-224")
        );
    }

    @Test
    public void parseSHA256FromString() {
        assertEquals(
                HashType.SHA_256,
                HashType.getHashTypeFromString("SHA-256")
        );
    }

    @Test
    public void parseSHA384FromString() {
        assertEquals(
                HashType.SHA_384,
                HashType.getHashTypeFromString("SHA-384")
        );
    }

    @Test
    public void parseSHA512FromString() {
        assertEquals(
                HashType.SHA_512,
                HashType.getHashTypeFromString("SHA-512")
        );
    }

    @Test
    public void parseCRC32FromString() {
        assertEquals(
                HashType.CRC_32,
                HashType.getHashTypeFromString("CRC-32")
        );
    }

    @Test
    public void parseMD4FromString() {
        assertEquals(
                HashType.MD5,
                HashType.getHashTypeFromString("MD4")
        );
    }

}
