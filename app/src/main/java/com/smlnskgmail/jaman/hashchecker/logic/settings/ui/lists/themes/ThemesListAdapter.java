package com.smlnskgmail.jaman.hashchecker.logic.settings.ui.lists.themes;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseListAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseListHolder;
import com.smlnskgmail.jaman.hashchecker.components.dialogs.system.AppAlertDialog;
import com.smlnskgmail.jaman.hashchecker.logic.settings.SettingsHelper;
import com.smlnskgmail.jaman.hashchecker.logic.support.Restart;

import java.util.List;

public class ThemesListAdapter extends BaseListAdapter<Theme> {

    private final Theme selectedTheme;

    ThemesListAdapter(
            @NonNull List<Theme> items,
            @NonNull BaseListBottomSheet<Theme> bottomSheet,
            @NonNull Theme selectedTheme
    ) {
        super(items, bottomSheet);
        this.selectedTheme = selectedTheme;
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
            super(themeContext, itemView);
        }

        @Override
        protected void callItemClick() {
            if (themeAtPosition == selectedTheme) {
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
            AppAlertDialog.show(
                    getContext(),
                    R.string.title_warning_dialog,
                    R.string.message_change_theme,
                    R.string.common_ok,
                    (dialog, which) -> {
                        configureNewTheme();
                        dialog.dismiss();
                        Restart.restartApp(
                                getBottomSheet().getActivity()
                        );
                    }
            );
        }

        private void configureNewTheme() {
            SettingsHelper.saveTheme(getContext(), themeAtPosition);
        }

        @Override
        protected boolean getConditionToPrimaryIconVisibleState() {
            return true;
        }

        @Override
        protected boolean getConditionToAdditionalIconVisibleState() {
            return themeAtPosition == selectedTheme;
        }

    }

}

