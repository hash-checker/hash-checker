package com.smlnskgmail.jaman.hashchecker.components.dialogs.system;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.vibrator.Vibrator;
import com.smlnskgmail.jaman.hashchecker.logic.settings.SettingsHelper;

public class AppSnackbar {

    private static final int COMMON_SNACKBAR_MARGIN = 12;

    private final Context context;
    private final View parent;
    private final String message;
    private String actionText;
    private View.OnClickListener action;
    private final Vibrator vibrator;
    private final int textColor;

    public AppSnackbar(
            @NonNull Context context,
            @NonNull View parent,
            @NonNull String message,
            @NonNull String actionText,
            @NonNull View.OnClickListener action,
            @NonNull Vibrator vibrator,
            int textColor
    ) {
        this.context = context;
        this.parent = parent;
        this.message = message;
        this.actionText = actionText;
        this.action = action;
        this.vibrator = vibrator;
        this.textColor = textColor;
    }

    public AppSnackbar(
            @NonNull Context context,
            @NonNull View parent,
            @NonNull String message,
            @NonNull Vibrator vibrator,
            int textColor
    ) {
        this.context = context;
        this.parent = parent;
        this.message = message;
        this.vibrator = vibrator;
        this.textColor = textColor;
    }

    public void show() {
        Snackbar snackbar = Snackbar.make(parent, message, Snackbar.LENGTH_SHORT);
        if (action != null) {
            snackbar.setAction(actionText, action);
        } else {
            final Snackbar closableSnackbar = snackbar;
            snackbar.setAction(
                    context.getResources().getString(R.string.common_ok),
                    v -> closableSnackbar.dismiss()
            );
        }
        snackbar.setActionTextColor(textColor);

        ViewGroup.MarginLayoutParams params
                = (ViewGroup.MarginLayoutParams) snackbar.getView().getLayoutParams();
        params.setMargins(
                COMMON_SNACKBAR_MARGIN,
                COMMON_SNACKBAR_MARGIN,
                COMMON_SNACKBAR_MARGIN,
                COMMON_SNACKBAR_MARGIN
        );
        snackbar.getView().setBackground(
                ContextCompat.getDrawable(context, R.drawable.bg_snackbar)
        );

        TextView snackbarText = snackbar.getView().findViewById(R.id.snackbar_text);
        snackbarText.setTextColor(ContextCompat.getColor(context, R.color.colorLightText));
        snackbar.show();

        if (SettingsHelper.getVibrateAccess(context)) {
            vibrator.vibrate();
        }
    }

}
