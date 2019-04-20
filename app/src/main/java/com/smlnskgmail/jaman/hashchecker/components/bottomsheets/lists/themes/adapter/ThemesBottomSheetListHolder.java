package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.themes.adapter;

import android.app.Activity;
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

    private Activity activity;
    private Themes themeAtPosition;

    ThemesBottomSheetListHolder(@NonNull View itemView, @NonNull BaseBottomSheetListAdapter adapter,
                                @NonNull Activity activity) {
        super(itemView, adapter);
        this.activity = activity;
    }

    @Override
    protected void callItemClick() {
        if (Preferences.getTheme(getContext()).equals(themeAtPosition.toString())) {
            getListAdapter().getBottomSheet().dismissAllowingStateLoss();
        } else {
            showThemeApplyDialog();
        }
    }

    @Override
    protected void bind(@NonNull ListItemMarker listItemMarker) {
        super.bind(listItemMarker);
        themeAtPosition = (Themes) getListAdapter().getItems().get(getAdapterPosition());
    }

    private void showThemeApplyDialog() {
        AppAlertDialog.show(getContext(), R.string.title_warning_dialog,
                R.string.message_change_theme, R.string.common_ok, (dialog, which) -> {
                    configureNewTheme();
                    dialog.dismiss();
                    AppUtils.restartApp(activity);
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
        Themes theme = (Themes) getListAdapter().getItems().get(getAdapterPosition());
        return theme.equals(Themes.getThemeFromPreferences(getContext()));
    }

}
