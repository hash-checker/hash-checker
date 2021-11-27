package com.smlnskgmail.jaman.hashchecker.components.settings.api;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashType;
import com.smlnskgmail.jaman.hashchecker.components.locale.api.Language;
import com.smlnskgmail.jaman.hashchecker.components.theme.api.Theme;

public interface Settings {

    int FILE_CREATE = 3;

    int HASH_GENERATION_COUNT_BEFORE_RATE_APP_DIALOG_CALL = 5;

    void saveHashTypeAsLast(@NonNull HashType hashType);

    @NonNull
    HashType getLastHashType();

    boolean languageIsInitialized();

    void saveLanguage(@NonNull Language language);

    @NonNull
    Language getLanguage();

    boolean isUsingMultilineHashFields();

    boolean canSaveResultToHistory();

    boolean getVibrateAccess();

    @NonNull
    Theme getSelectedTheme();

    void saveTheme(@NonNull Theme theme);

    boolean useUpperCase();

    boolean isShortcutsIsCreated();

    void saveShortcutsStatus(boolean value);

    void setGenerateFromShareIntentMode(boolean status);

    boolean refreshSelectedFile();

    void setRefreshSelectedFileStatus(boolean status);

    boolean canShowRateAppDialog();

    void increaseHashGenerationCount();

}
