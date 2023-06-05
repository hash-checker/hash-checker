package com.smlnskgmail.jaman.hashchecker.components.settings.impl;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashType;
import com.smlnskgmail.jaman.hashchecker.components.locale.api.Language;
import com.smlnskgmail.jaman.hashchecker.components.settings.api.Settings;
import com.smlnskgmail.jaman.hashchecker.components.theme.api.Theme;
import com.smlnskgmail.jaman.hashchecker.utils.LogUtils;

public class SharedPreferencesSettings implements Settings {

    private final SharedPreferences sharedPreferences;
    private final SharedPreferencesSettingsKeyExtractor keyExtractor;

    public SharedPreferencesSettings(
            @NonNull SharedPreferences sharedPreferences,
            @NonNull SharedPreferencesSettingsKeyExtractor keyExtractor
    ) {
        this.sharedPreferences = sharedPreferences;
        this.keyExtractor = keyExtractor;
    }

    @Override
    public void saveHashTypeAsLast(@NonNull HashType hashType) {
        saveStringPreference(
                keyExtractor.extractById(R.string.key_last_type_value),
                hashType.toString()
        );
    }

    @NonNull
    @Override
    public HashType getLastHashType() {
        String hashValue = getStringPreference(
                keyExtractor.extractById(R.string.key_last_type_value),
                HashType.MD5.getHashName()
        );
        try {
            return HashType.valueOf(hashValue);
        } catch (IllegalArgumentException e) {
            LogUtils.e(e);
            return HashType.MD5;
        }
    }

    @Override
    public boolean languageIsInitialized() {
        return containsPreference(keyExtractor.extractById(R.string.key_language));
    }

    @Override
    public void saveLanguage(@NonNull Language language) {
        saveStringPreference(
                keyExtractor.extractById(R.string.key_language),
                language.toString()
        );
    }

    @NonNull
    @Override
    public Language getLanguage() {
        return Language.valueOf(
                getStringPreference(
                        keyExtractor.extractById(R.string.key_language),
                        Language.EN.toString()
                )
        );
    }

    @Override
    public boolean isUsingMultilineHashFields() {
        return getBooleanPreference(
                keyExtractor.extractById(R.string.key_multiline),
                false
        );
    }

    @Override
    public boolean canSaveResultToHistory() {
        return getBooleanPreference(
                keyExtractor.extractById(R.string.key_save_result_to_history),
                true
        );
    }

    @Override
    public boolean getVibrateAccess() {
        return getBooleanPreference(
                keyExtractor.extractById(R.string.key_vibrate),
                true
        );
    }

    @NonNull
    @Override
    public Theme getSelectedTheme() {
        String selectedTheme = getTheme();
        for (Theme theme : Theme.values()) {
            if (theme.toString().equals(selectedTheme)) {
                return theme;
            }
        }
        return Theme.DARK;
    }

    /*
     * Saved for compatibility with old versions (where themes count more than 2)
     */
    @NonNull
    private String getTheme() {
        String theme = getStringPreference(
                keyExtractor.extractById(R.string.key_selected_theme),
                Theme.DARK.toString()
        );
        if (validateAppTheme(theme)) {
            return theme;
        } else {
            return getThemeAnalogue(theme).toString();
        }
    }

    private boolean validateAppTheme(@NonNull String theme) {
        if (theme.equals(Theme.LIGHT.toString()) || theme.equals(Theme.DARK.toString())) {
            return true;
        }
        saveTheme(Theme.DARK);
        return false;
    }

    @NonNull
    private static Theme getThemeAnalogue(@NonNull String theme) {
        return theme.contains("DARK") ? Theme.DARK : Theme.LIGHT;
    }

    @Override
    public void saveTheme(@NonNull Theme theme) {
        saveStringPreference(
                keyExtractor.extractById(R.string.key_selected_theme),
                theme.toString()
        );
    }

    @Override
    public boolean useUpperCase() {
        return getBooleanPreference(
                keyExtractor.extractById(R.string.key_upper_case),
                false
        );
    }

    @Override
    public boolean isShortcutsIsCreated() {
        return getBooleanPreference(
                keyExtractor.extractById(R.string.key_shortcuts_created),
                false
        );
    }

    @Override
    public void saveShortcutsStatus(boolean value) {
        saveBooleanPreference(
                keyExtractor.extractById(R.string.key_shortcuts_created),
                value
        );
    }

    @Override
    public void setGenerateFromShareIntentMode(boolean status) {
        saveBooleanPreference(
                keyExtractor.extractById(R.string.key_generate_from_share_intent),
                status
        );
    }

    @Override
    public boolean refreshSelectedFile() {
        return getBooleanPreference(
                keyExtractor.extractById(R.string.key_refresh_selected_file),
                false
        );
    }

    @Override
    public void setRefreshSelectedFileStatus(boolean status) {
        saveBooleanPreference(
                keyExtractor.extractById(R.string.key_refresh_selected_file),
                status
        );
    }

    @Override
    public boolean canShowRateAppDialog() {
        int hashGenerationCount = getIntPreference(keyExtractor.extractById(R.string.key_hash_generation_count));
        return hashGenerationCount == HASH_GENERATION_COUNT_BEFORE_RATE_APP_DIALOG_CALL;
    }

    @Override
    public void increaseHashGenerationCount() {
        int count = getIntPreference(keyExtractor.extractById(R.string.key_hash_generation_count));
        if (count <= HASH_GENERATION_COUNT_BEFORE_RATE_APP_DIALOG_CALL) {
            saveIntPreference(
                    keyExtractor.extractById(R.string.key_hash_generation_count),
                    ++count
            );
        }
    }

    private boolean containsPreference(@NonNull String key) {
        return sharedPreferences.contains(key);
    }

    private void saveStringPreference(@NonNull String key, @Nullable String value) {
        sharedPreferences
                .edit()
                .putString(key, value)
                .apply();
    }

    @NonNull
    private String getStringPreference(@NonNull String key, @Nullable String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    private void saveBooleanPreference(@NonNull String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    private boolean getBooleanPreference(@NonNull String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    private void saveIntPreference(@NonNull String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    private int getIntPreference(@NonNull String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public abstract static class SharedPreferencesSettingsKeyExtractor {

        @NonNull
        public abstract String extractById(@StringRes int id);

    }

}
