package com.smlnskgmail.jaman.hashchecker.di.modules;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.logic.database.api.DatabaseHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseHelperModule {

    private final DatabaseHelper databaseHelper;

    public DatabaseHelperModule(
            @NonNull DatabaseHelper databaseHelper
    ) {
        this.databaseHelper = databaseHelper;
    }

    @NonNull
    @Provides
    public DatabaseHelper databaseHelper() {
        return databaseHelper;
    }

}
