package com.example.final_project.Database.BorrowersDB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.final_project.Database.BlankContract;
import com.example.final_project.Database.DatabaseHelper;

public class BorrowersDbProvider {
    private static final String TAG = "BorrowersDbProvider";
    private SQLiteDatabase read;
    private SQLiteDatabase write;
    private String id;
    private String name;
    private String date;
    private String imageUrl;
    private String payed;
    private double amount;
    private int count;
    public BorrowersDbProvider(Context context){
        SQLiteOpenHelper dbHelper;
        read = new DatabaseHelper(context).getReadableDatabase();
        write = new DatabaseHelper(context).getWritableDatabase();
    }

    public String getId() {
        try(Cursor cursor = read.query(BlankContract.BlankEnter.BORROWER_TABLE_NAME, new String[]{BlankContract.BlankEnter._ID}, null, null, null, null, null)) {

            if (cursor != null) {
                cursor.moveToFirst();
                String s = cursor.getString(0);
                Log.e(TAG, "Id : " + s);
                return s;
            } else {
                Log.e(TAG, "cant find the id");
                return null;
            }
        }
    }

    public String getName() {
            try(Cursor cursor = read.query(BlankContract.BlankEnter.BORROWER_TABLE_NAME, new String[]{BlankContract.BlankEnter.COLUMNS_BORROWER_NAME}, null, null, null, null, null)) {

                if (cursor != null) {
                    cursor.moveToFirst();
                    String s = cursor.getString(0);
                    Log.e(TAG, "Id : " + s);
                    return s;
                } else {
                    Log.e(TAG, "cant find the id");
                    return null;
                }
            }
    }

    public String getDate() {
        try(Cursor cursor = read.query(BlankContract.BlankEnter.BORROWER_TABLE_NAME, new String[]{BlankContract.BlankEnter._ID}, null, null, null, null, null)) {

            if (cursor != null) {
                cursor.moveToFirst();
                String s = cursor.getString(0);
                Log.e(TAG, "Id : " + s);
                return s;
            } else {
                Log.e(TAG, "cant find the id");
                return null;
            }
        }
    }

    public String getImageUrl() {
        try(Cursor cursor = read.query(BlankContract.BlankEnter.BORROWER_TABLE_NAME, new String[]{BlankContract.BlankEnter._ID}, null, null, null, null, null)) {

            if (cursor != null) {
                cursor.moveToFirst();
                String s = cursor.getString(0);
                Log.e(TAG, "Id : " + s);
                return s;
            } else {
                Log.e(TAG, "cant find the id");
                return null;
            }
        }
    }

    public String getPayed() {
        try(Cursor cursor = read.query(BlankContract.BlankEnter.BORROWER_TABLE_NAME, new String[]{BlankContract.BlankEnter._ID}, null, null, null, null, null)) {

            if (cursor != null) {
                cursor.moveToFirst();
                String s = cursor.getString(0);
                Log.e(TAG, "Id : " + s);
                return s;
            } else {
                Log.e(TAG, "cant find the id");
                return null;
            }
        }
    }

    public double getAmount() {
        try(Cursor cursor = read.query(BlankContract.BlankEnter.BORROWER_TABLE_NAME, new String[]{BlankContract.BlankEnter._ID}, null, null, null, null, null)) {

            if (cursor != null) {
                cursor.moveToFirst();
                double s = cursor.getDouble(0);
                Log.e(TAG, "Id : " + s);
                return s;
            } else {
                Log.e(TAG, "cant find the id");
                return -1;
            }
        }
    }

    public int getCount() {
        try(Cursor cursor = read.query(BlankContract.BlankEnter.BORROWER_TABLE_NAME, new String[]{BlankContract.BlankEnter._ID}, null, null, null, null, null)) {

            if (cursor != null) {
                cursor.moveToFirst();
                int s = cursor.getInt(0);
                Log.e(TAG, "Id : " + s);
                return s;
            } else {
                Log.e(TAG, "cant find the id");
                return 0;
            }
        }
    }
}
