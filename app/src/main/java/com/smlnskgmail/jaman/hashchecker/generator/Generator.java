package com.smlnskgmail.jaman.hashchecker.generator;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

public class Generator {

    private HashTypes hashType;

    public interface IGeneratorCompleteListener {

        void onGeneratingComplete(@NonNull String hashValue);

    }

    public interface IGeneratorResultAvailable {

        void onResultAvailable(@NonNull String text);

    }

    Generator(@NonNull HashTypes hashType) {
        this.hashType = hashType;
    }

    @NonNull
    String generateFromString(@NonNull String text) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance(hashType.getTypeAsString());
        messageDigest.reset();
        messageDigest.update(text.getBytes("UTF-8"));
        return new BigInteger(1, messageDigest.digest()).toString(16);
    }

    @NonNull
    String generateFromFile(@NonNull Context context, @NonNull Uri path) throws Exception {
        InputStream stream = context.getContentResolver().openInputStream(path);
        if (stream != null) {
            byte[] buffer = new byte[1024];
            MessageDigest digest = MessageDigest.getInstance(hashType.getTypeAsString());
            int read;
            do {
                read = stream.read(buffer);
                if (read > 0) {
                    digest.update(buffer, 0, read);
                }
            } while (read != -1);
            return new BigInteger(1, digest.digest()).toString(16);
        } else {
            return "";
        }
    }

}
