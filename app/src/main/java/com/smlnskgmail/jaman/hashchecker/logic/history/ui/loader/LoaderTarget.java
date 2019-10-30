package com.smlnskgmail.jaman.hashchecker.logic.history.ui.loader;

import com.smlnskgmail.jaman.hashchecker.logic.history.ui.entities.HistoryPortion;

import java.util.List;

public interface LoaderTarget<T> {

    void postLoad(List<T> items);

    HistoryPortion dataPortion();

}
