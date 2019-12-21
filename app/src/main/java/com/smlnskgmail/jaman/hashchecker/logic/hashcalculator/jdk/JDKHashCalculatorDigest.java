package com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.jdk;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.HashType;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.CRC32;

public class JDKHashCalculatorDigest {

    private MessageDigest messageDigest;
    private CRC32 crc32;

    private boolean useCRC32;

    public static JDKHashCalculatorDigest newInstance(@NonNull HashType hashType)
            throws NoSuchAlgorithmException {
        JDKHashCalculatorDigest jdkHashCalculatorDigest = new JDKHashCalculatorDigest();
        jdkHashCalculatorDigest.setHashType(hashType);
        return jdkHashCalculatorDigest;
    }

    private void setHashType(HashType hashType)
            throws NoSuchAlgorithmException {
        if (hashType != HashType.CRC_32) {
            messageDigest = MessageDigest.getInstance(hashType.getHashName());
        } else {
            crc32 = new CRC32();
            useCRC32 = true;
        }
    }

    public void update(byte[] input) {
        if (!useCRC32) {
            messageDigest.update(input);
        } else {
            crc32.update(input);
        }
    }

    public void update(byte[] input, int length) {
        if (!useCRC32) {
            messageDigest.update(input, 0, length);
        } else {
            crc32.update(input, 0, length);
        }
    }

    public String result() {
        return !useCRC32
                ? JDKHashTools.getStringFromByteArray(messageDigest.digest())
                : JDKHashTools.getStringFromLong(crc32.getValue());
    }

}
