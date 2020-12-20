package com.smlnskgmail.jaman.hashchecker.logic.history.ui.loader;

import androidx.annotation.NonNull;

import java.util.List;

public interface HistoryItemsLoaderTaskTarget<T> {

    void postLoad(@NonNull List<T> items);

    HistoryPortion dataPortion();

}
