package com.example.final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.final_project.ui.customNotificationBox.CustomNotificationView;
import com.example.final_project.ui.home.HomeFragment;
import com.example.final_project.ui.payments.PaymentsFragment;
import com.example.final_project.ui.settings.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RootActivity extends AppCompatActivity {
private BottomNavigationView bottomNavigationView;
private FrameLayout frameLayout;
private HomeFragment homeFragment;
private CustomNotificationView notification;
private PaymentsFragment paymentsFragment;
private SettingsFragment settingsFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_activity);
        bottomNavigationView=findViewById(R.id.navigation_view);
        frameLayout=findViewById(R.id.main_frame);
        homeFragment=new HomeFragment();
        notification = new CustomNotificationView(this,this);
        setFragment(homeFragment);
        paymentsFragment=new PaymentsFragment();
        settingsFragment=new SettingsFragment(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                notification.getNotificationBox();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}