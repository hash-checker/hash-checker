package com.smlnskgmail.jaman.hashchecker.features.settings.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.smlnskgmail.jaman.hashchecker.App;
import com.smlnskgmail.jaman.hashchecker.MainActivity;
import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.localdatastorage.api.LocalDataExporter;
import com.smlnskgmail.jaman.hashchecker.components.localdatastorage.api.LocalDataStorage;
import com.smlnskgmail.jaman.hashchecker.components.settings.api.Settings;
import com.smlnskgmail.jaman.hashchecker.components.theme.api.ThemeConfig;
import com.smlnskgmail.jaman.hashchecker.features.feedback.view.FeedbackFragment;
import com.smlnskgmail.jaman.hashchecker.features.settings.presenter.SettingsPresenter;
import com.smlnskgmail.jaman.hashchecker.features.settings.presenter.SettingsPresenterImpl;
import com.smlnskgmail.jaman.hashchecker.features.settings.view.lists.languages.LanguagesBottomSheet;
import com.smlnskgmail.jaman.hashchecker.features.settings.view.lists.themes.ThemesBottomSheet;
import com.smlnskgmail.jaman.hashchecker.features.settings.view.lists.weblinks.AuthorWebLinksBottomSheet;
import com.smlnskgmail.jaman.hashchecker.features.settings.view.lists.weblinks.LibrariesWebLinksBottomSheet;
import com.smlnskgmail.jaman.hashchecker.features.settings.view.lists.weblinks.PrivacyPolicyWebLinksBottomSheet;
import com.smlnskgmail.jaman.hashchecker.ui.dialogs.system.AppSnackbar;
import com.smlnskgmail.jaman.hashchecker.ui.states.AppBackClickTarget;
import com.smlnskgmail.jaman.hashchecker.ui.states.AppResumeTarget;
import com.smlnskgmail.jaman.hashchecker.utils.LogUtils;
import com.smlnskgmail.jaman.hashchecker.utils.UIUtils;
import com.smlnskgmail.jaman.hashchecker.utils.WebUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;

