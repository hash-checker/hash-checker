package com.smlnskgmail.jaman.hashchecker.features.settings.presenter;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.components.localdatastorage.api.LocalDataStorage;
import com.smlnskgmail.jaman.hashchecker.features.settings.view.SettingsView;

public interface SettingsPresenter {

    void init(@NonNull SettingsView view, @NonNull LocalDataStorage localDataStorage);

    void saveUserData();

    enum SaveUserDataResult {
        DONE,
        ERROR,
        EMPTY,
    }

}

