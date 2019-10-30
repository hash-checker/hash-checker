package com.smlnskgmail.jaman.hashchecker.logic.calculator.functions;

import android.content.Context;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.ListMarker;

public enum HashType implements ListMarker {

    MD5("MD5"),
    SHA_1("SHA-1"),
    SHA_224("SHA-224"),
    SHA_256("SHA-256"),
    SHA_384("SHA-384"),
    SHA_512("SHA-512"),
    CRC_32("CRC32");

    private final String hashName;

    HashType(String hashName) {
        this.hashName = hashName;
    }

    public String getHashName() {
        return hashName;
    }

    @NonNull
    public String getTypeAsString() {
        return hashName;
    }

    @NonNull
    public static HashType getHashTypeFromString(@NonNull String string) {
        if (string.equals(SHA_1.hashName)) {
            return SHA_1;
        } else if (string.equals(SHA_224.hashName)) {
            return SHA_224;
        } else if (string.equals(SHA_256.hashName)) {
            return SHA_256;
        } else if (string.equals(SHA_384.hashName)) {
            return SHA_384;
        } else if (string.equals(SHA_512.hashName)) {
            return SHA_512;
        } else if (string.equals(CRC_32.hashName)) {
            return CRC_32;
        }
        return MD5;
    }

    @Override
    public String getTitle(@NonNull Context context) {
        return hashName;
    }

    @Override
    public int getPrimaryIconResId() {
        return -1;
    }

    @Override
    public int getAdditionalIconResId() {
        return R.drawable.ic_done;
    }

}
