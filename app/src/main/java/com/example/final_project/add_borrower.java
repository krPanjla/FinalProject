package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.final_project.Daatabase.BorrowersDB.Borrowers_Dd;

public class add_borrower extends AppCompatActivity {
    Borrowers_Dd borrowers_db;
    EditText id,email,ammount;
    Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_borrower_activity);
        borrowers_db=new Borrowers_Dd(this);
        id=findViewById(R.id.enterid);
        email=findViewById(R.id.entermail);
        ammount=findViewById(R.id.enterammount);
        done=findViewById(R.id.button_done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){

                }
            }
        });


    }

    private void addContact(){

    }

}
