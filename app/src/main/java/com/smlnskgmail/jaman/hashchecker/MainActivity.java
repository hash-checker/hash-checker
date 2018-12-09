package com.smlnskgmail.jaman.hashchecker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.smlnskgmail.jaman.hashchecker.fragments.MainFragment;
import com.smlnskgmail.jaman.hashchecker.fragments.SettingsFragment;
import com.smlnskgmail.jaman.hashchecker.fragments.interfaces.IBack;
import com.smlnskgmail.jaman.hashchecker.fragments.interfaces.IResume;
import com.smlnskgmail.jaman.hashchecker.utils.AppUtils;
import com.smlnskgmail.jaman.hashchecker.utils.Constants;
import com.smlnskgmail.jaman.hashchecker.utils.UIUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainFragment mainFragment = new MainFragment();

        Bundle shortcutArguments = new Bundle();
        String action = getIntent().getAction();
        if (action != null && action.equals(Constants.ShortcutActions.ACTION_TEXT)) {
            shortcutArguments.putBoolean(Constants.ShortcutActions.ACTION_TEXT, true);
        } else if (action != null && action.equals(Constants.ShortcutActions.ACTION_FILE)) {
            shortcutArguments.putBoolean(Constants.ShortcutActions.ACTION_FILE, true);
        }
        mainFragment.setArguments(shortcutArguments);

        UIUtils.showFragment(getSupportFragmentManager(), mainFragment);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                UIUtils.hideKeyboard(this, findViewById(android.R.id.content));
                UIUtils.showFragment(getSupportFragmentManager(), new SettingsFragment());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(Constants.TAGS.CURRENT_FRAGMENT_TAG);
        if (fragment instanceof IBack) {
            ((IBack) fragment).back();
        }
        for (Fragment fragmentInApp: getSupportFragmentManager().getFragments()) {
            if (fragmentInApp instanceof IResume) {
                ((IResume) fragmentInApp).resume();
            }
        }
    }

}
