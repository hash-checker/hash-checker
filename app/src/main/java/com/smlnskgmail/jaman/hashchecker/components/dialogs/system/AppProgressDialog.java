package com.smlnskgmail.jaman.hashchecker.components.dialogs.system;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.smlnskgmail.jaman.hashchecker.R;

public class AppProgressDialog {

    @NonNull
    @SuppressLint("ResourceType")
    public static ProgressDialog getDialog(
            @NonNull Context context,
            @IdRes int textMessageResId
    ) {
        ProgressDialog progressDialog = new ProgressDialog(context, R.style.AppAlertDialog);
        progressDialog.setMessage(
                context.getText(textMessageResId)
        );
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
