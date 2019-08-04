package com.smlnskgmail.jaman.hashchecker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.Build;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.smlnskgmail.jaman.hashchecker.db.HelperFactory;
import com.smlnskgmail.jaman.hashchecker.support.prefs.SettingsHelper;

import java.util.Arrays;

public class App extends android.app.Application {

    public static final String ACTION_START_WITH_TEXT_SELECTION = "com.smlnskgmail.jaman.hashchecker.ACTION_START_WITH_TEXT_SELECTION";
    public static final String ACTION_START_WITH_FILE_SELECTION = "com.smlnskgmail.jaman.hashchecker.ACTION_START_WITH_FILE_SELECTION";

    public static final String SHORTCUT_TEXT_ID = "shortcut_text";
    public static final String SHORTCUT_FILE_ID = "shortcut_file";

    @Override
    public void onCreate() {
        super.onCreate();
        if (!SettingsHelper.isShortcutsIsCreated(this)) {
            createShortcuts();
            SettingsHelper.saveShortcutsStatus(this, true);
        }
        HelperFactory.setHelper(this);
    }

    private void createShortcuts() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);
            shortcutManager.setDynamicShortcuts(Arrays.asList(getShortcutForTextType(),
                    getShortcutForFileType()));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    @SuppressLint("ResourceType")
    @NonNull
    private ShortcutInfo getShortcutForTextType() {
        return getShortcut(SHORTCUT_TEXT_ID, R.string.common_text,
                R.drawable.ic_shortcut_text, ACTION_START_WITH_TEXT_SELECTION);
    }

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    @SuppressLint("ResourceType")
    @NonNull
    private ShortcutInfo getShortcutForFileType() {
        return getShortcut(SHORTCUT_FILE_ID, R.string.common_file,
                R.drawable.ic_shortcut_file, ACTION_START_WITH_FILE_SELECTION);
    }

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    @SuppressLint("ResourceType")
    @NonNull
    private ShortcutInfo getShortcut(@NonNull String id, @IdRes int labelResId,
                                     @IdRes int iconResId, @NonNull String intentAction) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setAction(intentAction);
        return new ShortcutInfo.Builder(this, id)
                .setShortLabel(getString(labelResId))
                .setIcon(Icon.createWithResource(this, iconResId))
                .setIntent(intent)
                .build();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        HelperFactory.releaseHelper();
    }

}
