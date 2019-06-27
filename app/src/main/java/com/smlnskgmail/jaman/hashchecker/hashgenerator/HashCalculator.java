package com.smlnskgmail.jaman.hashchecker.hashgenerator;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.smlnskgmail.jaman.hashchecker.hashgenerator.support.HashType;
import com.smlnskgmail.jaman.hashchecker.support.logs.L;
import com.smlnskgmail.jaman.hashchecker.support.prefs.PrefsHelper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;

public class HashCalculator {

    private HashType hashType;

    public HashCalculator(@NonNull HashType hashType) {
        this.hashType = hashType;
    }

    @Nullable
    public String generateFromString(@NonNull String text) {
        try {
            return new HashDigest(hashType).getResultFromString(text);
        } catch (Exception e) {
            L.error(e);
            return null;
        }
    }

    @Nullable
    String generateFromFile(@NonNull Context context, @NonNull Uri path) {
        try {
            InputStream fileStream = getInputStreamFromUri(context, path);
            return generateFromFile(fileStream);
        } catch (Exception e) {
            L.error(e);
            return null;
        }
    }

    private InputStream getInputStreamFromUri(@NonNull Context context, @NonNull Uri path)
            throws Exception {
        if (!PrefsHelper.isUsingInnerFileManager(context)
                || PrefsHelper.getGenerateFromShareIntentStatus(context)) {
            return context.getContentResolver().openInputStream(path);
        }
        return new FileInputStream(new File(new URI(path.toString())));
    }

    @Nullable
    public String generateFromFile(@Nullable InputStream inputStream) throws Exception {
        if (inputStream != null) {
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int read;
            do {
                read = inputStream.read(buffer);
                if (read > 0) {
                    byteArrayOutputStream.write(buffer, 0, read);
                }
            } while (read != -1);
            return new HashDigest(hashType).getResultFromByteArray(byteArrayOutputStream.toByteArray());
        }
        return null;
    }

}
