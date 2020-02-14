package com.smlnskgmail.jaman.hashchecker.logic.settings.ui.lists.languages;

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

public class LanguagesListAdapter extends BaseListAdapter {

    private final Language selectedLanguage;

    LanguagesListAdapter(
            @NonNull List<ListItemTarget> items,
            @NonNull BaseListBottomSheet bottomSheet,
            @NonNull Language selectedLanguage
    ) {
        super(items, bottomSheet);
        this.selectedLanguage = selectedLanguage;
    }

    @Override
    protected BaseListHolder getItemsHolder(
            @NonNull View view,
            @NonNull Context themeContext
    ) {
        return new LanguagesListHolder(view, themeContext);
    }

    private class LanguagesListHolder extends BaseListHolder {

        private Language languageAtPosition;

        LanguagesListHolder(
                @NonNull View itemView,
                @NonNull Context themeContext
        ) {
            super(itemView, themeContext);
        }

        @Override
        protected void callItemClick() {
            AppAlertDialog.show(
                    getContext(),
                    R.string.title_warning_dialog,
                    R.string.message_change_language,
                    R.string.common_ok,
                    (dialog, which) -> {
                        SettingsHelper.saveLanguage(
                                getContext(),
                                languageAtPosition
                        );
                        dialog.dismiss();
                        RestartAppTool.restartApp(
                                getBottomSheet().getActivity()
                        );
                    }
            );
        }

        @Override
        protected void bind(@NonNull ListItemTarget listItemTarget) {
            languageAtPosition = (Language) listItemTarget;
            super.bind(listItemTarget);
        }

        @Override
        protected boolean getConditionToAdditionalIconVisibleState() {
            return languageAtPosition == selectedLanguage;
        }

    }

}
