package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.final_project.Database.BorrowersDB.BlankContact;
import com.example.final_project.Database.BorrowersDB.Borrowers_Dd;
import com.example.final_project.Database.BorrowersDB.DataContact;
import com.example.final_project.firebaseConnection.ConnectionFireBase;
import com.example.final_project.ui.home.HomeFragment;

import static android.content.ContentValues.TAG;

public class add_borrower extends AppCompatActivity {
    EditText id,email,amount;
    Button done;
    private String i,e;
    private float a;
    private final Borrowers_Dd mdbHelper = new Borrowers_Dd(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_borrower_activity);

        id=findViewById(R.id.enter_id);
        email=findViewById(R.id.enter_mail);
        amount=findViewById(R.id.enter_amount);
        done=findViewById(R.id.button_done);

        done.setOnClickListener(v -> {

            if(!id.getText().toString().trim().isEmpty() && !amount.getText().toString().trim().isEmpty() || !email.getText().toString().trim().isEmpty()  ) {
                //fetching values from edit texts
                Log.e(TAG,"data check ok in add_borrower");
                i = id.getText().toString().trim();
                e = email.getText().toString().trim();
                a = Float.parseFloat(amount.getText().toString().trim());

                //save dataContact is a method in database class which inserts the values to the table

                boolean rowCount = saveContact();

            if(rowCount) {
                Toast.makeText(add_borrower.this, "inserted", Toast.LENGTH_SHORT).show();
            }

            else
               //#Pro line Bro :) :)
               Toast.makeText(add_borrower.this, "pehli fursat m nikal", Toast.LENGTH_SHORT).show();
            add_borrower.this.finishActivity(0);
            add_borrower.this.finish();
            }else{
                if(amount.getText().toString().trim().isEmpty())
                    Toast.makeText(getApplicationContext(),"You can't left amount empty",Toast.LENGTH_SHORT).show();

                if(id.getText().toString().trim().isEmpty())
                    Toast.makeText(getApplicationContext(),"You can't left id empty",Toast.LENGTH_SHORT).show();
            }
        });


    }


    private boolean saveContact(){

        SQLiteDatabase sqLiteDatabase = null;
        ContentValues values = new ContentValues();
        try {
            sqLiteDatabase = mdbHelper.getWritableDatabase();
            values.put(BlankContact.BlankEnter.COLUMNS_BORROWER_NAME,i);
            values.put(BlankContact.BlankEnter.COLUMNS_BORROWER_DATE,e);
            values.put(BlankContact.BlankEnter.COLUMNS_BORROWER_FLAG,Boolean.FALSE);
            values.put(BlankContact.BlankEnter.COLUMNS_BORROWER_AMOUNT,a);
            Log.e(TAG,i+"  "+e+"  "+a);
        }catch(Exception ignored){
            Log.e(TAG,"In side Exception "+ignored);
        }
        long result = -1;
        if(sqLiteDatabase != null) result = sqLiteDatabase.insert(BlankContact.BlankEnter.BORROWER_TABLE_NAME, null,values);
        Log.e(TAG,result+"@");
        if(result != -1){
            ConnectionFireBase connectionFireBase = new ConnectionFireBase();
            DataContact contact = new DataContact(getApplicationContext());
            contact.setId(i);
            contact.setDate(e);
            contact.setAmount(a);
            contact.setImageUrl("prof+image/"+i);
            contact.setPayed(Boolean.FALSE+"");
            connectionFireBase.pushNotification(contact,i);

        }
        return result != -1;

    }

}
