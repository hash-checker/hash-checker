package com.smlnskgmail.jaman.hashchecker.logic.calculator.functions;

import com.smlnskgmail.jaman.hashchecker.logic.calculator.functions.support.HashTools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.CRC32;

class HashCalculatorDigest {

    private MessageDigest messageDigest;
    private CRC32 crc32;

    private boolean useCRC32;

    void setHashType(HashType hashType) throws NoSuchAlgorithmException {
        if (hashType != HashType.CRC_32) {
            messageDigest = MessageDigest.getInstance(hashType.getHashName());
        } else {
            crc32 = new CRC32();
            useCRC32 = true;
        }
    }

    void update(byte[] input) {
        if (!useCRC32) {
            messageDigest.update(input);
        } else {
            crc32.update(input);
        }
    }

    void update(byte[] input, int length) {
        if (!useCRC32) {
            messageDigest.update(input, 0, length);
        } else {
            crc32.update(input, 0, length);
        }
    }

    String result() {
        return !useCRC32 ? HashTools.getStringFromByteArray(messageDigest.digest()) : HashTools.getStringFromLong(crc32.getValue());
    }

}
