package com.smlnskgmail.jaman.hashchecker.features.history.view.loader;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.components.localdatastorage.models.HistoryItem;

import java.util.List;

public interface HistoryItemsLoaderTaskTarget {

    void postLoad(@NonNull List<HistoryItem> items);

    @NonNull
    HistoryPortion dataPortion();

}
