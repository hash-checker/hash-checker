package com.smlnskgmail.jaman.hashchecker.fragments.functionality.history;

import android.support.annotation.NonNull;
import android.view.View;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.containers.AdaptiveRecyclerView;
import com.smlnskgmail.jaman.hashchecker.db.helper.HelperFactory;
import com.smlnskgmail.jaman.hashchecker.fragments.BaseFragment;
import com.smlnskgmail.jaman.hashchecker.fragments.functionality.history.adapter.HistoryItemsAdapter;

import butterknife.BindView;

public class HistoryFragment extends BaseFragment {

    @BindView(R.id.rv_history_items)
    protected AdaptiveRecyclerView rvHistoryItems;

    @Override
    public void initializeUI(@NonNull View contentView) {
        rvHistoryItems.setEmptyMessageView(contentView.findViewById(R.id.ll_history_empty_view));
        rvHistoryItems.setAdapter(new HistoryItemsAdapter(HelperFactory.getHelper()
                .getAllHistoryItems()));
    }

    @Override
    public int getMenuResId() {
        return R.menu.menu_history;
    }

    @Override
    public int[] getMenuItemsIds() {
        return new int[0];
    }

    @Override
    public int getActionBarTitleResId() {
        return R.string.menu_title_history;
    }

    @Override
    public boolean setBackActionIcon() {
        return true;
    }

    @Override
    public int getBackActionIconResId() {
        return R.drawable.ic_arrow_back;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragmant_history;
    }

}
