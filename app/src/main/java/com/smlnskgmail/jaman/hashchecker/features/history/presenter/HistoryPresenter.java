package com.smlnskgmail.jaman.hashchecker.features.history.presenter;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.components.localdatastorage.api.LocalDataStorage;
import com.smlnskgmail.jaman.hashchecker.features.history.view.HistoryView;
import com.smlnskgmail.jaman.hashchecker.features.history.view.loader.HistoryItemsLoaderTaskTarget;

public interface HistoryPresenter extends HistoryItemsLoaderTaskTarget {

    void init(@NonNull HistoryView historyView, @NonNull LocalDataStorage localDataStorage);

    boolean isLoaded();

    void load();

    void clearHistory();

}
