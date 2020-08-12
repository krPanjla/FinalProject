package com.example.final_project.Database.BorrowersDB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.final_project.CheckService.Formate;
import com.example.final_project.Database.BlankContract;
import com.example.final_project.Database.DatabaseHelper;
import com.example.final_project.Database.useradate.UserDatadbProvider;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Home_DataContact {
    private static final String TAG = "DataContainer";
    protected SQLiteDatabase read ;
    private String id;
    private String name;
    private String date;
    private String imageUrl;
    private boolean payed;
    private long count;
    private double amount;

    public Home_DataContact(){
        //For Firebase use
    }

    public Home_DataContact(Context context){
        DatabaseHelper mdbHelper;
        mdbHelper = new DatabaseHelper(context);
        read = mdbHelper.getReadableDatabase();
    }

    public static int Count(Context context){
        DatabaseHelper mdbHelper;
        SQLiteDatabase read ;
        mdbHelper = new DatabaseHelper(context);
        read = mdbHelper.getReadableDatabase();
        try (Cursor cursor = read.query(BlankContract.BlankEnter.BORROWER_TABLE_NAME, null, null, null, null, null, null)) {
            Log.e(TAG,"getCount : "+cursor.getCount());
            return Math.max(cursor.getCount(),0);
        }
    }

    public void setCount(long count) {
        this.count = count;
    }
    public long getCount() {
        return this.count;
    }

//    public int Count(){
//        try (Cursor cursor = read.query(BlankContract.BlankEnter.BORROWER_TABLE_NAME, null, null, null, null, null, null)) {
//            if(cursor.getCount() == -1) Log.e(TAG,"getCount : "+cursor.getCount());
//            return Math.max(cursor.getCount(),0);
//        }
//    }
//    public Long getCount(){
//        try (Cursor cursor = read.query(BlankContract.BlankEnter.BORROWER_TABLE_NAME, null, null, null, null, null, null)) {
//            if(cursor.getCount() == -1) Log.e(TAG,"getCount : "+cursor.getCount());
//            return this.count;
//        }
//    }

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

    public boolean getPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
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
            contact.setPayed(Boolean.parseBoolean(cursor.getString(4)));
            contact.setAmount(cursor.getDouble(6));
            mContacts.add(contact);
        }while(cursor.moveToNext());
        cursor.close();
        return mContacts;
    }
}
