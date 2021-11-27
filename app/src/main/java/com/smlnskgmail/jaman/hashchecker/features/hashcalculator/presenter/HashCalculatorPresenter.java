package com.smlnskgmail.jaman.hashchecker.features.hashcalculator.presenter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashType;
import com.smlnskgmail.jaman.hashchecker.components.localdatastorage.api.LocalDataStorage;
import com.smlnskgmail.jaman.hashchecker.components.settings.api.Settings;
import com.smlnskgmail.jaman.hashchecker.features.hashcalculator.view.HashCalculatorView;

public interface HashCalculatorPresenter {

    void init(
            @NonNull HashCalculatorView view,
            @NonNull LocalDataStorage localDataStorage,
            @NonNull Settings settings,
            @Nullable Bundle shortcutArguments
    );

    void generateHash(@NonNull Context context);

    void setHashType(@NonNull HashType hashType);

    void setObjectValue(@NonNull String objectValue, boolean isText);

    boolean isTextIsSelected();

    void exportAsFile();
}
