package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class UserNameImageActivity extends AppCompatActivity {

    /**
     * @Variable uname ,Is use to store the value of user name enter by user*/
    private EditText uname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_name_image);
        uname = findViewById(R.id.text1);
        //TODO : Add photo picker in on clicking on Image id(imageView)Gallery and Camera both
        //check if the name is empty then can't go to next Activity
        if(!uname.getText().toString().isEmpty())
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserNameImageActivity.this,MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
        else
            Snackbar.make(findViewById(R.id.ll),"Name Can't be empty",Snackbar.LENGTH_LONG)
                    .show();
    }
}