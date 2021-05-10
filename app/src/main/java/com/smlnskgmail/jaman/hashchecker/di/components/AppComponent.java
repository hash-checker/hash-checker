package com.smlnskgmail.jaman.hashchecker.di.components;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.MainActivity;
import com.smlnskgmail.jaman.hashchecker.di.modules.DatabaseHelperModule;
import com.smlnskgmail.jaman.hashchecker.di.modules.LangHelperModule;
import com.smlnskgmail.jaman.hashchecker.di.modules.SettingsHelperModule;
import com.smlnskgmail.jaman.hashchecker.di.modules.ThemeHelperModule;
import com.smlnskgmail.jaman.hashchecker.di.scopes.ApplicationScope;
import com.smlnskgmail.jaman.hashchecker.logic.feedback.ui.FeedbackFragment;
import com.smlnskgmail.jaman.hashchecker.logic.filemanager.ui.FileManagerActivity;
import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.ui.HashCalculatorFragment;
import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.ui.lists.actions.ui.ActionSelectActionsBottomSheet;
import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.ui.lists.actions.ui.SourceSelectActionsBottomSheet;
import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.ui.lists.hashtypes.GenerateToBottomSheet;
import com.smlnskgmail.jaman.hashchecker.logic.history.ui.HistoryFragment;
import com.smlnskgmail.jaman.hashchecker.logic.settings.ui.SettingsFragment;
import com.smlnskgmail.jaman.hashchecker.logic.settings.ui.lists.languages.LanguagesBottomSheet;
import com.smlnskgmail.jaman.hashchecker.logic.settings.ui.lists.themes.ThemesBottomSheet;
import com.smlnskgmail.jaman.hashchecker.logic.settings.ui.lists.weblinks.AuthorWebLinksBottomSheet;
import com.smlnskgmail.jaman.hashchecker.logic.settings.ui.lists.weblinks.LibrariesWebLinksBottomSheet;
import com.smlnskgmail.jaman.hashchecker.logic.settings.ui.lists.weblinks.PrivacyPolicyWebLinksBottomSheet;

import dagger.Component;

@ApplicationScope
@Component(modules = {
        DatabaseHelperModule.class,
        SettingsHelperModule.class,
        LangHelperModule.class,
        ThemeHelperModule.class,
})
public interface AppComponent {

    void inject(@NonNull MainActivity mainActivity);

    void inject(@NonNull FileManagerActivity fileManagerActivity);

    void inject(@NonNull HashCalculatorFragment hashCalculatorFragment);

    void inject(@NonNull HistoryFragment historyFragment);

    void inject(@NonNull FeedbackFragment feedbackFragment);

    void inject(@NonNull SettingsFragment settingsFragment);

    void inject(@NonNull GenerateToBottomSheet generateToBottomSheet);

    void inject(@NonNull ActionSelectActionsBottomSheet actionSelectActionsBottomSheet);

    void inject(@NonNull SourceSelectActionsBottomSheet sourceSelectActionsBottomSheet);

    void inject(@NonNull LanguagesBottomSheet languagesBottomSheet);

    void inject(@NonNull ThemesBottomSheet themesBottomSheet);

    void inject(@NonNull AuthorWebLinksBottomSheet authorWebLinksBottomSheet);

    void inject(@NonNull LibrariesWebLinksBottomSheet librariesWebLinksBottomSheet);

    void inject(@NonNull PrivacyPolicyWebLinksBottomSheet privacyPolicyWebLinksBottomSheet);

}
