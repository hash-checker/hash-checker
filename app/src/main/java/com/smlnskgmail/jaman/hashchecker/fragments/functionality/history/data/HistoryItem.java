package com.smlnskgmail.jaman.hashchecker.fragments.functionality.history.data;

import android.support.annotation.NonNull;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.smlnskgmail.jaman.hashchecker.db.entity.DBEntity;
import com.smlnskgmail.jaman.hashchecker.generator.HashTypes;

import java.util.Date;

@DatabaseTable(tableName = "history")
public class HistoryItem extends DBEntity {

    @DatabaseField(columnName = "generation_date", dataType = DataType.DATE_STRING)
    private Date generationDate;

    @DatabaseField(columnName = "hash_type", dataType = DataType.ENUM_STRING)
    private HashTypes hashType;

    @DatabaseField(columnName = "is_file", dataType = DataType.BOOLEAN)
    private boolean isFile;

    @DatabaseField(columnName = "object_value", dataType = DataType.STRING)
    private String objectValue;

    @DatabaseField(columnName = "hash_value", dataType = DataType.STRING)
    private String hashValue;

    public HistoryItem(@NonNull Date generationDate, @NonNull HashTypes hashType,
                       boolean isFile, @NonNull String objectValue, @NonNull String hashValue) {
        this.generationDate = generationDate;
        this.hashType = hashType;
        this.isFile = isFile;
        this.objectValue = objectValue;
        this.hashValue = hashValue;
    }

    public HistoryItem() {

    }

    @NonNull
    public Date getGenerationDate() {
        return generationDate;
    }

    @NonNull
    public HashTypes getHashType() {
        return hashType;
    }

    public boolean isFile() {
        return isFile;
    }

    @NonNull
    public String getObjectValue() {
        return objectValue;
    }

    @NonNull
    public String getHashValue() {
        return hashValue;
    }

}
