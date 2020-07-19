package com.example.final_project.Daatabase.BorrowersDB;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.final_project.Contact;
import com.example.final_project.R;

public class add_borrower extends AppCompatActivity {
    Borrowers_Dd borrowers_Dd;
    EditText id,email,ammount;
    Button done;
    Contact contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_borrower_activity);

        id=findViewById(R.id.enterid);
        email=findViewById(R.id.entermail);
        ammount=findViewById(R.id.enterammount);
        borrowers_Dd=new Borrowers_Dd(this);
        done=findViewById(R.id.button_done);
      done.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              //fetching values from edit texts
              String i = id.getText().toString();
              String e = email.getText().toString();
              String a = ammount.getText().toString();
              Toast.makeText(add_borrower.this, ""+i+e+a, Toast.LENGTH_SHORT).show();

              //contact is a getter_setter class
            Contact contact=new Contact();
              contact.setId(i);
              contact.setMail(e);
              contact.setAmount(a);

            //save contact is a method in database class which inserts the values to the table
             long rowCount= borrowers_Dd.saveContact(contact);
             if(rowCount > 0){
                 Toast.makeText(add_borrower.this, "inserted", Toast.LENGTH_SHORT).show();
             }
             else {
                 Toast.makeText(add_borrower.this, "pehli fursat m nikal", Toast.LENGTH_SHORT).show();
             }

          }
      });


    }



}
