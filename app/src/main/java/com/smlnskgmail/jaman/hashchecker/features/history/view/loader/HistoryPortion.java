package com.smlnskgmail.jaman.hashchecker.features.history.view.loader;

public class HistoryPortion {

    private static final int PAGE_SIZE = 30;

    private int page;
    private boolean isLoaded;

    public long pageSize() {
        return PAGE_SIZE;
    }

    public int page() {
        return page;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean isLoaded() {
        return isLoaded;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setLoaded(boolean loaded) {
        isLoaded = loaded;
    }

}
