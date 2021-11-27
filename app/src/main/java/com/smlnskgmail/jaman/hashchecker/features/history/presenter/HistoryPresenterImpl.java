package com.smlnskgmail.jaman.hashchecker.features.history.presenter;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.components.localdatastorage.api.LocalDataStorage;
import com.smlnskgmail.jaman.hashchecker.components.localdatastorage.models.HistoryItem;
import com.smlnskgmail.jaman.hashchecker.features.history.view.HistoryView;
import com.smlnskgmail.jaman.hashchecker.features.history.view.loader.HistoryItemsLoaderTask;
import com.smlnskgmail.jaman.hashchecker.features.history.view.loader.HistoryPortion;

import java.util.List;

public class HistoryPresenterImpl implements HistoryPresenter {

    private final HistoryPortion historyPortion = new HistoryPortion();

    private LocalDataStorage localDataStorage;
    private HistoryView view;

    private boolean isLoading = false;

    @Override
    public void init(@NonNull HistoryView view, @NonNull LocalDataStorage localDataStorage) {
        this.view = view;
        this.localDataStorage = localDataStorage;
        load();
    }

    @Override
    public boolean isLoaded() {
        return !isLoading && !historyPortion.isLoaded();
    }

    @Override
    public void load() {
        if (!historyPortion.isLoaded()) {
            isLoading = true;
            view.setLoading();
            new HistoryItemsLoaderTask(this, localDataStorage).execute();
        }
    }

    @Override
    public void clearHistory() {
        localDataStorage.deleteAllHistoryItems();
        view.refreshHistory();
    }

    @Override
    public void postLoad(@NonNull List<HistoryItem> items) {
        isLoading = false;
        view.setLoaded(items);
    }

    @NonNull
    @Override
    public HistoryPortion dataPortion() {
        return historyPortion;
    }

}
