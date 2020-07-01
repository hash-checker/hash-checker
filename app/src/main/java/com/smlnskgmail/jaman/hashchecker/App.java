package com.smlnskgmail.jaman.hashchecker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.content.res.Configuration;
import android.graphics.drawable.Icon;
import android.os.Build;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.smlnskgmail.jaman.hashchecker.logic.database.HelperFactory;
import com.smlnskgmail.jaman.hashchecker.logic.settings.SettingsHelper;
import com.smlnskgmail.jaman.hashchecker.logic.settings.ui.lists.languages.Language;

import java.util.Arrays;
import java.util.Locale;

public class App extends android.app.Application {

    public static final String ACTION_START_WITH_TEXT
            = "com.smlnskgmail.jaman.hashchecker.ACTION_START_WITH_TEXT";
    public static final String ACTION_START_WITH_FILE
            = "com.smlnskgmail.jaman.hashchecker.ACTION_START_WITH_FILE";

    private static final String SHORTCUT_TEXT_ID = "shortcut_text";
    private static final String SHORTCUT_FILE_ID = "shortcut_file";

    @Override
    public void onCreate() {
        super.onCreate();
        if (!SettingsHelper.isShortcutsIsCreated(this)) {
            createShortcuts();
            SettingsHelper.saveShortcutsStatus(
                    this,
                    true
            );
        }
        HelperFactory.setHelper(this);
        setLocale(getApplicationContext());
    }

    private void createShortcuts() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            ShortcutManager shortcutManager = getSystemService(
                    ShortcutManager.class
            );
            if (shortcutManager != null) {
                shortcutManager.setDynamicShortcuts(
                        Arrays.asList(
                                getShortcutForTextType(),
                                getShortcutForFileType()
                        )
                );
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    @SuppressLint("ResourceType")
    @NonNull
    private ShortcutInfo getShortcutForTextType() {
        return getShortcut(
                SHORTCUT_TEXT_ID,
                R.string.common_text,
                R.drawable.ic_shortcut_text,
                ACTION_START_WITH_TEXT
        );
    }

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    @SuppressLint("ResourceType")
    @NonNull
    private ShortcutInfo getShortcutForFileType() {
        return getShortcut(
                SHORTCUT_FILE_ID,
                R.string.common_file,
                R.drawable.ic_shortcut_file,
                ACTION_START_WITH_FILE
        );
    }

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    @SuppressLint("ResourceType")
    @NonNull
    private ShortcutInfo getShortcut(
            @NonNull String id,
            @IdRes int labelResId,
            @IdRes int iconResId,
            @NonNull String intentAction
    ) {
        Intent intent = new Intent(
                this,
                MainActivity.class
        );
        intent.setAction(intentAction);
        return new ShortcutInfo.Builder(this, id)
                .setShortLabel(getString(labelResId))
                .setIcon(
                        Icon.createWithResource(
                                this,
                                iconResId
                        )
                )
                .setIntent(intent)
                .build();
    }

    private void setLocale(@NonNull Context context) {
        Language language = null;
        if (!SettingsHelper.languageIsInitialized(context)) {
            String deviceLocale = Locale.getDefault().toString();
            for (Language lang: Language.values()) {
                if (deviceLocale.equals(lang.code())) {
                    language = lang;
                    break;
                }
            }
            if (language == null) {
                language = Language.EN;
            }
            SettingsHelper.saveLanguage(
                    context,
                    language
            );
        }
    }

    @Override
    public void onConfigurationChanged(
            @NonNull Configuration newConfig
    ) {
        super.onConfigurationChanged(newConfig);
        setLocale(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SettingsHelper.savePathForInnerFileManager(
                this,
                null
        );
        HelperFactory.releaseHelper();
    }

}
