package com.smlnskgmail.jaman.hashchecker.logic.database;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.logic.history.HistoryItem;
import com.smlnskgmail.jaman.hashchecker.logic.history.ui.loader.HistoryPortion;

import java.util.List;

public interface DatabaseHelper {

    void releaseHelper();

    void addHistoryItem(@NonNull HistoryItem historyItem);
    List<HistoryItem> historyItems(@NonNull HistoryPortion historyPortion);
    void deleteAllHistoryItems();
    boolean isHistoryItemsListIsEmpty();

    void backupCheckpoint();

    String getDatabaseFolder();
    String getDatabaseFileName();

}
