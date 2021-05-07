package com.smlnskgmail.jaman.hashchecker.components.dialogs.system;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.logic.settings.impl.SharedPreferencesSettingsHelper;
import com.smlnskgmail.jaman.hashchecker.logic.support.Vibrator;
import com.smlnskgmail.jaman.hashchecker.utils.UIUtils;

public class AppSnackbar {

    private static final int COMMON_SNACKBAR_MARGIN = 8;

    private final Context context;
    private final View parent;
    private final int messageResId;
    private String actionText;
    private View.OnClickListener action;

    public AppSnackbar(
            @NonNull Context context,
            @NonNull View parent,
            @StringRes int messageResId,
            @NonNull String actionText,
            @NonNull View.OnClickListener action
    ) {
        this.context = context;
        this.parent = parent;
        this.messageResId = messageResId;
        this.actionText = actionText;
        this.action = action;
    }

    public AppSnackbar(
            @NonNull Context context,
            @NonNull View parent,
            @StringRes int messageResId
    ) {
        this.context = context;
        this.parent = parent;
        this.messageResId = messageResId;
    }

    public void show() {
        Snackbar snackbar = Snackbar.make(
                parent,
                messageResId,
                Snackbar.LENGTH_SHORT
        );
        if (action != null) {
            snackbar.setAction(actionText, action);
        } else {
            final Snackbar closableSnackbar = snackbar;
            snackbar.setAction(
                    context.getResources().getString(R.string.common_ok),
                    v -> closableSnackbar.dismiss()
            );
            ((ViewGroup) snackbar.getView()).getChildAt(0)
                    .setPadding(
                            COMMON_SNACKBAR_MARGIN,
                            COMMON_SNACKBAR_MARGIN,
                            COMMON_SNACKBAR_MARGIN,
                            COMMON_SNACKBAR_MARGIN
                    );
        }
        snackbar.setActionTextColor(
                UIUtils.getAccentColor(
                        context
                )
        );
        snackbar.getView().setBackground(
                ContextCompat.getDrawable(
                        context,
                        R.drawable.bg_snackbar
                )
        );

        TextView tvSnackbar = snackbar.getView().findViewById(
                R.id.snackbar_text
        );
        tvSnackbar.setTextColor(
                UIUtils.getCommonTextColor(
                        context
                )
        );
        snackbar.show();

        if (SharedPreferencesSettingsHelper.getVibrateAccess(context)) {
            new Vibrator(context).vibrate();
        }
    }

}
