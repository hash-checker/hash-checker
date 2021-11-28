package com.smlnskgmail.jaman.hashchecker.ui.dialogs.system;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.settings.api.Settings;
import com.smlnskgmail.jaman.hashchecker.components.theme.api.ThemeConfig;
import com.smlnskgmail.jaman.hashchecker.components.vibrator.Vibrator;

public class AppSnackbar {

    private static final int COMMON_SNACKBAR_MARGIN = 8;

    private final Context context;
    private final View parent;
    private final int messageResId;
    private String actionText;
    private View.OnClickListener action;
    private final Settings settings;
    private final ThemeConfig themeConfig;

    public AppSnackbar(
            @NonNull Context context,
            @NonNull View parent,
            @StringRes int messageResId,
            @NonNull String actionText,
            @NonNull View.OnClickListener action,
            @NonNull Settings settings,
            @NonNull ThemeConfig themeConfig
    ) {
        this.context = context;
        this.parent = parent;
        this.messageResId = messageResId;
        this.actionText = actionText;
        this.action = action;
        this.settings = settings;
        this.themeConfig = themeConfig;
    }

    public AppSnackbar(
            @NonNull Context context,
            @NonNull View parent,
            @StringRes int messageResId,
            @NonNull Settings settings,
            @NonNull ThemeConfig themeConfig
    ) {
        this.context = context;
        this.parent = parent;
        this.messageResId = messageResId;
        this.settings = settings;
        this.themeConfig = themeConfig;
    }

    public void show() {
        Snackbar snackbar = Snackbar.make(parent, messageResId, Snackbar.LENGTH_SHORT);
        if (action != null) {
            snackbar.setAction(actionText, action);
        } else {
            final Snackbar closableSnackbar = snackbar;
            snackbar.setAction(
                    context.getResources().getString(R.string.common_ok),
                    v -> closableSnackbar.dismiss()
            );
            ((ViewGroup) snackbar.getView()).getChildAt(0).setPadding(
                    COMMON_SNACKBAR_MARGIN,
                    COMMON_SNACKBAR_MARGIN,
                    COMMON_SNACKBAR_MARGIN,
                    COMMON_SNACKBAR_MARGIN
            );
        }
        snackbar.setActionTextColor(themeConfig.getAccentColor());
        snackbar.getView().setBackground(
                ContextCompat.getDrawable(
                        context,
                        R.drawable.bg_snackbar
                )
        );

        TextView tvSnackbar = snackbar.getView().findViewById(R.id.snackbar_text);
        tvSnackbar.setTextColor(themeConfig.getCommonTextColor());
        snackbar.show();

        if (settings.getVibrateAccess()) {
            new Vibrator(context).vibrate();
        }
    }

}
