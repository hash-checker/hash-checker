package com.smlnskgmail.jaman.hashchecker.features.settings.view.lists.themes;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.theme.api.Theme;
import com.smlnskgmail.jaman.hashchecker.components.theme.api.ThemeConfig;
import com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.adapter.BaseListAdapter;
import com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.adapter.BaseListHolder;
import com.smlnskgmail.jaman.hashchecker.ui.dialogs.system.AppAlertDialog;
import com.smlnskgmail.jaman.hashchecker.utils.AppUtils;

import java.util.List;

public class ThemesListAdapter extends BaseListAdapter<Theme> {

    private final ThemeConfig themeConfig;

    ThemesListAdapter(
            @NonNull List<Theme> items,
            @NonNull BaseListBottomSheet<Theme> bottomSheet,
            @NonNull ThemeConfig themeConfig
    ) {
        super(items, bottomSheet);
        this.themeConfig = themeConfig;
    }

    @NonNull
    @Override
    public BaseListHolder<Theme> getItemsHolder(@NonNull Context themeContext, @NonNull View view) {
        return new ThemesListHolder(themeContext, view);
    }

    class ThemesListHolder extends BaseListHolder<Theme> {

        private Theme themeAtPosition;

        ThemesListHolder(@NonNull Context themeContext, @NonNull View itemView) {
            super(themeContext, itemView, themeConfig);
        }

        @Override
        protected void callItemClick() {
            if (themeAtPosition == themeConfig.currentTheme()) {
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
                    themeConfig
            ).show();
        }

        private void configureNewTheme() {
            themeConfig.setCurrentTheme(themeAtPosition);
        }

        @Override
        protected boolean getConditionToPrimaryIconVisibleState() {
            return true;
        }

        @Override
        protected boolean getConditionToAdditionalIconVisibleState() {
            return themeAtPosition == themeConfig.currentTheme();
        }

    }

}

