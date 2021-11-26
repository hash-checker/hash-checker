package com.smlnskgmail.jaman.hashchecker.logic.settings.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.api.HashType;
import com.smlnskgmail.jaman.hashchecker.logic.locale.api.Language;
import com.smlnskgmail.jaman.hashchecker.logic.themes.api.Theme;

public interface SettingsHelper {

    int FILE_CREATE = 3;

    int HASH_GENERATION_COUNT_BEFORE_RATE_APP_DIALOG_CALL = 5;

    void saveHashTypeAsLast(@NonNull HashType hashType);

    @NonNull
    HashType getLastHashType();

    boolean languageIsInitialized();

    void saveLanguage(@NonNull Language language);

    @NonNull
    Language getLanguage();

    boolean isUsingInnerFileManager();

    void savePathForInnerFileManager(@Nullable String path);

//    @NonNull
//    String getLastPathForInnerFileManager();

    boolean isUsingMultilineHashFields();

    boolean canSaveResultToHistory();

    boolean getVibrateAccess();

    @NonNull
    Theme getSelectedTheme();

    void saveTheme(@NonNull Theme theme);

    boolean useUpperCase();

    boolean isShortcutsIsCreated();

    void saveShortcutsStatus(boolean value);

    boolean getGenerateFromShareIntentStatus();

    void setGenerateFromShareIntentMode(boolean status);

    boolean refreshSelectedFile();

    void setRefreshSelectedFileStatus(boolean status);

    boolean canShowRateAppDialog();

    void increaseHashGenerationCount();

}
