package com.smlnskgmail.jaman.hashchecker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.smlnskgmail.jaman.hashchecker.fragments.MainFragment;
import com.smlnskgmail.jaman.hashchecker.fragments.SettingsFragment;
import com.smlnskgmail.jaman.hashchecker.fragments.interfaces.OnNavigationListener;
import com.smlnskgmail.jaman.hashchecker.fragments.interfaces.Resume;
import com.smlnskgmail.jaman.hashchecker.utils.Constants;
import com.smlnskgmail.jaman.hashchecker.utils.UIUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainFragment mainFragment = new MainFragment();

        Bundle shortcutArguments = new Bundle();
        String action = getIntent().getAction();
        if (action != null && action.equals(Constants.ShortcutActions.ACTION_START_WITH_TEXT_SELECTION)) {
            shortcutArguments.putBoolean(Constants.ShortcutActions.ACTION_START_WITH_TEXT_SELECTION, true);
        } else if (action != null && action.equals(Constants.ShortcutActions.ACTION_START_WITH_FILE_SELECTION)) {
            shortcutArguments.putBoolean(Constants.ShortcutActions.ACTION_START_WITH_FILE_SELECTION, true);
        }
        mainFragment.setArguments(shortcutArguments);

        UIUtils.showFragment(getSupportFragmentManager(), mainFragment);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                UIUtils.hideKeyboard(this, findViewById(android.R.id.content));
                UIUtils.showFragment(getSupportFragmentManager(), new SettingsFragment());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(Constants.TAGS.CURRENT_FRAGMENT_TAG);
        if (fragment instanceof OnNavigationListener) {
            ((OnNavigationListener) fragment).onBack();
        }
        for (Fragment fragmentInApp: getSupportFragmentManager().getFragments()) {
            if (fragmentInApp instanceof Resume) {
                ((Resume) fragmentInApp).resume();
            }
        }
    }

}
