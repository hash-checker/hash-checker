package com.smlnskgmail.jaman.hashchecker.tools;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.logs.L;

public class WebTools {

    public static void openWebLink(@NonNull Context context, @NonNull String link) {
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
        } catch (ActivityNotFoundException e) {
            L.e(e);
        }
    }

}
