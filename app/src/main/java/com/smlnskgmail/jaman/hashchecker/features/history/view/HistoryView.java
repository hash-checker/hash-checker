package com.smlnskgmail.jaman.hashchecker.features.history.view;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.components.localdatastorage.models.HistoryItem;

import java.util.List;

public interface HistoryView {

    void setLoading();

    void setLoaded(@NonNull List<HistoryItem> items);

    void refreshHistory();

}
