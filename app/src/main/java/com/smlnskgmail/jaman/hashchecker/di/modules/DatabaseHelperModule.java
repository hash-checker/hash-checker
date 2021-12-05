package com.smlnskgmail.jaman.hashchecker.di.modules;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.components.localdatastorage.api.LocalDataStorage;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseHelperModule {

    private final LocalDataStorage localDataStorage;

    public DatabaseHelperModule(@NonNull LocalDataStorage localDataStorage) {
        this.localDataStorage = localDataStorage;
    }

    @NonNull
    @Provides
    public LocalDataStorage databaseHelper() {
        return localDataStorage;
    }

}
