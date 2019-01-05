package com.smlnskgmail.jaman.hashchecker;

import android.app.Application;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;

import com.smlnskgmail.jaman.hashchecker.utils.Constants;
import com.smlnskgmail.jaman.hashchecker.utils.Preferences;

import java.util.Arrays;

public class HashCheckerApplication extends Application {

    private void createShortcuts() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);

            Intent textIntent = new Intent(this, MainActivity.class);
            textIntent.setAction(Constants.ShortcutActions.ACTION_START_WITH_TEXT_SELECTION);

            Intent fileIntent = new Intent(this, MainActivity.class);
            fileIntent.setAction(Constants.ShortcutActions.ACTION_START_WITH_FILE_SELECTION);

            ShortcutInfo textShortcut = new ShortcutInfo.Builder(this, Constants.ShortcutIds.SHORTCUT_TEXT_ID)
                    .setShortLabel(getString(R.string.common_text))
                    .setIcon(Icon.createWithResource(this, R.drawable.ic_shortcut_text))
                    .setIntent(textIntent)
                    .build();
            ShortcutInfo fileShortcut = new ShortcutInfo.Builder(this, Constants.ShortcutIds.SHORTCUT_FILE_ID)
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
