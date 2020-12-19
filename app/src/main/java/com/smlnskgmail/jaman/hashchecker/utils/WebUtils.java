package com.smlnskgmail.jaman.hashchecker.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.logic.logs.L;

public class WebUtils {

    public static void openGooglePlay(
            @NonNull Context context,
            @NonNull View view
    ) {
        final String appPackageName = context.getPackageName();
        Uri link;
        try {
            link = Uri.parse("market://details?id=" + appPackageName);
            context.startActivity(
                    new Intent(
                            Intent.ACTION_VIEW,
                            link
                    )
            );
        } catch (ActivityNotFoundException e) {
            try {
                link = Uri.parse(
                        "https://play.google.com/store/apps/details?id=" + appPackageName
                );
                context.startActivity(
                        new Intent(
                                Intent.ACTION_VIEW,
                                link
                        )
                );
            } catch (ActivityNotFoundException e2) {
                L.e(e2);
                UIUtils.showSnackbar(
                        context,
                        view,
                        context.getString(
                                R.string.message_error_start_google_play
                        )
                );
            }
        }
    }

    public static void openWebLink(
            @NonNull Context context,
            @NonNull String link
    ) {
        try {
            context.startActivity(
                    new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(link)
                    )
            );
        } catch (ActivityNotFoundException e) {
            L.e(e);
        }
    }

}
