package com.smlnskgmail.jaman.hashchecker.logic.calculator.functions;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.logic.calculator.functions.support.HashTools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.CRC32;

public class HashCalculatorDigest {

    private MessageDigest messageDigest;
    private CRC32 crc32;

    private boolean useCRC32;

    private HashCalculatorDigest() {

    }

    public static HashCalculatorDigest newInstance(@NonNull HashType hashType) throws NoSuchAlgorithmException {
        HashCalculatorDigest hashCalculatorDigest = new HashCalculatorDigest();
        hashCalculatorDigest.setHashType(hashType);
        return hashCalculatorDigest;
    }

    private void setHashType(HashType hashType) throws NoSuchAlgorithmException {
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
                ? HashTools.getStringFromByteArray(messageDigest.digest())
                : HashTools.getStringFromLong(crc32.getValue());
    }

}
