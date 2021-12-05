package com.smlnskgmail.jaman.hashchecker.features.history.view;

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
import com.smlnskgmail.jaman.hashchecker.components.localdatastorage.api.LocalDataStorage;
import com.smlnskgmail.jaman.hashchecker.components.localdatastorage.models.HistoryItem;
import com.smlnskgmail.jaman.hashchecker.components.locale.api.LanguageConfig;
import com.smlnskgmail.jaman.hashchecker.components.theme.api.ThemeConfig;
import com.smlnskgmail.jaman.hashchecker.features.history.presenter.HistoryPresenter;
import com.smlnskgmail.jaman.hashchecker.features.history.presenter.HistoryPresenterImpl;
import com.smlnskgmail.jaman.hashchecker.features.history.view.list.HistoryItemsAdapter;
import com.smlnskgmail.jaman.hashchecker.ui.BaseFragment;
import com.smlnskgmail.jaman.hashchecker.ui.dialogs.system.AppAlertDialog;

import java.util.List;

import javax.inject.Inject;

public class HistoryFragment extends BaseFragment implements HistoryView {

    @Inject
    public LocalDataStorage localDataStorage;

    @Inject
    public LanguageConfig languageConfig;

    @Inject
    public ThemeConfig themeConfig;

    private HistoryPresenter historyPresenter;

    private AdaptiveRecyclerView arvHistoryItems;
    private ProgressBar pbHistory;

    // CPD-OFF
    @Override
    public void onAttach(@NonNull Context context) {
        App.appComponent.inject(this);
        super.onAttach(context);
    }
    // CPD-ON

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pbHistory = view.findViewById(R.id.pb_history);
        arvHistoryItems = view.findViewById(R.id.rv_history_items);
        arvHistoryItems.setMessageView(view.findViewById(R.id.ll_history_empty_view));
        arvHistoryItems.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager != null) {
                    int totalItemCount = layoutManager.getItemCount();
                    int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                    if (canLoad(totalItemCount, lastVisibleItem)) {
                        historyPresenter.load();
                    }
                }
            }

            private boolean canLoad(int totalItemCount, int lastVisibleItem) {
                return !historyPresenter.isLoaded() && totalItemCount <= lastVisibleItem + 2;
            }
        });
        historyPresenter = new HistoryPresenterImpl();
        historyPresenter.init(this, localDataStorage);
    }

    @NonNull
    @Override
    protected LanguageConfig langHelper() {
        return languageConfig;
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
                        (dialog, which) -> historyPresenter.clearHistory(),
                        themeConfig
                ).show();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setLoading() {
        pbHistory.setVisibility(View.VISIBLE);
        arvHistoryItems.setVisibility(View.GONE);
    }

    @Override
    public void setLoaded(@NonNull List<HistoryItem> items) {
        pbHistory.setVisibility(View.GONE);
        arvHistoryItems.setVisibility(View.VISIBLE);
        HistoryItemsAdapter adapter = (HistoryItemsAdapter) arvHistoryItems.getAdapter();
        if (adapter != null) {
            adapter.addHistoryItems(items);
        } else {
            HistoryItemsAdapter historyItemsAdapter = new HistoryItemsAdapter();
            historyItemsAdapter.addHistoryItems(items);
            arvHistoryItems.setAdapter(historyItemsAdapter);
        }
    }

    @Override
    public void refreshHistory() {
        arvHistoryItems.setAdapter(new HistoryItemsAdapter());
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
