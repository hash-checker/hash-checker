package com.smlnskgmail.jaman.hashchecker.navigation.fragments.history.loader;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.db.HelperFactory;
import com.smlnskgmail.jaman.hashchecker.navigation.fragments.history.entities.HistoryItem;
import com.smlnskgmail.jaman.hashchecker.navigation.fragments.history.entities.HistoryPortion;

import java.util.List;

public class HistoryItemsLoader extends AsyncTask<Void, List<HistoryItem>, List<HistoryItem>> {

    private final LoaderTarget<HistoryItem> loaderTarget;

    public HistoryItemsLoader(@NonNull LoaderTarget<HistoryItem> loaderTarget) {
        this.loaderTarget = loaderTarget;
    }

    @Override
    protected List<HistoryItem> doInBackground(Void... voids) {
        return HelperFactory.getHelper().historyItems(loaderTarget.dataPortion());
    }

    @Override
    protected void onPostExecute(List<HistoryItem> historyItems) {
        completeLoad(historyItems);
    }

    private void completeLoad(@NonNull List<HistoryItem> historyItems) {
        HistoryPortion historyPortion = loaderTarget.dataPortion();
        historyPortion.setLoaded(historyItems.size() < loaderTarget.dataPortion().pageSize());
        historyPortion.setPage(loaderTarget.dataPortion().page() + 1);
        loaderTarget.postLoad(historyItems);
    }

}
