package com.smlnskgmail.jaman.hashchecker.logic.settings.impl;

import android.content.Context;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.locale.api.Language;
import com.smlnskgmail.jaman.hashchecker.components.theme.api.Theme;
import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.api.HashType;
import com.smlnskgmail.jaman.hashchecker.logic.settings.api.SettingsHelper;
import com.smlnskgmail.jaman.hashchecker.utils.LogUtils;

public class SharedPreferencesSettingsHelper implements SettingsHelper {

    private final Context context;

    public SharedPreferencesSettingsHelper(
            @NonNull Context context
    ) {
        this.context = context;
    }

    @Override
    public void saveHashTypeAsLast(
            @NonNull HashType hashType
    ) {
        saveStringPreference(
                context.getString(R.string.key_last_type_value),
                hashType.toString()
        );
    }

    @NonNull
    @Override
    public HashType getLastHashType() {
        String hashValue = getStringPreference(
                context.getString(R.string.key_last_type_value),
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
        return containsPreference(
                context.getString(R.string.key_language)
        );
    }

    @Override
    public void saveLanguage(
            @NonNull Language language
    ) {
        saveStringPreference(
                context.getString(R.string.key_language),
                language.toString()
        );
    }

    @NonNull
    @Override
    public Language getLanguage() {
        return Language.valueOf(
                getStringPreference(
                        context.getString(R.string.key_language),
                        Language.EN.toString()
                )
        );
    }

    @Override
    public boolean isUsingInnerFileManager() {
        return false;
//        return getBooleanPreference(
//                context,
//                context.getString(R.string.key_inner_file_manager),
//                false
//        );
    }

    @Override
    public void savePathForInnerFileManager(
            @Nullable String path
    ) {
        saveStringPreference(
                context.getString(R.string.key_last_path),
                path
        );
    }

//    @NonNull
//    @Override
//    public String getLastPathForInnerFileManager() {
//        return getStringPreference(
//                context.getString(R.string.key_last_path),
//                null
//        );
//    }

    @Override
    public boolean isUsingMultilineHashFields() {
        return getBooleanPreference(
                context.getString(R.string.key_multiline),
                false
        );
    }

    @Override
    public boolean canSaveResultToHistory() {
        return getBooleanPreference(
                context.getString(R.string.key_save_result_to_history),
                true
        );
    }

    @Override
    public boolean getVibrateAccess() {
        return getBooleanPreference(
                context.getString(R.string.key_vibrate),
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
     * Saved for old versions compatibility (where themes count > 2)
     */
    @NonNull
    private String getTheme() {
        String theme = getStringPreference(
                context.getString(R.string.key_selected_theme),
                Theme.DARK.toString()
        );
        if (validateAppTheme(theme)) {
            return theme;
        } else {
            return getThemeAnalogue(theme).toString();
        }
    }

    private boolean validateAppTheme(
            @NonNull String theme
    ) {
        if (theme.equals(Theme.LIGHT.toString())
                || theme.equals(Theme.DARK.toString())) {
            return true;
        }
        saveTheme(Theme.DARK);
        return false;
    }

    @NonNull
    private static Theme getThemeAnalogue(
            @NonNull String theme
    ) {
        if (theme.contains("DARK")) {
            return Theme.DARK;
        } else {
            return Theme.LIGHT;
        }
    }

    @Override
    public void saveTheme(
            @NonNull Theme theme
    ) {
        saveStringPreference(
                context.getString(R.string.key_selected_theme),
                theme.toString()
        );
    }

    @Override
    public boolean useUpperCase() {
        return getBooleanPreference(
                context.getString(R.string.key_upper_case),
                false
        );
    }

    @Override
    public boolean isShortcutsIsCreated() {
        return getBooleanPreference(
                context.getString(R.string.key_shortcuts_created),
                false
        );
    }

    @Override
    public void saveShortcutsStatus(
            boolean value
    ) {
        saveBooleanPreference(
                context.getString(R.string.key_shortcuts_created),
                value
        );
    }

    @Override
    public boolean getGenerateFromShareIntentStatus() {
        return getBooleanPreference(
                context.getString(R.string.key_generate_from_share_intent),
                false
        );
    }

    @Override
    public void setGenerateFromShareIntentMode(
            boolean status
    ) {
        saveBooleanPreference(
                context.getString(R.string.key_generate_from_share_intent),
                status
        );
    }

    @Override
    public boolean refreshSelectedFile() {
        return getBooleanPreference(
                context.getString(R.string.key_refresh_selected_file),
                false
        );
    }

    @Override
    public void setRefreshSelectedFileStatus(
            boolean status
    ) {
        saveBooleanPreference(
                context.getString(R.string.key_refresh_selected_file),
                status
        );
    }

    @Override
    public boolean canShowRateAppDialog() {
        int hashGenerationCount = getIntPreference(
                context.getString(R.string.key_hash_generation_count)
        );
        return hashGenerationCount == HASH_GENERATION_COUNT_BEFORE_RATE_APP_DIALOG_CALL;
    }

    @Override
    public void increaseHashGenerationCount() {
        int count = getIntPreference(
                context.getString(R.string.key_hash_generation_count)
        );
        if (count <= HASH_GENERATION_COUNT_BEFORE_RATE_APP_DIALOG_CALL) {
            saveIntPreference(
                    context.getString(R.string.key_hash_generation_count),
                    ++count
            );
        }
    }

    private boolean containsPreference(
            @NonNull String key
    ) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .contains(key);
    }

    private void saveStringPreference(
            @NonNull String key,
            @Nullable String value
    ) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(key, value)
                .apply();
    }

    @NonNull
    private String getStringPreference(
            @NonNull String key,
            @Nullable String defaultValue
    ) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(key, defaultValue);
    }

    private void saveBooleanPreference(
            @NonNull String key,
            boolean value
    ) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean(key, value)
                .apply();
    }

    private boolean getBooleanPreference(
            @NonNull String key,
            boolean defaultValue
    ) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(key, defaultValue);
    }

    private void saveIntPreference(
            @NonNull String key,
            int value
    ) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putInt(key, value)
                .apply();
    }

    private int getIntPreference(
            @NonNull String key
    ) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getInt(key, 0);
    }

}
