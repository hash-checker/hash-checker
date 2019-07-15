package com.smlnskgmail.jaman.hashchecker.navigation.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.smlnskgmail.jaman.hashchecker.navigation.states.OnBackListener;
import com.smlnskgmail.jaman.hashchecker.support.params.Tags;
import com.smlnskgmail.jaman.hashchecker.support.prefs.SettingsHelper;
import com.smlnskgmail.jaman.hashchecker.utils.AppUtils;
import com.smlnskgmail.jaman.hashchecker.utils.UIUtils;

import java.util.Arrays;

public class SettingsFragment extends PreferenceFragmentCompat implements OnBackListener {

    private ActionBar actionBar;
    private FragmentManager fragmentManager;
    private Context context;

    @SuppressLint("ResourceType")
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settings);
        fragmentManager = getActivity().getSupportFragmentManager();
        context = getContext();
        initializeActionBar();
        if (AppUtils.isNotQAndAbove()) {
            initializeInnerFileManagerSwitcher();
        } else {
            disableInnerFileManagerSettings();
        }
        initializeThemes();
        initializeAuthorLinks();
        initializeRateButton();
        initializeAppVersionInfo();
    }

    private void initializeActionBar() {
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setHomeAsUpIndicator(ContextCompat.getDrawable(context, R.drawable.ic_arrow_back));
    }

    private void initializeAuthorLinks() {
        findPreference(getString(R.string.key_author)).setOnPreferenceClickListener(preference -> {
            WebLinksBottomSheet webLinksBottomSheet = new WebLinksBottomSheet();
            webLinksBottomSheet.setItems(WebLink.getAuthorLinks());
            webLinksBottomSheet.show(fragmentManager, Tags.CURRENT_BOTTOM_SHEET_TAG);
            return false;
        });
    }

    private void initializeThemes() {
        findPreference(getString(R.string.key_theme)).setOnPreferenceClickListener(preference -> {
            ThemesBottomSheet themesBottomSheet = new ThemesBottomSheet();
            themesBottomSheet.setItems(Arrays.asList(Theme.values()));
            themesBottomSheet.show(fragmentManager, Tags.CURRENT_BOTTOM_SHEET_TAG);
            return false;
        });
    }

    private void initializeRateButton() {
        findPreference(getString(R.string.key_rate_app)).setOnPreferenceClickListener(preference -> {
            AppUtils.openGooglePlay(context, getView());
            return false;
        });
    }

    private void initializeAppVersionInfo() {
        findPreference(getString(R.string.key_version)).setSummary(String.format("%s (%s)",
                BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE));
    }

    private void initializeInnerFileManagerSwitcher() {
        findPreference(getString(R.string.key_inner_file_manager))
                .setOnPreferenceChangeListener((preference, o) -> {
                    SettingsHelper.setRefreshSelectedFileStatus(context, true);
                    return true;
                });
    }

    private void disableInnerFileManagerSettings() {
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
    public void onBack() {
        UIUtils.removeFragment(fragmentManager, this);
    }

}
