package com.smlnskgmail.jaman.hashchecker.logic.settings.lists.themes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.MainActivity;
import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.ListMarker;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseBottomSheetListAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.adapter.BaseBottomSheetListHolder;
import com.smlnskgmail.jaman.hashchecker.components.dialogs.AppAlertDialog;
import com.smlnskgmail.jaman.hashchecker.logic.settings.SettingsHelper;

import java.util.List;

public class ThemesBottomSheetListAdapter extends BaseBottomSheetListAdapter {

    private final Theme selectedTheme;

    ThemesBottomSheetListAdapter(@NonNull List<ListMarker> items, @NonNull BaseListBottomSheet bottomSheet) {
        super(items, bottomSheet);
        selectedTheme = Theme.getThemeFromPreferences(getBottomSheet().getContext());
    }

    @Override
    public BaseBottomSheetListHolder getItemsHolder(@NonNull View view, @NonNull Context themeContext) {
        return new ThemesBottomSheetListHolder(view, themeContext);
    }

    private class ThemesBottomSheetListHolder extends BaseBottomSheetListHolder {

        private Theme themeAtPosition;

        ThemesBottomSheetListHolder(@NonNull View itemView, @NonNull Context themeContext) {
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
        protected void bind(@NonNull ListMarker listMarker) {
            themeAtPosition = (Theme) getItems().get(getAdapterPosition());
            super.bind(listMarker);
        }

        private void showThemeApplyDialog() {
            AppAlertDialog.show(getContext(), R.string.title_warning_dialog,
                    R.string.message_change_theme, R.string.common_ok, (dialog, which) -> {
                        configureNewTheme();
                        dialog.dismiss();
                        restartApp(getBottomSheet().getActivity());
                    });
        }

        private void restartApp(@NonNull Activity activity) {
            Intent intent = new Intent(activity, MainActivity.class);
            activity.startActivity(intent);
            activity.finish();
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

