package com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.themes.adapter;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetItemsAdapter;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.base.adapter.BaseBottomSheetItemsHolder;
import com.smlnskgmail.jaman.hashchecker.components.bottomsheets.lists.themes.Themes;
import com.smlnskgmail.jaman.hashchecker.support.utils.AppUtils;
import com.smlnskgmail.jaman.hashchecker.support.utils.UIUtils;
import com.smlnskgmail.jaman.hashchecker.support.values.Preferences;

public class ThemesBottomSheetItemsHolder extends BaseBottomSheetItemsHolder {

    private Activity activity;

    ThemesBottomSheetItemsHolder(@NonNull View itemView,
                                 @NonNull BaseBottomSheetItemsAdapter baseBottomSheetItemsAdapter,
                                 @NonNull Activity activity) {
        super(itemView, baseBottomSheetItemsAdapter);
        this.activity = activity;
    }

    @Override
    protected void callItemClick() {
        showWarningDialog();
    }

    private void showWarningDialog() {
        AlertDialog warningMessageDialog = new AlertDialog.Builder(getContext(), R.style.AppAlertDialog)
                .setTitle(R.string.title_warning_dialog)
                .setMessage(R.string.message_change_theme)
                .setPositiveButton(R.string.common_ok, (dialog, which) -> {
                    configureNewTheme();
                    dialog.dismiss();
                    AppUtils.restartApp(activity);
                })
                .setNegativeButton(R.string.common_cancel, (dialog, which) -> {
                    dialog.cancel();
                })
                .create();
        warningMessageDialog.setOnShowListener(dialog -> {
            AlertDialog alertDialog = ((AlertDialog) dialog);
            int textColor = UIUtils.getAccentColor(getContext());
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(textColor);
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(textColor);
        });
        warningMessageDialog.show();
    }

    private void configureNewTheme() {
        Themes theme = (Themes) getBaseBottomSheetItemsAdapter()
                .getListItemMarkers().get(getAdapterPosition());
        Preferences.saveTheme(getContext(), theme);
    }

    @Override
    protected boolean getConditionToPrimaryIconVisibleState() {
        return true;
    }

    @Override
    protected boolean getConditionToAdditionalIconVisibleState() {
        Themes theme = (Themes) getBaseBottomSheetItemsAdapter()
                .getListItemMarkers().get(getAdapterPosition());
        return theme.equals(Themes.getThemeFromPreferences(getContext()));
    }

}
