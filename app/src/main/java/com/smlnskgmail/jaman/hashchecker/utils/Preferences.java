package com.smlnskgmail.jaman.hashchecker.utils;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;

public class Preferences {

    private static void saveBooleanPreference(@NonNull Context context, @NonNull String key, boolean value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(key, value).apply();
    }

    private static boolean getBooleanPreference(@NonNull Context context, @NonNull String key, boolean defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(key, defaultValue);
    }

    static boolean getVibrateAccess(@NonNull Context context) {
        return getBooleanPreference(context, context.getString(R.string.key_vibrate), true);
    }

    public static boolean useUpperCase(@NonNull Context context) {
        return getBooleanPreference(context, context.getString(R.string.key_upper_case), false);
    }

    public static void saveTypeAsLast(@NonNull Context context, @NonNull String value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(context.getString(R.string.key_last_type_value), value).apply();
    }

    public static String getLastType(@NonNull Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.key_last_type_value),
                context.getString(R.string.hash_type_md5));
    }

    public static boolean typeIsSelected(@NonNull Context context, int key) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(context.getString(key), true);
    }

    public static boolean isShortcutsIsCreated(@NonNull Context context) {
        return getBooleanPreference(context, context.getString(R.string.key_shortcuts_created), false);
    }

    public static void saveShortcutsStatus(@NonNull Context context, boolean value) {
        saveBooleanPreference(context, context.getString(R.string.key_shortcuts_created), value);
    }

}
