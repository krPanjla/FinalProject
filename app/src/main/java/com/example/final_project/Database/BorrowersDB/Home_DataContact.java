package com.example.final_project.Database.BorrowersDB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.final_project.Database.BlankContract;
import com.example.final_project.Database.DatabaseHelper;

import java.util.ArrayList;

public class Home_DataContact {
    private static final String TAG = "DataContainer";
    protected SQLiteDatabase read ;
    private String id;
    private String name;
    private String date;
    private String imageUrl;
    private String payed;
    private double amount;

    public Home_DataContact(Context context){
        DatabaseHelper mdbHelper;
        mdbHelper = new DatabaseHelper(context);
        read = mdbHelper.getReadableDatabase();
    }

    public static int getCount(Context context){
        DatabaseHelper mdbHelper;
        SQLiteDatabase read ;
        mdbHelper = new DatabaseHelper(context);
        read = mdbHelper.getReadableDatabase();
        try (Cursor cursor = read.query(BlankContract.BlankEnter.BORROWER_TABLE_NAME, null, null, null, null, null, null)) {
            Log.e(TAG,"getCount : "+cursor.getCount());
            return Math.max(cursor.getCount(),0);
        }
    }
    public int getCount(){
        try (Cursor cursor = read.query(BlankContract.BlankEnter.BORROWER_TABLE_NAME, null, null, null, null, null, null)) {
            if(cursor.getCount() == -1) Log.e(TAG,"getCount : "+cursor.getCount());
            return Math.max(cursor.getCount(),0);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPayed() {
        return payed;
    }

    public void setPayed(String payed) {
        this.payed = payed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    private static int lastContactId = 0;

    public static ArrayList<Home_DataContact> createContactsList(Context context) {
        ArrayList<Home_DataContact> mContacts = new ArrayList<>();
        SQLiteDatabase read = new DatabaseHelper(context).getReadableDatabase();
        Cursor cursor = read.query(BlankContract.BlankEnter.BORROWER_TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        do{
            Home_DataContact contact = new Home_DataContact(context);
            Log.e(TAG,"Putting : "+cursor.getCount());
            String email = cursor.getString(0);
            contact.setId(email);
            contact.setName(cursor.getString(1));
            contact.setDate(cursor.getString(2));
            contact.setImageUrl(cursor.getString(3));
            contact.setPayed(cursor.getString(4));
            contact.setAmount(cursor.getDouble(5));
            mContacts.add(contact);
        }while(cursor.moveToNext());
        cursor.close();
        return mContacts;
    }
}
