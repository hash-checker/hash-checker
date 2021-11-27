package com.smlnskgmail.jaman.hashchecker.features.hashcalculator.view;

import android.net.Uri;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface HashCalculatorView {

    void setGeneratedHash(@NonNull Pair<HashGenerationResult, String> result);

    void showProgress();

    void hideProgress();

    void showRateDialog();

    void showNoObjectSelected();

    void saveTextFile(@Nullable Uri uri, boolean isTestIsSelected);

    enum HashGenerationResult {
        done,
        error,
    }

}
