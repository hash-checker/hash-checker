package com.smlnskgmail.jaman.hashchecker.components.clipboard;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;

public class Clipboard {

    private final Context context;
    private final String text;
    private final ClipboardManager clipboard;
    private final Handler handler = new Handler();
    private final Runnable runnable = this::clear;

    public Clipboard(@NonNull Context context, @NonNull String text) {
        this.context = context;
        this.text = text;
        this.clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
    }

    public void copy() {
        if (clipboard != null) {
            clipboard.setPrimaryClip(ClipData.newPlainText(context.getString(R.string.common_app_name), text));
            handler.removeCallbacks(runnable);
            handler.postDelayed(runnable, 60_000);
        }
    }

    private void clear() {
        ClipData clipData = ClipData.newPlainText(context.getString(R.string.common_app_name), "");
        Log.i("Clipboard", "Remove Clipboard Text with (Label) : " + context.getString(R.string.common_app_name));
        clipboard.setPrimaryClip(clipData);
    }

}
