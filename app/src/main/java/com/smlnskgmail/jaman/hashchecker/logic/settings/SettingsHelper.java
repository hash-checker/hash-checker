package com.smlnskgmail.jaman.hashchecker.logic.settings;

import android.content.Context;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.HashType;
import com.smlnskgmail.jaman.hashchecker.logic.logs.L;
import com.smlnskgmail.jaman.hashchecker.logic.settings.ui.lists.languages.Language;
import com.smlnskgmail.jaman.hashchecker.logic.settings.ui.lists.themes.Theme;

public class SettingsHelper {

    public static final int FILE_CREATE = 3;

    public static void saveHashTypeAsLast(
            @NonNull Context context,
            @NonNull HashType hashType
    ) {
        saveStringPreference(
                context,
                context.getString(R.string.key_last_type_value),
                hashType.toString()
        );
    }

    public static HashType getLastHashType(
            @NonNull Context context
    ) {
        String hashValue = getStringPreference(
                context,
                context.getString(R.string.key_last_type_value),
                HashType.MD5.getHashName()
        );
        try {
            return HashType.valueOf(hashValue);
        } catch (IllegalArgumentException e) {
            L.e(e);
            return HashType.MD5;
        }
    }

    public static boolean languageIsInitialized(
            @NonNull Context context
    ) {
        return containsPreference(
                context,
                context.getString(R.string.key_language)
        );
    }

    public static void saveLanguage(
            @NonNull Context context,
            @NonNull Language language
    ) {
        saveStringPreference(
                context,
                context.getString(R.string.key_language),
                language.toString()
        );
    }

    @NonNull
    public static Language getLanguage(
            @NonNull Context context
    ) {
        return Language.valueOf(
                getStringPreference(
                        context,
                        context.getString(R.string.key_language),
                        Language.EN.toString()
                )
        );
    }

    public static boolean isUsingInnerFileManager(
            @NonNull Context context
    ) {
        return getBooleanPreference(
                context,
                context.getString(R.string.key_inner_file_manager),
                false
        );
    }

    public static void savePathForInnerFileManager(
            @NonNull Context context,
            @Nullable String path
    ) {
        saveStringPreference(
                context,
                context.getString(R.string.key_last_path),
                path
        );
    }

    @Nullable
    public static String getLastPathForInnerFileManager(
            @NonNull Context context
    ) {
        return getStringPreference(
                context,
                context.getString(R.string.key_last_path),
                null
        );
    }

    public static boolean isUsingMultilineHashFields(
            @NonNull Context context
    ) {
        return getBooleanPreference(
                context,
                context.getString(R.string.key_multiline),
                false
        );
    }

    public static boolean canSaveResultToHistory(
            @NonNull Context context
    ) {
        return getBooleanPreference(
                context,
                context.getString(R.string.key_save_result_to_history),
                true
        );
    }

    public static boolean getVibrateAccess(
            @NonNull Context context
    ) {
        return getBooleanPreference(
                context,
                context.getString(R.string.key_vibrate),
                true
        );
    }

    public static Theme getSelectedTheme(
            @NonNull Context context
    ) {
        String selectedTheme = SettingsHelper.getTheme(context);
        for (Theme theme: Theme.values()) {
            if (theme.toString().equals(selectedTheme)) {
                return theme;
            }
        }
        return Theme.DARK;
    }

    /*
     * Saved for old versions compatibility (where themes count > 2)
     */
    private static String getTheme(
            @NonNull Context context
    ) {
        String theme = getStringPreference(
                context,
                context.getString(R.string.key_selected_theme),
                Theme.DARK.toString()
        );
        if (validateAppTheme(context, theme)) {
            return theme;
        } else {
            return getThemeAnalogue(theme).toString();
        }
    }

    private static boolean validateAppTheme(
            @NonNull Context context,
            @NonNull String theme
    ) {
        if (theme.equals(Theme.LIGHT.toString())
                || theme.equals(Theme.DARK.toString())) {
            return true;
        }
        saveTheme(context, Theme.DARK);
        return false;
    }

    private static Theme getThemeAnalogue(
            @NonNull String theme
    ) {
        if (theme.contains("DARK")) {
            return Theme.DARK;
        } else {
            return Theme.LIGHT;
        }
    }

    public static boolean useUpperCase(
            @NonNull Context context
    ) {
        return getBooleanPreference(
                context,
                context.getString(R.string.key_upper_case),
                false
        );
    }

    public static boolean isShortcutsIsCreated(
            @NonNull Context context
    ) {
        return getBooleanPreference(
                context,
                context.getString(R.string.key_shortcuts_created),
                false
        );
    }

    public static void saveShortcutsStatus(
            @NonNull Context context,
            boolean value
    ) {
        saveBooleanPreference(
                context,
                context.getString(R.string.key_shortcuts_created),
                value
        );
    }

    public static void saveTheme(
            @NonNull Context context,
            Theme theme
    ) {
        saveStringPreference(
                context,
                context.getString(R.string.key_selected_theme),
                theme.toString()
        );
    }

    public static boolean getGenerateFromShareIntentStatus(
            @NonNull Context context
    ) {
        return getBooleanPreference(
                context,
                context.getString(R.string.key_generate_from_share_intent),
                false
        );
    }

    public static void setGenerateFromShareIntentMode(
            @NonNull Context context,
            boolean status
    ) {
        saveBooleanPreference(
                context,
                context.getString(R.string.key_generate_from_share_intent),
                status
        );
    }

    public static boolean refreshSelectedFile(
            @NonNull Context context
    ) {
        return getBooleanPreference(
                context,
                context.getString(R.string.key_refresh_selected_file),
                false
        );
    }

    public static void setRefreshSelectedFileStatus(
            @NonNull Context context,
            boolean status
    ) {
        saveBooleanPreference(
                context,
                context.getString(R.string.key_refresh_selected_file),
                status
        );
    }

    private static boolean containsPreference(
            @NonNull Context context,
            @NonNull String key
    ) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .contains(key);
    }

    private static void saveStringPreference(
            @NonNull Context context,
            @NonNull String key,
            @Nullable String value
    ) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(key, value)
                .apply();
    }

    private static String getStringPreference(
            @NonNull Context context,
            @NonNull String key,
            @Nullable String defaultValue
    ) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(key, defaultValue);
    }

    private static void saveBooleanPreference(
            @NonNull Context context,
            @NonNull String key,
            boolean value
    ) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean(key, value)
                .apply();
    }

    private static boolean getBooleanPreference(
            @NonNull Context context,
            @NonNull String key,
            boolean defaultValue
    ) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(key, defaultValue);
    }

}
