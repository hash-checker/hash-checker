package com.smlnskgmail.jaman.hashchecker.components.dialogs.system;

import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.utils.UIUtils;

public class AppAlertDialog {

    public static void show(
            @NonNull Context context,
            int titleResId,
            int messageResId,
            int positiveButtonTextResId,
            @Nullable DialogInterface.OnClickListener positiveClickListener
    ) {
        AlertDialog alertDialog = new AlertDialog.Builder(context, R.style.AppAlertDialog)
                .setTitle(titleResId)
                .setMessage(messageResId)
                .setPositiveButton(
                        positiveButtonTextResId,
                        positiveClickListener
                )
                .setNegativeButton(
                        R.string.common_cancel,
                        (dialog, which) -> dialog.cancel()
                )
                .create();
        alertDialog.setOnShowListener(dialog -> {
            AlertDialog currentDialog = ((AlertDialog) dialog);

            int textColor = UIUtils.getAccentColor(context);
            currentDialog.getButton(
                    DialogInterface.BUTTON_POSITIVE
            ).setTextColor(textColor);
            currentDialog.getButton(
                    DialogInterface.BUTTON_NEGATIVE
            ).setTextColor(textColor);
        });
        alertDialog.show();
    }

}
