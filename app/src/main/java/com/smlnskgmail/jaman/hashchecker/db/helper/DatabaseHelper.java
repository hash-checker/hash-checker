package com.smlnskgmail.jaman.hashchecker.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.db.entity.DBEntity;
import com.smlnskgmail.jaman.hashchecker.fragments.functionality.history.data.HistoryItem;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "hashchecker.db";

    private static final int DATABASE_VERSION_1 = 1; // History

    private static final int DATABASE_VERSION = DATABASE_VERSION_1;

    private static final List<Class<? extends DBEntity>> tablesClasses = Collections.singletonList(
            HistoryItem.class
    );

    private Context context;

    public DatabaseHelper(@NonNull Context context) {
        this(context, DATABASE_NAME);
    }

    public DatabaseHelper(@NonNull Context context, @NonNull String databaseName) {
        super(context, databaseName, null, DATABASE_VERSION, R.raw.ormlite_config);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            for (Class clazz: tablesClasses){
                TableUtils.createTable(connectionSource, clazz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion,
                          int newVersion) {

    }

    public void addGeneratorHistoryItem(@NonNull HistoryItem historyItem) {
        try {
            getDao(HistoryItem.class).create(historyItem);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
