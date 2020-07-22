package com.example.final_project.firebaseConnection;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.final_project.Database.useradate.UserDatadbProvider;
import com.example.final_project.R;
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
import java.util.Objects;

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

    public void setData(UserData data){
        myRef.child("/member");
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

    public void uploadImageToStorage(String location, String imageName, byte[] imageUrl, AppCompatActivity view){
        Log.e(TAG,location+" "+imageName+" "+imageUrl);
        ProgressBar n = new ProgressBar(view.getApplication());
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
                    }).addOnProgressListener(view, taskSnapshot -> {
                n.setVisibility(View.VISIBLE);
                int progress = (int) (100.0 * taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                n.setProgress(progress);
            });
        else{
            Log.e(TAG,"Url is empty");
        }
    }

    @SuppressLint("ShowToast")
    public void uploadImageToStorage(String location, String imageName, Uri imageUrl, AppCompatActivity view){
        StorageReference riversRef = mStorageRef.child(location+"/"+imageName);
        ProgressBar n = new ProgressBar(view.getApplication());
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
                    n.setVisibility(View.VISIBLE);
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

                Log.e(TAG, "download image url : " + downloadImageUri.toString()+"\n path : "+uri.getPath()+"\n LPS : "+uri.getLastPathSegment());
                }
        }).addOnFailureListener(exception -> {
            Log.e(TAG,"Can't able to find the photo");
        });
    }

    /**
     * <p>return the url od the image of given location,name</p>
     * @param location ,where image has to be stored in the database
     * @param imageName name of the image
     * */
    public void downloadProfileImage(String location, String imageName, ImageView imageView, Context context) {
        StorageReference riversRef = mStorageRef.child(location+"/"+imageName);
        ProgressDialog n = new ProgressDialog(context);
        n.setTitle("Loading");
        n.setMessage("Loading your image");
        n.show();
        riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()  {
            // Got the download URL for 'users/me/profile.png'
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(context.getApplicationContext())
                        .load(uri)
                        .placeholder(R.drawable.account_pic)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                n.dismiss();
                                return false;
                            }
                        })
                        .into(imageView);
               /* Log.e(TAG, "download image url : " + Objects.requireNonNull(downloadImageUri.toString())+
                        "\n path : " + Objects.requireNonNull(uri.getPath())+
                        "\n LPS : " + Objects.requireNonNull(uri.getLastPathSegment()));*/
                }
        }).addOnFailureListener(exception -> {
            Log.e(TAG,"Can't able to find the photo");
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
