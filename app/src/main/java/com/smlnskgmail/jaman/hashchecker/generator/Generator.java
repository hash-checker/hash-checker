package com.smlnskgmail.jaman.hashchecker.generator;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

public class Generator {

    public interface OnGeneratorCompleteListener {

        void onComplete(@NonNull String hashValue);

    }

    private HashTypes hashType;

    Generator(@NonNull HashTypes hashType) {
        this.hashType = hashType;
    }

    @NonNull
    String generateFromString(@NonNull Context context, @NonNull String text) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(hashType.getTypeAsString(context));
            messageDigest.reset();
            messageDigest.update(text.getBytes("UTF-8"));
            return new BigInteger(1, messageDigest.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @NonNull
    String generateFromFile(@NonNull Context context, @NonNull Uri path) {
        try {
            InputStream stream = context.getContentResolver().openInputStream(path);
            if (stream != null) {
                byte[] buffer = new byte[1024];
                MessageDigest digest = MessageDigest.getInstance(hashType.getTypeAsString(context));
                int read;
                do {
                    read = stream.read(buffer);
                    if (read > 0) {
                        digest.update(buffer, 0, read);
                    }
                } while (read != -1);
                return new BigInteger(1, digest.digest()).toString(16);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
