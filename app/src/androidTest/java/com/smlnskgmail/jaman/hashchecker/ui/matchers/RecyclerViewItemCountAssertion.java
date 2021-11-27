package com.smlnskgmail.jaman.hashchecker.ui.matchers;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

// https://stackoverflow.com/a/37339656/10684765
public class RecyclerViewItemCountAssertion implements ViewAssertion {

    private final int expectedCount;

    public RecyclerViewItemCountAssertion(int expectedCount) {
        this.expectedCount = expectedCount;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        if (noViewFoundException != null) {
            throw noViewFoundException;
        }
        RecyclerView recyclerView = (RecyclerView) view;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        assertNotNull(adapter);
        assertEquals(
                adapter.getItemCount(),
                expectedCount
        );
    }

}
