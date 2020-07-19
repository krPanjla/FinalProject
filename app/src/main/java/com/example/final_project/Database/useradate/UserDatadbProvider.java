package com.example.final_project.Database.useradate;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class UserDatadbProvider {

    SQLiteDatabase read;
    SQLiteDatabase write;
    /**
     * this user to read the data from the Database
     * @param userDatadbHelper use this to get the current location in the data
     * */
    public UserDatadbProvider(UserDatadbHelper userDatadbHelper){
        read = userDatadbHelper.getReadableDatabase();
    }
    public int getCount(){
        Cursor cursor = read.query(BlankContract.BlankEnter.LOGIN_TABLE_NAME, null, null, null, null, null, null);

        try{
            return cursor.getCount();
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            cursor.close();
        }
        return Integer.parseInt(null);
    }

    public String getValueString(String obj){
        Cursor cursor = read.query(BlankContract.BlankEnter.LOGIN_TABLE_NAME , null,null,null,null,null,null);;
        int CURSOR_FLAG = cursor.getColumnIndex(obj);
        try {
            if (cursor != null && cursor.moveToFirst()) {
                return cursor.getString(CURSOR_FLAG);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            cursor.close();
        }
        return null;
    }

    public int getValueInt(String obj){
        Cursor cursor = read.query(BlankContract.BlankEnter.LOGIN_TABLE_NAME, null, null, null, null, null, null);

        try{
            return cursor.getInt(cursor.getColumnIndex(obj));
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            cursor.close();
        }
        return Integer.parseInt(null);
    }

    public byte[] setImage(String obj){
        Cursor cursor = read.query(BlankContract.BlankEnter.LOGIN_TABLE_NAME, null, null, null, null, null, null);

        try{
            return cursor.getBlob(cursor.getColumnIndex(obj));
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            cursor.close();
        }
        //return Integer.parseInt(null);
        return new byte[0];
    }

}
