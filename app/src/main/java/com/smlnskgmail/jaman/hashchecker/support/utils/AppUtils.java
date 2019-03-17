package com.smlnskgmail.jaman.hashchecker.support.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;

import com.smlnskgmail.jaman.hashchecker.MainActivity;
import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.filemanager.selector.FileSelectorActivity;
import com.smlnskgmail.jaman.hashchecker.support.preferences.Constants;

public class AppUtils {

    private static final int VIBRATION_LENGTH = 30;

    public static void openInnerFileManager(@NonNull Fragment fragment) {
        Intent openExplorerIntent = new Intent(fragment.getContext(), FileSelectorActivity.class);
        fragment.startActivityForResult(openExplorerIntent,
                Constants.Requests.FILE_SELECT_REQUEST_FROM_APP_FILE_MANAGER);
    }

    public static void openDefaultFileManager(@NonNull Fragment fragment, @NonNull View view) {
        try {
            Intent openExplorerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            openExplorerIntent.addCategory(Intent.CATEGORY_OPENABLE);
            openExplorerIntent.setType("*/*");
            fragment.startActivityForResult(openExplorerIntent,
                    Constants.Requests.FILE_SELECT_REQUEST);
        } catch (ActivityNotFoundException e) {
            UIUtils.showSnackbar(fragment.getContext(), view,
                    fragment.getString(R.string.message_error_start_file_selector), Snackbar.LENGTH_LONG);
        }
    }

    public static void openAppSettings(@NonNull Activity activity) {
        try {
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
            intent.setData(uri);
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            activity.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
        }
    }

    public static void openWebLink(@NonNull Context context, @NonNull String link) {
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
    }

    public static void sendEMail(@NonNull Context context, @NonNull String text, @NonNull String email) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO,
                Uri.fromParts("mailto", email, null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name));
        emailIntent.putExtra(Intent.EXTRA_TEXT, text);
        context.startActivity(Intent.createChooser(emailIntent, context.getString(R.string.message_email_app_chooser)));
    }

    static void vibrate(@NonNull Context context) {
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(VIBRATION_LENGTH,
                    VibrationEffect.DEFAULT_AMPLITUDE));
        }else{
            v.vibrate(VIBRATION_LENGTH);
        }
    }

    public static void closeApp(@NonNull Activity activity) {
        activity.finish();
    }

    public static void restartApp(@NonNull Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        closeApp(activity);
    }

}
