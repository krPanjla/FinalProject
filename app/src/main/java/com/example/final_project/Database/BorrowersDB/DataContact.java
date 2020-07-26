package com.example.final_project.Database.BorrowersDB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.final_project.Database.BorrowersDB.BlankContact;
import com.example.final_project.Database.useradate.BlankContract;
import com.example.final_project.Database.useradate.UserDatadbHelper;

import java.util.ArrayList;

public class DataContact {
    private static final String TAG = "DataContainer";
    protected SQLiteDatabase read ;
    private String id;
    private String name;
    private String date;
    private String imageUrl;
    private String payed;
    private double amount;
    private int count;
    public DataContact(Context context){
    read = new UserDatadbHelper(context).getReadableDatabase();
    }

    public int getCount(){
        try (Cursor cursor = read.query(BlankContract.BlankEnter.LOGIN_TABLE_NAME, null, null, null, null, null, null)) {
            return cursor.getCount();
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

    public static ArrayList<DataContact> createContactsList(Context context) {
        ArrayList<DataContact> mcontacts = new ArrayList<>();
        DataContact contact = new DataContact(context);

        contact.setDate("12/2/1010");
        contact.setAmount(100000.0);
        contact.setImageUrl("https://firebasestorage.googleapis.com/v0/b/projectandroid-8505b.appspot.com/o/prof_image%2Fmokshchoudhary2%40gmail.com?alt=media&token=45bde5a5-18e7-480f-b9ca-1496e39ad4b3");

        contact.setPayed(false+"");
        String[] column = new String[]{BlankContact.BlankEnter.COLUMNS_BORROWER_NAME,BlankContact.BlankEnter.COLUMNS_BORROWER_DATE,BlankContact.BlankEnter.COLUMNS_BORROWER_AMOUNT,BlankContact.BlankEnter.COLUMNS_BORROWER_FLAG};
        try{
            Cursor cursor = contact.read.query(BlankContact.BlankEnter.BORROWER_TABLE_NAME, column, null, null, null, null, null) ;

            if (cursor != null) {
                cursor.moveToFirst();
                //Sending the data to recycleView
                String s = cursor.getString(0);
                contact.setName(s);
                contact.setDate(cursor.getString(1));
                contact.setAmount(cursor.getDouble(2));
                //TODO Set image usr
                StringBuilder l= new StringBuilder();
                for(int i =0 ; i<s.length() ; i++){
                    if(s.charAt(i)!='.' && s.charAt(i)!='#' && s.charAt(i)!='$' && s.charAt(i)!='[' && s.charAt(i)!=']')
                        l.append(s.charAt(i));
                }
                contact.setImageUrl("user_prof/" + l);
                contact.setPayed(cursor.getString(3));
                Log.e(TAG, "name : " + s);
            } else {
                Log.e(TAG, "cant find the Name");
            }
            for (int i = 1; i <= contact.getCount(); i++) {
                mcontacts.add(contact);
            }

            Log.e(TAG,"ADD the data");
            return mcontacts;
        }catch(Exception e){
            Log.e(TAG,e+"");
            return null;
        }


    }
}
