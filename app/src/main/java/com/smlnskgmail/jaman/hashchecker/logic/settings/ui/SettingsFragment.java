package com.smlnskgmail.jaman.hashchecker.logic.settings.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.preference.PreferenceFragmentCompat;

import com.smlnskgmail.jaman.hashchecker.BuildConfig;
import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.BaseFragment;
import com.smlnskgmail.jaman.hashchecker.components.states.AppBackClickTarget;
import com.smlnskgmail.jaman.hashchecker.components.states.AppResumeTarget;
import com.smlnskgmail.jaman.hashchecker.logic.database.DatabaseExporter;
import com.smlnskgmail.jaman.hashchecker.logic.database.HelperFactory;
import com.smlnskgmail.jaman.hashchecker.logic.feedback.FeedbackFragment;
import com.smlnskgmail.jaman.hashchecker.logic.logs.L;
import com.smlnskgmail.jaman.hashchecker.logic.settings.SettingsHelper;
import com.smlnskgmail.jaman.hashchecker.logic.settings.ui.lists.languages.LanguagesBottomSheet;
import com.smlnskgmail.jaman.hashchecker.logic.settings.ui.lists.themes.ThemesBottomSheet;
import com.smlnskgmail.jaman.hashchecker.logic.settings.ui.lists.weblinks.AuthorWebLinksBottomSheet;
import com.smlnskgmail.jaman.hashchecker.logic.settings.ui.lists.weblinks.LibrariesWebLinksBottomSheet;
import com.smlnskgmail.jaman.hashchecker.logic.settings.ui.lists.weblinks.PrivacyPolicyWebLinksBottomSheet;
import com.smlnskgmail.jaman.hashchecker.utils.UIUtils;
import com.smlnskgmail.jaman.hashchecker.utils.WebUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SettingsFragment extends PreferenceFragmentCompat implements AppBackClickTarget, AppResumeTarget {

    private ActionBar actionBar;
    private FragmentManager fragmentManager;
    private Context context;

    @SuppressLint("ResourceType")
    @Override
    public void onCreatePreferences(
            Bundle savedInstanceState,
            String rootKey
    ) {
        addPreferencesFromResource(R.xml.settings);
        fragmentManager = getActivity().getSupportFragmentManager();
        context = getContext();

        initializeActionBar();
        initializeFileManagerSwitcher();
        initializeLanguageSettings();
        initializeThemesSettings();
        initializePrivacyPolicy();
        initializeUserDataExport();
        initializeAuthorLinks();
        initializeLibrariesLinks();
        initializeFeedbackButton();
        initializeRateButton();
        initializeHelpWithTranslationButton();
        initializeAppVersionInfo();
    }

    private void initializeActionBar() {
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setHomeAsUpIndicator(
                ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_arrow_back
                )
        );
    }

    private void initializeLanguageSettings() {
        findPreference(getString(R.string.key_language))
                .setOnPreferenceClickListener(preference -> {
                    LanguagesBottomSheet languagesBottomSheet = new LanguagesBottomSheet();
                    languagesBottomSheet.show(
                            fragmentManager,
                            languagesBottomSheet.getClass().getCanonicalName()
                    );
                    return false;
                });
    }

    private void initializeThemesSettings() {
        findPreference(getString(R.string.key_theme))
                .setOnPreferenceClickListener(preference -> {
                    ThemesBottomSheet themesBottomSheet = new ThemesBottomSheet();
                    themesBottomSheet.show(
                            fragmentManager,
                            themesBottomSheet.getClass().getCanonicalName()
                    );
                    return false;
                });
    }

    private void initializePrivacyPolicy() {
        findPreference(getString(R.string.key_privacy_policy))
                .setOnPreferenceClickListener(preference -> {
                    PrivacyPolicyWebLinksBottomSheet privacyPolicyWebLinksBottomSheet
                            = new PrivacyPolicyWebLinksBottomSheet();
                    privacyPolicyWebLinksBottomSheet.show(
                            fragmentManager,
                            privacyPolicyWebLinksBottomSheet.getClass().getCanonicalName()
                    );
                    return false;
                });
    }

    private void initializeUserDataExport() {
        findPreference(getString(R.string.key_export_user_data))
                .setOnPreferenceClickListener(preference -> {
                    saveUserData();
                    return false;
                });
    }

    private void saveUserData() {
        if (HelperFactory.getHelper().isHistoryItemsListIsEmpty()) {
            try {
                Intent saveFileIntent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
                saveFileIntent.addCategory(Intent.CATEGORY_OPENABLE);
                saveFileIntent.setType("application/zip");
                saveFileIntent.putExtra(
                        Intent.EXTRA_TITLE,
                        DatabaseExporter.EXPORT_FILE
                );
                startActivityForResult(
                        saveFileIntent,
                        SettingsHelper.FILE_CREATE
                );
            } catch (ActivityNotFoundException e) {
                L.e(e);
                UIUtils.showSnackbar(
                        context,
                        getView(),
                        getString(R.string.message_error_start_file_selector)
                );
            }
        } else {
            UIUtils.showSnackbar(
                    context,
                    getView(),
                    getString(R.string.history_empty_view_message)
            );
        }
    }

    private void initializeAuthorLinks() {
        findPreference(getString(R.string.key_author))
                .setOnPreferenceClickListener(preference -> {
                    AuthorWebLinksBottomSheet authorWebLinksBottomSheet
                            = new AuthorWebLinksBottomSheet();
                    authorWebLinksBottomSheet.show(
                            fragmentManager,
                            authorWebLinksBottomSheet.getClass().getCanonicalName()
                    );
                    return false;
                });
    }

    private void initializeLibrariesLinks() {
        findPreference(getString(R.string.key_libraries))
                .setOnPreferenceClickListener(preference -> {
                    LibrariesWebLinksBottomSheet librariesWebLinksBottomSheet
                            = new LibrariesWebLinksBottomSheet();
                    librariesWebLinksBottomSheet.show(
                            fragmentManager,
                            librariesWebLinksBottomSheet.getClass().getCanonicalName()
                    );
                    return false;
                });
    }

    private void initializeHelpWithTranslationButton() {
        findPreference(getString(R.string.key_help_with_translation))
                .setOnPreferenceClickListener(preference -> {
                    WebUtils.openWebLink(
                            context,
                            context.getString(R.string.web_link_help_with_translation)
                    );
                    return false;
                });
    }

    private void initializeRateButton() {
        findPreference(getString(R.string.key_rate_app))
                .setOnPreferenceClickListener(preference -> {
                    WebUtils.openGooglePlay(
                            context,
                            getView()
                    );
                    return false;
                });
    }

    private void initializeFeedbackButton() {
        findPreference(getString(R.string.key_feedback))
                .setOnPreferenceClickListener(preference -> {
                    showFragment(new FeedbackFragment());
                    return false;
                });
    }

    private void showFragment(@NonNull Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(
                        android.R.id.content,
                        fragment,
                        BaseFragment.CURRENT_FRAGMENT_TAG
                )
                .setCustomAnimations(
                        android.R.anim.fade_in,
                        android.R.anim.fade_out
                )
                .addToBackStack(null)
                .commit();
    }


    private void initializeAppVersionInfo() {
        findPreference(getString(R.string.key_version)).setSummary(
                String.format(
                        "%s (%s)",
                        BuildConfig.VERSION_NAME,
                        BuildConfig.VERSION_CODE
                )
        );
    }

    private void initializeFileManagerSwitcher() {
        findPreference(getString(R.string.key_inner_file_manager))
                .setOnPreferenceChangeListener((preference, o) -> {
                    SettingsHelper.setRefreshSelectedFileStatus(
                            context,
                            true
                    );
                    return true;
                });
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        view.setBackgroundColor(
                UIUtils.getCommonBackgroundColor(
                        context
                )
        );
        setDividerHeight(0);
    }

    @Override
    public void onActivityResult(
            int requestCode,
            int resultCode,
            @Nullable Intent data
    ) {
        if (data != null) {
            if (requestCode == SettingsHelper.FILE_CREATE) {
                if (resultCode == Activity.RESULT_OK) {
                    copyUserDataToUserFolder(
                            data.getData()
                    );
                }
            }
        }
    }

    private void copyUserDataToUserFolder(@Nullable Uri uri) {
        if (uri != null) {
            try {
                DatabaseExporter.exportDatabase(context);
                ParcelFileDescriptor descriptor = context.getApplicationContext().getContentResolver()
                        .openFileDescriptor(uri, "w");
                if (descriptor != null) {
                    FileOutputStream outputStream = new FileOutputStream(
                            descriptor.getFileDescriptor()
                    );
                    copyFile(
                            new File(
                                    DatabaseExporter.getUserDataZip(
                                            context
                                    )
                            ),
                            outputStream
                    );
                }
            } catch (IOException e) {
                L.e(e);
            }
        }
    }

    private void copyFile(
            @NonNull File source,
            @NonNull FileOutputStream outputStream
    ) throws IOException {
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
    public void onResume() {
        super.onResume();
        appResume();
    }

    @Override
    public void onCreateOptionsMenu(
            @NonNull Menu menu,
            @NonNull MenuInflater inflater
    ) {
        menu.clear();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void appBackClick() {
        UIUtils.removeFragment(
                fragmentManager,
                this
        );
    }

    @Override
    public void appResume() {
        UIUtils.setActionBarTitle(
                actionBar,
                R.string.menu_title_settings
        );
        actionBar.setDisplayHomeAsUpEnabled(true);
        initializeActionBar();
    }

}
