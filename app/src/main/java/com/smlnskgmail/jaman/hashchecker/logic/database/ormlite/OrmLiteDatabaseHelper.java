package com.smlnskgmail.jaman.hashchecker.logic.database.ormlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.logic.database.DatabaseHelper;
import com.smlnskgmail.jaman.hashchecker.logic.database.DbEntity;
import com.smlnskgmail.jaman.hashchecker.logic.history.HistoryItem;
import com.smlnskgmail.jaman.hashchecker.logic.history.ui.loader.HistoryPortion;
import com.smlnskgmail.jaman.hashchecker.logic.logs.L;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrmLiteDatabaseHelper extends OrmLiteSqliteOpenHelper implements DatabaseHelper {

    private static final String DATABASE_FOLDER = "databases" + File.separator;
    private static final String DATABASE_NAME = "hashchecker.db";

    private static final int DATABASE_VERSION_1 = 1; // Added History

    private static final int DATABASE_VERSION = DATABASE_VERSION_1;

    private static final List<Class<? extends DbEntity>> tablesClasses = Collections.singletonList(
            HistoryItem.class
    );

    public OrmLiteDatabaseHelper(@NonNull Context context) {
        super(
                context,
                DATABASE_NAME,
                null,
                DATABASE_VERSION,
                R.raw.ormlite_config
        );
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(
            SQLiteDatabase database,
            ConnectionSource connectionSource
    ) {
        try {
            for (Class clazz: tablesClasses) {
                TableUtils.createTable(
                        connectionSource,
                        clazz
                );
            }
        } catch (SQLException e) {
            L.e(e);
        }
    }

    @Override
    public void onUpgrade(
            SQLiteDatabase database,
            ConnectionSource connectionSource,
            int oldVersion,
            int newVersion
    ) {

    }

    @Override
    public void releaseHelper() {
        OpenHelperManager.releaseHelper();
    }

    @Override
    public void addHistoryItem(@NonNull HistoryItem historyItem) {
        try {
            getDao(HistoryItem.class).create(historyItem);
        } catch (SQLException e) {
            L.e(e);
        }
    }

    @Override
    public List<HistoryItem> historyItems(
            @NonNull HistoryPortion historyPortion
    ) {
        try {
            long portion = historyPortion.pageSize();
            int page = historyPortion.page();

            QueryBuilder<HistoryItem, ?> queryBuilder = getDao(HistoryItem.class)
                    .queryBuilder()
                    .orderBy(HistoryItem.COLUMN_GENERATION_DATE, false)
                    .limit(portion);
            if (page != -1L) {
                queryBuilder.offset(page * portion);
            }
            return queryBuilder.query();
        } catch (SQLException e) {
            L.e(e);
        }
        return new ArrayList<>();
    }

    @Override
    public void deleteAllHistoryItems() {
        try {
            getDao(HistoryItem.class)
                    .deleteBuilder()
                    .delete();
        } catch (SQLException e) {
            L.e(e);
        }
    }

    @Override
    public boolean isHistoryItemsListIsEmpty() {
        try {
            return getDao(HistoryItem.class).countOf() > 0;
        } catch (SQLException e) {
            L.e(e);
            return false;
        }
    }

    //https://stackoverflow.com/questions/19574286/how-to-merge-contents-of-sqlite-3-7-wal-file-into-main-database-file
    @Override
    public void backupCheckpoint() {
        try {
            getWritableDatabase().execSQL("PRAGMA wal_checkpoint");
        } catch (Exception e) {
            L.e(e);
        }
    }

    @Override
    public String getDatabaseFolder() {
        return DATABASE_FOLDER;
    }

    @Override
    public String getDatabaseFileName() {
        return DATABASE_NAME;
    }

}
