package com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api;

import android.content.Context;

import androidx.annotation.NonNull;

import com.github.aelstad.keccakj.provider.Constants;
import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.ListItem;

public enum HashType implements ListItem {

    MD5("MD5", "md5"),
    SHA_1("SHA-1", "sha1"),
    SHA_224("SHA-224", "sha224"),
    SHA_256("SHA-256", "sha256"),
    SHA_384("SHA-384", "sha384"),
    SHA_512("SHA-512", "sha512"),
    SHA3_224(Constants.SHA3_224, "sha3_224"),
    SHA3_256(Constants.SHA3_256, "sha3_256"),
    SHA3_384(Constants.SHA3_384, "sha3_384"),
    SHA3_512(Constants.SHA3_512, "sha3_512"),
    CRC_32("CRC-32", "crc32");

    private final String hashName;
    private final String hashFileExtension;

    HashType(@NonNull String hashName, @NonNull String hashFileExtension) {
        this.hashName = hashName;
        this.hashFileExtension = hashFileExtension;
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

    @NonNull
    public String getFileExtension() {
        return hashFileExtension;
    }

    public boolean isKeccakj() {
        return this == SHA3_224 || this == SHA3_256 || this == SHA3_384 || this == SHA3_512;
    }

}
