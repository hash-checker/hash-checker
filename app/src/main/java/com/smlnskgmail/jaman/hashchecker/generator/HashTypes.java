package com.smlnskgmail.jaman.hashchecker.generator;

import android.support.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.selectors.bottomsheets.IBottomSheetItem;

public enum HashTypes implements IBottomSheetItem {

    MD5("MD5", R.string.key_md5),
    SHA_1("SHA-1", R.string.key_sha1),
    SHA_224("SHA-224", R.string.key_sha244),
    SHA_256("SHA-256", R.string.key_sha256),
    SHA_384("SHA-384", R.string.key_sha384),
    SHA_512("SHA-512", R.string.key_sha512);

    private String hashType;
    private int key;

    HashTypes(@NonNull String hashType, int key) {
        this.hashType = hashType;
        this.key = key;
    }

    @NonNull
    public String getTypeAsString() {
        return hashType;
    }

    public int getKey() {
        return key;
    }

    @NonNull
    public static HashTypes parseHashTypeFromString(@NonNull String data) {
        if (MD5.hashType.equals(data)) {
            return MD5;
        } else if (SHA_1.hashType.equals(data)) {
            return SHA_1;
        } else if (SHA_224.hashType.equals(data)) {
            return SHA_224;
        } else if (SHA_256.hashType.equals(data)) {
            return SHA_256;
        } else if (SHA_384.hashType.equals(data)) {
            return SHA_384;
        } else {
            return SHA_512;
        }
    }

}
