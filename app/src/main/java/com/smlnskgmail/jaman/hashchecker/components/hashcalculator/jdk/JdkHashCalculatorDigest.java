package com.smlnskgmail.jaman.hashchecker.components.hashcalculator.jdk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashType;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.zip.CRC32;

public class JdkHashCalculatorDigest {

    private MessageDigest messageDigest;
    private CRC32 crc32;

    private boolean useCRC32;

    private JdkHashCalculatorDigest() {

    }

    @NonNull
    public static JdkHashCalculatorDigest instanceFor(
            @NonNull HashType hashType
    ) throws NoSuchAlgorithmException, NoSuchProviderException {
        JdkHashCalculatorDigest jdkHashCalculatorDigest = new JdkHashCalculatorDigest();
        jdkHashCalculatorDigest.setHashType(hashType, null);
        return jdkHashCalculatorDigest;
    }

    @NonNull
    public static JdkHashCalculatorDigest instanceFor(
            @NonNull HashType hashType,
            @NonNull String provider
    ) throws NoSuchAlgorithmException, NoSuchProviderException {
        JdkHashCalculatorDigest jdkHashCalculatorDigest = new JdkHashCalculatorDigest();
        jdkHashCalculatorDigest.setHashType(hashType, provider);
        return jdkHashCalculatorDigest;
    }

    private void setHashType(
            @NonNull HashType hashType,
            @Nullable String provider
    ) throws NoSuchAlgorithmException, NoSuchProviderException {
        if (hashType != HashType.CRC_32) {
            if (provider == null) {
                messageDigest = MessageDigest.getInstance(hashType.getHashName());
            } else {
                messageDigest = MessageDigest.getInstance(hashType.getHashName(), provider);
            }
        } else {
            crc32 = new CRC32();
            useCRC32 = true;
        }
    }

    public void update(@NonNull byte[] input) {
        if (!useCRC32) {
            messageDigest.reset();
            messageDigest.update(input);
        } else {
            crc32.reset();
            crc32.update(input);
        }
    }

    public void update(@NonNull byte[] input, int length) {
        if (!useCRC32) {
            messageDigest.update(input, 0, length);
        } else {
            crc32.update(input, 0, length);
        }
    }

    @NonNull
    public String result() {
        return !useCRC32
                ? JdkHashUtils.getStringFromByteArray(messageDigest.digest())
                : JdkHashUtils.getStringFromLong(crc32.getValue());
    }

}
