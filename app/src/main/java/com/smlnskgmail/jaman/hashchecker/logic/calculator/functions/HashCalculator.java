package com.smlnskgmail.jaman.hashchecker.logic.calculator.functions;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smlnskgmail.jaman.hashchecker.logic.settings.SettingsHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

public class HashCalculator {

    private HashCalculatorDigest hashCalculatorDigest;

    private HashCalculator() {

    }

    public static HashCalculator newInstance(@NonNull HashType hashType) throws NoSuchAlgorithmException {
        HashCalculator hashCalculator = new HashCalculator();
        hashCalculator.setHashType(hashType);
        return hashCalculator;
    }

    private void setHashType(@NonNull HashType hashType) throws NoSuchAlgorithmException {
        this.hashCalculatorDigest = HashCalculatorDigest.newInstance(hashType);
    }

    @Nullable
    public String fromString(@NonNull String text) {
        byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
        hashCalculatorDigest.update(bytes);
        return hashCalculatorDigest.result();
    }

    @Nullable
    public String fromFile(@NonNull Context context, @NonNull Uri path) throws Exception {
        InputStream fileStream = inputStreamFromUri(context, path);
        return fromFile(fileStream);
    }

    @Nullable
    public String fromFile(@Nullable InputStream inputStream) throws Exception {
        if (inputStream != null) {
            byte[] buffer = new byte[1024];
            int read;
            do {
                read = inputStream.read(buffer);
                if (read > 0) {
                    hashCalculatorDigest.update(buffer, read);
                }
            } while (read != -1);
            return hashCalculatorDigest.result();
        }
        return null;
    }

    private InputStream inputStreamFromUri(@NonNull Context context, @NonNull Uri path)
            throws Exception {
        if (!SettingsHelper.isUsingInnerFileManager(context)
                || SettingsHelper.getGenerateFromShareIntentStatus(context)) {
            return context.getContentResolver().openInputStream(path);
        }
        return new FileInputStream(new File(new URI(path.toString())));
    }

}
