package com.smlnskgmail.jaman.hashchecker;

import android.app.Activity;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.IntentSender;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;
import com.smlnskgmail.jaman.hashchecker.components.BaseActivity;
import com.smlnskgmail.jaman.hashchecker.components.BaseFragment;
import com.smlnskgmail.jaman.hashchecker.components.states.AppBackClickTarget;
import com.smlnskgmail.jaman.hashchecker.components.states.AppResumeTarget;
import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.ui.HashCalculatorFragment;
import com.smlnskgmail.jaman.hashchecker.logic.history.ui.HistoryFragment;
import com.smlnskgmail.jaman.hashchecker.logic.locale.api.LangHelper;
import com.smlnskgmail.jaman.hashchecker.logic.settings.api.SettingsHelper;
import com.smlnskgmail.jaman.hashchecker.logic.settings.ui.SettingsFragment;
import com.smlnskgmail.jaman.hashchecker.logic.themes.api.ThemeHelper;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    public static final String URI_FROM_EXTERNAL_APP
            = "com.smlnskgmail.jaman.hashchecker.URI_FROM_EXTERNAL_APP";

    private static final int MENU_MAIN_SECTION_SETTINGS = R.id.menu_main_section_settings;
    private static final int MENU_MAIN_SECTION_HISTORY = R.id.menu_main_section_history;

    private static final int REQUEST_APP_UPDATE = 1;

    @Inject
    SettingsHelper settingsHelper;

    @Inject
    LangHelper langHelper;

    @Inject
    ThemeHelper themeHelper;

    private AppUpdateManager appUpdateManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        App.appComponent.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        appUpdateManager
                .getAppUpdateInfo()
                .addOnSuccessListener(appUpdateInfo -> {
                    if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                        popupSnackbarForCompleteUpdate();
                    }
                });
    }

    @NonNull
    @Override
    protected SettingsHelper settingsHelper() {
        return settingsHelper;
    }

    @NonNull
    @Override
    protected LangHelper langHelper() {
        return langHelper;
    }

    @NonNull
    @Override
    protected ThemeHelper themeHelper() {
        return themeHelper;
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
            mainFragment.setArguments(
                    getConfiguredBundleWithDataUri(
                            intent.getData()
                    )
            );
            settingsHelper.setGenerateFromShareIntentMode(true);
        } else if (externalFileUri != null) {
            mainFragment.setArguments(
                    getConfiguredBundleWithDataUri(
                            externalFileUri
                    )
            );
            settingsHelper.setGenerateFromShareIntentMode(true);
        } else if (intent != null) {
            mainFragment.setArguments(
                    getBundleForShortcutAction(
                            intent.getAction()
                    )
            );
            settingsHelper.setGenerateFromShareIntentMode(false);
        }
        showFragment(mainFragment);
        checkForUpdateAvailability();
    }

    public void showFragment(@NonNull Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(
                        android.R.id.content,
                        fragment,
                        BaseFragment.CURRENT_FRAGMENT_TAG
                )
                .setCustomAnimations(
                        android.R.anim.fade_in,
                        android.R.anim.fade_out
                )
                .addToBackStack(null)
                .commit();
    }

    @NonNull
    private Bundle getConfiguredBundleWithDataUri(@NonNull Uri uri) {
        Bundle bundle = new Bundle();
        bundle.putString(
                URI_FROM_EXTERNAL_APP,
                uri.toString()
        );
        return bundle;
    }

    @NonNull
    private Bundle getBundleForShortcutAction(
            @Nullable String action
    ) {
        Bundle shortcutArguments = new Bundle();
        if (action != null) {
            if (action.equals(App.ACTION_START_WITH_TEXT)) {
                shortcutArguments.putBoolean(
                        App.ACTION_START_WITH_TEXT,
                        true
                );
            } else if (action.equals(App.ACTION_START_WITH_FILE)) {
                shortcutArguments.putBoolean(
                        App.ACTION_START_WITH_FILE,
                        true
                );
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
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(
                Activity.INPUT_METHOD_SERVICE
        );
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
        Fragment fragment = fragmentManager.findFragmentByTag(
                BaseFragment.CURRENT_FRAGMENT_TAG
        );
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

    private void checkForUpdateAvailability() {
        appUpdateManager = AppUpdateManagerFactory.create(this);
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();
        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {
                requestUpdate(appUpdateInfo);
            }
        });
    }

    private void requestUpdate(@NonNull AppUpdateInfo appUpdateInfo) {
        try {
            appUpdateManager.startUpdateFlowForResult(
                    appUpdateInfo,
                    AppUpdateType.FLEXIBLE,
                    this,
                    REQUEST_APP_UPDATE
            );
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }

    private void popupSnackbarForCompleteUpdate() {
        Snackbar snackbar = Snackbar.make(
                findViewById(android.R.id.content).getRootView(),
                getResources().getString(R.string.update_downloaded_message),
                Snackbar.LENGTH_INDEFINITE
        );
        snackbar.setAction(
                getResources().getString(R.string.update_restart_action),
                view -> appUpdateManager.completeUpdate()
        );
        snackbar.show();
    }

}