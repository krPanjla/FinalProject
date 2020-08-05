package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.final_project.Database.useradate.UserDatadbProvider;
import com.example.final_project.firebaseConnection.UserNotification;
import com.example.final_project.ui.customNotificationBox.CustomNotificationView;
import com.example.final_project.ui.customNotificationBox.NotificationData;
import com.example.final_project.ui.home.HomeFragment;
import com.example.final_project.ui.payments.PaymentsFragment;
import com.example.final_project.ui.settings.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class RootActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private HomeFragment homeFragment;
    private CustomNotificationView notification;
    private PaymentsFragment paymentsFragment;
    private SettingsFragment settingsFragment;
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
        //TODO Get the notification from firebase NotificationData
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        StringBuilder email = new StringBuilder();
        String s= new UserDatadbProvider(this.getApplicationContext()).getEmail();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!='.' && s.charAt(i)!='#' && s.charAt(i)!='$' && s.charAt(i)!='[' && s.charAt(i)!=']')
                email.append(s.charAt(i));
        }
        DatabaseReference ref = database.getReference("Member/"+email+"/Notification");

        // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                NotificationData post = dataSnapshot.getValue(NotificationData.class);

                Log.e(TAG,"Unique key :");
                notificationDataList.add(post);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
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

    }
    void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId() == R.id.action_settings) {
            notification.getNotificationBox(notificationDataList);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}