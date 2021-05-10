package com.smlnskgmail.jaman.hashchecker.logic.history.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smlnskgmail.jaman.adaptiverecyclerview.AdaptiveRecyclerView;
import com.smlnskgmail.jaman.hashchecker.App;
import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.BaseFragment;
import com.smlnskgmail.jaman.hashchecker.components.dialogs.system.AppAlertDialog;
import com.smlnskgmail.jaman.hashchecker.logic.database.api.DatabaseHelper;
import com.smlnskgmail.jaman.hashchecker.logic.history.HistoryItem;
import com.smlnskgmail.jaman.hashchecker.logic.history.ui.list.HistoryItemsAdapter;
import com.smlnskgmail.jaman.hashchecker.logic.history.ui.loader.HistoryItemsLoaderTask;
import com.smlnskgmail.jaman.hashchecker.logic.history.ui.loader.HistoryItemsLoaderTaskTarget;
import com.smlnskgmail.jaman.hashchecker.logic.history.ui.loader.HistoryPortion;
import com.smlnskgmail.jaman.hashchecker.logic.locale.api.LangHelper;
import com.smlnskgmail.jaman.hashchecker.logic.themes.api.ThemeHelper;

import java.util.List;

import javax.inject.Inject;

public class HistoryFragment extends BaseFragment implements HistoryItemsLoaderTaskTarget<HistoryItem> {

    @Inject
    public DatabaseHelper databaseHelper;

    @Inject
    public LangHelper langHelper;

    @Inject
    public ThemeHelper themeHelper;

    private AdaptiveRecyclerView arvHistoryItems;
    private ProgressBar pbHistory;

    private final HistoryPortion historyPortion = new HistoryPortion();

    private boolean isLoading;

    // CPD-OFF
    @Override
    public void onAttach(@NonNull Context context) {
        App.appComponent.inject(this);
        super.onAttach(context);
    }
    // CPD-ON

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);
        pbHistory = view.findViewById(R.id.pb_history);
        arvHistoryItems = view.findViewById(R.id.rv_history_items);
        arvHistoryItems.setMessageView(view.findViewById(R.id.ll_history_empty_view));
        arvHistoryItems.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(
                    @NonNull RecyclerView recyclerView,
                    int dx,
                    int dy
            ) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager != null) {
                    int totalItemCount = layoutManager.getItemCount();
                    int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                    if (canLoad(totalItemCount, lastVisibleItem)) {
                        isLoading = true;
                        load();
                    }
                }
            }

            private boolean canLoad(int totalItemCount, int lastVisibleItem) {
                return !isLoading && !historyPortion.isLoaded()
                        && totalItemCount <= lastVisibleItem + 2;
            }
        });
        resetHistoryAdapter();
        load();
    }

    @NonNull
    @Override
    protected LangHelper langHelper() {
        return langHelper;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_item_clean_history) {
            Context context = getContext();
            if (context != null) {
                new AppAlertDialog(
                        context,
                        R.string.title_warning_dialog,
                        R.string.message_delete_all_history_items,
                        R.string.common_ok,
                        (dialog, which) -> {
                            databaseHelper.deleteAllHistoryItems();
                            resetHistoryAdapter();
                        },
                        themeHelper
                ).show();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void resetHistoryAdapter() {
        arvHistoryItems.setAdapter(new HistoryItemsAdapter());
    }

    private void load() {
        if (!historyPortion.isLoaded()) {
            pbHistory.setVisibility(View.VISIBLE);
            arvHistoryItems.setVisibility(View.GONE);
            new HistoryItemsLoaderTask(
                    this,
                    databaseHelper
            ).execute();
        }
    }

    @Override
    public void postLoad(@NonNull List<HistoryItem> items) {
        pbHistory.setVisibility(View.GONE);
        arvHistoryItems.setVisibility(View.VISIBLE);
        ((HistoryItemsAdapter) arvHistoryItems.getAdapter()).addHistoryItems(items);
    }

    @NonNull
    @Override
    public HistoryPortion dataPortion() {
        return historyPortion;
    }

    @Override
    public int getMenuResId() {
        return R.menu.menu_history;
    }

    @Override
    public int getActionBarTitleResId() {
        return R.string.menu_title_history;
    }

    @Override
    public boolean setAllowBackAction() {
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
