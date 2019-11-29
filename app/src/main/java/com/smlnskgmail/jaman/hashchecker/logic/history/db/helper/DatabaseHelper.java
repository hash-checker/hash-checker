package com.smlnskgmail.jaman.hashchecker.logic.history.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.logic.history.db.entity.DBEntity;
import com.smlnskgmail.jaman.hashchecker.logic.history.ui.entities.HistoryItem;
import com.smlnskgmail.jaman.hashchecker.logic.history.ui.loader.HistoryPortion;
import com.smlnskgmail.jaman.hashchecker.tools.LogTool;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    public static final String DATABASE_FOLDER = "databases" + File.separator;
    public static final String DATABASE_NAME = "hashchecker.db";

    private static final int DATABASE_VERSION_1 = 1; // Added History

    private static final int DATABASE_VERSION = DATABASE_VERSION_1;

    private static final List<Class<? extends DBEntity>> tablesClasses = Collections.singletonList(
            HistoryItem.class
    );

    public DatabaseHelper(@NonNull Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            for (Class clazz: tablesClasses) {
                TableUtils.createTable(connectionSource, clazz);
            }
        } catch (SQLException e) {
            LogTool.e(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    public void addHistoryItem(@NonNull HistoryItem historyItem) {
        try {
            getDao(HistoryItem.class).create(historyItem);
        } catch (SQLException e) {
            LogTool.e(e);
        }
    }

    public List<HistoryItem> historyItems(@NonNull HistoryPortion historyPortion) {
        try {
            long portion = historyPortion.pageSize();
            int page = historyPortion.page();

            QueryBuilder<HistoryItem, ?> queryBuilder = getDao(HistoryItem.class).queryBuilder().limit(portion);
            if (page != -1L) {
                queryBuilder.offset(page * portion);
            }
            return queryBuilder.query();
        } catch (SQLException e) {
            LogTool.e(e);
        }
        return new ArrayList<>();
    }

    public void deleteAllHistoryItems() {
        try {
            getDao(HistoryItem.class).deleteBuilder().delete();
        } catch (SQLException e) {
            LogTool.e(e);
        }
    }

    public boolean isHistoryItemsListIsEmpty() {
        try {
            return getDao(HistoryItem.class).countOf() > 0;
        } catch (SQLException e) {
            LogTool.e(e);
            return false;
        }
    }

    //https://stackoverflow.com/questions/19574286/how-to-merge-contents-of-sqlite-3-7-wal-file-into-main-database-file
    public void checkpoint() {
        try {
            getWritableDatabase().execSQL("PRAGMA wal_checkpoint");
        } catch (Exception e) {
            LogTool.e(e);
        }
    }

}
