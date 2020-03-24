package com.smlnskgmail.jaman.hashchecker.logic.support;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;

public class Clipboard {

    private Context context;
    private String text;

    public Clipboard(
            @NonNull Context context,
            @NonNull String text
    ) {
        this.context = context;
        this.text = text;
    }

    public void copy() {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(
                Context.CLIPBOARD_SERVICE
        );
        ClipData clip = ClipData.newPlainText(
                context.getString(R.string.common_app_name),
                text
        );
        if (clipboard != null) {
            clipboard.setPrimaryClip(clip);
        }
    }

}
