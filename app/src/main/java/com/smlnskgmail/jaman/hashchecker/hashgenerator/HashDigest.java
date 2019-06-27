package com.smlnskgmail.jaman.hashchecker.hashgenerator;

import android.support.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.hashgenerator.support.HashType;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.CRC32;

class HashDigest {

    private static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();

    private HashType hashType;

    HashDigest(HashType hashType) {
        this.hashType = hashType;
    }

    @NonNull
    String getResultFromString(String string) throws NoSuchAlgorithmException {
        byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
        return getResultFromByteArray(bytes);
    }

    @NonNull
    String getResultFromByteArray(byte[] bytes) throws NoSuchAlgorithmException {
        if (HashType.isMessageDigestUtilSupport(hashType)) {
            MessageDigest messageDigest = MessageDigest.getInstance(hashType.getMessageDigestHashName());
            messageDigest.update(bytes);
            return getStringFromBytes(messageDigest.digest());
        } else {
            CRC32 crc32 = new CRC32();
            crc32.update(bytes);
            return Long.toHexString(crc32.getValue());
        }
    }

    private String getStringFromBytes(byte[] data) {
        char[] chars = new char[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            chars[i * 2] = HEX_DIGITS[(data[i] >> 4) & 0xf];
            chars[i * 2 + 1] = HEX_DIGITS[data[i] & 0xf];
        }
        return new String(chars).toLowerCase();
    }

}
