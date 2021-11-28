package com.smlnskgmail.jaman.hashchecker.components.localdatastorage.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.smlnskgmail.jaman.hashchecker.components.hashcalculator.api.HashType;

import java.util.Date;
import java.util.Objects;

@DatabaseTable(tableName = HistoryItem.TABLE_HISTORY)
public class HistoryItem extends DbEntity {

    public static final String TABLE_HISTORY = "history";

    public static final String COLUMN_GENERATION_DATE = "generation_date";
    public static final String COLUMN_HASH_TYPE = "hash_type";
    public static final String COLUMN_IS_FILE = "is_file";
    public static final String COLUMN_OBJECT_VALUE = "object_value";
    public static final String COLUMN_HASH_VALUE = "hash_value";

    @DatabaseField(columnName = HistoryItem.COLUMN_GENERATION_DATE, dataType = DataType.DATE_STRING)
    private Date generationDate;

    @DatabaseField(columnName = HistoryItem.COLUMN_HASH_TYPE, dataType = DataType.ENUM_STRING)
    private HashType hashType;

    @DatabaseField(columnName = HistoryItem.COLUMN_IS_FILE, dataType = DataType.BOOLEAN)
    private boolean isFile;

    @DatabaseField(columnName = HistoryItem.COLUMN_OBJECT_VALUE, dataType = DataType.STRING)
    private String objectValue;

    @DatabaseField(columnName = HistoryItem.COLUMN_HASH_VALUE, dataType = DataType.STRING)
    private String hashValue;

    public HistoryItem(
            @NonNull Date generationDate,
            @NonNull HashType hashType,
            boolean isFile,
            @NonNull String objectValue,
            @NonNull String hashValue
    ) {
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
    public HashType getHashType() {
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

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HistoryItem that = (HistoryItem) o;
        return isFile == that.isFile
                && Objects.equals(generationDate, that.generationDate)
                && hashType == that.hashType
                && Objects.equals(objectValue, that.objectValue)
                && Objects.equals(hashValue, that.hashValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(generationDate, hashType, isFile, objectValue, hashValue);
    }

}
