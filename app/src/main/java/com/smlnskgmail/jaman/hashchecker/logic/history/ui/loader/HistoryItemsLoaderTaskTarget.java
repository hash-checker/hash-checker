package com.smlnskgmail.jaman.hashchecker.logic.history.ui.loader;

import java.util.List;

public interface HistoryItemsLoaderTaskTarget<T> {

    void postLoad(List<T> items);

    HistoryPortion dataPortion();

}
