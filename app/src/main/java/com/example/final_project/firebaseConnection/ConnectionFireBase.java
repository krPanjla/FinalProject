package com.example.final_project.firebaseConnection;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

import static android.content.ContentValues.TAG;

public class ConnectionFireBase {

    private FirebaseDatabase database;
    public DatabaseReference myRef;
    private StorageReference mStorageRef;
    public Uri downloadImageUri;
    public ConnectionFireBase(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("user_prof");
        mStorageRef = FirebaseStorage.getInstance().getReference();

        /*myRef.setValue("This is root node value").addOnCompleteListener(new OnCompleteListener<Void>() {
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
        });*/
    }

    public void setName(UserData data){
        myRef.child("member");
        myRef.push().setValue(data);
        Log.e(TAG,"Data Set to database");
    }

    public void uploadImageToStorage(String location, String imageName, Uri imageUrl){
        Log.e(TAG,location+" "+imageName+" "+imageUrl);
        StorageReference riversRef = mStorageRef.child(location+"/"+imageName);
        if(imageUrl != null)
        riversRef.putFile(imageUrl)
                .addOnSuccessListener(taskSnapshot -> {
                    // Get a URL to the uploaded content
                    downloadImageUri = taskSnapshot.getUploadSessionUri();
                    Log.e(TAG,"image uploaded");
                })
                .addOnFailureListener(exception -> {
                    Log.e(TAG,"Cant upload file");
                    downloadImageUri = null;
                });
        else{
            Log.e(TAG,"Url is empty");
        }
    }

    public void uploadImageToStorage(String location, String imageName, byte[] imageUrl){
        Log.e(TAG,location+" "+imageName+" "+imageUrl);
        StorageReference riversRef = mStorageRef.child(location+"/"+imageName);
        if(imageUrl != null)
            riversRef.putBytes(imageUrl)
                    .addOnSuccessListener(taskSnapshot -> {
                        // Get a URL to the uploaded content
                        downloadImageUri = taskSnapshot.getUploadSessionUri();
                        Log.e(TAG,"image uploaded");
                    })
                    .addOnFailureListener(exception -> {
                        Log.e(TAG,"Cant upload file");
                        downloadImageUri = null;
                    });
        else{
            Log.e(TAG,"Url is empty");
        }
    }

    public void uploadImageToStorage(String location, String imageName, Uri imageUrl, Activity view){
        StorageReference riversRef = mStorageRef.child(location+"/"+imageName);

        riversRef.putFile(imageUrl)
                .addOnSuccessListener(taskSnapshot -> {
                    // Get a URL to the uploaded content
                    downloadImageUri = taskSnapshot.getUploadSessionUri();
                })
                .addOnFailureListener(exception -> {
                    Log.e(TAG,"Cant upload file");
                    Toast.makeText(view.getApplicationContext(),"Can't able to upload file",Toast.LENGTH_SHORT);
                    downloadImageUri = null;
                }).addOnProgressListener(view, taskSnapshot -> {
                    ProgressBar n = new ProgressBar(view.getApplication());
                    int progress = (int) (100.0 * taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                    n.setProgress(progress);
                });
    }
    /**
     * <p>return the url od the image of given location,name</p>
     * @param location ,where image has to be stored in the database
     * @param imageName name of the image
     * */
    public void downloadImage(String location, String imageName) {
        StorageReference riversRef = mStorageRef.child(location+"/"+imageName);
        riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()  {
            // Got the download URL for 'users/me/profile.png'
            @Override
            public void onSuccess(Uri uri) {
                downloadImageUri = uri;

                Log.e(TAG, "download image url : " + downloadImageUri.toString());
                }
        }).addOnFailureListener(exception -> {
            // Handle any errors
        });
    }

    public String getEmail(){
        final String[] value = new String[1];
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                value[0] = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value[0]);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        return value[0];
    }

}
