package com.smlnskgmail.jaman.hashchecker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.smlnskgmail.jaman.hashchecker.fragments.BaseFragment;
import com.smlnskgmail.jaman.hashchecker.fragments.IResume;
import com.smlnskgmail.jaman.hashchecker.fragments.MainFragment;
import com.smlnskgmail.jaman.hashchecker.fragments.SettingsFragment;
import com.smlnskgmail.jaman.hashchecker.utils.AppUtils;
import com.smlnskgmail.jaman.hashchecker.utils.UIUtils;

public class MainActivity extends AppCompatActivity {

    private static final String CURRENT_FRAGMENT_TAG = "CURRENT_FRAGMENT";

    private void showFragment(@NonNull Fragment fragment, boolean animate, int in, int out) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        if (animate) {
            fragmentTransaction.setCustomAnimations(in, out);
        }
        fragmentTransaction.add(android.R.id.content, fragment, CURRENT_FRAGMENT_TAG)
                .addToBackStack(null)
                .commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainFragment mainFragment = new MainFragment();

        Bundle shortcutArguments = new Bundle();
        String action = getIntent().getAction();
        if (action != null && action.equals(HashCheckerApplication.ACTION_TEXT)) {
            shortcutArguments.putBoolean(HashCheckerApplication.ACTION_TEXT, true);
        } else if (action != null && action.equals(HashCheckerApplication.ACTION_FILE)) {
            shortcutArguments.putBoolean(HashCheckerApplication.ACTION_FILE, true);
        }
        mainFragment.setArguments(shortcutArguments);

        showFragment(mainFragment, false, -1, -1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                UIUtils.hideKeyboard(this, findViewById(android.R.id.content));
                showFragment(new SettingsFragment(), true, R.anim.slide_in_right, R.anim.slide_out_left);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(CURRENT_FRAGMENT_TAG);
        if (fragment instanceof BaseFragment) {
            ((BaseFragment) fragment).back();
        } else {
            if (fragment instanceof SettingsFragment) {
                for (Fragment fragmentInApp: getSupportFragmentManager().getFragments()) {
                    if (fragmentInApp instanceof IResume) {
                        ((IResume) fragmentInApp).resume();
                    }
                }
            }
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AppUtils.closeApp(this);
            return true;
        }
        return super.onKeyLongPress(keyCode, event);
    }

}
