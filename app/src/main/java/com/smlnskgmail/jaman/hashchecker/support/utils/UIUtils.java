package com.smlnskgmail.jaman.hashchecker.support.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.themes.Themes;
import com.smlnskgmail.jaman.hashchecker.support.preferences.Constants;
import com.smlnskgmail.jaman.hashchecker.support.preferences.Preferences;

public class UIUtils {

    private static final int COMMON_SNACKBAR_MAGRIN = 12;

    public static void showFragment(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(android.R.id.content, fragment, Constants.Tags.CURRENT_FRAGMENT_TAG)
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .addToBackStack(null)
                .commit();
    }

    public static void hideKeyboard(@NonNull Context context, @NonNull View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context
                .getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void removeFragment(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment).commit();
        fragmentManager.popBackStackImmediate();
    }

    public static void showSnackbar(@NonNull Context context, @NonNull View parent, @NonNull String message, int length) {
        showSnackbar(context, parent, message, null, null, length);
    }

    public static void showSnackbar(@NonNull Context context, @NonNull View parent, @NonNull String message,
                                    @Nullable String actionText, @Nullable View.OnClickListener action, int length) {
        Snackbar snackbar = Snackbar.make(parent, message, length);
        if (action != null) {
            snackbar.setAction(actionText, action);
        } else {
            final Snackbar closableSnackbar = snackbar;
            snackbar.setAction(context.getResources().getString(R.string.common_ok), v -> closableSnackbar.dismiss());
        }
        snackbar.setActionTextColor(getAccentColor(context));

        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) snackbar.getView()
                .getLayoutParams();
        params.setMargins(COMMON_SNACKBAR_MAGRIN, COMMON_SNACKBAR_MAGRIN, COMMON_SNACKBAR_MAGRIN,
                COMMON_SNACKBAR_MAGRIN);
        snackbar.getView().setBackground(ContextCompat.getDrawable(context, R.drawable.bg_snackbar));

        TextView snackbarText = snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
        Typeface font = getAppFont(context);
        snackbarText.setTypeface(font);
        snackbarText.setTextColor(ContextCompat.getColor(context, R.color.colorLightText));
        ((TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_action))
                .setTypeface(font);
        snackbar.show();

        if (Preferences.getVibrateAccess(context)) {
            AppUtils.vibrate(context);
        }
    }

    public static void applyAdaptiveFontWithBoldStyle(@NonNull Context context, @NonNull TextView textView) {
        applyAdaptiveFont(context, textView, false);
        textView.setTypeface(getAppFont(context), Typeface.BOLD);
    }

    public static void applyAdaptiveFont(@NonNull Context context, @NonNull TextView textView,
                                         boolean useThemeColor) {
        textView.setTypeface(getAppFont(context));
        if (useThemeColor) {
            textView.setTextColor(UIUtils.getDarkTextColor(context));
        }
    }

    public static Typeface getAppFont(@NonNull Context context) {
        return ResourcesCompat.getFont(context, R.font.google_sans_regular);
    }

    public static void colorizeImageSourceToAccentColor(@NonNull Context context, @NonNull Drawable drawable) {
        drawable.setColorFilter(getAccentColor(context), PorterDuff.Mode.SRC_ATOP);
    }

    public static int getThemeResId(@NonNull Context context) {
        return Themes.getThemeFromPreferences(context).getThemeResId();
    }

    @SuppressLint("ResourceType")
    public static int getAccentColor(@NonNull Context context) {
        return getColorFromAttrs(context, R.attr.colorAccent);
    }

    @SuppressLint("ResourceType")
    public static int getCommonBackgroundColor(@NonNull Context context) {
        return getColorFromAttrs(context, R.attr.colorBackground);
    }

    @SuppressLint("ResourceType")
    public static int getDarkTextColor(@NonNull Context context) {
        return getColorFromAttrs(context, R.attr.colorCommonText);
    }

    @SuppressLint("ResourceType")
    public static int getUnselectedColor(@NonNull Context context) {
        return getColorFromAttrs(context, R.attr.colorCommonUnselected);
    }

    private static int getColorFromAttrs(@NonNull Context context, @IdRes int themeColor) {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = context.getTheme();
        theme.resolveAttribute(themeColor, typedValue, true);
        return typedValue.data;
    }

    public static void setActionBarTitle(@NonNull ActionBar actionBar, @NonNull String title) {
        actionBar.setTitle(title);
    }

    public static void setActionBarTitle(@NonNull ActionBar actionBar, int titleResId) {
        actionBar.setTitle(titleResId);
    }

}
