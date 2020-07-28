package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.final_project.Database.BlankContract;
import com.example.final_project.Database.BorrowersDB.Home_DataContact;
import com.example.final_project.Database.DatabaseHelper;
import com.example.final_project.Database.useradate.UserDatadbProvider;
import com.example.final_project.firebaseConnection.ConnectionFireBase;

import static android.content.ContentValues.TAG;

public class add_borrower extends AppCompatActivity {
    EditText id,email,amount;
    Button done;
    private String i,e;
    private float a;
    private final DatabaseHelper mdbHelper = new DatabaseHelper(this);
    //TODO Read below line
    //Required sigaction, We can create this requester(add_borrow) Activity as dialog box in the HomeFragment to make application more user friendly?
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

            else{
                Toast.makeText(add_borrower.this, "pehli fursat m nikal", Toast.LENGTH_SHORT).show();
            }
            this.finish();
            this.finishActivity(0);
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
        ConnectionFireBase connection = new ConnectionFireBase();
        try {
            sqLiteDatabase = mdbHelper.getWritableDatabase();
            values.put(BlankContract.BlankEnter._ID,i);
            String userEmail = i;
            StringBuilder l= new StringBuilder();
            for(int i =0 ; i<userEmail.length() ; i++){
                if(userEmail.charAt(i)!='.' && userEmail.charAt(i)!='#' && userEmail.charAt(i)!='$' && userEmail.charAt(i)!='[' && userEmail.charAt(i)!=']')
                    l.append(userEmail.charAt(i));
            }
            values.put(BlankContract.BlankEnter.COLUMNS_BORROWER_NAME,connection.getBorrowDbProvider(e,this.getApplicationContext()).getName());
            Log.e(TAG,"Name : "+connection.getBorrowDbProvider(e,this.getApplicationContext()));
            values.put(BlankContract.BlankEnter.COLUMNS_BORROWER_DATE,e);
            values.put(BlankContract.BlankEnter.COLUMNS_BORROWER_FLAG,Boolean.FALSE);
            values.put(BlankContract.BlankEnter.COLUMNS_BORROWER_AMOUNT,a);
            Log.e(TAG,i+"  "+e+"  "+a);
        }catch(Exception e){
            Log.e(TAG,"In side Exception "+e);
        }
        long result = -1;
        if(sqLiteDatabase != null) result = sqLiteDatabase.insert(BlankContract.BlankEnter.BORROWER_TABLE_NAME, null,values);
        Log.e(TAG,result+"@");
        if(result != -1){
            //Adding the notification in the firebase
            ConnectionFireBase connectionFireBase = new ConnectionFireBase();
            Home_DataContact contact = new Home_DataContact(getApplicationContext());
            UserDatadbProvider provider = new UserDatadbProvider(getApplicationContext());
            contact.setId(provider.getEmail());
            String userEmail = i;
            StringBuilder l= new StringBuilder();
            for(int i =0 ; i<userEmail.length() ; i++){
                if(userEmail.charAt(i)!='.' && userEmail.charAt(i)!='#' && userEmail.charAt(i)!='$' && userEmail.charAt(i)!='[' && userEmail.charAt(i)!=']')
                    l.append(userEmail.charAt(i));
            }
            contact.setDate(e);
            contact.setAmount(a);
            contact.setName(connection.getBorrowDbProvider(e,this.getApplicationContext()).getName());
            Log.e(TAG,connection.getBorrowDbProvider(e,this.getApplicationContext()).getName());
            contact.setImageUrl("prof_image/"+l);
            contact.setPayed(Boolean.FALSE+"");
            connectionFireBase.pushNotification(contact,i);
        }
        return result != -1;

    }

}
