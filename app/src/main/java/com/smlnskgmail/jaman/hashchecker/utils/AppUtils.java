package com.smlnskgmail.jaman.hashchecker.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;

import com.smlnskgmail.jaman.hashchecker.R;

public class AppUtils {

    private static final int VIBRATION_LENGTH = 30;

    public static void closeApp(@NonNull Activity activity) {
        activity.finish();
    }

    public static void searchFile(@NonNull Fragment fragment, @NonNull View mainScreen) {
        try {
            Intent openExplorerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            openExplorerIntent.addCategory(Intent.CATEGORY_OPENABLE);
            openExplorerIntent.setType("*/*");
            fragment.startActivityForResult(openExplorerIntent, Constants.Requests.FILE_SELECT_REQUEST);
        } catch (ActivityNotFoundException e) {
            UIUtils.createSnackbar(mainScreen, R.id.main_screen,
                    fragment.getString(R.string.message_error_start_file_selector), Snackbar.LENGTH_LONG);
        }
    }

    static void vibrate(@NonNull Context context) {
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(VIBRATION_LENGTH, VibrationEffect.DEFAULT_AMPLITUDE));
        }else{
            v.vibrate(VIBRATION_LENGTH);
        }
    }

}
