package com.smlnskgmail.jaman.hashchecker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.content.res.Configuration;
import android.graphics.drawable.Icon;
import android.os.Build;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.smlnskgmail.jaman.hashchecker.di.components.AppComponent;
import com.smlnskgmail.jaman.hashchecker.di.components.DaggerAppComponent;
import com.smlnskgmail.jaman.hashchecker.di.modules.DatabaseHelperModule;
import com.smlnskgmail.jaman.hashchecker.di.modules.LangHelperModule;
import com.smlnskgmail.jaman.hashchecker.di.modules.SettingsHelperModule;
import com.smlnskgmail.jaman.hashchecker.di.modules.ThemeHelperModule;
import com.smlnskgmail.jaman.hashchecker.logic.database.api.DatabaseHelper;
import com.smlnskgmail.jaman.hashchecker.logic.database.impl.ormlite.OrmLiteDatabaseHelper;
import com.smlnskgmail.jaman.hashchecker.logic.locale.api.LangHelper;
import com.smlnskgmail.jaman.hashchecker.logic.locale.api.Language;
import com.smlnskgmail.jaman.hashchecker.logic.locale.impl.LangHelperImpl;
import com.smlnskgmail.jaman.hashchecker.logic.settings.api.SettingsHelper;
import com.smlnskgmail.jaman.hashchecker.logic.settings.impl.SharedPreferencesSettingsHelper;
import com.smlnskgmail.jaman.hashchecker.logic.themes.impl.ThemeHelperImpl;

import java.util.Arrays;
import java.util.Locale;

public class App extends android.app.Application {

    public static AppComponent appComponent;

    public static final String ACTION_START_WITH_TEXT
            = "com.smlnskgmail.jaman.hashchecker.ACTION_START_WITH_TEXT";
    public static final String ACTION_START_WITH_FILE
            = "com.smlnskgmail.jaman.hashchecker.ACTION_START_WITH_FILE";

    private static final String SHORTCUT_TEXT_ID = "shortcut_text";
    private static final String SHORTCUT_FILE_ID = "shortcut_file";

    private DatabaseHelper databaseHelper;
    private SettingsHelper settingsHelper;
    private LangHelper langHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        databaseHelper = new OrmLiteDatabaseHelper(this);
        settingsHelper = new SharedPreferencesSettingsHelper(this);
        langHelper = new LangHelperImpl(
                this,
                settingsHelper
        );
        setTheme(settingsHelper.getSelectedTheme().getThemeResId());
        appComponent = DaggerAppComponent
                .builder()
                .databaseHelperModule(
                        new DatabaseHelperModule(
                                databaseHelper
                        )
                )
                .settingsHelperModule(
                        new SettingsHelperModule(
                                settingsHelper
                        )
                )
                .langHelperModule(
                        new LangHelperModule(
                                langHelper
                        )
                )
                .themeHelperModule(
                        new ThemeHelperModule(
                                new ThemeHelperImpl(
                                        this,
                                        settingsHelper
                                )
                        )
                )
                .build();
        if (!settingsHelper.isShortcutsIsCreated()) {
            createShortcuts();
            settingsHelper.saveShortcutsStatus(true);
        }
        setLocale();
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

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    @SuppressLint("ResourceType")
    private ShortcutInfo getShortcutForTextType() {
        return getShortcut(
                SHORTCUT_TEXT_ID,
                R.string.common_text,
                R.drawable.ic_shortcut_text,
                ACTION_START_WITH_TEXT
        );
    }

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    @SuppressLint("ResourceType")
    private ShortcutInfo getShortcutForFileType() {
        return getShortcut(
                SHORTCUT_FILE_ID,
                R.string.common_file,
                R.drawable.ic_shortcut_file,
                ACTION_START_WITH_FILE
        );
    }

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    @SuppressLint("ResourceType")
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

    private void setLocale() {
        Language language = null;
        if (!settingsHelper.languageIsInitialized()) {
            String deviceLocale = Locale.getDefault().toString();
            for (Language lang : Language.values()) {
                if (deviceLocale.equals(lang.code())) {
                    language = lang;
                    break;
                }
            }
            if (language == null) {
                language = Language.EN;
            }
            langHelper.setLanguage(language);
        }
    }

    @Override
    public void onConfigurationChanged(
            @NonNull Configuration newConfig
    ) {
        super.onConfigurationChanged(newConfig);
        setLocale();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        settingsHelper.savePathForInnerFileManager(null);
        databaseHelper.releaseHelper();
    }

}
