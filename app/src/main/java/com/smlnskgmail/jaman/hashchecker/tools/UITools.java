package com.smlnskgmail.jaman.hashchecker.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.snackbar.Snackbar;
import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.logic.settings.SettingsHelper;
import com.smlnskgmail.jaman.hashchecker.logic.settings.ui.lists.themes.Theme;

public class UITools {

    private static final int COMMON_SNACKBAR_MARGIN = 12;

    public static void removeFragment(
            @NonNull FragmentManager fragmentManager,
            @NonNull Fragment fragment
    ) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment).commit();
        fragmentManager.popBackStackImmediate();
    }

    public static void showSnackbar(
            @NonNull Context context,
            @NonNull View parent,
            @NonNull String message
    ) {
        showSnackbar(
                context,
                parent,
                message,
                null,
                null
        );
    }

    public static void showSnackbar(
            @NonNull Context context,
            @NonNull View parent,
            @NonNull String message,
            @Nullable String actionText,
            @Nullable View.OnClickListener action
    ) {
        Snackbar snackbar = Snackbar.make(parent, message, Snackbar.LENGTH_SHORT);
        if (action != null) {
            snackbar.setAction(actionText, action);
        } else {
            final Snackbar closableSnackbar = snackbar;
            snackbar.setAction(context.getResources().getString(R.string.common_ok), v -> closableSnackbar.dismiss());
        }
        snackbar.setActionTextColor(getAccentColor(context));

        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) snackbar.getView().getLayoutParams();
        params.setMargins(
                COMMON_SNACKBAR_MARGIN,
                COMMON_SNACKBAR_MARGIN,
                COMMON_SNACKBAR_MARGIN,
                COMMON_SNACKBAR_MARGIN
        );
        snackbar.getView().setBackground(ContextCompat.getDrawable(context, R.drawable.bg_snackbar));

        TextView snackbarText = snackbar.getView().findViewById(R.id.snackbar_text);
        snackbarText.setTextColor(ContextCompat.getColor(context, R.color.colorLightText));
        snackbar.show();

        if (SettingsHelper.getVibrateAccess(context)) {
            vibrate(context);
        }
    }

    private static void vibrate(@NonNull Context context) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        int vibrationLength = 30;

        if (vibrator != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(vibrationLength,
                        VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                vibrator.vibrate(vibrationLength);
            }
        }
    }

    public static void applyAccentColorToImage(@NonNull Context context, @NonNull Drawable drawable) {
        drawable.setColorFilter(getAccentColor(context), PorterDuff.Mode.SRC_ATOP);
    }

    public static int getThemeResId(@NonNull Context context) {
        return Theme.getThemeFromPreferences(context).getThemeResId();
    }

    @SuppressLint("ResourceType")
    public static int getAccentColor(@NonNull Context context) {
        return getColorFromAttrs(context, R.attr.colorAccent);
    }

    @SuppressLint("ResourceType")
    public static int getCommonBackgroundColor(@NonNull Context context) {
        return getColorFromAttrs(context, R.attr.colorBackground);
    }

    private static int getColorFromAttrs(@NonNull Context context, @IdRes int themeColor) {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = context.getTheme();
        theme.resolveAttribute(themeColor, typedValue, true);
        return typedValue.data;
    }

    public static void setActionBarTitle(@NonNull ActionBar actionBar, int titleResId) {
        actionBar.setTitle(titleResId);
    }

}
