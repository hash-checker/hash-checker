package com.smlnskgmail.jaman.hashchecker.generator.ui;

import android.support.annotation.IdRes;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.smlnskgmail.jaman.hashchecker.MainActivity;
import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.utils.Logger;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ScreenRunnerTest {

    private static final int ONE_SECOND_IN_MILLISECONDS = 1000;

    @Rule
    public ActivityTestRule<MainActivity> menuActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void startScreenRunner() {
        showBottomSheet(R.id.ll_as_selector_hash_types);
        showBottomSheet(R.id.btn_generate_from);
        showBottomSheet(R.id.btn_hash_actions);

        showSettingsFragment();
        showFragmentInActionBar(R.id.menu_main_section_history);
        showFragmentInMenu(R.string.menu_title_feedback, true);
    }

    private void showBottomSheet(int bottomSheetButtonResId) {
        Espresso.onView(ViewMatchers.withId(bottomSheetButtonResId)).perform(ViewActions.click());
        delayAndBack();
    }

    private void showSettingsFragment() {
        showFragmentInMenu(R.string.menu_title_settings, false);
        clickToSettingsItem(R.string.settings_title_theme);
        clickToSettingsItem(R.string.settings_title_author);
        delayAndBack();
    }

    private void showFragmentInMenu(@IdRes int menuTitleResId, boolean withBackAction) {
        Espresso.openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        Espresso.onView(ViewMatchers.withText(InstrumentationRegistry.getTargetContext()
                .getString(menuTitleResId))).perform(ViewActions.click());
        if (withBackAction) {
            delayAndBack();
        }
    }

    private void showFragmentInActionBar(@IdRes int itemResId) {
        Espresso.onView(ViewMatchers.withId(itemResId)).perform(ViewActions.click());
        delayAndBack();
    }

    private void clickToSettingsItem(int settingsTitleResId) {
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view))
                .perform(RecyclerViewActions.actionOnItem(ViewMatchers
                                .hasDescendant(ViewMatchers.withText(settingsTitleResId)),
                        ViewActions.click()));
        delayAndBack();
    }

    private void delayAndBack() {
        secondDelay();
        Espresso.pressBack();
        secondDelay();
    }

    private void secondDelay() {
        try {
            Thread.sleep(ONE_SECOND_IN_MILLISECONDS);
        } catch (InterruptedException e) {
            Logger.error(e);
        }
    }

}
