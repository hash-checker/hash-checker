package com.smlnskgmail.jaman.hashchecker.logic.settings.api;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.api.HashType;
import com.smlnskgmail.jaman.hashchecker.logic.locale.api.Language;
import com.smlnskgmail.jaman.hashchecker.logic.settings.ui.lists.themes.Theme;

public interface SettingsHelper {

    void saveHashTypeAsLast(@NonNull HashType hashType);

    @NonNull
    HashType getLastHashType(@NonNull Context context);

    boolean languageIsInitialized();

    void saveLanguage(@NonNull Language language);

    @NonNull
    Language getLanguage(@NonNull Context context);

    boolean isUsingInnerFileManager();

    void savePathForInnerFileManager(@Nullable String path);

    @NonNull
    String getLastPathForInnerFileManager();

    boolean isUsingMultilineHashFields();

    boolean canSaveResultToHistory();

    boolean getVibrateAccess();

    @NonNull
    Theme getSelectedTheme();

    boolean useUpperCase();

    boolean isShortcutsIsCreated();

    void saveShortcutsStatus(boolean value);

    void saveTheme(@NonNull Theme theme);

    boolean getGenerateFromShareIntentStatus();

    void setGenerateFromShareIntentMode(boolean status);

    boolean refreshSelectedFile();

    void setRefreshSelectedFileStatus(boolean status);

    boolean canShowRateAppDialog();

    void increaseHashGenerationCount();

}
