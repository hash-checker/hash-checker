package com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api;

import android.content.Context;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.hash.HashConstants;
import com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.ListItem;

public enum HashType implements ListItem {

    MD5(HashConstants.MD5, "md5"),
    SHA_1(HashConstants.SHA_1, "sha1"),
    SHA_224(HashConstants.SHA_224, "sha224"),
    SHA_256(HashConstants.SHA_256, "sha256"),
    SHA_384(HashConstants.SHA_384, "sha384"),
    SHA_512(HashConstants.SHA_512, "sha512"),
    SHA3_224(HashConstants.SHA3_224, "sha3_224"),
    SHA3_256(HashConstants.SHA3_256, "sha3_256"),
    SHA3_384(HashConstants.SHA3_384, "sha3_384"),
    SHA3_512(HashConstants.SHA3_512, "sha3_512"),
    CRC_32(HashConstants.CRC_32, "crc32"),
    BLAKE_2B(HashConstants.BLAKE_2B, "blake2b"),
    FNV_1A_32(HashConstants.FNV_1A_32, "fnv1a32"),
    FNV_1A_64(HashConstants.FNV_1A_64, "fnv1a64"),
    FNV_1A_128(HashConstants.FNV_1A_128, "fnv1a128"),
    FNV_1A_256(HashConstants.FNV_1A_256, "fnv1a256"),
    FNV_1A_512(HashConstants.FNV_1A_512, "fnv1a512"),
    FNV_1A_1024(HashConstants.FNV_1A_1024, "fnv1a1024");


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