public class SettingsFragment extends PreferenceFragmentCompat
        implements SettingsView, AppBackClickTarget, AppResumeTarget {

    @Inject
    public LocalDataStorage localDataStorage;

    @Inject
    public Settings settings;

    @Inject
    public ThemeConfig themeConfig;

    private SettingsPresenter settingsPresenter;

    private ActionBar actionBar;
    private FragmentManager fragmentManager;
    private Context context;

    // CPD-OFF
    @Override
    public void onAttach(@NonNull Context context) {
        App.appComponent.inject(this);
        super.onAttach(context);
    }
    // CPD-ON

    @SuppressWarnings("MethodParametersAnnotationCheck")
    @SuppressLint("ResourceType")
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settings);
        fragmentManager = getActivity().getSupportFragmentManager();
        context = getContext();

        initializeActionBar();

        settingsPresenter = new SettingsPresenterImpl();
        settingsPresenter.init(this, localDataStorage);
    }

    private void initializeActionBar() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            actionBar = activity.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setHomeAsUpIndicator(
                        ContextCompat.getDrawable(
                                context,
                                R.drawable.ic_arrow_back
                        )
                );
            }
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        view.setBackgroundColor(themeConfig.getCommonBackgroundColor());
        setDividerHeight(0);
    }

    @Override
    public void onActivityResult(
            int requestCode,
            int resultCode,
            @Nullable Intent data
    ) {
        if (data != null && requestCode == Settings.FILE_CREATE && resultCode == Activity.RESULT_OK) {
            copyUserDataToUserFolder(data.getData());
        }
    }

    private void copyUserDataToUserFolder(@Nullable Uri uri) {
        if (uri != null) {
            try {
                LocalDataExporter.exportDatabase(context, localDataStorage);
                ParcelFileDescriptor descriptor = context.getApplicationContext().getContentResolver()
                        .openFileDescriptor(uri, "w");
                if (descriptor != null) {
                    FileOutputStream outputStream = new FileOutputStream(descriptor.getFileDescriptor());
                    copyFile(new File(LocalDataExporter.getUserDataZip(context)), outputStream);
                }
            } catch (IOException e) {
                LogUtils.e(e);
            }
        }
    }

    private void copyFile(@NonNull File source, @NonNull FileOutputStream outputStream) throws IOException {
        try (InputStream inputStream = new FileInputStream(source)) {
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];

            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.close();
        }
    }

    @Override
    public void initLanguageSettings() {
        Preference languagePreference = findPreference(getString(R.string.key_language));
        if (languagePreference != null) {
            languagePreference.setOnPreferenceClickListener(preference -> {
                LanguagesBottomSheet bottomSheet = new LanguagesBottomSheet();
                bottomSheet.show(fragmentManager, bottomSheet.key());
                return false;
            });
        }
    }

    @Override
    public void initThemeSettings() {
        Preference themePreference = findPreference(getString(R.string.key_theme));
        if (themePreference != null) {
            themePreference.setOnPreferenceClickListener(preference -> {
                ThemesBottomSheet bottomSheet = new ThemesBottomSheet();
                bottomSheet.show(fragmentManager, bottomSheet.key());
                return false;
            });
        }
    }

    @Override
    public void initPrivacyPolicy() {
        Preference privacyPolicyPreference = findPreference(getString(R.string.key_privacy_policy));
        if (privacyPolicyPreference != null) {
            privacyPolicyPreference.setOnPreferenceClickListener(preference -> {
                PrivacyPolicyWebLinksBottomSheet bottomSheet = new PrivacyPolicyWebLinksBottomSheet();
                bottomSheet.show(fragmentManager, bottomSheet.key());
                return false;
            });
        }
    }

    @Override
    public void initUserDataExport() {
        Preference exportUserDataPreference = findPreference(getString(R.string.key_export_user_data));
        if (exportUserDataPreference != null) {
            exportUserDataPreference.setOnPreferenceClickListener(preference -> {
                settingsPresenter.saveUserData();
                return false;
            });
        }
    }

    @Override
    public void initAuthorLinks() {
        Preference authorPreference = findPreference(getString(R.string.key_author));
        if (authorPreference != null) {
            authorPreference.setOnPreferenceClickListener(preference -> {
                AuthorWebLinksBottomSheet bottomSheet = new AuthorWebLinksBottomSheet();
                bottomSheet.show(fragmentManager, bottomSheet.key());
                return false;
            });
        }
    }

    @Override
    public void initLibrariesLinks() {
        Preference librariesPreference = findPreference(getString(R.string.key_libraries));
        if (librariesPreference != null) {
            librariesPreference.setOnPreferenceClickListener(preference -> {
                LibrariesWebLinksBottomSheet bottomSheet = new LibrariesWebLinksBottomSheet();
                bottomSheet.show(fragmentManager, bottomSheet.key());
                return false;
            });
        }
    }

    @Override
    public void initHelpWithTranslations() {
        Preference helpWithTranslationPreference = findPreference(getString(R.string.key_help_with_translation));
        if (helpWithTranslationPreference != null) {
            helpWithTranslationPreference.setOnPreferenceClickListener(preference -> {
                WebUtils.openWebLink(
                        context,
                        context.getString(R.string.web_link_help_with_translation)
                );
                return false;
            });
        }
    }

    @Override
    public void initRate() {
        Preference rateAppPreference = findPreference(getString(R.string.key_rate_app));
        if (rateAppPreference != null) {
            View view = getView();
            if (view != null) {
                rateAppPreference.setOnPreferenceClickListener(preference -> {
                    WebUtils.openGooglePlay(
                            context,
                            view,
                            settings,
                            themeConfig
                    );
                    return false;
                });
            }
        }
    }

    @Override
    public void initFeedback() {
        Preference feedbackPreference = findPreference(getString(R.string.key_feedback));
        if (feedbackPreference != null) {
            feedbackPreference.setOnPreferenceClickListener(preference -> {
                MainActivity activity = (MainActivity) getActivity();
                if (activity != null) {
                    activity.showFragment(new FeedbackFragment());
                }
                return false;
            });
        }
    }

    @Override
    public void initAppVersionInfo(@NonNull String appVersion) {
        Preference versionPreference = findPreference(getString(R.string.key_version));
        if (versionPreference != null) {
            versionPreference.setSummary(appVersion);
        }
    }

    @Override
    public void saveUserDataResult(@NonNull Pair<SettingsPresenter.SaveUserDataResult, Intent> result) {
        switch (result.first) {
            case DONE:
                startActivityForResult(result.second, Settings.FILE_CREATE);
                break;
            case ERROR:
                View v1 = getView();
                if (v1 != null) {
                    new AppSnackbar(
                            context,
                            v1,
                            R.string.message_error_start_file_selector,
                            settings,
                            themeConfig
                    ).show();
                }
                break;
            case EMPTY:
                View v2 = getView();
                if (v2 != null) {
                    new AppSnackbar(
                            context,
                            v2,
                            R.string.history_empty_view_message,
                            settings,
                            themeConfig
                    ).show();
                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        appResume();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
    }

    // CPD-OFF
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Activity activity = getActivity();
            if (activity != null) {
                activity.onBackPressed();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    // CPD-ON

    @Override
    public void appBackClick() {
        UIUtils.removeFragment(fragmentManager, this);
    }

    @Override
    public void appResume() {
        actionBar.setTitle(R.string.menu_title_settings);
        actionBar.setDisplayHomeAsUpEnabled(true);
        initializeActionBar();
    }

}
