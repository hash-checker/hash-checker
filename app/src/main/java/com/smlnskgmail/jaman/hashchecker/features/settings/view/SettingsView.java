package com.smlnskgmail.jaman.hashchecker.features.settings.view;

import android.content.Intent;
import android.util.Pair;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.features.settings.presenter.SettingsPresenter;

public interface SettingsView {

    void initLanguageSettings();

    void initThemeSettings();

    void initPrivacyPolicy();

    void initUserDataExport();

    void initAuthorLinks();

    void initLibrariesLinks();

    void initHelpWithTranslations();

    void initRate();

    void initFeedback();

    void initAppVersionInfo(@NonNull String appVersion);

    void saveUserDataResult(@NonNull Pair<SettingsPresenter.SaveUserDataResult, Intent> result);

}
