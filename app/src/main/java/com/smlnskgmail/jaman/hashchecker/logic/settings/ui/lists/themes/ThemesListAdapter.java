package com.smlnskgmail.jaman.hashchecker.logic.settings.ui.lists.themes;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseListAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseListHolder;
import com.smlnskgmail.jaman.hashchecker.components.dialogs.system.AppAlertDialog;
import com.smlnskgmail.jaman.hashchecker.logic.themes.api.Theme;
import com.smlnskgmail.jaman.hashchecker.logic.themes.api.ThemeHelper;
import com.smlnskgmail.jaman.hashchecker.utils.AppUtils;

import java.util.List;

public class ThemesListAdapter extends BaseListAdapter<Theme> {

    private final ThemeHelper themeHelper;

    ThemesListAdapter(
            @NonNull List<Theme> items,
            @NonNull BaseListBottomSheet<Theme> bottomSheet,
            @NonNull ThemeHelper themeHelper
    ) {
        super(items, bottomSheet);
        this.themeHelper = themeHelper;
    }

    @NonNull
    @Override
    public BaseListHolder<Theme> getItemsHolder(
            @NonNull Context themeContext,
            @NonNull View view
    ) {
        return new ThemesListHolder(
                themeContext,
                view
        );
    }

    class ThemesListHolder extends BaseListHolder<Theme> {

        private Theme themeAtPosition;

        ThemesListHolder(
                @NonNull Context themeContext,
                @NonNull View itemView
        ) {
            super(themeContext, itemView, themeHelper);
        }

        @Override
        protected void callItemClick() {
            if (themeAtPosition == themeHelper.currentTheme()) {
                dismissBottomSheet();
            } else {
                showThemeApplyDialog();
            }
        }

        @Override
        protected void bind(@NonNull Theme listItem) {
            themeAtPosition = listItem;
            super.bind(listItem);
        }

        private void showThemeApplyDialog() {
            new AppAlertDialog(
                    getContext(),
                    R.string.title_warning_dialog,
                    R.string.message_change_theme,
                    R.string.common_ok,
                    (dialog, which) -> {
                        configureNewTheme();
                        dialog.dismiss();
                        Activity activity = getBottomSheet().getActivity();
                        if (activity != null) {
                            AppUtils.restartApp(activity);
                        }
                    },
                    themeHelper
            ).show();
        }

        private void configureNewTheme() {
            themeHelper.setCurrentTheme(themeAtPosition);
        }

        @Override
        protected boolean getConditionToPrimaryIconVisibleState() {
            return true;
        }

        @Override
        protected boolean getConditionToAdditionalIconVisibleState() {
            return themeAtPosition == themeHelper.currentTheme();
        }

    }

}

