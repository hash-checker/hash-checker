package com.smlnskgmail.jaman.hashchecker.calculator.jdk;

import static org.junit.Assert.assertEquals;

import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashType;
import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.jdk.JdkHashCalculatorDigest;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class JdkHashCalculatorDigestTest {

    private final byte[] input = "Test".getBytes();

    @Test
    public void md5HashDigest() throws NoSuchAlgorithmException, NoSuchProviderException {
        JdkHashCalculatorDigest jdkHashCalculatorDigest = JdkHashCalculatorDigest.instanceFor(HashType.MD5);
        jdkHashCalculatorDigest.update(input);
        assertEquals(
                "0cbc6611f5540bd0809a388dc95a615b",
                jdkHashCalculatorDigest.result()
        );
    }

    @Test
    public void crc32HashDigest() throws NoSuchAlgorithmException, NoSuchProviderException {
        JdkHashCalculatorDigest jdkHashCalculatorDigest = JdkHashCalculatorDigest.instanceFor(HashType.CRC_32);
        jdkHashCalculatorDigest.update(input);
        assertEquals(
                "784dd132",
                jdkHashCalculatorDigest.result()
        );
    }

    @Test
    public void blake2bHashDigest() throws NoSuchAlgorithmException, NoSuchProviderException {
        JdkHashCalculatorDigest jdkHashCalculatorDigest = JdkHashCalculatorDigest.instanceFor(HashType.BLAKE_2B);
        jdkHashCalculatorDigest.update(input);
        assertEquals(
                "3d896914f86ae22c48b06140adb4492fa3f8e2686a83cec0c8b1dcd6903168751370078bbd6bbfe02a6ab1df12a19b5991b58e65e243ec279f6a5770b2dd0e31",
                jdkHashCalculatorDigest.result()
        );
    }

}
