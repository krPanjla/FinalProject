package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.final_project.Database.BorrowersDB.BlankContact;
import com.example.final_project.Database.BorrowersDB.Borrowers_Dd;
import com.example.final_project.Database.BorrowersDB.Contact;
import com.example.final_project.Database.useradate.BlankContract;

import java.io.ByteArrayOutputStream;

import static android.content.ContentValues.TAG;
import static java.sql.Types.FLOAT;

public class add_borrower extends AppCompatActivity {
    EditText id,email,amount;
    Button done;
    private String i,e;
    private float a;
    Contact contact;
    private final Borrowers_Dd mdbHelper = new Borrowers_Dd(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_borrower_activity);

        id=findViewById(R.id.enter_id);
        email=findViewById(R.id.enter_mail);
        amount=findViewById(R.id.enter_amount);
        done=findViewById(R.id.button_done);

        done.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              if(id.getText().toString().isEmpty() && amount.getText().toString().isEmpty() || email.getText().toString().isEmpty()  ) {
                  //fetching values from edit texts
                  i = id.getText().toString().trim();
                  e = email.getText().toString().trim();
                  a = Float.parseFloat(amount.getText().toString().trim());


                  //save contact is a method in database class which inserts the values to the table

                  boolean rowCount = saveContact();

                  if (rowCount) {
                      Toast.makeText(add_borrower.this, "inserted", Toast.LENGTH_SHORT).show();
                  } else
                      //#Pro line Bro :) :)
                      Toast.makeText(add_borrower.this, "pehli fursat m nikal", Toast.LENGTH_SHORT).show();
                  s();
              }else{
                  if(amount.getText().toString().trim().isEmpty())
                      Toast.makeText(getApplicationContext(),"You can't left amount empty",Toast.LENGTH_SHORT).show();

                  if(id.getText().toString().trim().isEmpty())
                      Toast.makeText(getApplicationContext(),"You can't left id empty",Toast.LENGTH_SHORT).show();
              }
          }
      });


    }


    private boolean saveContact(){

        SQLiteDatabase sqLiteDatabase = null;
        ContentValues values = new ContentValues();
        try {
            sqLiteDatabase = mdbHelper.getWritableDatabase();
            values.put(BlankContact.BlankEnter._ID,i);
            values.put(BlankContact.BlankEnter.COLUMNS_BORROWER_DATE,e);
            values.put(BlankContact.BlankEnter.COLUMNS_BORROWER_AMOUNT,a);
            Log.e(TAG,i+"  "+e+"  "+a);
        }catch(Exception ignored){

        }
        long result = -1;
        if(sqLiteDatabase != null) result = sqLiteDatabase.insert(BlankContact.BlankEnter.BORROWER_TABLE_NAME, BlankContact.BlankEnter._ID,values);
        Log.e(TAG,result+"");
        return result != -1;

    }

    private void s(){
        this.finishActivity(0);
    }
}
