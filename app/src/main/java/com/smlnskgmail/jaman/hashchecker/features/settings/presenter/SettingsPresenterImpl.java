package com.smlnskgmail.jaman.hashchecker.features.settings.presenter;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.util.Pair;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.BuildConfig;
import com.smlnskgmail.jaman.hashchecker.components.localdatastorage.api.LocalDataExporter;
import com.smlnskgmail.jaman.hashchecker.components.localdatastorage.api.LocalDataStorage;
import com.smlnskgmail.jaman.hashchecker.features.settings.view.SettingsView;
import com.smlnskgmail.jaman.hashchecker.utils.LogUtils;

public class SettingsPresenterImpl implements SettingsPresenter {

    private LocalDataStorage localDataStorage;
    private SettingsView view;

    @Override
    public void init(@NonNull SettingsView view, @NonNull LocalDataStorage localDataStorage) {
        this.localDataStorage = localDataStorage;
        this.view = view;
        view.initLanguageSettings();
        view.initThemeSettings();
        view.initPrivacyPolicy();
        view.initUserDataExport();
        view.initAuthorLinks();
        view.initLibrariesLinks();
        view.initHelpWithTranslations();
        view.initRate();
        view.initFeedback();
        view.initAppVersionInfo(
                String.format(
                        "%s (%s)",
                        BuildConfig.VERSION_NAME,
                        BuildConfig.VERSION_CODE
                )
        );
    }

    @Override
    public void saveUserData() {
        if (localDataStorage.isHistoryItemsListIsEmpty()) {
            try {
                Intent saveFileIntent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
                saveFileIntent.addCategory(Intent.CATEGORY_OPENABLE);
                saveFileIntent.setType("application/zip");
                saveFileIntent.putExtra(Intent.EXTRA_TITLE, LocalDataExporter.EXPORT_FILE);
                view.saveUserDataResult(Pair.create(SaveUserDataResult.DONE, saveFileIntent));
            } catch (ActivityNotFoundException e) {
                LogUtils.e(e);
                view.saveUserDataResult(Pair.create(SaveUserDataResult.ERROR, null));
            }
        } else {
            view.saveUserDataResult(Pair.create(SaveUserDataResult.EMPTY, null));
        }
    }

}
