package com.example.final_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.example.final_project.CheckService.Formate;
import com.example.final_project.CheckService.GoogleServices;
import com.example.final_project.Database.useradate.UserDatadbProvider;
import com.example.final_project.ui.customNotificationBox.NotificationData;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.OAuthProvider;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class SplashActivity extends AppCompatActivity {

    /*This a splash screen which is going to show at the stating*/

    /**
     * @Variable provides ,Is use to store the values of all the authentication which are
     * going to add in this application.
     *
     * @Variable RC_SIGN_IN ,Is use the store the value for Return Statement for Sing in methods
     *
     * @Variable mFirebaseAuth ,Is use to get the state of the app
     *
     * @Variable mAuthStateListener ,Is use to the listen the state of the user sign_in or sign_out
     * **/

    public final static int RC_SIGN_IN = 7;
    private List<AuthUI.IdpConfig> providers;
    private boolean flag =true;
    private String TAG = "SplashScreen";
    private ArrayList<NotificationData> notificationDataList = new ArrayList<>();
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private Object View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Checking Dark theme

        int currentNightMode = /*configuration.uiMode &*/ Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                // Night mode is not active, we're using the light theme
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                // Night mode is active, we're using dark theme
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
        }

        //Animation for splash text
        Animation animationUtils = AnimationUtils.loadAnimation(this,R.anim.anim);

        /**
         * @Decleartion provides, Here we are going to make permissions of Email,Phone and Gmail.
         * @Initialize , The mFirebaseAuth.
         * **/
        mFirebaseAuth = FirebaseAuth.getInstance();
        //Init the AuthProvide for twitter
        OAuthProvider.Builder provider = OAuthProvider.newBuilder("https://projectandroid-8505b.firebaseapp.com/__/auth/handler");

        // Target specific email with login hint.
        provider.addCustomParameter("lang", "en");


        //Anonymous user are only for development purpose on release it get removed
        providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.EmailBuilder().build());

        /**
         * @Variable actionCodeSetting ,Is use to store the values of the email verification code generated by firebase
         **/
        /*ActionCodeSettings actionCodeSettings = ActionCodeSettings.newBuilder()
                .setAndroidPackageName(/* yourPackageName= */// , /* installIfNotAvailable= */ true,
        /* minimumVersion= */ //null)
      /*  .setHandleCodeInApp(true) // This must be set to true
                .setUrl("https://google.com") // This URL needs to be whitelisted
                .build();*/


        //Checking for google play services
        if (!GoogleServices.checkGooglePlayServices(this)) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("No Google Play Services");
            alertDialog.setMessage("This app requires Google Play Services to be installed and enabled");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    (dialog, which) -> {
                        dialog.dismiss();
                        finish();
                        System.exit(0);
                    });
            alertDialog.show();

        }else{
            mAuthStateListener = firebaseAuth -> {
                //@Variable user, Is store the user currently sign in ,if no user logged in it show null
                FirebaseUser user = firebaseAuth.getCurrentUser();
                //Checking is the user is logged in
                if(user != null){
                    //Toast.makeText(getApplicationContext(),"You are Signed in, Welcome to Project Android",Toast.LENGTH_LONG).show();
                   if(flag){
                       if(new UserDatadbProvider(this.getApplicationContext()).getCount() != 0){
                           FirebaseDatabase.getInstance().getReference("Member/"+
                                   Formate.toUsername(new UserDatadbProvider(this.getApplicationContext()).getEmail()) +
                                   "/Notification")
                                   .addChildEventListener(new ChildEventListener() {

                               /**
                                * This method is triggered when a new child is added to the location to which this listener was
                                * added.
                                *
                                * Fetch the data from the Notification from firebase and put it into the NotificationDialog box
                                *
                                * @param dataSnapshot          An immutable snapshot of the data at the new child location
                                * @param previousChildName The key name of sibling location ordered before the new child. This
                                */
                               @Override
                               public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                                   Log.e(TAG,"Data in json : "+dataSnapshot);
                                   NotificationData post = dataSnapshot.getValue(NotificationData.class);
                                   if(dataSnapshot.getChildrenCount()!=0) {
                                       assert post != null;
                                       Log.e(TAG,"Name : " + post.getName());
                                       Log.e(TAG,"Id : " + post.getId());
                                       notificationDataList.add(post);
                                   }
                               }

                               /**
                                * This method is triggered when the data at a child location has changed.
                                *
                                * @param snapshot          An immutable snapshot of the data at the new data at the child location
                                * @param previousChildName The key name of sibling location ordered before the child. This will
                                */
                               @Override
                               public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                               }

                               /**
                                * This method is triggered when a child is removed from the location to which this listener was
                                * added.
                                *
                                * @param snapshot An immutable snapshot of the data at the child that was removed.
                                */
                               @Override
                               public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                               }

                               /**
                                * This method is triggered when a child location's priority changes. See {@link
                                * DatabaseReference#setPriority(Object)} and <a
                                * href="https://firebase.google.com/docs/database/android/retrieve-data#data_order"
                                * target="_blank">Ordered Data</a> for more information on priorities and ordering data.
                                *
                                * @param snapshot          An immutable snapshot of the data at the location that moved.
                                * @param previousChildName The key name of the sibling location ordered before the child
                                */
                               @Override
                               public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                               }

                               @Override
                               public void onCancelled(@NonNull DatabaseError databaseError) {
                                   Log.e(TAG,"The read failed: " + databaseError.getCode());
                               }
                           });
                          new Handler().postDelayed(()->{
                                      startActivity(new Intent(SplashActivity.this, RootActivity.class)
                                              .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                      this.finish();
                           },
                           1000);

                       }else{
                           new Handler().postDelayed(
                                   ()->{
                                       AuthUI.getInstance().signOut(getApplicationContext());
                                       finish();
                                       overridePendingTransition(0, 0);
                                       startActivity(getIntent());
                                       overridePendingTransition(0, 0);
                                   },
                                   1000);
                       }
                   }

                }else{
                    //Else statement run if the use is not sign in

                    //making User entering first time

                    flag =false;

                    // Create and launch sign-in intent
                    //for lambda function ()-> use java version 1.8+ low java version do't support lambda function
                    new Handler().postDelayed(()->{startActivityForResult(AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setLogo(R.mipmap.ic_launcher_round)
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(providers)
                                    .build(),
                            RC_SIGN_IN);},
                            1000);
                }
            };
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null) {
                 //Send the Extra With intent
                    startActivity(new Intent(this, UserNameImageActivity.class)
                            .putExtra("id",user.getUid())
                            .putExtra("Email", user.getEmail())
                            .putExtra("name",user.getDisplayName())
                            .putExtra("image",user.getPhotoUrl())
                            .putExtra("phone",user.getPhoneNumber())
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    this.finish();

                    Toast.makeText(getApplicationContext(), "Your are Logged in " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
                    this.finish();
                }
            } else {
                Toast.makeText(getApplicationContext(),"Sorry you aren't able to make it",Toast.LENGTH_SHORT).show();
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        //Removing the Authentication Listener when app get pause
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }

    /**
     * Dispatch onResume() to fragments.  Note that for better inter-operation
     * with older versions of the platform, at the point of this call the
     * fragments attached to the activity are <em>not</em> resumed.
     */
    @Override
    protected void onResume() {
        super.onResume();
        //Adding the Authentication Listener when app get Resume
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}
