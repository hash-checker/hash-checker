package com.smlnskgmail.jaman.hashchecker.ui.dialogs.system;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;

import com.smlnskgmail.jaman.hashchecker.R;

public class AppProgressDialog {

    private final Context context;
    private final int textMessageResId;

    public AppProgressDialog(@NonNull Context context, @StringRes int textMessageResId) {
        this.context = context;
        this.textMessageResId = textMessageResId;
    }

    @NonNull
    public ProgressDialog getDialog() {
        ProgressDialog progressDialog = new ProgressDialog(context, R.style.AppAlertDialog);
        progressDialog.setMessage(context.getText(textMessageResId));
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setBackgroundDrawable(
                ContextCompat.getDrawable(
                        context,
                        R.drawable.bg_dialog_rounded_corners
                )
        );
        return progressDialog;
    }

}
