package com.smlnskgmail.jaman.hashchecker.logic.history.ui.loader;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.logic.database.HelperFactory;
import com.smlnskgmail.jaman.hashchecker.logic.history.HistoryItem;

import java.util.List;

public class HistoryItemsLoaderTask extends AsyncTask<Void, List<HistoryItem>, List<HistoryItem>> {

    private final HistoryItemsLoaderTaskTarget<HistoryItem> loaderTaskTarget;

    public HistoryItemsLoaderTask(
            @NonNull HistoryItemsLoaderTaskTarget<HistoryItem> loaderTaskTarget
    ) {
        this.loaderTaskTarget = loaderTaskTarget;
    }

    @Override
    protected List<HistoryItem> doInBackground(Void... voids) {
        return HelperFactory.getHelper().historyItems(
                loaderTaskTarget.dataPortion()
        );
    }

    @Override
    protected void onPostExecute(List<HistoryItem> historyItems) {
        completeLoad(historyItems);
    }

    private void completeLoad(@NonNull List<HistoryItem> historyItems) {
        HistoryPortion historyPortion = loaderTaskTarget.dataPortion();
        historyPortion.setLoaded(
                historyItems.size() < loaderTaskTarget.dataPortion().pageSize()
        );
        historyPortion.setPage(loaderTaskTarget.dataPortion().page() + 1);
        loaderTaskTarget.postLoad(historyItems);
    }

}
