package com.smlnskgmail.jaman.hashchecker;

import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.smlnskgmail.jaman.hashchecker.components.BaseActivity;
import com.smlnskgmail.jaman.hashchecker.navigation.fragments.FeedbackFragment;
import com.smlnskgmail.jaman.hashchecker.navigation.fragments.MainFragment;
import com.smlnskgmail.jaman.hashchecker.navigation.fragments.SettingsFragment;
import com.smlnskgmail.jaman.hashchecker.navigation.fragments.history.HistoryFragment;
import com.smlnskgmail.jaman.hashchecker.navigation.states.OnAppResume;
import com.smlnskgmail.jaman.hashchecker.navigation.states.OnBackListener;
import com.smlnskgmail.jaman.hashchecker.support.params.Constants;
import com.smlnskgmail.jaman.hashchecker.support.params.Shortcuts;
import com.smlnskgmail.jaman.hashchecker.support.params.Tags;
import com.smlnskgmail.jaman.hashchecker.support.prefs.PrefsHelper;
import com.smlnskgmail.jaman.hashchecker.utils.UIUtils;

public class MainActivity extends BaseActivity {

    @Override
    public void initialize() {
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

        MainFragment mainFragment = new MainFragment();
        if (scheme != null && scheme.compareTo(ContentResolver.SCHEME_CONTENT) == 0) {
            mainFragment.setArguments(getConfiguredBundleWithDataUri(intent.getData()));
            PrefsHelper.setGenerateFromShareIntentMode(this, true);
        } else if (externalFileUri != null) {
            mainFragment.setArguments(getConfiguredBundleWithDataUri(externalFileUri));
            PrefsHelper.setGenerateFromShareIntentMode(this, true);
        } else {
            mainFragment.setArguments(getBundleForShortcutAction(intent.getAction()));
            PrefsHelper.setGenerateFromShareIntentMode(this, false);
        }

        UIUtils.showFragment(getSupportFragmentManager(), mainFragment);
    }

    @NonNull
    private Bundle getConfiguredBundleWithDataUri(@NonNull Uri uri) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.URI_FROM_EXTERNAL_APP, uri.toString());
        return bundle;
    }

    @NonNull
    private Bundle getBundleForShortcutAction(@Nullable String action) {
        Bundle shortcutArguments = new Bundle();
        if (action != null && action.equals(Shortcuts.ACTION_START_WITH_TEXT_SELECTION)) {
            shortcutArguments.putBoolean(Shortcuts.ACTION_START_WITH_TEXT_SELECTION, true);
        } else if (action != null && action.equals(Shortcuts.ACTION_START_WITH_FILE_SELECTION)) {
            shortcutArguments.putBoolean(Shortcuts.ACTION_START_WITH_FILE_SELECTION, true);
        }
        return shortcutArguments;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        UIUtils.hideKeyboard(this, findViewById(android.R.id.content));
        switch (item.getItemId()) {
            case R.id.menu_main_section_settings:
                UIUtils.showFragment(getSupportFragmentManager(), new SettingsFragment());
                break;
            case R.id.menu_main_section_feedback:
                UIUtils.showFragment(getSupportFragmentManager(), new FeedbackFragment());
                break;
            case R.id.menu_main_section_history:
                UIUtils.showFragment(getSupportFragmentManager(), new HistoryFragment());
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager()
                .findFragmentByTag(Tags.CURRENT_FRAGMENT_TAG);
        if (fragment instanceof OnBackListener) {
            ((OnBackListener) fragment).onBack();
        }
        for (Fragment fragmentInApp: getSupportFragmentManager().getFragments()) {
            if (fragmentInApp instanceof OnAppResume) {
                ((OnAppResume) fragmentInApp).onAppResume();
            }
        }
    }

}
