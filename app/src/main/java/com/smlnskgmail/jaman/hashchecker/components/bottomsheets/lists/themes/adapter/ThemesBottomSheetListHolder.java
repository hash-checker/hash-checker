package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.themes.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.ListItemMarker;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetListHolder;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.themes.Themes;
import com.smlnskgmail.jaman.hashchecker.components.dialogs.system.AppAlertDialog;
import com.smlnskgmail.jaman.hashchecker.support.preferences.Preferences;
import com.smlnskgmail.jaman.hashchecker.support.utils.AppUtils;

public class ThemesBottomSheetListHolder extends BaseBottomSheetListHolder {

    private Themes themeAtPosition;
    private Themes selectedTheme;

    ThemesBottomSheetListHolder(@NonNull View itemView, @NonNull BaseBottomSheetListAdapter adapter,
                                Themes selectedTheme) {
        super(itemView, adapter);
        this.selectedTheme = selectedTheme;
    }

    @Override
    protected void callItemClick() {
        if (themeAtPosition == selectedTheme) {
            getListAdapter().getBottomSheet().dismissAllowingStateLoss();
        } else {
            showThemeApplyDialog();
        }
    }

    @Override
    protected void bind(@NonNull ListItemMarker listItemMarker) {
        themeAtPosition = (Themes) getListAdapter().getItems().get(getAdapterPosition());
        super.bind(listItemMarker);
    }

    private void showThemeApplyDialog() {
        AppAlertDialog.show(getContext(), R.string.title_warning_dialog,
                R.string.message_change_theme, R.string.common_ok, (dialog, which) -> {
                    configureNewTheme();
                    dialog.dismiss();
                    AppUtils.restartApp(getListAdapter().getBottomSheet().getActivity());
        });
    }

    private void configureNewTheme() {
        Preferences.saveTheme(getContext(), themeAtPosition);
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
