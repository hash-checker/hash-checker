package com.smlnskgmail.jaman.hashchecker.calculator;

import static org.junit.Assert.assertEquals;

import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashType;
import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.hash.HashConstants;

import org.junit.Test;

public class HashTypeTest {

    @Test
    public void parseMD5FromString() {
        assertEquals(
                HashType.MD5,
                HashType.getHashTypeFromString(HashConstants.MD5)
        );
    }

    @Test
    public void parseSHA1FromString() {
        assertEquals(
                HashType.SHA_1,
                HashType.getHashTypeFromString(HashConstants.SHA_1)
        );
    }

    @Test
    public void parseSHA224FromString() {
        assertEquals(
                HashType.SHA_224,
                HashType.getHashTypeFromString(HashConstants.SHA_224)
        );
    }

    @Test
    public void parseSHA256FromString() {
        assertEquals(
                HashType.SHA_256,
                HashType.getHashTypeFromString(HashConstants.SHA_256)
        );
    }

    @Test
    public void parseSHA384FromString() {
        assertEquals(
                HashType.SHA_384,
                HashType.getHashTypeFromString(HashConstants.SHA_384)
        );
    }

    @Test
    public void parseSHA512FromString() {
        assertEquals(
                HashType.SHA_512,
                HashType.getHashTypeFromString(HashConstants.SHA_512)
        );
    }

    @Test
    public void parseCRC32FromString() {
        assertEquals(
                HashType.CRC_32,
                HashType.getHashTypeFromString(HashConstants.CRC_32)
        );
    }

    @Test
    public void parseMD4FromString() {
        assertEquals(
                HashType.MD5,
                HashType.getHashTypeFromString(HashConstants.MD5)
        );
    }

    @Test
    public void parseBlake2bFromString() {
        assertEquals(
                HashType.BLAKE_2B,
                HashType.getHashTypeFromString(HashConstants.BLAKE_2B)
        );
    }

    @Test
    public void parseFnv1a32FromString() {
        assertEquals(
                HashType.FNV_1A_32,
                HashType.getHashTypeFromString(HashConstants.FNV_1A_32)
        );
    }

    @Test
    public void parseFnv1a64FromString() {
        assertEquals(
                HashType.FNV_1A_64,
                HashType.getHashTypeFromString(HashConstants.FNV_1A_64)
        );
    }

    @Test
    public void parseFnv1a128FromString() {
        assertEquals(
                HashType.FNV_1A_128,
                HashType.getHashTypeFromString(HashConstants.FNV_1A_128)
        );
    }

    @Test
    public void parseFnv1a256FromString() {
        assertEquals(
                HashType.FNV_1A_256,
                HashType.getHashTypeFromString(HashConstants.FNV_1A_256)
        );
    }

    @Test
    public void parseFnv1a512FromString() {
        assertEquals(
                HashType.FNV_1A_512,
                HashType.getHashTypeFromString(HashConstants.FNV_1A_512)
        );
    }

    @Test
    public void parseFnv1a1024FromString() {
        assertEquals(
                HashType.FNV_1A_1024,
                HashType.getHashTypeFromString(HashConstants.FNV_1A_1024)
        );
    }

}
