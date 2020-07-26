package com.example.final_project.Database.BorrowersDB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.final_project.add_borrower;

public class Borrowers_Dd extends SQLiteOpenHelper {
    private static int DATABASE_VERSION=1;


    public static final String CREATE_TABLE = "CREATE TABLE "+BlankContact.BlankEnter.BORROWER_TABLE_NAME+"("+
            BlankContact.BlankEnter.COLUMNS_BORROWER_NAME + " TEXT NOT NULL, "+
            BlankContact.BlankEnter.COLUMNS_BORROWER_DATE+" TEXT,"+
            BlankContact.BlankEnter.COLUMNS_BORROWER_FLAG+" TEXT NOT NULL ,"+
            /*RAEL is use to store the floating point number in the sqlite database*/
            BlankContact.BlankEnter.COLUMNS_BORROWER_AMOUNT +" REAL NOT NULL) ;";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS'"+ BlankContact.BlankEnter.BORROWER_TABLE_NAME +"';";

    public Borrowers_Dd(Context context) {
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
         public Cursor readAllData(){
        String query="SELECT * FROM " + BlankContact.BlankEnter.BORROWER_TABLE_NAME;
        SQLiteDatabase DB= this.getReadableDatabase();
        Cursor cursor=null;
        if(DB!=null){
            cursor=DB.rawQuery(query,null);

        }
        return cursor;
        }

}


