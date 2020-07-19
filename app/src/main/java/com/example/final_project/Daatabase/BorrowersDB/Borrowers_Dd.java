package com.example.final_project.Daatabase.BorrowersDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.final_project.Contact;

public class Borrowers_Dd extends SQLiteOpenHelper {
    private static String DATABASE_NAME="Borrower";
    private static int DATABASE_VERSION=1;
    private String TABLE="Borrower";
    private String COLUMN_1="ID";
    private String COLUMN_2="E-mail";
    private String COLUMN_3="AMMOUNT";
    SQLiteDatabase db;

    public Borrowers_Dd( add_borrower context) {

            super(context, DATABASE_NAME, null,DATABASE_VERSION);
        }
         @Override
        public void onCreate(SQLiteDatabase db) {

          String sql="create table "+TABLE+"(ID INTEGER PRIMARY KEY,NAME TEXT,AMMOUNT TEXT);";

           db.execSQL(sql);



        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS'"+TABLE+"'");
        onCreate(db);

        }

    public long saveContact(Contact contact) {
        db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_1,contact.getId());
        values.put(COLUMN_2,contact.getMail());
        values.put(COLUMN_3,contact.getAmount());

        long rowCount=db.insert(TABLE,null,values);
        db.close();
        return rowCount;
    }
}


