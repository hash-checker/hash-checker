package com.smlnskgmail.jaman.hashchecker.logic.history.db.config;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

class DatabaseConfigUtil extends OrmLiteConfigUtil {

    public static void main(String[] args) throws Exception {
        writeConfigFile("ormlite_config.txt");
    }

}
