package com.smlnskgmail.jaman.hashchecker.fragments.functionality.history;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.containers.AdaptiveRecyclerView;
import com.smlnskgmail.jaman.hashchecker.components.dialogs.system.AppAlertDialog;
import com.smlnskgmail.jaman.hashchecker.db.helper.DatabaseHelper;
import com.smlnskgmail.jaman.hashchecker.db.helper.HelperFactory;
import com.smlnskgmail.jaman.hashchecker.fragments.BaseFragment;
import com.smlnskgmail.jaman.hashchecker.fragments.functionality.history.adapter.HistoryItemsAdapter;
import com.smlnskgmail.jaman.hashchecker.fragments.functionality.history.data.HistoryItem;

import java.util.List;

import butterknife.BindView;

public class HistoryFragment extends BaseFragment {

    private class HistoryItemsLoader extends AsyncTask<Void, List<HistoryItem>, List<HistoryItem>> {

        @Override
        protected List<HistoryItem> doInBackground(Void... voids) {
            return HelperFactory.getHelper().getAllHistoryItems();
        }

        @Override
        protected void onPostExecute(List<HistoryItem> historyItems) {
            pbHistory.setVisibility(View.GONE);
            rvHistoryItems.setVisibility(View.VISIBLE);
            ((HistoryItemsAdapter) rvHistoryItems.getAdapter()).addHistoryItems(historyItems);
        }

    }

    @BindView(R.id.rv_history_items)
    protected AdaptiveRecyclerView rvHistoryItems;

    @BindView(R.id.pb_history)
    protected ProgressBar pbHistory;

    @Override
    public void initializeUI(@NonNull View contentView) {
        rvHistoryItems.setEmptyMessageView(contentView.findViewById(R.id.ll_history_empty_view));
        resetHistoryAdapter();
        load();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_item_clean_history) {
            AppAlertDialog.show(getContext(), R.string.title_warning_dialog,
                    R.string.message_delete_all_history_items, R.string.common_ok, (dialog, which) -> {
                HelperFactory.getHelper().deleteAllHistoryItems();
                resetHistoryAdapter();
            });
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void resetHistoryAdapter() {
        rvHistoryItems.setAdapter(new HistoryItemsAdapter());
    }

    private void load() {
        pbHistory.setVisibility(View.VISIBLE);
        rvHistoryItems.setVisibility(View.GONE);
        rvHistoryItems.getEmptyMessage().setVisibility(View.GONE);
        new HistoryItemsLoader().execute();
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
