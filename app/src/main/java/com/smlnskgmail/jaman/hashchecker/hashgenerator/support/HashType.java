package com.smlnskgmail.jaman.hashchecker.hashgenerator.support;

import android.content.Context;
import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.ListItemMarker;

public enum HashType implements ListItemMarker {

    MD5(R.string.hash_type_md5, "MD5"),
    SHA_1(R.string.hash_type_sha1, "SHA-1"),
    SHA_224(R.string.hash_type_sha224, "SHA-224"),
    SHA_256(R.string.hash_type_sha256, "SHA-256"),
    SHA_384(R.string.hash_type_sha384, "SHA-384"),
    SHA_512(R.string.hash_type_sha512, "SHA-512"),
    CRC_32(R.string.hash_type_crc32, "");

    private int hashTypeNameResId;
    private String messageDigestHashName;

    HashType(int hashTypeNameResId, String messageDigestHashName) {
        this.hashTypeNameResId = hashTypeNameResId;
        this.messageDigestHashName = messageDigestHashName;
    }

    public String getMessageDigestHashName() {
        return messageDigestHashName;
    }

    @NonNull
    public String getTypeAsString(@NonNull Context context) {
        return context.getString(hashTypeNameResId);
    }

    @NonNull
    public static HashType parseHashTypeFromString(@NonNull Context context, @NonNull String data) {
        if (data.equals(context.getString(SHA_1.hashTypeNameResId))) {
            return SHA_1;
        } else if (data.equals(context.getString(SHA_224.hashTypeNameResId))) {
            return SHA_224;
        } else if (data.equals(context.getString(SHA_256.hashTypeNameResId))) {
            return SHA_256;
        } else if (data.equals(context.getString(SHA_384.hashTypeNameResId))) {
            return SHA_384;
        } else if (data.equals(context.getString(SHA_512.hashTypeNameResId))) {
            return SHA_512;
        } else if (data.equals(context.getString(CRC_32.hashTypeNameResId))) {
            return CRC_32;
        }
        return MD5;
    }

    @Override
    public int getTitleTextResId() {
        return hashTypeNameResId;
    }

    @Override
    public int getPrimaryIconResId() {
        return -1;
    }

    @Override
    public int getAdditionalIconResId() {
        return R.drawable.ic_done;
    }

    public static boolean isMessageDigestUtilSupport(HashType hashType) {
        return hashType != HashType.CRC_32;
    }

}
