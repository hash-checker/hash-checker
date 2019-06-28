package com.smlnskgmail.jaman.hashchecker.hashgenerator;

import com.smlnskgmail.jaman.hashchecker.hashgenerator.support.HashType;
import com.smlnskgmail.jaman.hashchecker.utils.HashUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.CRC32;

public class CheckerMessageDigest {

    private HashType hashType;

    private MessageDigest messageDigest;
    private CRC32 crc32;

    private boolean useCRC32;

    public CheckerMessageDigest(HashType hashType) {
        this.hashType = hashType;
    }

    public void init() throws NoSuchAlgorithmException {
        if (HashType.isMessageDigestUtilSupport(hashType)) {
            messageDigest = MessageDigest.getInstance(hashType.getMessageDigestHashName());
        } else {
            crc32 = new CRC32();
            useCRC32 = true;
        }
    }

    public void update(byte[] input, int offset, int length) {
        if (!useCRC32) {
            messageDigest.update(input, offset, length);
        } else {
            crc32.update(input, offset, length);
        }
    }

    public String getResult() {
        if (!useCRC32) {
            return HashUtils.getStringFromBytes(messageDigest.digest());
        } else {
            return Long.toHexString(crc32.getValue());
        }
    }

}
