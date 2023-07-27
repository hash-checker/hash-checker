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

    @Test
    public void fnv1a32HashDigest() throws NoSuchAlgorithmException, NoSuchProviderException {
        JdkHashCalculatorDigest jdkHashCalculatorDigest = JdkHashCalculatorDigest.instanceFor(HashType.FNV_1A_32);
        jdkHashCalculatorDigest.update(input);
        assertEquals(
                "2ffcbe05",
                jdkHashCalculatorDigest.result()
        );
    }

    @Test
    public void fnv1a64HashDigest() throws NoSuchAlgorithmException, NoSuchProviderException {
        JdkHashCalculatorDigest jdkHashCalculatorDigest = JdkHashCalculatorDigest.instanceFor(HashType.FNV_1A_64);
        jdkHashCalculatorDigest.update(input);
        assertEquals(
                "2474e7fb1aec9f05",
                jdkHashCalculatorDigest.result()
        );
    }

    @Test
    public void fnv1a128HashDigest() throws NoSuchAlgorithmException, NoSuchProviderException {
        JdkHashCalculatorDigest jdkHashCalculatorDigest = JdkHashCalculatorDigest.instanceFor(HashType.FNV_1A_128);
        jdkHashCalculatorDigest.update(input);
        assertEquals(
                "68e554f9c5757277b806e94c1fb4fcc5",
                jdkHashCalculatorDigest.result()
        );
    }

    @Test
    public void fnv1a256HashDigest() throws NoSuchAlgorithmException, NoSuchProviderException {
        JdkHashCalculatorDigest jdkHashCalculatorDigest = JdkHashCalculatorDigest.instanceFor(HashType.FNV_1A_256);
        jdkHashCalculatorDigest.update(input);
        assertEquals(
                "e46ddd4ed460b28c4ece66459f2a8e9d123f79d831721584cc463c5a98bd4c65",
                jdkHashCalculatorDigest.result()
        );
    }

    @Test
    public void fnv1a512HashDigest() throws NoSuchAlgorithmException, NoSuchProviderException {
        JdkHashCalculatorDigest jdkHashCalculatorDigest = JdkHashCalculatorDigest.instanceFor(HashType.FNV_1A_512);
        jdkHashCalculatorDigest.update(input);
        assertEquals(
                "f9fe9eefe38ca43fcf36c8fbc0d25bef51797ddeec00000000002a5259a146c7f24cae042d99828e5baba0a28b18bf530de9c3137ca2a36973f8d0786c7072b5",
                jdkHashCalculatorDigest.result()
        );
    }

    @Test
    public void fnv1a1024HashDigest() throws NoSuchAlgorithmException, NoSuchProviderException {
        JdkHashCalculatorDigest jdkHashCalculatorDigest = JdkHashCalculatorDigest.instanceFor(HashType.FNV_1A_1024);
        jdkHashCalculatorDigest.update(input);
        assertEquals(
                "26f791f9147aedad1354bef7d238f3219005cbd6e8d664f6b4eefdbe94929e41548c17f600860000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001ba08046e07e0418fb7be0ec07b8ea87a61bb4f073e2bab740db8398ef60cb9b50bff5f3dd1081",
                jdkHashCalculatorDigest.result()
        );
    }

}
