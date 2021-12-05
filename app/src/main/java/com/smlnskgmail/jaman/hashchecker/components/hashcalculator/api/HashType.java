package com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api;

import android.content.Context;

import androidx.annotation.NonNull;

import com.github.aelstad.keccakj.provider.Constants;
import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.ListItem;

public enum HashType implements ListItem {

    MD5("MD5"),
    SHA_1("SHA-1"),
    SHA_224("SHA-224"),
    SHA_256("SHA-256"),
    SHA_384("SHA-384"),
    SHA_512("SHA-512"),
    SHA3_224(Constants.SHA3_224),
    SHA3_256(Constants.SHA3_256),
    SHA3_384(Constants.SHA3_384),
    SHA3_512(Constants.SHA3_512),
    CRC_32("CRC-32");

    private final String hashName;

    HashType(String hashName) {
        this.hashName = hashName;
    }

    @NonNull
    public String getHashName() {
        return hashName;
    }

    @NonNull
    public String getTypeAsString() {
        return hashName;
    }

    @NonNull
    public static HashType getHashTypeFromString(@NonNull String string) {
        for (HashType hashType : values()) {
            if (hashType.hashName.equals(string)) {
                return hashType;
            }
        }
        return MD5;
    }

    @NonNull
    @Override
    public String getTitle(@NonNull Context context) {
        return hashName;
    }

    @Override
    public int getPrimaryIconResId() {
        return DEFAULT_ICON_VALUE;
    }

    @Override
    public int getAdditionalIconResId() {
        return R.drawable.ic_done;
    }

    public boolean isKeccakj() {
        return this == SHA3_224 || this == SHA3_256 || this == SHA3_384 || this == SHA3_512;
    }

}
