package com.smlnskgmail.jaman.hashchecker.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;

import com.smlnskgmail.jaman.hashchecker.MainActivity;
import com.smlnskgmail.jaman.hashchecker.R;

public class AppUtils {

    public static final int FILE_SELECT_REQUEST = 1;

    private static final int VIBRATION_LENGTH = 30;

    public static void closeApp() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    public static void  restartApp(@NonNull Activity activity) {
        activity.finish();
        activity.startActivity(new Intent(activity, MainActivity.class));
    }

    public static void searchFile(@NonNull Fragment fragment, @NonNull View mainScreen) {
        try {
            Intent openExplorerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            openExplorerIntent.addCategory(Intent.CATEGORY_OPENABLE);
            openExplorerIntent.setType("*/*");
            fragment.startActivityForResult(openExplorerIntent, FILE_SELECT_REQUEST);
        } catch (ActivityNotFoundException e) {
            UIUtils.createSnackbar(mainScreen, R.id.main_screen,
                    fragment.getString(R.string.message_error_start_file_selector), Snackbar.LENGTH_LONG);
        }
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
            v.vibrate(VibrationEffect.createOneShot(VIBRATION_LENGTH, VibrationEffect.DEFAULT_AMPLITUDE));
        }else{
            v.vibrate(VIBRATION_LENGTH);
        }
    }

    public static void openWebLink(@NonNull Context context, @NonNull String link) {
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
    }

}
