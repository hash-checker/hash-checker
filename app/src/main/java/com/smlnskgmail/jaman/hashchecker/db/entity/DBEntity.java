package com.smlnskgmail.jaman.hashchecker.db.entity;

import com.j256.ormlite.field.DatabaseField;

public class DBEntity {

    @DatabaseField(generatedId = true)
    private long id = -1;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
