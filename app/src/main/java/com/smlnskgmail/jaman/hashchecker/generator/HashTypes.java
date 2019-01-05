package com.smlnskgmail.jaman.hashchecker.generator;

import android.content.Context;
import android.support.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.selectors.bottomsheets.base.ListItemElement;

public enum HashTypes implements ListItemElement {

    MD5(R.string.hash_type_md5, R.string.key_md5),
    SHA_1(R.string.hash_type_sha1, R.string.key_sha1),
    SHA_224(R.string.hash_type_sha224, R.string.key_sha224),
    SHA_256(R.string.hash_type_sha256, R.string.key_sha256),
    SHA_384(R.string.hash_type_sha384, R.string.key_sha384),
    SHA_512(R.string.hash_type_sha512, R.string.key_sha512);

    private int hashTypeNameResId;
    private int preferenceKey;

    HashTypes(int hashTypeNameResId, int preferenceKey) {
        this.hashTypeNameResId = hashTypeNameResId;
        this.preferenceKey = preferenceKey;
    }

    @NonNull
    public String getTypeAsString(@NonNull Context context) {
        return context.getString(hashTypeNameResId);
    }

    public int getPreferenceKey() {
        return preferenceKey;
    }

    @NonNull
    public static HashTypes parseHashTypeFromString(@NonNull Context context, @NonNull String data) {
        if (context.getString(SHA_1.hashTypeNameResId).equals(data)) {
            return SHA_1;
        } else if (context.getString(SHA_224.hashTypeNameResId).equals(data)) {
            return SHA_224;
        } else if (context.getString(SHA_256.hashTypeNameResId).equals(data)) {
            return SHA_256;
        } else if (context.getString(SHA_384.hashTypeNameResId).equals(data)) {
            return SHA_384;
        } else if (context.getString(SHA_512.hashTypeNameResId).equals(data)) {
           return SHA_512;
        }
        return MD5;
    }

}
