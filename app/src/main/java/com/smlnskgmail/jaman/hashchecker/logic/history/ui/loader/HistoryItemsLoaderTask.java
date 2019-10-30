package com.smlnskgmail.jaman.hashchecker.logic.history.ui.loader;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.logic.history.db.HelperFactory;
import com.smlnskgmail.jaman.hashchecker.logic.history.ui.entities.HistoryItem;

import java.util.List;

public class HistoryItemsLoaderTask extends AsyncTask<Void, List<HistoryItem>, List<HistoryItem>> {

    private final HistoryItemsLoaderTaskTarget<HistoryItem> historyItemsLoaderTaskTarget;

    public HistoryItemsLoaderTask(@NonNull HistoryItemsLoaderTaskTarget<HistoryItem> historyItemsLoaderTaskTarget) {
        this.historyItemsLoaderTaskTarget = historyItemsLoaderTaskTarget;
    }

    @Override
    protected List<HistoryItem> doInBackground(Void... voids) {
        return HelperFactory.getHelper().historyItems(historyItemsLoaderTaskTarget.dataPortion());
    }

    @Override
    protected void onPostExecute(List<HistoryItem> historyItems) {
        completeLoad(historyItems);
    }

    private void completeLoad(@NonNull List<HistoryItem> historyItems) {
        HistoryPortion historyPortion = historyItemsLoaderTaskTarget.dataPortion();
        historyPortion.setLoaded(historyItems.size() < historyItemsLoaderTaskTarget.dataPortion().pageSize());
        historyPortion.setPage(historyItemsLoaderTaskTarget.dataPortion().page() + 1);
        historyItemsLoaderTaskTarget.postLoad(historyItems);
    }

}
