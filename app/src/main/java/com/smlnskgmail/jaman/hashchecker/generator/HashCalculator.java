package com.smlnskgmail.jaman.hashchecker.generator;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;

public class HashCalculator extends AsyncTask<Void, Void, Void> {

    @SuppressLint("StaticFieldLeak") private Context context;
    private Generator.IGeneratorCompleteListener value;
    private Uri fileUri;
    private String message;

    private boolean isText;

    private ProgressDialog generateDialog;
    private HashTypes type;
    private String result;
    private int progress;

    public HashCalculator(@NonNull HashTypes type, @NonNull Context context, @NonNull Uri fileUri,
                          @NonNull Generator.IGeneratorCompleteListener value, boolean isText) {
        this.type = type;
        this.context = context;
        this.fileUri = fileUri;
        this.value = value;
        this.isText = isText;
    }

    public HashCalculator(@NonNull HashTypes type, @NonNull Context context, @NonNull String message,
                          @NonNull Generator.IGeneratorCompleteListener value, boolean isText) {
        this.type = type;
        this.context = context;
        this.message = message;
        this.value = value;
        this.isText = isText;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        generateDialog = ProgressDialog.show(context, "", context.getString(R.string.message_generate_dialog), true);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        while (progress == 0) {
            HashTypes hashType = HashTypes.MD5;
            switch (type) {
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
            try {
                if (isText) {
                    result = new Generator(hashType).generateFromString(message);
                } else {
                    result = new Generator(hashType).generateFromFile(context, fileUri);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            progress = 1;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (generateDialog != null) {
            generateDialog.dismiss();
        }
        value.onGeneratingComplete(result);
    }

}
