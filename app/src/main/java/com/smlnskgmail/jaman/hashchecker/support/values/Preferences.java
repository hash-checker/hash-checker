package com.smlnskgmail.jaman.hashchecker.support.values;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.themes.Themes;

public class Preferences {

    public static void saveTypeAsLast(@NonNull Context context, @NonNull String value) {
        saveStringPreference(context, context.getString(R.string.key_last_type_value), value);
    }

    public static String getLastType(@NonNull Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(context.getString(R.string.key_last_type_value),
                context.getString(R.string.hash_type_md5));
    }

    public static String getTheme(@NonNull Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(context.getString(R.string.key_selected_theme),
                Themes.LIGHT.toString());
    }

    public static boolean getVibrateAccess(@NonNull Context context) {
        return getBooleanPreference(context, context.getString(R.string.key_vibrate), true);
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

    private static void saveStringPreference(@NonNull Context context, @NonNull String key, @NonNull String value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(key, value).apply();
    }

    private static void saveBooleanPreference(@NonNull Context context, @NonNull String key, boolean value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(key, value).apply();
    }

    private static boolean getBooleanPreference(@NonNull Context context, @NonNull String key, boolean defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(key, defaultValue);
    }

}
