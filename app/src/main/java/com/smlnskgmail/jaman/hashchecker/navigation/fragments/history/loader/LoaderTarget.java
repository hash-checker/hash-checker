package com.smlnskgmail.jaman.hashchecker.navigation.fragments.history.loader;

import com.smlnskgmail.jaman.hashchecker.navigation.fragments.history.entities.HistoryPortion;

import java.util.List;

public interface LoaderTarget<T> {

    void postLoad(List<T> items);

    HistoryPortion dataPortion();

}
