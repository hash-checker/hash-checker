package com.smlnskgmail.jaman.hashchecker.logic.settings;

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
import androidx.fragment.app.FragmentManager;
import androidx.preference.PreferenceFragmentCompat;

import com.smlnskgmail.jaman.hashchecker.BuildConfig;
import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.states.AppBackClickTarget;
import com.smlnskgmail.jaman.hashchecker.logic.filemanager.manager.support.Requests;
import com.smlnskgmail.jaman.hashchecker.logic.history.db.utils.DatabaseExporter;
import com.smlnskgmail.jaman.hashchecker.logic.settings.lists.themes.Theme;
import com.smlnskgmail.jaman.hashchecker.logic.settings.lists.themes.ThemesBottomSheet;
import com.smlnskgmail.jaman.hashchecker.logic.settings.lists.weblinks.WebLink;
import com.smlnskgmail.jaman.hashchecker.logic.settings.lists.weblinks.WebLinksBottomSheet;
import com.smlnskgmail.jaman.hashchecker.logs.L;
import com.smlnskgmail.jaman.hashchecker.utils.AppUtils;
import com.smlnskgmail.jaman.hashchecker.utils.UIUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class SettingsFragment extends PreferenceFragmentCompat implements AppBackClickTarget {

    private ActionBar actionBar;
    private FragmentManager fragmentManager;
    private Context context;

    @SuppressLint("ResourceType")
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settings);
        fragmentManager = getActivity().getSupportFragmentManager();
        context = getContext();

        initActionBar();
        initFileManagerSwitcher();
        initThemesSettings();
        initPrivacyPolicy();
        initUserDataExport();
        initAuthorLinks();
        initRateButton();
        initAppVersionInfo();
    }

    private void initActionBar() {
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setHomeAsUpIndicator(ContextCompat.getDrawable(context, R.drawable.ic_arrow_back));
    }

    private void initThemesSettings() {
        findPreference(getString(R.string.key_theme)).setOnPreferenceClickListener(preference -> {
            ThemesBottomSheet themesBottomSheet = new ThemesBottomSheet();
            themesBottomSheet.setItems(Arrays.asList(Theme.values()));
            themesBottomSheet.showBottomSheet(fragmentManager);
            return false;
        });
    }

    private void initPrivacyPolicy() {
        findPreference(getString(R.string.key_privacy_policy)).setOnPreferenceClickListener(preference -> {
            AppUtils.openWebLink(context, context.getString(R.string.web_link_privacy_policy));
            return false;
        });
    }

    private void initUserDataExport() {
        findPreference(getString(R.string.key_export_user_data)).setOnPreferenceClickListener(preference -> {
            AppUtils.saveUserData(SettingsFragment.this, getView());
            return false;
        });
    }

    private void initAuthorLinks() {
        findPreference(getString(R.string.key_author)).setOnPreferenceClickListener(preference -> {
            WebLinksBottomSheet webLinksBottomSheet = new WebLinksBottomSheet();
            webLinksBottomSheet.setItems(WebLink.getAuthorLinks());
            webLinksBottomSheet.showBottomSheet(fragmentManager);
            return false;
        });
    }

    private void initRateButton() {
        findPreference(getString(R.string.key_rate_app)).setOnPreferenceClickListener(preference -> {
            openGooglePlay();
            return false;
        });
    }

    private void openGooglePlay() {
        final String appPackageName = context.getPackageName();
        Uri link;
        try {
            link = Uri.parse("market://details?id=" + appPackageName);
            context.startActivity(new Intent(Intent.ACTION_VIEW, link));
        } catch (ActivityNotFoundException e) {
            try {
                link = Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName);
                context.startActivity(new Intent(Intent.ACTION_VIEW, link));
            } catch (ActivityNotFoundException e2) {
                UIUtils.showSnackbar(context, getView(), context.getString(R.string.message_error_start_google_play));
                L.e(e2);
            }
        }
    }

    private void initAppVersionInfo() {
        findPreference(getString(R.string.key_version)).setSummary(String.format("%s (%s)",
                BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE));
    }

    private void initFileManagerSwitcher() {
        findPreference(getString(R.string.key_inner_file_manager)).setOnPreferenceChangeListener((preference, o) -> {
            SettingsHelper.setRefreshSelectedFileStatus(context, true);
            return true;
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        view.setBackgroundColor(UIUtils.getCommonBackgroundColor(context));
        setDividerHeight(0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            if (requestCode == Requests.FILE_CREATE) {
                if (resultCode == Activity.RESULT_OK) {
                    copyUserDataToUserFolder(data.getData());
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
                    FileOutputStream outputStream = new FileOutputStream(descriptor.getFileDescriptor());
                    copyFile(new File(DatabaseExporter.getUserDataZip(context)), outputStream);
                }
            } catch (IOException e) {
                L.e(e);
            }
        }
    }

    private void copyFile(@NonNull File source, @NonNull FileOutputStream outputStream)
            throws IOException {
        try (InputStream inputStream = new FileInputStream(source)) {
            byte[] buffer = new byte[1024];
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
        UIUtils.setActionBarTitle(actionBar, R.string.menu_title_settings);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void appBackClick() {
        UIUtils.removeFragment(fragmentManager, this);
    }

}
