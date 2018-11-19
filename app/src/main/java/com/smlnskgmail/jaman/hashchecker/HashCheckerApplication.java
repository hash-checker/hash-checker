package com.smlnskgmail.jaman.hashchecker;

import android.app.Application;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;

import com.smlnskgmail.jaman.hashchecker.utils.Preferences;

import java.util.Arrays;

public class HashCheckerApplication extends Application {

    public static final String ACTION_TEXT = "com.smlnskgmail.jaman.hashchecker.TEXT";
    public static final String ACTION_FILE = "com.smlnskgmail.jaman.hashchecker.FILE";

    private void createShortcuts() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);

            Intent textIntent = new Intent(this, MainActivity.class);
            textIntent.setAction(ACTION_TEXT);

            Intent fileIntent = new Intent(this, MainActivity.class);
            fileIntent.setAction(ACTION_FILE);

            ShortcutInfo textShortcut = new ShortcutInfo.Builder(this, "shortcut_text")
                    .setShortLabel(getString(R.string.common_text))
                    .setIcon(Icon.createWithResource(this, R.drawable.ic_shortcut_text))
                    .setIntent(textIntent)
                    .build();
            ShortcutInfo fileShortcut = new ShortcutInfo.Builder(this, "shortcut_file")
                    .setShortLabel(getString(R.string.common_file))
                    .setIcon(Icon.createWithResource(this, R.drawable.ic_shortcut_file))
                    .setIntent(fileIntent)
                    .build();
            shortcutManager.setDynamicShortcuts(Arrays.asList(textShortcut, fileShortcut));
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (!Preferences.isShortcutsIsCreated(this)) {
            createShortcuts();
            Preferences.saveShortcutsStatus(this, true);
        }
    }

}
