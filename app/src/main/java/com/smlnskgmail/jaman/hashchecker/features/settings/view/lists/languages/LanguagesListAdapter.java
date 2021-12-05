package com.smlnskgmail.jaman.hashchecker.features.settings.view.lists.languages;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.locale.api.Language;
import com.smlnskgmail.jaman.hashchecker.components.locale.api.LanguageConfig;
import com.smlnskgmail.jaman.hashchecker.components.theme.api.ThemeConfig;
import com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.adapter.BaseListAdapter;
import com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.adapter.BaseListHolder;
import com.smlnskgmail.jaman.hashchecker.ui.dialogs.system.AppAlertDialog;
import com.smlnskgmail.jaman.hashchecker.utils.AppUtils;

import java.util.List;

public class LanguagesListAdapter extends BaseListAdapter<Language> {

    private final Language selectedLanguage;
    private final LanguageConfig languageConfig;
    private final ThemeConfig themeConfig;

    LanguagesListAdapter(
            @NonNull List<Language> items,
            @NonNull BaseListBottomSheet<Language> bottomSheet,
            @NonNull Language selectedLanguage,
            @NonNull LanguageConfig languageConfig,
            @NonNull ThemeConfig themeConfig
    ) {
        super(items, bottomSheet);
        this.selectedLanguage = selectedLanguage;
        this.languageConfig = languageConfig;
        this.themeConfig = themeConfig;
    }

    @NonNull
    @Override
    protected BaseListHolder<Language> getItemsHolder(@NonNull Context themeContext, @NonNull View view) {
        return new LanguagesListHolder(themeContext, view);
    }

    private class LanguagesListHolder extends BaseListHolder<Language> {

        private Language languageAtPosition;

        LanguagesListHolder(@NonNull Context themeContext, @NonNull View itemView) {
            super(themeContext, itemView, themeConfig);
        }

        @Override
        protected void callItemClick() {
            new AppAlertDialog(
                    getContext(),
                    R.string.title_warning_dialog,
                    R.string.message_change_language,
                    R.string.common_ok,
                    (dialog, which) -> {
                        languageConfig.setLanguage(languageAtPosition);
                        dialog.dismiss();
                        Activity activity = getBottomSheet().getActivity();
                        if (activity != null) {
                            AppUtils.restartApp(activity);
                        }
                    },
                    themeConfig
            ).show();
        }

        @Override
        protected void bind(@NonNull Language listItem) {
            languageAtPosition = listItem;
            super.bind(listItem);
        }

        @Override
        protected boolean getConditionToAdditionalIconVisibleState() {
            return languageAtPosition == selectedLanguage;
        }

    }

}
