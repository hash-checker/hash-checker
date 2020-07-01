package com.smlnskgmail.jaman.hashchecker.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.logic.settings.SettingsHelper;

public class UIUtils {

    public static void removeFragment(
            @NonNull FragmentManager fragmentManager,
            @NonNull Fragment fragment
    ) {
        FragmentTransaction fragmentTransaction
                = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment).commit();
        fragmentManager.popBackStackImmediate();
    }

    public static void applyAccentColorToImage(
            @NonNull Context context,
            @NonNull Drawable drawable
    ) {
        drawable.setColorFilter(
                getAccentColor(context),
                PorterDuff.Mode.SRC_ATOP
        );
    }

    public static int getThemeResId(
            @NonNull Context context
    ) {
        return SettingsHelper.getSelectedTheme(context).getThemeResId();
    }

    @SuppressLint("ResourceType")
    public static int getAccentColor(
            @NonNull Context context
    ) {
        return getColorFromAttrs(
                context,
                R.attr.colorAccent
        );
    }

    @SuppressLint("ResourceType")
    public static int getCommonTextColor(
            @NonNull Context context
    ) {
        return getColorFromAttrs(
                context,
                R.attr.colorCommonText
        );
    }

    @SuppressLint("ResourceType")
    public static int getCommonBackgroundColor(
            @NonNull Context context
    ) {
        return getColorFromAttrs(
                context,
                R.attr.colorBackground
        );
    }

    private static int getColorFromAttrs(
            @NonNull Context context,
            @IdRes int themeColor
    ) {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = context.getTheme();
        theme.resolveAttribute(
                themeColor,
                typedValue,
                true
        );
        return typedValue.data;
    }

    public static void setActionBarTitle(
            @NonNull ActionBar actionBar,
            int titleResId
    ) {
        actionBar.setTitle(titleResId);
    }

}
