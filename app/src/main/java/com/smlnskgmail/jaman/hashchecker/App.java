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
import androidx.preference.PreferenceManager;

import com.github.aelstad.keccakj.provider.KeccakjProvider;
import com.smlnskgmail.jaman.hashchecker.components.localdatastorage.api.LocalDataStorage;
import com.smlnskgmail.jaman.hashchecker.components.localdatastorage.impl.ormlite.OrmLiteLocalDataStorage;
import com.smlnskgmail.jaman.hashchecker.components.locale.api.Language;
import com.smlnskgmail.jaman.hashchecker.components.locale.api.LanguageConfig;
import com.smlnskgmail.jaman.hashchecker.components.locale.impl.LanguageConfigImpl;
import com.smlnskgmail.jaman.hashchecker.components.settings.api.Settings;
import com.smlnskgmail.jaman.hashchecker.components.settings.impl.SharedPreferencesSettings;
import com.smlnskgmail.jaman.hashchecker.components.theme.impl.ThemeConfigImpl;
import com.smlnskgmail.jaman.hashchecker.di.components.AppComponent;
import com.smlnskgmail.jaman.hashchecker.di.components.DaggerAppComponent;
import com.smlnskgmail.jaman.hashchecker.di.modules.DatabaseHelperModule;
import com.smlnskgmail.jaman.hashchecker.di.modules.LangHelperModule;
import com.smlnskgmail.jaman.hashchecker.di.modules.SettingsHelperModule;
import com.smlnskgmail.jaman.hashchecker.di.modules.ThemeHelperModule;

import java.security.Security;
import java.util.Arrays;
import java.util.Locale;

public class App extends android.app.Application {

    public static AppComponent appComponent;

    public static final String ACTION_START_WITH_TEXT = "com.smlnskgmail.jaman.hashchecker.ACTION_START_WITH_TEXT";
    public static final String ACTION_START_WITH_FILE = "com.smlnskgmail.jaman.hashchecker.ACTION_START_WITH_FILE";
    public static final String ACTION_START_WITH_FOLDER = "com.smlnskgmail.jaman.hashchecker.ACTION_START_WITH_FOLDER";

    private static final String SHORTCUT_TEXT_ID = "shortcut_text";
    private static final String SHORTCUT_FILE_ID = "shortcut_file";
    private static final String SHORTCUT_FOLDER_ID = "shortcut_folder";

    private LocalDataStorage localDataStorage;
    private Settings settings;
    private LanguageConfig languageConfig;

    @Override
    public void onCreate() {
        super.onCreate();
        Security.addProvider(new KeccakjProvider());
        localDataStorage = new OrmLiteLocalDataStorage(this);
        settings = new SharedPreferencesSettings(
                PreferenceManager.getDefaultSharedPreferences(this),
                new SharedPreferencesSettings.SharedPreferencesSettingsKeyExtractor() {
                    @NonNull
                    @Override
                    public String extractById(int resId) {
                        return getString(resId);
                    }
                }
        );
        languageConfig = new LanguageConfigImpl(this, settings);
        setTheme(settings.getSelectedTheme().getThemeResId());
        appComponent = DaggerAppComponent
                .builder()
                .databaseHelperModule(new DatabaseHelperModule(localDataStorage))
                .settingsHelperModule(new SettingsHelperModule(settings))
                .langHelperModule(new LangHelperModule(languageConfig))
                .themeHelperModule(new ThemeHelperModule(new ThemeConfigImpl(this, settings)))
                .build();
        if (!settings.isShortcutsIsCreated()) {
            createShortcuts();
            settings.saveShortcutsStatus(true);
        }
        setLocale();
    }

    private void createShortcuts() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);
            if (shortcutManager != null) {
                shortcutManager.setDynamicShortcuts(
                        Arrays.asList(
                                getShortcutForTextType(),
                                getShortcutForFileType(),
                                getShortcutForFolderType()
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
    private ShortcutInfo getShortcutForFolderType() {
        return getShortcut(
                SHORTCUT_FOLDER_ID,
                R.string.common_folder,
                R.drawable.ic_shortcut_folder,
                ACTION_START_WITH_FOLDER
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
        Intent intent = new Intent(this, MainActivity.class);
        intent.setAction(intentAction);
        return new ShortcutInfo.Builder(this, id)
                .setShortLabel(getString(labelResId))
                .setIcon(Icon.createWithResource(this, iconResId))
                .setIntent(intent)
                .build();
    }

    private void setLocale() {
        Language language = null;
        if (!settings.languageIsInitialized()) {
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
            languageConfig.setLanguage(language);
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setLocale();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        localDataStorage.releaseHelper();
    }

}
