package com.smlnskgmail.jaman.hashchecker.utils;

import android.support.annotation.NonNull;
import android.widget.EditText;

public class TextUtils {

    public static void convertToUpperCase(@NonNull EditText etText) {
        etText.setText(etText.getText().toString().toUpperCase());
    }

    public static void convertToLowerCase(@NonNull EditText etText) {
        etText.setText(etText.getText().toString().toLowerCase());
    }

    public static boolean fieldIsNotEmpty(@NonNull EditText etToCheck) {
        return !etToCheck.getText().toString().equals("");
    }

    public static boolean compareText(@NonNull String firstText, @NonNull String secondText) {
        return firstText.toLowerCase().equals(secondText.toLowerCase());
    }

}
