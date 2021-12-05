package com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.InputStream;

public interface HashCalculator {

    void setHashType(@NonNull HashType hashType);

    @Nullable
    String fromString(@NonNull String text);

    @Nullable
    String fromFile(@NonNull Context context, @NonNull Uri path);

    @Nullable
    String fromFile(@Nullable InputStream inputStream);

}
