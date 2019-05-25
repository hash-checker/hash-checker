package com.smlnskgmail.jaman.hashchecker.db.config;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

public class DatabaseConfigUtil extends OrmLiteConfigUtil {

    public static void main(String[] args) throws Exception {
        writeConfigFile("ormlite_config.txt");
    }

}
