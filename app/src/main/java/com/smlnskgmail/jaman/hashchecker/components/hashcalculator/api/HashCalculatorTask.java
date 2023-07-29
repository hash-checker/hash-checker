package com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.jdk.JdkHashCalculator;
import com.smlnskgmail.jaman.hashchecker.utils.LogUtils;

public class HashCalculatorTask extends AsyncTask<Void, String, String> {

    @SuppressLint("StaticFieldLeak")
    private final Context context;

    private final HashCalculatorTaskTarget completeListener;
    private final HashType hashType;

    private Uri fileUri;
    private Uri folderUri;
    private String textValue;

    private final boolean isText;

    public HashCalculatorTask(
            @NonNull Context context,
            @NonNull HashType hashType,
            @NonNull Uri fileUri,
            @NonNull Uri folderUri,
            @NonNull HashCalculatorTaskTarget completeListener
    ) {
        this(context, hashType, completeListener, false);
        this.fileUri = fileUri;
        this.folderUri = folderUri;
    }

    public HashCalculatorTask(
            @NonNull Context context,
            @NonNull HashType hashType,
            @NonNull String textValue,
            @NonNull HashCalculatorTaskTarget completeListener
    ) {
        this(context, hashType, completeListener, true);
        this.textValue = textValue;

    }

    private HashCalculatorTask(
            @NonNull Context context,
            @NonNull HashType hashType,
            @NonNull HashCalculatorTaskTarget completeListener,
            boolean isText
    ) {
        this.hashType = hashType;
        this.context = context;
        this.completeListener = completeListener;
        this.isText = isText;
    }

    @Nullable
    @SuppressWarnings("MethodParametersAnnotationCheck")
    @Override
    protected String doInBackground(Void... voids) {
        try {
            HashCalculator hashCalculator = new JdkHashCalculator();
            hashCalculator.setHashType(hashType);
            if (isText) {
                return hashCalculator.fromString(textValue);
            } else if (fileUri != null) {
                return hashCalculator.fromFile(context, fileUri);
            } else {
                return hashCalculator.fromFolder(context, folderUri);
            }
        } catch (Exception e) {
            LogUtils.e(e);
            return null;
        }
    }

    @SuppressWarnings("MethodParametersAnnotationCheck")
    @Override
    protected void onPostExecute(String result) {
        completeListener.hashCalculationComplete(result);
    }

    public interface HashCalculatorTaskTarget {

        void hashCalculationComplete(@Nullable String hashValue);

    }

}
