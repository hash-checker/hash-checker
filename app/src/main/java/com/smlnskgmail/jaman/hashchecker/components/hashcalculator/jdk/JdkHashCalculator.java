package com.smlnskgmail.jaman.hashchecker.components.hashcalculator.jdk;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.aelstad.keccakj.provider.Constants;
import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashCalculator;
import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashType;
import com.smlnskgmail.jaman.hashchecker.utils.LogUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;

public class JdkHashCalculator implements HashCalculator {

    private JdkHashCalculatorDigest jdkHashCalculatorDigest;

    @Override
    public void setHashType(@NonNull HashType hashType) {
        try {
            if (hashType.isKeccakj()) {
                jdkHashCalculatorDigest = JdkHashCalculatorDigest.instanceFor(hashType, Constants.PROVIDER);
            } else {
                jdkHashCalculatorDigest = JdkHashCalculatorDigest.instanceFor(hashType);
            }
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            LogUtils.e(e);
        }
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
    public String fromFile(@NonNull Context context, @NonNull Uri path) {
        try {
            InputStream fileStream = inputStreamFromUri(context, path);
            return fromFile(fileStream);
        } catch (Exception e) {
            LogUtils.e(e);
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
                        jdkHashCalculatorDigest.update(buffer, read);
                    }
                } while (read != -1);
                return jdkHashCalculatorDigest.result();
            } catch (IOException e) {
                LogUtils.e(e);
            }
        }
        return null;
    }

    @Nullable
    @Override
    public String fromFolder(@NonNull Context context, @NonNull Uri path) {
        try {
            List<InputStream> fileStream = inputStreamsFormFolder(context, path);
            return fromFolder(fileStream);
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return null;
    }

    @Nullable

    public String fromFolder(@Nullable List<InputStream> inputStream) {
        if (inputStream != null) {
            for (InputStream stream : inputStream) {
                try {
                    byte[] buffer = new byte[1024];
                    int read;
                    do {
                        read = stream.read(buffer);
                        if (read > 0) {
                            jdkHashCalculatorDigest.update(buffer, read);
                        }
                    } while (read != -1);
                } catch (IOException e) {
                    LogUtils.e(e);
                }
            }
            return jdkHashCalculatorDigest.result();
        }
        return null;
    }

    @Nullable
    private InputStream inputStreamFromUri(@NonNull Context context, @NonNull Uri path) throws Exception {
        return context.getContentResolver().openInputStream(path);
    }

    @Nullable
    private List<InputStream> inputStreamsFormFolder(Context context, Uri folderUri) throws IOException {
        List<InputStream> inputStreams = new ArrayList<>();

        ContentResolver contentResolver = context.getContentResolver();
        Uri childrenUri = DocumentsContract.buildChildDocumentsUriUsingTree(folderUri,
                DocumentsContract.getTreeDocumentId(folderUri));

        Cursor cursor = contentResolver.query(childrenUri,
                new String[]{DocumentsContract.Document.COLUMN_DOCUMENT_ID}, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String documentId = cursor.getString(0);
                Uri documentUri = DocumentsContract.buildDocumentUriUsingTree(folderUri, documentId);

                try {
                    InputStream inputStream = inputStreamFromUri(context, documentUri);
                    if (inputStream != null) {
                        inputStreams.add(inputStream);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            cursor.close();
        }

        return inputStreams;
    }

}
