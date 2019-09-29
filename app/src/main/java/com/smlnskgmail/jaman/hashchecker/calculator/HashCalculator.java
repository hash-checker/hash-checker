package com.smlnskgmail.jaman.hashchecker.calculator;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smlnskgmail.jaman.hashchecker.calculator.support.HashType;
import com.smlnskgmail.jaman.hashchecker.support.prefs.SettingsHelper;
import com.smlnskgmail.jaman.hashchecker.utils.HashUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.CRC32;

public class HashCalculator {

    private final HashType hashType;

    public HashCalculator(@NonNull HashType hashType) {
        this.hashType = hashType;
    }

    @Nullable
    public String fromString(@NonNull String text) throws NoSuchAlgorithmException {
        byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
        if (HashType.isMessageDigestUtilSupport(hashType)) {
            MessageDigest messageDigest = MessageDigest.getInstance(hashType.getMessageDigestHashName());
            messageDigest.update(bytes);
            return HashUtils.getStringFromBytes(messageDigest.digest());
        } else {
            CRC32 crc32 = new CRC32();
            crc32.update(bytes);
            return HashUtils.getStringFromLong(crc32.getValue());
        }
    }

    @Nullable
    String fromFile(@NonNull Context context, @NonNull Uri path) throws Exception {
        InputStream fileStream = inputStreamFromUri(context, path);
        return fromFile(fileStream);
    }

    private InputStream inputStreamFromUri(@NonNull Context context, @NonNull Uri path)
            throws Exception {
        if (!SettingsHelper.isUsingInnerFileManager(context)
                || SettingsHelper.getGenerateFromShareIntentStatus(context)) {
            return context.getContentResolver().openInputStream(path);
        }
        return new FileInputStream(new File(new URI(path.toString())));
    }

    @Nullable
    public String fromFile(@Nullable InputStream inputStream) throws Exception {
        if (inputStream != null) {
            byte[] buffer = new byte[1024];
            CheckerMessageDigest checkerMessageDigest = new CheckerMessageDigest(hashType);
            checkerMessageDigest.init();
            int read;
            do {
                read = inputStream.read(buffer);
                if (read > 0) {
                    checkerMessageDigest.update(buffer, read);
                }
            } while (read != -1);
            return checkerMessageDigest.result();
        }
        return null;
    }

}
