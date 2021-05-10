package com.smlnskgmail.jaman.hashchecker.logic.history.ui.loader;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.logic.database.api.DatabaseHelper;
import com.smlnskgmail.jaman.hashchecker.logic.history.HistoryItem;

import java.util.List;

public class HistoryItemsLoaderTask extends AsyncTask<Void, List<HistoryItem>, List<HistoryItem>> {

    private final HistoryItemsLoaderTaskTarget<HistoryItem> loaderTaskTarget;
    private final DatabaseHelper databaseHelper;

    public HistoryItemsLoaderTask(
            @NonNull HistoryItemsLoaderTaskTarget<HistoryItem> loaderTaskTarget,
            @NonNull DatabaseHelper databaseHelper
    ) {
        this.loaderTaskTarget = loaderTaskTarget;
        this.databaseHelper = databaseHelper;
    }

    @SuppressWarnings({"MethodParametersAnnotationCheck", "MethodObjectReturnAnnotationCheck"})
    @Override
    protected List<HistoryItem> doInBackground(Void... voids) {
        return databaseHelper.historyItems(
                loaderTaskTarget.dataPortion()
        );
    }

    @SuppressWarnings("MethodParametersAnnotationCheck")
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
