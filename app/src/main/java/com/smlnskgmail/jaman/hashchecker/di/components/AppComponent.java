package com.smlnskgmail.jaman.hashchecker.di.components;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.MainActivity;
import com.smlnskgmail.jaman.hashchecker.di.modules.DatabaseHelperModule;
import com.smlnskgmail.jaman.hashchecker.di.modules.LangHelperModule;
import com.smlnskgmail.jaman.hashchecker.di.modules.SettingsHelperModule;
import com.smlnskgmail.jaman.hashchecker.di.modules.ThemeHelperModule;
import com.smlnskgmail.jaman.hashchecker.di.scopes.ApplicationScope;
import com.smlnskgmail.jaman.hashchecker.features.feedback.view.FeedbackFragment;
import com.smlnskgmail.jaman.hashchecker.features.hashcalculator.HashCalculatorFragment;
import com.smlnskgmail.jaman.hashchecker.features.hashcalculator.lists.actions.ui.ActionSelectActionsBottomSheet;
import com.smlnskgmail.jaman.hashchecker.features.hashcalculator.lists.actions.ui.SourceSelectActionsBottomSheet;
import com.smlnskgmail.jaman.hashchecker.features.hashcalculator.lists.hashtypes.GenerateToBottomSheet;
import com.smlnskgmail.jaman.hashchecker.features.history.view.HistoryFragment;
import com.smlnskgmail.jaman.hashchecker.features.settings.view.SettingsFragment;
import com.smlnskgmail.jaman.hashchecker.features.settings.view.lists.languages.LanguagesBottomSheet;
import com.smlnskgmail.jaman.hashchecker.features.settings.view.lists.themes.ThemesBottomSheet;
import com.smlnskgmail.jaman.hashchecker.features.settings.view.lists.weblinks.AuthorWebLinksBottomSheet;
import com.smlnskgmail.jaman.hashchecker.features.settings.view.lists.weblinks.LibrariesWebLinksBottomSheet;
import com.smlnskgmail.jaman.hashchecker.features.settings.view.lists.weblinks.PrivacyPolicyWebLinksBottomSheet;

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
