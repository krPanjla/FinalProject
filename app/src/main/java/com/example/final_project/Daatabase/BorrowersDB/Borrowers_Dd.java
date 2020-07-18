package com.example.final_project.Daatabase.BorrowersDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Borrowers_Dd extends SQLiteOpenHelper {
    private String TABLE="Borrower";
    private String COLUMN_1="ID";
    private String COLUMN_2="E-mail";
    private String COLUMN_3="AMMOUNT";






    public Borrowers_Dd( Context context) {

            super(context, "Borrower_Db", null, 1);
        }
         @Override
        public void onCreate(SQLiteDatabase db) {
           // String Sql="CREATE TABLE"+TABLE+"("+COLUMN_1+"INTEGER PRIMARY KEY"+COLUMN_2+"TEXT"+COLUMN_3+"TEXT"+")";
           // db.execSQL(Sql);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS'"+TABLE+"'");
        onCreate(db);

        }
    }


