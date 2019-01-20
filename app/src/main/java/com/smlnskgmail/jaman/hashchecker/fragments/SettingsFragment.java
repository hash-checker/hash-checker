package com.smlnskgmail.jaman.hashchecker.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.smlnskgmail.jaman.hashchecker.BuildConfig;
import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.weblinks.WebLinks;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.weblinks.WebLinksBottomSheet;
import com.smlnskgmail.jaman.hashchecker.fragments.interfaces.OnNavigationListener;
import com.smlnskgmail.jaman.hashchecker.support.values.Constants;
import com.smlnskgmail.jaman.hashchecker.support.utils.UIUtils;

import java.util.Arrays;

public class SettingsFragment extends PreferenceFragmentCompat implements OnNavigationListener {

    @SuppressLint("ResourceType")
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settings);
        initializeActionBar();
        addAuthorLinks();
        findPreference(getString(R.string.key_version)).setSummary(String.format("%s (%s)", BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE));
    }

    private void initializeActionBar() {
        ((AppCompatActivity) getActivity()).getSupportActionBar()
                .setHomeAsUpIndicator(ContextCompat.getDrawable(getContext(), R.drawable.ic_arrow_back));
    }

    private void addAuthorLinks() {
        findPreference(getString(R.string.key_author)).setOnPreferenceClickListener(preference -> {
            WebLinksBottomSheet webLinksBottomSheet = new WebLinksBottomSheet();
            webLinksBottomSheet.setListItems(Arrays.asList(WebLinks.SOURCE_CODE, WebLinks.MY_APPS));
            webLinksBottomSheet.show(getActivity().getSupportFragmentManager(), Constants.TAGS.CURRENT_BOTTOM_SHEET_TAG);
            return false;
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        view.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorCommonBackground));
        setDividerHeight(0);
    }

    @Override
    public void onResume() {
        super.onResume();
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle(R.string.menu_settings_title);
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
        UIUtils.removeFragment(getActivity().getSupportFragmentManager(), this);
    }

}
