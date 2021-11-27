package com.smlnskgmail.jaman.hashchecker.features.settings.view.lists.languages;

import android.content.Context;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.App;
import com.smlnskgmail.jaman.hashchecker.components.locale.api.Language;
import com.smlnskgmail.jaman.hashchecker.components.locale.api.LanguageConfig;
import com.smlnskgmail.jaman.hashchecker.components.theme.api.ThemeConfig;
import com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.BaseListBottomSheet;
import com.smlnskgmail.jaman.hashchecker.ui.bottomsheets.lists.adapter.BaseListAdapter;

import java.util.Arrays;

import javax.inject.Inject;

public class LanguagesBottomSheet extends BaseListBottomSheet<Language> {

    @Inject
    public LanguageConfig languageConfig;

    @Inject
    public ThemeConfig themeConfig;

    // CPD-OFF
    @Override
    public void onAttach(@NonNull Context context) {
        App.appComponent.inject(this);
        super.onAttach(context);
    }
    // CPD-ON

    @NonNull
    @Override
    protected BaseListAdapter<Language> getItemsAdapter() {
        return new LanguagesListAdapter(
                Arrays.asList(Language.values()),
                this,
                languageConfig.currentLanguage(),
                languageConfig,
                themeConfig
        );
    }

}
