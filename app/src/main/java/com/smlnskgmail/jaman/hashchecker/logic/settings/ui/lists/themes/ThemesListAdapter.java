package com.smlnskgmail.jaman.hashchecker.logic.settings.ui.lists.themes;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.ListItemTarget;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseListAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseListHolder;
import com.smlnskgmail.jaman.hashchecker.components.dialogs.system.AppAlertDialog;
import com.smlnskgmail.jaman.hashchecker.logic.settings.SettingsHelper;
import com.smlnskgmail.jaman.hashchecker.tools.RestartAppTool;

import java.util.List;

public class ThemesListAdapter extends BaseListAdapter {

    private final Theme selectedTheme;

    ThemesListAdapter(
            @NonNull List<ListItemTarget> items,
            @NonNull BaseListBottomSheet bottomSheet,
            @NonNull Theme selectedTheme
    ) {
        super(items, bottomSheet);
        this.selectedTheme = selectedTheme;
    }

    @Override
    public BaseListHolder getItemsHolder(
            @NonNull View view,
            @NonNull Context themeContext
    ) {
        return new ThemesListHolder(view, themeContext);
    }

    private class ThemesListHolder extends BaseListHolder {

        private Theme themeAtPosition;

        ThemesListHolder(
                @NonNull View itemView,
                @NonNull Context themeContext
        ) {
            super(itemView, themeContext);
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
        protected void bind(@NonNull ListItemTarget listItemTarget) {
            themeAtPosition = (Theme) getItems().get(getAdapterPosition());
            super.bind(listItemTarget);
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
                        RestartAppTool.restartApp(
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

