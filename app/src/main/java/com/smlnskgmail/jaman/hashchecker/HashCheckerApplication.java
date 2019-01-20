package com.smlnskgmail.jaman.hashchecker;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import com.smlnskgmail.jaman.hashchecker.support.values.Constants;
import com.smlnskgmail.jaman.hashchecker.support.values.Preferences;

import java.util.Arrays;

public class HashCheckerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (!Preferences.isShortcutsIsCreated(this)) {
            createShortcuts();
            Preferences.saveShortcutsStatus(this, true);
        }
    }

    private void createShortcuts() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);
            shortcutManager.setDynamicShortcuts(Arrays.asList(getShortcutForTextType(), getShortcutForFileType()));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    @SuppressLint("ResourceType")
    @NonNull
    private ShortcutInfo getShortcutForTextType() {
        return getShortcut(Constants.ShortcutIds.SHORTCUT_TEXT_ID, R.string.common_text,
                R.drawable.ic_shortcut_text, Constants.ShortcutActions.ACTION_START_WITH_TEXT_SELECTION);
    }

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    @SuppressLint("ResourceType")
    @NonNull
    private ShortcutInfo getShortcutForFileType() {
        return getShortcut(Constants.ShortcutIds.SHORTCUT_FILE_ID, R.string.common_file,
                R.drawable.ic_shortcut_file, Constants.ShortcutActions.ACTION_START_WITH_FILE_SELECTION);
    }

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    @SuppressLint("ResourceType")
    @NonNull
    private ShortcutInfo getShortcut(@NonNull String id, @IdRes int labelResId, @IdRes int iconResId,
                                     @NonNull String intentAction) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setAction(intentAction);
        return new ShortcutInfo.Builder(this, id)
                .setShortLabel(getString(labelResId))
                .setIcon(Icon.createWithResource(this, iconResId))
                .setIntent(intent)
                .build();
    }

}
