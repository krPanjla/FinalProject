package com.example.final_project.Database.useradate;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class UserDatadbProvider {

    private static final String TAG = "UserDatadbProvider";
    SQLiteDatabase read;
    SQLiteDatabase write;
    /**
     * this user to read the data from the Database
     * @param context use this to get the context of the activity
     * */
    public UserDatadbProvider(Context context){
        read = new UserDatadbHelper(context).getReadableDatabase();
        write = new UserDatadbHelper(context).getWritableDatabase();
    }

    public int getCount(){
        try (Cursor cursor = read.query(BlankContract.BlankEnter.LOGIN_TABLE_NAME, null, null, null, null, null, null)) {
            return cursor.getCount();
        }
    }

    public String getId(){
         try(Cursor cursor = read.query(BlankContract.BlankEnter.LOGIN_TABLE_NAME, new String[]{BlankContract.BlankEnter._ID}, null, null, null, null, null)){

             if(cursor != null){
                 cursor.moveToFirst();
                 String s = cursor.getString(0);
                 Log.e(TAG,"Id : "+s);
                 return s;
             }
             else{
                 Log.e(TAG,"cant find the id");
                 return null;
             }

         }
    }

    public String getEmail(){
        try(Cursor cursor = read.query(BlankContract.BlankEnter.LOGIN_TABLE_NAME, new String[]{BlankContract.BlankEnter.COLUMNS_USER_EMAIL}, null, null, null, null, null)){

            if(cursor != null){
                cursor.moveToFirst();
                String s = cursor.getString(0);
                Log.e(TAG,"email : "+s);
                return s;
            }
            else{
                Log.e(TAG,"cant find the email");
                return null;
            }

        }
    }

    public String getName(){
        try(Cursor cursor = read.query(BlankContract.BlankEnter.LOGIN_TABLE_NAME, new String[]{BlankContract.BlankEnter.COLUMNS_USER_NAME}, null, null, null, null, null)){

            if(cursor != null){
                cursor.moveToFirst();
                String s = cursor.getString(0);
                Log.e(TAG,"name : "+s);
                return s;
            }
            else{
                Log.e(TAG,"cant find the Name");
                return null;
            }

        }
    }

    public String getGender(){
        try(Cursor cursor = read.query(BlankContract.BlankEnter.LOGIN_TABLE_NAME, new String[]{BlankContract.BlankEnter.COLUMNS_USER_GENDER}, null, null, null, null, null)){

            if(cursor != null){
                cursor.moveToFirst();
                String s = cursor.getInt(0)+"";
                Log.e(TAG,"Gender : "+s);
                return s;
            }
            else{
                Log.e(TAG,"cant find the Gender");
                return null;
            }

        }
    }

    public int getPhone(){
        try(Cursor cursor = read.query(BlankContract.BlankEnter.LOGIN_TABLE_NAME, new String[]{BlankContract.BlankEnter.COLUMNS_USER_PHONE}, null, null, null, null, null)){

            if(cursor != null){
                cursor.moveToFirst();
                int s = cursor.getInt(0);
                Log.e(TAG,"Phone : "+s);
                return s;
            }
            else{
                Log.e(TAG,"cant find the phone");
                return 0;
            }

        }
    }

    public String getPassword(){
        try(Cursor cursor = read.query(BlankContract.BlankEnter.LOGIN_TABLE_NAME, new String[]{BlankContract.BlankEnter.COLUMNS_USER_PASSWORD}, null, null, null, null, null)){

            if(cursor != null){
                cursor.moveToFirst();
                String s = cursor.getString(0);
                Log.e(TAG,"Password : "+s);
                return s;
            }
            else{
                Log.e(TAG,"cant find the password");
                return null;
            }

        }
    }

    public String getImage(){
        try(Cursor cursor = read.query(BlankContract.BlankEnter.LOGIN_TABLE_NAME, new String[]{BlankContract.BlankEnter.COLUMNS_USER_IMAGE}, null, null, null, null, null)){

            if(cursor != null){
                cursor.moveToFirst();
                String s = cursor.getString(0);
                Log.e(TAG,"Image url : "+s);
                return s;
            }
            else{
                Log.e(TAG,"cant find the image url");
                return null;
            }

        }
    }

}
