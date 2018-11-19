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
import com.smlnskgmail.jaman.hashchecker.fragments.MainFragment;
import com.smlnskgmail.jaman.hashchecker.fragments.SettingsFragment;
import com.smlnskgmail.jaman.hashchecker.utils.AppUtils;

public class MainActivity extends AppCompatActivity {

    private static final String CURRENT_FRAGMENT_TAG = "CURRENT_FRAGMENT";

    private MainFragment mainFragment;

    private void showFragment(@NonNull Fragment fragment, boolean animate, int in, int out) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        if (animate) {
            fragmentTransaction.setCustomAnimations(in, out);
        }
        fragmentTransaction.replace(android.R.id.content, fragment, CURRENT_FRAGMENT_TAG)
                .addToBackStack(null)
                .commit();
    }

    public void showMainFragment(boolean animate, int in, int out) {
        showFragment(mainFragment, animate, in, out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainFragment = new MainFragment();

        String action = getIntent().getAction();
        if (action != null && action.equals(HashCheckerApplication.ACTION_TEXT)) {
            mainFragment.setStartWithTextSelection(true);
        } else if (action != null && action.equals(HashCheckerApplication.ACTION_FILE)) {
            mainFragment.setStartWithFileSelection(true);
        }

        showFragment(mainFragment, false, -1, -1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
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
        } else if (fragment instanceof SettingsFragment) {
            ((SettingsFragment) fragment).back();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AppUtils.closeApp();
            return true;
        }
        return super.onKeyLongPress(keyCode, event);
    }

}
