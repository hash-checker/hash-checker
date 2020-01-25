package com.smlnskgmail.jaman.hashchecker.tools;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.MainActivity;

public class RestartAppTool {

    public static void restartApp(@NonNull Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

}
