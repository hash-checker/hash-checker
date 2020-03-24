package com.smlnskgmail.jaman.hashchecker.logic.hashcalculator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.jdk.JdkHashCalculator;
import com.smlnskgmail.jaman.hashchecker.logic.logs.L;

public class HashCalculatorTask extends AsyncTask<Void, String, String> {

    @SuppressLint("StaticFieldLeak")
    private final Context context;

    private final HashCalculatorTaskTarget completeListener;
    private final HashType hashType;

    private Uri fileUri;
    private String textValue;

    private final boolean isText;

    public HashCalculatorTask(
            @NonNull HashType hashType,
            @NonNull Context context,
            @NonNull Uri fileUri,
            @NonNull HashCalculatorTaskTarget completeListener
    ) {
        this(hashType, context, completeListener, false);
        this.fileUri = fileUri;
    }

    public HashCalculatorTask(
            @NonNull HashType hashType,
            @NonNull Context context,
            @NonNull String textValue,
            @NonNull HashCalculatorTaskTarget completeListener
    ) {
        this(hashType, context, completeListener, true);
        this.textValue = textValue;

    }

    private HashCalculatorTask(
            @NonNull HashType hashType,
            @NonNull Context context,
            @NonNull HashCalculatorTaskTarget completeListener,
            boolean isText
    ) {
        this.hashType = hashType;
        this.context = context;
        this.completeListener = completeListener;
        this.isText = isText;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            HashCalculator hashCalculator = new JdkHashCalculator();
            hashCalculator.setHashType(hashType);
            return !isText
                    ? hashCalculator.fromFile(context, fileUri)
                    : hashCalculator.fromString(textValue);
        } catch (Exception e) {
            L.e(e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        completeListener.hashCalculationComplete(result);
    }

    public interface HashCalculatorTaskTarget {

        void hashCalculationComplete(@Nullable String hashValue);

    }

}
