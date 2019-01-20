package com.smlnskgmail.jaman.hashchecker.generator;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashCalculator {

    public interface OnHashGeneratorCompleteListener {

        void onHashGeneratorComplete(@NonNull String hashValue);

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
            messageDigest.update(text.getBytes(StandardCharsets.UTF_8));
            return getResultAsString(messageDigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @NonNull
    String generateFromFile(@NonNull Context context, @NonNull Uri path) {
        try {
            return generateFromFile(context.getContentResolver().openInputStream(path));
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    @NonNull
    String generateFromFile(@Nullable InputStream inputStream) throws IOException, NoSuchAlgorithmException {
        if (inputStream != null) {
            byte[] buffer = new byte[1024];
            MessageDigest messageDigest = MessageDigest.getInstance(hashType);
            int read;
            do {
                read = inputStream.read(buffer);
                if (read > 0) {
                    messageDigest.update(buffer, 0, read);
                }
            } while (read != -1);
            return getResultAsString(messageDigest.digest());
        }
        return "";
    }

    private String getResultAsString(byte[] data) {
        char[] chars = new char[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            chars[i * 2] = HEX_DIGITS[(data[i] >> 4) & 0xf];
            chars[i * 2 + 1] = HEX_DIGITS[data[i] & 0xf];
        }
        return new String(chars).toLowerCase();
    }

}
