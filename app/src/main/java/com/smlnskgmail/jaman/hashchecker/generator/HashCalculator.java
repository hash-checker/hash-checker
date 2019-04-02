package com.smlnskgmail.jaman.hashchecker.generator;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.smlnskgmail.jaman.hashchecker.support.preferences.Preferences;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashCalculator {

    private static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();

    private String hashType;

    public HashCalculator(@NonNull String hashType) {
        this.hashType = hashType;
    }

    @NonNull
    public String generateFromString(@NonNull String text) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(hashType);
            messageDigest.reset();
            messageDigest.update(text.getBytes(StandardCharsets.UTF_8));
            return getResultAsString(messageDigest.digest());
        } catch (Exception e) {
            return "";
        }
    }

    @NonNull
    String generateFromFile(@NonNull Context context, @NonNull Uri path) {
        try {
            InputStream fileStream = Preferences.isUsingInnerFileManager(context)
                    ? new FileInputStream(new File(new URI(path.toString())))
                    : context.getContentResolver().openInputStream(path);
            return generateFromFile(fileStream);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @NonNull
    public String generateFromFile(@Nullable InputStream inputStream)
            throws IOException, NoSuchAlgorithmException, SecurityException {
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
