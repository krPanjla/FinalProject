package com.example.final_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.final_project.CheckService.Formate;
import com.example.final_project.Database.BlankContract;
import com.example.final_project.Database.BorrowersDB.Home_DataContact;
import com.example.final_project.Database.DatabaseHelper;
import com.example.final_project.Database.useradate.UserDatadbProvider;
import com.example.final_project.ui.customNotificationBox.CustomNotificationView;
import com.example.final_project.ui.customNotificationBox.NotificationData;
import com.example.final_project.ui.home.HomeFragment;
import com.example.final_project.ui.payments.PaymentsFragment;
import com.example.final_project.ui.settings.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class RootActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private HomeFragment homeFragment;
    private CustomNotificationView notification;
    private PaymentsFragment paymentsFragment;
    private SettingsFragment settingsFragment;
    private Menu mMenu;
    private ArrayList<NotificationData> notificationDataList = new ArrayList<>();
    private String TAG = "RootActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_activity);
        bottomNavigationView=findViewById(R.id.navigation_view);
        frameLayout=findViewById(R.id.main_frame);
        homeFragment=new HomeFragment();
        notification = new CustomNotificationView(this);
        MenuItem notificationItem = findViewById(R.id.action_settings);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        String email= Formate.toUsername(new UserDatadbProvider(this.getApplicationContext()).getEmail());
        DatabaseReference ref = database.getReference("Member/"+email+"/Notification");

        // Attach a listener to read the data at our posts reference
        ref.addChildEventListener(new ChildEventListener() {

            /**
             * This method is triggered when a new child is added to the location to which this listener was
             * added.
             *
             * @param dataSnapshot          An immutable snapshot of the data at the new child location
             * @param previousChildName The key name of sibling location ordered before the new child. This
             */
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                //Log.e(TAG,"Data in json : "+dataSnapshot);
                NotificationData post = dataSnapshot.getValue(NotificationData.class);
                if(dataSnapshot.getChildrenCount()!=0) {
                    assert post != null;
                    //Log.e(TAG,"Name : " + post.getName());
                    //Log.e(TAG,"Id : " + post.getId());
                    notificationDataList.add(post);
                    //TODO change the color of the notification item in the menu Bar to the normal use the notification icon present in the Drawable folder
                }else{
                    //TODO change the color of the notification item in the menu Bar to the red use the notification icon present in the Drawable folder
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
                NotificationData post = snapshot.getValue(NotificationData.class);
                if(snapshot.getChildrenCount()!=0) {
                    assert post != null;
                    //Log.e(TAG,"Name : " + post.getName());
                    //Log.e(TAG,"Id : " + post.getId());
                    notificationDataList.add(post);
                }
            }

            /**
             * This method is triggered when a child is removed from the location to which this listener was
             * added.
             *
             * @param snapshot An immutable snapshot of the data at the child that was removed.
             */
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                //Log.e(TAG,snapshot.toString()+"Remove Data");
                NotificationData post = snapshot.getValue(NotificationData.class);

                notificationDataList.remove(post);
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
        setFragment(homeFragment);
        paymentsFragment=new PaymentsFragment();
        settingsFragment=new SettingsFragment(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.nav_home1:
                    setFragment(homeFragment);
                  break;

                case R.id.due_payments:
                    setFragment(paymentsFragment);
                  break;

                case R.id.settings:
                    setFragment(settingsFragment);
                    break;
            }
            return true;
        });

        //adding data in database form firebase

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member/"+email+"/lends/");
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Home_DataContact post = snapshot.getValue(Home_DataContact.class);
                Log.e(TAG,"POST : "+snapshot.getValue());
                if(post!=null){
                    SQLiteDatabase database1 = new DatabaseHelper(getApplicationContext()).getWritableDatabase();
                    Log.e(TAG,"Email : "+post.getId());
                    Log.e(TAG,"Count : "+post.getCount());
                    Log.e(TAG,"Amount : "+post.getAmount());
                    ContentValues values = new ContentValues();
                    values.put(BlankContract.BlankEnter.COLUMNS_BORROWER_COUNT,post.getCount());
                    values.put(BlankContract.BlankEnter._ID,post.getId());
                    values.put(BlankContract.BlankEnter.COLUMNS_BORROWER_NAME, post.getName());
                    values.put(BlankContract.BlankEnter.COLUMNS_BORROWER_DATE,post.getDate());
                    values.put(BlankContract.BlankEnter.COLUMNS_BORROWER_IMAGE, post.getImageUrl());
                    values.put(BlankContract.BlankEnter.COLUMNS_BORROWER_FLAG,post.getPayed());
                    values.put(BlankContract.BlankEnter.COLUMNS_BORROWER_AMOUNT,post.getAmount());
                    long i = database1.insert(BlankContract.BlankEnter.BORROWER_TABLE_NAME, BlankContract.BlankEnter.COLUMNS_BORROWER_COUNT,values);
                    if(i!=-1){
                        Log.e(TAG,"Inserted");
                        getSupportFragmentManager().beginTransaction().detach(homeFragment).attach(homeFragment).commit();
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                SQLiteDatabase database1 = new DatabaseHelper(getApplicationContext()).getWritableDatabase();
                Home_DataContact post = snapshot.getValue(Home_DataContact.class);
                if(post!=null){
                    boolean i = database1.delete(BlankContract.BlankEnter.BORROWER_TABLE_NAME, BlankContract.BlankEnter.COLUMNS_BORROWER_COUNT+"=?",new String[]{String.valueOf(post.getCount())})>0;
                    if(i){
                        Log.e(TAG,"Delete");
                        getSupportFragmentManager().beginTransaction().detach(homeFragment).attach(homeFragment).commit();
                        Snackbar.make(findViewById(R.id.ll2),post.getName()+" do not accept your request",Snackbar.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        mMenu = menu;
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId() == R.id.action_settings) {
            ArrayList invertList = new ArrayList<NotificationData>();
            //Todo add ; invert of the array list
            notification.getNotificationBox(notificationDataList);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}