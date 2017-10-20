package com.sreevidya.opal.model.database;


public class DatabaseInjector {
    private static DatabaseSource DATABASESOURCE;

    public static DatabaseSource getInstance() {
        if (DATABASESOURCE == null) {
            DATABASESOURCE = new DatabaseSourceImpl();
        }
        return DATABASESOURCE;
    }
}
