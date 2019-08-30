package com.smlnskgmail.jaman.hashchecker.calculator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.calculator.support.HashGeneratorTarget;
import com.smlnskgmail.jaman.hashchecker.calculator.support.HashType;
import com.smlnskgmail.jaman.hashchecker.support.logs.L;

public class HashGenerator extends AsyncTask<Void, String, String> {

    @SuppressLint("StaticFieldLeak")
    private final Context context;

    private final HashGeneratorTarget completeListener;
    private final HashType hashType;

    private Uri fileUri;
    private String textValue;

    private final boolean isText;

    public HashGenerator(@NonNull HashType hashType, @NonNull Context context, @NonNull Uri fileUri,
                         @NonNull HashGeneratorTarget completeListener) {
        this(hashType, context, completeListener, false);
        this.fileUri = fileUri;
    }

    public HashGenerator(@NonNull HashType hashType, @NonNull Context context, @NonNull String textValue,
                         @NonNull HashGeneratorTarget completeListener) {
        this(hashType, context, completeListener, true);
        this.textValue = textValue;

    }

    private HashGenerator(@NonNull HashType hashType, @NonNull Context context,
                          @NonNull HashGeneratorTarget completeListener, boolean isText) {
        this.hashType = hashType;
        this.context = context;
        this.completeListener = completeListener;
        this.isText = isText;
    }

    @Override
    protected String doInBackground(Void... voids) {
        HashType hashType = HashType.MD5;
        switch (this.hashType) {
            case SHA_1:
                hashType = HashType.SHA_1;
                break;
            case SHA_224:
                hashType = HashType.SHA_224;
                break;
            case SHA_256:
                hashType = HashType.SHA_256;
                break;
            case SHA_384:
                hashType = HashType.SHA_384;
                break;
            case SHA_512:
                hashType = HashType.SHA_512;
                break;
            case CRC_32:
                hashType = HashType.CRC_32;
                break;
        }
        try {
            if (isText) {
                return new HashCalculator(hashType).fromString(textValue);
            } else {
                return new HashCalculator(hashType).fromFile(context, fileUri);
            }
        } catch (Exception e) {
            L.e(e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        completeListener.hashGenerationComplete(result);
    }

}
