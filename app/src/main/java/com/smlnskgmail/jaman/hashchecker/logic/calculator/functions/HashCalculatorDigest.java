package com.smlnskgmail.jaman.hashchecker.logic.calculator.functions;

import com.smlnskgmail.jaman.hashchecker.logic.calculator.functions.support.HashType;
import com.smlnskgmail.jaman.hashchecker.logic.calculator.functions.support.HashUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.CRC32;

class HashCalculatorDigest {

    private final HashType hashType;

    private MessageDigest messageDigest;
    private CRC32 crc32;

    private boolean useCRC32;

    HashCalculatorDigest(HashType hashType) {
        this.hashType = hashType;
    }

    void init() throws NoSuchAlgorithmException {
        if (hashType != HashType.CRC_32) {
            messageDigest = MessageDigest.getInstance(hashType.getHashName());
        } else {
            crc32 = new CRC32();
            useCRC32 = true;
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
        if (!useCRC32) {
            return HashUtils.getStringFromByteArray(messageDigest.digest());
        } else {
            return Long.toHexString(crc32.getValue());
        }
    }

}
