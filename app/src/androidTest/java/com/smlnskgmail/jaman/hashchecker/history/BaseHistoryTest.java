package com.smlnskgmail.jaman.hashchecker.history;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.BaseUITest;
import com.smlnskgmail.jaman.hashchecker.components.matchers.RecyclerViewItemCountAssertion;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public abstract class BaseHistoryTest extends BaseUITest {

    protected void openHistory() {
        clickById(R.id.menu_main_section_history);
    }

    protected void clearHistory() {
        clickById(R.id.menu_item_clean_history);
        onView(withText(R.string.common_ok)).perform(click());
        onView(withId(R.id.rv_history_items)).check(new RecyclerViewItemCountAssertion(0));
        delayAndBack();
    }

}
