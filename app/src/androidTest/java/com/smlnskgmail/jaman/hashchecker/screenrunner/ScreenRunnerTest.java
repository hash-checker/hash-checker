package com.smlnskgmail.jaman.hashchecker.screenrunner;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.contrib.RecyclerViewActions;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.ui.BaseUITest;

public class ScreenRunnerTest extends BaseUITest {

    @Override
    public void runTest() {
        showHashTypesSelector();
        showResourcesActions();
        showHashActions();

        showHistoryFragment();
        showSettingsFragment();
    }

    private void showHashTypesSelector() {
        showBottomSheet(R.id.tv_selected_hash_type);
    }

    private void showResourcesActions() {
        showBottomSheet(R.id.btn_generate_from);
    }

    private void showHashActions() {
        showBottomSheet(R.id.btn_hash_actions);
    }

    private void showBottomSheet(int bottomSheetButtonResId) {
        clickById(bottomSheetButtonResId);
        delayAndBack();
    }

    private void showSettingsFragment() {
        clickById(R.id.menu_main_section_settings);
        clickOnSettingsItem(R.string.settings_title_language);
        clickOnSettingsItem(R.string.settings_title_theme);
        clickOnSettingsItem(R.string.settings_title_author);
        clickOnSettingsItem(R.string.menu_title_feedback);
        delayAndBack();
        delayAndBack();
    }

    private void showHistoryFragment() {
        clickById(R.id.menu_main_section_history);
        delayAndBack();
    }

    private void clickOnSettingsItem(int settingsTitleResId) {
        onView(withId(R.id.recycler_view)).perform(
                RecyclerViewActions.actionOnItem(
                        hasDescendant(withText(settingsTitleResId)),
                        click()
                )
        );
        delayAndBack();
    }

}
