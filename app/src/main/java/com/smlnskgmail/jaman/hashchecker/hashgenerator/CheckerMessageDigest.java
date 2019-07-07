package com.smlnskgmail.jaman.hashchecker.hashgenerator;

import com.smlnskgmail.jaman.hashchecker.hashgenerator.support.HashType;
import com.smlnskgmail.jaman.hashchecker.utils.HashUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.CRC32;

class CheckerMessageDigest {

    private HashType hashType;

    private MessageDigest messageDigest;
    private CRC32 crc32;

    private boolean useCRC32;

    CheckerMessageDigest(HashType hashType) {
        this.hashType = hashType;
    }

    void init() throws NoSuchAlgorithmException {
        if (HashType.isMessageDigestUtilSupport(hashType)) {
            messageDigest = MessageDigest.getInstance(hashType.getMessageDigestHashName());
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

    String getResult() {
        if (!useCRC32) {
            return HashUtils.getStringFromBytes(messageDigest.digest());
        } else {
            return Long.toHexString(crc32.getValue());
        }
    }

}
