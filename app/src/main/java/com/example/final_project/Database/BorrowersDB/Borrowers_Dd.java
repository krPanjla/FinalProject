package com.example.final_project.Database.BorrowersDB;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.final_project.add_borrower;

import static android.content.ContentValues.TAG;

public class Borrowers_Dd extends SQLiteOpenHelper {
    private static int DATABASE_VERSION=1;


    public static final String CREATE_TABLE = "CREATE TABLE "+BlankContact.BlankEnter.BORROWER_TABLE_NAME+"("+
            BlankContact.BlankEnter._ID + " TEXT NOT NULL PRIMARY KEY, "+
            BlankContact.BlankEnter.COLUMNS_BORROWER_DATE+" TEXT,"+
            /*RAEL is use to store the floating point number in the sqlite database*/
            BlankContact.BlankEnter.COLUMNS_BORROWER_AMOUNT +" REAL NOT NULL) ;";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS'"+ BlankContact.BlankEnter.BORROWER_TABLE_NAME +"';";

    public Borrowers_Dd( add_borrower context) {

            super(context, BlankContact.BlankEnter.DATABASE_NAME, null,DATABASE_VERSION);
        }
         @Override
        public void onCreate(SQLiteDatabase db) {
           db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
        }

}


