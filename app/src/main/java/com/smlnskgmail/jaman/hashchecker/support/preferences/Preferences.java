package com.smlnskgmail.jaman.hashchecker.support.preferences;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.themes.Themes;
import com.smlnskgmail.jaman.hashchecker.generator.HashTypes;
import com.smlnskgmail.jaman.hashchecker.support.utils.Logger;

public class Preferences {

    public static void saveHashTypeAsLast(@NonNull Context context, @NonNull HashTypes hashType) {
        saveStringPreference(context, context.getString(R.string.key_last_type_value), hashType.toString());
    }

    public static HashTypes getLastHashType(@NonNull Context context) {
        String hashValue = getStringPreference(context, context.getString(R.string.key_last_type_value),
                        context.getString(R.string.hash_type_md5));
        try {
            return HashTypes.valueOf(hashValue);
        } catch (IllegalArgumentException e) {
            Logger.error(e);
            return HashTypes.MD5;
        }
    }

    public static boolean isUsingInnerFileManager(@NonNull Context context) {
        return getBooleanPreference(context, context.getString(R.string.key_inner_file_manager), false);
    }

    public static boolean canSaveResultToHistory(@NonNull Context context) {
        return getBooleanPreference(context, context.getString(R.string.key_save_result_to_history), true);
    }

    public static boolean getVibrateAccess(@NonNull Context context) {
        return getBooleanPreference(context, context.getString(R.string.key_vibrate), true);
    }

    public static String getTheme(@NonNull Context context) {
        return getStringPreference(context, context.getString(R.string.key_selected_theme),
                        Themes.LIGHT.toString());
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

    public static void saveTheme(@NonNull Context context, Themes theme) {
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
