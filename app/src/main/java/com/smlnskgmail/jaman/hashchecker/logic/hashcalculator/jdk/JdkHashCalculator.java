package com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.jdk;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.HashCalculator;
import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.HashType;
import com.smlnskgmail.jaman.hashchecker.logic.logs.L;
import com.smlnskgmail.jaman.hashchecker.logic.settings.SettingsHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

public class JdkHashCalculator implements HashCalculator {

    private JdkHashCalculatorDigest jdkHashCalculatorDigest;

    @Override
    public void setHashType(
            @NonNull HashType hashType
    ) throws NoSuchAlgorithmException {
        this.jdkHashCalculatorDigest
                = JdkHashCalculatorDigest.instanceFor(hashType);
    }

    @Nullable
    @Override
    public String fromString(@NonNull String text) {
        byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
        jdkHashCalculatorDigest.update(bytes);
        return jdkHashCalculatorDigest.result();
    }

    @Nullable
    @Override
    public String fromFile(
            @NonNull Context context,
            @NonNull Uri path
    ) {
        try {
            InputStream fileStream = inputStreamFromUri(
                    context,
                    path
            );
            return fromFile(fileStream);
        } catch (Exception e) {
            L.e(e);
        }
        return null;
    }

    @Nullable
    @Override
    public String fromFile(@Nullable InputStream inputStream) {
        if (inputStream != null) {
            try {
                byte[] buffer = new byte[1024];
                int read;
                do {
                    read = inputStream.read(buffer);
                    if (read > 0) {
                        jdkHashCalculatorDigest.update(
                                buffer,
                                read
                        );
                    }
                } while (read != -1);
                return jdkHashCalculatorDigest.result();
            } catch (IOException e) {
                L.e(e);
            }
        }
        return null;
    }

    private InputStream inputStreamFromUri(
            @NonNull Context context,
            @NonNull Uri path
    ) throws Exception {
        if (!SettingsHelper.isUsingInnerFileManager(context)
                || SettingsHelper.getGenerateFromShareIntentStatus(context)) {
            return context.getContentResolver().openInputStream(path);
        }
        return new FileInputStream(
                new File(
                        new URI(path.toString())
                )
        );
    }

}
