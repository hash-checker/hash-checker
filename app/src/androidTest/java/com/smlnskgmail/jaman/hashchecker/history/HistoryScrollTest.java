package com.smlnskgmail.jaman.hashchecker.history;

import androidx.test.espresso.contrib.RecyclerViewActions;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.matchers.RecyclerViewItemCountAssertion;
import com.smlnskgmail.jaman.hashchecker.logic.hashcalculator.api.HashType;
import com.smlnskgmail.jaman.hashchecker.logic.history.HistoryItem;

import java.util.Calendar;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class HistoryScrollTest extends BaseHistoryTest {

    @Override
    public void runTest() throws InterruptedException {
        openHistory();
        clearHistory();
        for (int i = 0; i < 50; i++) {
            HelperFactory.getHelper().addHistoryItem(
                    new HistoryItem(
                            Calendar.getInstance().getTime(),
                            HashType.MD5,
                            true,
                            "",
                            ""
                    )
            );
        }
        openHistory();
        onView(withId(R.id.rv_history_items)).check(
                new RecyclerViewItemCountAssertion(30)
        );
        onView(withId(R.id.rv_history_items)).perform(
                RecyclerViewActions.scrollToPosition(29)
        );
        CountDownLatch countDownLatch = new CountDownLatch(1);
        countDownLatch.await(
                SECOND_IN_MILLIS,
                TimeUnit.MILLISECONDS
        );
        onView(withId(R.id.rv_history_items)).check(
                new RecyclerViewItemCountAssertion(50)
        );
    }

}
