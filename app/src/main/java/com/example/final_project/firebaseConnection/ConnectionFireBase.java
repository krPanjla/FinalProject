package com.example.final_project.firebaseConnection;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import static android.content.ContentValues.TAG;

public class ConnectionFireBase {

    private FirebaseDatabase database;
    public DatabaseReference myRef;
    public ConnectionFireBase(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("member");

        myRef.setValue("This is root node value").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    //Task was successful, data written!
                    Log.e(TAG, "Data Saved" + Objects.requireNonNull(task.getException()).getLocalizedMessage() );

                }else{
                    //Log the error message
                    Log.e(TAG, "onComplete: ERROR: " + task.getException().getLocalizedMessage() );
                }

            }
        });

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }



}
