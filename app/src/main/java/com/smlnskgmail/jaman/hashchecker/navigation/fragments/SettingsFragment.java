package com.smlnskgmail.jaman.hashchecker.navigation.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import androidx.preference.PreferenceGroup;

import com.smlnskgmail.jaman.hashchecker.BuildConfig;
import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.settings.themes.Theme;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.settings.themes.ThemesBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.settings.weblinks.WebLink;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.settings.weblinks.WebLinksBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.filemanager.manager.support.Requests;
import com.smlnskgmail.jaman.hashchecker.navigation.states.BackClickTarget;
import com.smlnskgmail.jaman.hashchecker.support.prefs.SettingsHelper;
import com.smlnskgmail.jaman.hashchecker.utils.AppUtils;
import com.smlnskgmail.jaman.hashchecker.utils.UIUtils;

import java.util.Arrays;

public class SettingsFragment extends PreferenceFragmentCompat implements BackClickTarget {

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
        initFileManagerSettings();
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

    private void initFileManagerSettings() {
        if (AppUtils.isNotQAndAbove()) {
            initFileManagerSwitcher();
        } else {
            disableFileManagerSwitcher();
        }
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
            AppUtils.openGooglePlay(context, getView());
            return false;
        });
    }

    private void initAppVersionInfo() {
        findPreference(getString(R.string.key_version)).setSummary(String.format("%s (%s)",
                BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE));
    }

    private void initFileManagerSwitcher() {
        findPreference(getString(R.string.key_inner_file_manager))
                .setOnPreferenceChangeListener((preference, o) -> {
                    SettingsHelper.setRefreshSelectedFileStatus(context, true);
                    return true;
                });
    }

    private void disableFileManagerSwitcher() {
        ((PreferenceGroup) findPreference(getString(R.string.key_category_app)))
                .removePreference(findPreference(getString(R.string.key_inner_file_manager)));
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
                    AppUtils.copyUserDataToUserFolder(context, data.getData());
                }
            }
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
    public void onBackClick() {
        UIUtils.removeFragment(fragmentManager, this);
    }

}
