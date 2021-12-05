package com.smlnskgmail.jaman.hashchecker;

import android.app.Activity;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.smlnskgmail.jaman.hashchecker.components.locale.api.LanguageConfig;
import com.smlnskgmail.jaman.hashchecker.components.settings.api.Settings;
import com.smlnskgmail.jaman.hashchecker.components.theme.api.ThemeConfig;
import com.smlnskgmail.jaman.hashchecker.features.hashcalculator.HashCalculatorFragment;
import com.smlnskgmail.jaman.hashchecker.features.history.view.HistoryFragment;
import com.smlnskgmail.jaman.hashchecker.features.settings.view.SettingsFragment;
import com.smlnskgmail.jaman.hashchecker.ui.BaseActivity;
import com.smlnskgmail.jaman.hashchecker.ui.BaseFragment;
import com.smlnskgmail.jaman.hashchecker.ui.states.AppBackClickTarget;
import com.smlnskgmail.jaman.hashchecker.ui.states.AppResumeTarget;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    public static final String URI_FROM_EXTERNAL_APP
            = "com.smlnskgmail.jaman.hashchecker.URI_FROM_EXTERNAL_APP";

    private static final int MENU_MAIN_SECTION_SETTINGS = R.id.menu_main_section_settings;
    private static final int MENU_MAIN_SECTION_HISTORY = R.id.menu_main_section_history;

    @Inject
    public Settings settings;

    @Inject
    public LanguageConfig languageConfig;

    @Inject
    public ThemeConfig themeConfig;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        App.appComponent.inject(this);
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    protected LanguageConfig langHelper() {
        return languageConfig;
    }

    @NonNull
    @Override
    protected ThemeConfig themeHelper() {
        return themeConfig;
    }

    @Override
    public void create() {
        Intent intent = getIntent();
        String scheme = null;
        ClipData clipData = null;
        if (intent != null) {
            scheme = intent.getScheme();
            clipData = intent.getClipData();
        }
        Uri externalFileUri = null;
        if (clipData != null) {
            externalFileUri = clipData.getItemAt(0).getUri();
        }

        HashCalculatorFragment mainFragment = new HashCalculatorFragment();
        if (scheme != null
                && (scheme.equals(ContentResolver.SCHEME_CONTENT) || scheme.equals(ContentResolver.SCHEME_FILE))) {
            mainFragment.setArguments(getConfiguredBundleWithDataUri(intent.getData()));
            settings.setGenerateFromShareIntentMode(true);
        } else if (externalFileUri != null) {
            mainFragment.setArguments(getConfiguredBundleWithDataUri(externalFileUri));
            settings.setGenerateFromShareIntentMode(true);
        } else if (intent != null) {
            mainFragment.setArguments(getBundleForShortcutAction(intent.getAction()));
            settings.setGenerateFromShareIntentMode(false);
        }
        showFragment(mainFragment);
    }

    public void showFragment(@NonNull Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(android.R.id.content, fragment, BaseFragment.CURRENT_FRAGMENT_TAG)
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .addToBackStack(null)
                .commit();
    }

    @NonNull
    private Bundle getConfiguredBundleWithDataUri(@NonNull Uri uri) {
        Bundle bundle = new Bundle();
        bundle.putString(URI_FROM_EXTERNAL_APP, uri.toString());
        return bundle;
    }

    @NonNull
    private Bundle getBundleForShortcutAction(@Nullable String action) {
        Bundle shortcutArguments = new Bundle();
        if (action != null) {
            if (action.equals(App.ACTION_START_WITH_TEXT)) {
                shortcutArguments.putBoolean(App.ACTION_START_WITH_TEXT, true);
            } else if (action.equals(App.ACTION_START_WITH_FILE)) {
                shortcutArguments.putBoolean(App.ACTION_START_WITH_FILE, true);
            }
        }
        return shortcutArguments;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        hideKeyboard();
        switch (item.getItemId()) {
            case MENU_MAIN_SECTION_SETTINGS:
                showFragment(new SettingsFragment());
                break;
            case MENU_MAIN_SECTION_HISTORY:
                showFragment(new HistoryFragment());
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(
                    findViewById(android.R.id.content).getWindowToken(),
                    0
            );
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(BaseFragment.CURRENT_FRAGMENT_TAG);
        if (fragment instanceof AppBackClickTarget) {
            ((AppBackClickTarget) fragment).appBackClick();
        }
        List<Fragment> fragments = fragmentManager.getFragments();
        if (!fragments.isEmpty()) {
            Fragment nextFragment = fragments.get(fragments.size() - 1);
            if (nextFragment instanceof AppResumeTarget) {
                ((AppResumeTarget) nextFragment).appResume();
            }
        }
    }
}
