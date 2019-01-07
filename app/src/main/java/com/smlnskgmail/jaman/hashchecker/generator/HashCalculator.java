package com.smlnskgmail.jaman.hashchecker.generator;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

public class HashCalculator {

    public interface OnGeneratorCompleteListener {

        void onComplete(@NonNull String hashValue);

    }

    private static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();

    private String hashType;

    HashCalculator(@NonNull String hashType) {
        this.hashType = hashType;
    }

    @NonNull
    String generateFromString(@NonNull String text) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(hashType);
            messageDigest.reset();
            messageDigest.update(text.getBytes("UTF-8"));
            return toHex(messageDigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @NonNull
    String generateFromFile(@NonNull Context context, @NonNull Uri path) {
        try {
            InputStream stream = context.getContentResolver().openInputStream(path);
            if (stream != null) {
                byte[] buffer = new byte[1024];
                MessageDigest messageDigest = MessageDigest.getInstance(hashType);
                int read;
                do {
                    read = stream.read(buffer);
                    if (read > 0) {
                        messageDigest.update(buffer, 0, read);
                    }
                } while (read != -1);
                return toHex(messageDigest.digest());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private String toHex(byte[] data) {
        char[] chars = new char[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            chars[i * 2] = HEX_DIGITS[(data[i] >> 4) & 0xf];
            chars[i * 2 + 1] = HEX_DIGITS[data[i] & 0xf];
        }
        return new String(chars).toLowerCase();
    }

}
