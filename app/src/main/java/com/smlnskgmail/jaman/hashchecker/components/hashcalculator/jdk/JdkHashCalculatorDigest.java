package com.smlnskgmail.jaman.hashchecker.components.hashcalculator.jdk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashType;
import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.hash.Blake2B;
import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.hash.FNV1a;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.zip.CRC32;

public class JdkHashCalculatorDigest {

    private MessageDigest messageDigest;
    private CRC32 crc32;
    private Blake2B blake2B;
    private FNV1a fnv1A;

    private boolean useCRC32;
    private boolean useBLAKE2B;
    private boolean useFNV1A;

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
        if (hashType == HashType.CRC_32) {
            crc32 = new CRC32();
            useCRC32 = true;
        } else if (hashType == HashType.BLAKE_2B) {
            blake2B = new Blake2B();
            useBLAKE2B = true;
        } else if (hashType == HashType.FNV_1A_32 || hashType == HashType.FNV_1A_64 || hashType == HashType.FNV_1A_128 || hashType == HashType.FNV_1A_256 || hashType == HashType.FNV_1A_512 || hashType == HashType.FNV_1A_1024) {
            fnv1A = new FNV1a();
            fnv1A.setInstance(hashType.getTypeAsString());
            useFNV1A = true;
        } else {
            if (provider == null) {
                messageDigest = MessageDigest.getInstance(hashType.getHashName());
            } else {
                messageDigest = MessageDigest.getInstance(hashType.getHashName(), provider);
            }
        }
    }

    public void update(@NonNull byte[] input) {
        if (useCRC32) {
            crc32.reset();
            crc32.update(input);
        } else if (useBLAKE2B) {
            blake2B.reset();
            blake2B.update(input);
        } else if (useFNV1A) {
            fnv1A.reset();
            fnv1A.update(input);
        } else {
            messageDigest.reset();
            messageDigest.update(input);
        }
    }

    public void update(@NonNull byte[] input, int length) {
        if (useCRC32) {
            crc32.update(input, 0, length);
        } else if (useBLAKE2B) {
            blake2B.update(input, 0, length);
        } else if (useFNV1A) {
            fnv1A.update(input, 0, length);
        } else {
            messageDigest.update(input, 0, length);
        }
    }

    @NonNull
    public String result() {
        if (useCRC32) {
            return JdkHashUtils.getStringFromLong(crc32.getValue());
        } else if (useBLAKE2B) {
            return blake2B.getValue();
        } else if (useFNV1A) {
            return fnv1A.getValue();
        }
        return JdkHashUtils.getStringFromByteArray(messageDigest.digest());
    }

}
