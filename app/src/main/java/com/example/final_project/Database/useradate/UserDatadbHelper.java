package com.example.final_project.Database.useradate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UserDatadbHelper extends SQLiteOpenHelper {
    /** @Variable CREATE_LOGIN_USER, use to create user table in the application
     * @Variable USERDATA_TABLE_VERSION , version control in UserData table
     * @Variable DROP_LOGIN_TABLE ,use to drop Final_Project userData table
     * */

    public final static String CREATE_LOGIN_USER = "CREATE TABLE "+ BlankContract.BlankEnter.LOGIN_TABLE_NAME+"( " +
            BlankContract.BlankEnter._ID+" TEXT NOT NULL PRIMARY KEY," +
            BlankContract.BlankEnter.COLUMNS_USER_EMAIL+" TEXT NOT NULL, "+
            BlankContract.BlankEnter.COLUMNS_USER_NAME+" TEXT NOT NULL ,"+
            BlankContract.BlankEnter.COLUMNS_USER_GENDER+" INTEGER ,"+
            BlankContract.BlankEnter.COLUMNS_USER_PHONE+" INTEGER," +
            BlankContract.BlankEnter.COLUMNS_USER_PASSWORD+" PASSWORD ,"+
            BlankContract.BlankEnter.COLUMNS_USER_IMAGE+"BLOB "+
            ");";

    public static final int USERDATA_TABLE_VERSION = 1;

    public static final String DROP_LOGIN_TABLE = "DROP TABLE IF EXISTS "+BlankContract.BlankEnter.LOGIN_TABLE_NAME;

    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use for locating paths to the the database
     */
    public UserDatadbHelper(@Nullable Context context) {
        super(context, BlankContract.BlankEnter.DATABASE_NAME, null, USERDATA_TABLE_VERSION);
    }

    public void tableDrop(SQLiteDatabase db){
        db.execSQL(DROP_LOGIN_TABLE);
        onCreate(db);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LOGIN_USER);
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     *
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_LOGIN_TABLE);
        onCreate(db);
    }
}
