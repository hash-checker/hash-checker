package com.smlnskgmail.jaman.hashchecker.components.localdatastorage.api;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.components.localdatastorage.models.HistoryItem;
import com.smlnskgmail.jaman.hashchecker.features.history.view.loader.HistoryPortion;

import java.util.List;

public interface LocalDataStorage {

    void releaseHelper();

    void addHistoryItem(@NonNull HistoryItem historyItem);

    @NonNull
    List<HistoryItem> historyItems(@NonNull HistoryPortion historyPortion);

    void deleteAllHistoryItems();

    boolean isHistoryItemsListIsEmpty();

    void backupCheckpoint();

    @NonNull
    String getDatabaseFolder();

    @NonNull
    String getDatabaseFileName();

}
