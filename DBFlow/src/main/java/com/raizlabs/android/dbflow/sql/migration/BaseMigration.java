package com.raizlabs.android.dbflow.sql.migration;

import android.database.sqlite.SQLiteDatabase;

/**
 * Description: Provides the base implementation of {@link com.raizlabs.android.dbflow.sql.migration.Migration} with
 * only {@link #migrate(android.database.sqlite.SQLiteDatabase)} needing to be implemented.
 */
public abstract class BaseMigration implements Migration {


    @Override
    public void onPreMigrate() {

    }

    @Override
    public abstract void migrate(SQLiteDatabase database);

    @Override
    public void onPostMigrate() {

    }
}
