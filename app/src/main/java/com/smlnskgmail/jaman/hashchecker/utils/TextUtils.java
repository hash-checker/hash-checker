package com.smlnskgmail.jaman.hashchecker.utils;

import android.support.annotation.NonNull;
import android.widget.EditText;

public class TextUtils {

    public static boolean compareText(@NonNull String firstValue, @NonNull String secondValue) {
        return firstValue.toLowerCase().equals(secondValue.toLowerCase());
    }

    public static boolean fieldIsNotEmpty(@NonNull EditText fieldToCheck) {
        return !fieldToCheck.getText().toString().equals("");
    }

    public static void convertToUpperCase(@NonNull EditText editText) {
        editText.setText(editText.getText().toString().toUpperCase());
    }

    public static void convertToLowerCase(@NonNull EditText editText) {
        editText.setText(editText.getText().toString().toLowerCase());
    }

}
