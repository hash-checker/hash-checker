package com.smlnskgmail.jaman.hashchecker.db.entity;

public class DataPortion {

    private long pageSize;
    private int page;

    private boolean isLoaded;

    public DataPortion(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getPageSize() {
        return pageSize;
    }

    public int getPage() {
        return page;
    }

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
