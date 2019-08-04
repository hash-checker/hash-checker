package com.smlnskgmail.jaman.hashchecker.support.prefs;

import android.content.Context;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.settings.themes.Theme;
import com.smlnskgmail.jaman.hashchecker.generator.support.HashType;
import com.smlnskgmail.jaman.hashchecker.support.logger.L;
import com.smlnskgmail.jaman.hashchecker.utils.AppUtils;

public class SettingsHelper {

    public static void saveHashTypeAsLast(@NonNull Context context, @NonNull HashType hashType) {
        saveStringPreference(context, context.getString(R.string.key_last_type_value), hashType.toString());
    }

    public static HashType getLastHashType(@NonNull Context context) {
        String hashValue = getStringPreference(context, context.getString(R.string.key_last_type_value),
                        context.getString(R.string.hash_type_md5));
        try {
            return HashType.valueOf(hashValue);
        } catch (IllegalArgumentException e) {
            L.e(e);
            return HashType.MD5;
        }
    }

    public static boolean isUsingInnerFileManager(@NonNull Context context) {
        return AppUtils.isNotQAndAbove() && getBooleanPreference(context,
                context.getString(R.string.key_inner_file_manager), false);
    }

    public static boolean isUsingMultilineHashFields(@NonNull Context context) {
        return getBooleanPreference(context, context.getString(R.string.key_multiline), false);
    }

    public static boolean canSaveResultToHistory(@NonNull Context context) {
        return getBooleanPreference(context, context.getString(R.string.key_save_result_to_history), true);
    }

    public static boolean getVibrateAccess(@NonNull Context context) {
        return getBooleanPreference(context, context.getString(R.string.key_vibrate), true);
    }

    public static String getTheme(@NonNull Context context) {
        String theme = getStringPreference(context, context.getString(R.string.key_selected_theme),
                Theme.LIGHT.toString());
        if (validateAppTheme(context, theme)) {
            return theme;
        } else {
            return getThemeAnalogue(theme).toString();
        }
    }

    private static boolean validateAppTheme(@NonNull Context context, @NonNull String theme) {
        if (theme.equals(Theme.LIGHT.toString()) || theme.equals(Theme.DARK.toString())) {
            return true;
        }
        saveTheme(context, Theme.LIGHT);
        return false;
    }

    private static Theme getThemeAnalogue(@NonNull String theme) {
        if (theme.contains("DARK")) {
            return Theme.DARK;
        } else {
            return Theme.LIGHT;
        }
    }

    public static boolean useUpperCase(@NonNull Context context) {
        return getBooleanPreference(context, context.getString(R.string.key_upper_case), false);
    }

    public static boolean isShortcutsIsCreated(@NonNull Context context) {
        return getBooleanPreference(context, context.getString(R.string.key_shortcuts_created), false);
    }

    public static void saveShortcutsStatus(@NonNull Context context, boolean value) {
        saveBooleanPreference(context, context.getString(R.string.key_shortcuts_created), value);
    }

    public static void saveTheme(@NonNull Context context, Theme theme) {
        saveStringPreference(context, context.getString(R.string.key_selected_theme), theme.toString());
    }

    public static boolean getGenerateFromShareIntentStatus(@NonNull Context context) {
        return getBooleanPreference(context, context.getString(R.string.key_generate_from_share_intent),
                false);
    }

    public static void setGenerateFromShareIntentMode(@NonNull Context context, boolean status) {
        saveBooleanPreference(context, context.getString(R.string.key_generate_from_share_intent),
                status);
    }

    public static boolean refreshSelectedFile(@NonNull Context context) {
        return getBooleanPreference(context, context.getString(R.string.key_refresh_selected_file),
                false);
    }

    public static void setRefreshSelectedFileStatus(@NonNull Context context, boolean status) {
        saveBooleanPreference(context, context.getString(R.string.key_refresh_selected_file),
                status);
    }

    private static void saveStringPreference(@NonNull Context context, @NonNull String key,
                                             @NonNull String value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(key, value).apply();
    }

    private static String getStringPreference(@NonNull Context context, @NonNull String key,
                                              @NonNull String defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(key, defaultValue);
    }

    private static void saveBooleanPreference(@NonNull Context context, @NonNull String key,
                                              boolean value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(key, value).apply();
    }

    private static boolean getBooleanPreference(@NonNull Context context, @NonNull String key,
                                                boolean defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(key, defaultValue);
    }

}
