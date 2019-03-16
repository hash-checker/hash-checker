package com.smlnskgmail.jaman.hashchecker;

import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.smlnskgmail.jaman.hashchecker.components.BaseActivity;
import com.smlnskgmail.jaman.hashchecker.fragments.FeedbackFragment;
import com.smlnskgmail.jaman.hashchecker.fragments.MainFragment;
import com.smlnskgmail.jaman.hashchecker.fragments.SettingsFragment;
import com.smlnskgmail.jaman.hashchecker.fragments.interfaces.OnAppResume;
import com.smlnskgmail.jaman.hashchecker.fragments.interfaces.OnNavigationListener;
import com.smlnskgmail.jaman.hashchecker.support.preferences.Constants;
import com.smlnskgmail.jaman.hashchecker.support.utils.UIUtils;

public class MainActivity extends BaseActivity {

    @Override
    public void initialize() {
        Intent intent = getIntent();
        String actionFromIntent = intent.getAction();
        String scheme = intent.getScheme();

        MainFragment mainFragment = new MainFragment();
        if (scheme != null && scheme.compareTo(ContentResolver.SCHEME_CONTENT) == 0) {
            Bundle bundle = new Bundle();
            bundle.putString(Constants.Data.URI_FROM_EXTERNAL_APP, intent.getData().toString());
            mainFragment.setArguments(bundle);
        } else {
            mainFragment.setArguments(getBundleForShortcutAction(actionFromIntent));
        }

        UIUtils.showFragment(getSupportFragmentManager(), mainFragment);
    }

    private Bundle getBundleForShortcutAction(@Nullable String action) {
        Bundle shortcutArguments = new Bundle();
        if (action != null && action.equals(Constants.ShortcutActions.ACTION_START_WITH_TEXT_SELECTION)) {
            shortcutArguments.putBoolean(Constants.ShortcutActions.ACTION_START_WITH_TEXT_SELECTION, true);
        } else if (action != null && action.equals(Constants.ShortcutActions.ACTION_START_WITH_FILE_SELECTION)) {
            shortcutArguments.putBoolean(Constants.ShortcutActions.ACTION_START_WITH_FILE_SELECTION, true);
        }
        return shortcutArguments;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        UIUtils.hideKeyboard(this, findViewById(android.R.id.content));
        switch (item.getItemId()) {
            case R.id.menu_settings:
                UIUtils.showFragment(getSupportFragmentManager(), new SettingsFragment());
                break;
            case R.id.menu_feedback:
                UIUtils.showFragment(getSupportFragmentManager(), new FeedbackFragment());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(Constants.Tags.CURRENT_FRAGMENT_TAG);
        if (fragment instanceof OnNavigationListener) {
            ((OnNavigationListener) fragment).onBack();
        }
        for (Fragment fragmentInApp: getSupportFragmentManager().getFragments()) {
            if (fragmentInApp instanceof OnAppResume) {
                ((OnAppResume) fragmentInApp).resume();
            }
        }
    }

}
