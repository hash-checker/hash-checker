package com.smlnskgmail.jaman.hashchecker.screenrunner;

import android.content.Context;

import androidx.annotation.IdRes;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.smlnskgmail.jaman.hashchecker.MainActivity;
import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.BaseUITest;

import org.junit.Rule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@Deprecated()
public class ScreenRunnerTest extends BaseUITest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(
            MainActivity.class
    );

    @Override
    public void runTest() {
        showHashTypesSelector();
        showResourcesActions();
        showHashActions();

        showSettingsFragment();
        showHistoryFragment();
        showFragmentInMenu(R.string.menu_title_feedback, true);
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
        showFragmentInMenu(R.string.menu_title_settings, false);
        clickOnSettingsItem(R.string.settings_title_language);
        clickOnSettingsItem(R.string.settings_title_theme);
        clickOnSettingsItem(R.string.settings_title_author);
        delayAndBack();
    }

    private void showFragmentInMenu(
            @IdRes int menuTitleResId,
            boolean withBackAction
    ) {
        Context context = getContext();
        openActionBarOverflowOrOptionsMenu(context);
        clickByText(context.getString(menuTitleResId));
        if (withBackAction) {
            delayAndBack();
        }
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
