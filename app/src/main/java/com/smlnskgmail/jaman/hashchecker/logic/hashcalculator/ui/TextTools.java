package com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.ui;

import android.widget.EditText;

import androidx.annotation.NonNull;

public class TextTools {

    public static boolean compareText(
            @NonNull String firstValue,
            @NonNull String secondValue
    ) {
        return firstValue.equalsIgnoreCase(secondValue.toLowerCase());
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
