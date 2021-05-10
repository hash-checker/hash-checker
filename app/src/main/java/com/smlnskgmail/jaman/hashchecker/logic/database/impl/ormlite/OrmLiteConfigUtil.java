package com.smlnskgmail.jaman.hashchecker.logic.database.impl.ormlite;

import androidx.annotation.NonNull;

class OrmLiteConfigUtil extends com.j256.ormlite.android.apptools.OrmLiteConfigUtil {

    public static void main(@NonNull String[] args) throws Exception {
        writeConfigFile("ormlite_config.txt");
    }

}
