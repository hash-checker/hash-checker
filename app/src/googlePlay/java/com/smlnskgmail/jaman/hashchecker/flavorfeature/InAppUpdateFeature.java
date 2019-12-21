package com.smlnskgmail.jaman.hashchecker.flavorfeature;

import android.app.Activity;
import android.content.IntentSender;

import androidx.annotation.NonNull;

import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;
import com.smlnskgmail.jaman.hashchecker.tools.LogTool;

public class InAppUpdateFeature {

    private static final int UPDATE_REQUEST_CODE = 1101;

    private final Activity activity;

    private AppUpdateManager appUpdateManager;

    InAppUpdateFeature(@NonNull Activity activity) {
        this.activity = activity;
    }

    public void bind() {
        appUpdateManager = AppUpdateManagerFactory.create(activity);
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();

        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                try {
                    startUpdate(appUpdateInfo);
                } catch (IntentSender.SendIntentException e) {
                    LogTool.e(e);
                }
            }
        });
    }

    private void startUpdate(@NonNull AppUpdateInfo appUpdateInfo)
            throws IntentSender.SendIntentException {
        appUpdateManager.startUpdateFlowForResult(
                appUpdateInfo,
                AppUpdateType.IMMEDIATE,
                activity,
                UPDATE_REQUEST_CODE
        );
    }

    public void resume() {
        appUpdateManager.getAppUpdateInfo().addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability()
                    == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                try {
                    startUpdate(appUpdateInfo);
                } catch (IntentSender.SendIntentException e) {
                    LogTool.e(e);
                }
            }
        });
    }

}
