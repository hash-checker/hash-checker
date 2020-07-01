package com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.jdk;

public class JdkHashTools {

    private static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();

    public static String getStringFromByteArray(byte[] data) {
        char[] chars = new char[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            chars[i * 2] = HEX_DIGITS[(data[i] >> 4) & 0xf];
            chars[i * 2 + 1] = HEX_DIGITS[data[i] & 0xf];
        }
        return new String(chars).toLowerCase();
    }

    public static String getStringFromLong(long data) {
        return String.format(
                "%08x",
                data
        );
    }

}