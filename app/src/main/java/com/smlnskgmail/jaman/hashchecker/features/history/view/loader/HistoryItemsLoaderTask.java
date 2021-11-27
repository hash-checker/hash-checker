package com.smlnskgmail.jaman.hashchecker.features.history.view.loader;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.components.localdatastorage.api.LocalDataStorage;
import com.smlnskgmail.jaman.hashchecker.components.localdatastorage.models.HistoryItem;

import java.util.List;

public class HistoryItemsLoaderTask extends AsyncTask<Void, List<HistoryItem>, List<HistoryItem>> {

    private final HistoryItemsLoaderTaskTarget loaderTaskTarget;
    private final LocalDataStorage localDataStorage;

    public HistoryItemsLoaderTask(
            @NonNull HistoryItemsLoaderTaskTarget loaderTaskTarget,
            @NonNull LocalDataStorage localDataStorage
    ) {
        this.loaderTaskTarget = loaderTaskTarget;
        this.localDataStorage = localDataStorage;
    }

    @SuppressWarnings({"MethodParametersAnnotationCheck", "MethodObjectReturnAnnotationCheck"})
    @Override
    protected List<HistoryItem> doInBackground(Void... voids) {
        return localDataStorage.historyItems(loaderTaskTarget.dataPortion());
    }

    @SuppressWarnings("MethodParametersAnnotationCheck")
    @Override
    protected void onPostExecute(List<HistoryItem> historyItems) {
        completeLoad(historyItems);
    }

    private void completeLoad(@NonNull List<HistoryItem> historyItems) {
        HistoryPortion historyPortion = loaderTaskTarget.dataPortion();
        historyPortion.setLoaded(historyItems.size() < loaderTaskTarget.dataPortion().pageSize());
        historyPortion.setPage(loaderTaskTarget.dataPortion().page() + 1);
        loaderTaskTarget.postLoad(historyItems);
    }

}
