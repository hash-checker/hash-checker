package com.smlnskgmail.jaman.hashchecker.components.hashcalculator.jdk;

import androidx.annotation.NonNull;

public class JdkHashUtils {

    private static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();

    private JdkHashUtils() {

    }

    @NonNull
    public static String getStringFromByteArray(@NonNull byte[] data) {
        char[] chars = new char[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            chars[i * 2] = HEX_DIGITS[(data[i] >> 4) & 0xf];
            chars[i * 2 + 1] = HEX_DIGITS[data[i] & 0xf];
        }
        return new String(chars).toLowerCase();
    }

    @NonNull
    public static String getStringFromLong(long data) {
        return String.format("%08x", data);
    }

}
