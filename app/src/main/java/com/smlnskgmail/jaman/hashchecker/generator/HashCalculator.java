package com.smlnskgmail.jaman.hashchecker.generator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

public class HashCalculator extends AsyncTask<Void, Void, Void> {

    @SuppressLint("StaticFieldLeak") private Context context;
    private Generator.OnGeneratorCompleteListener onCompleteListener;
    private Uri fileUri;
    private String textValue, result;
    private HashTypes hashTypes;

    private boolean isText;

    private HashCalculator(@NonNull HashTypes hashTypes, @NonNull Context context,
                           @NonNull Generator.OnGeneratorCompleteListener onCompleteListener, boolean isText) {
        this.hashTypes = hashTypes;
        this.context = context;
        this.onCompleteListener = onCompleteListener;
        this.isText = isText;
    }

    public HashCalculator(@NonNull HashTypes hashTypes, @NonNull Context context, @NonNull Uri fileUri,
                          @NonNull Generator.OnGeneratorCompleteListener onCompleteListener, boolean isText) {
        this(hashTypes, context, onCompleteListener, isText);
        this.fileUri = fileUri;
    }

    public HashCalculator(@NonNull HashTypes hashTypes, @NonNull Context context, @NonNull String textValue,
                          @NonNull Generator.OnGeneratorCompleteListener onCompleteListener, boolean isText) {
        this(hashTypes, context, onCompleteListener, isText);
        this.textValue = textValue;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        HashTypes hashType = HashTypes.MD5;
        switch (hashTypes) {
            case SHA_1:
                hashType = HashTypes.SHA_1;
                break;
            case SHA_224:
                hashType = HashTypes.SHA_224;
                break;
            case SHA_256:
                hashType = HashTypes.SHA_256;
                break;
            case SHA_384:
                hashType = HashTypes.SHA_384;
                break;
            case SHA_512:
                hashType = HashTypes.SHA_512;
                break;
        }
        if (isText) {
            result = new Generator(hashType).generateFromString(context, textValue);
        } else {
            result = new Generator(hashType).generateFromFile(context, fileUri);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        onCompleteListener.onComplete(result);
    }

}
