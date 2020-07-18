package com.example.final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RootActivity extends AppCompatActivity {
private BottomNavigationView bottomNavigationView;
private FrameLayout frameLayout;
private HomeFragment homeFragment;
private PaymentsFragment paymentsFragment;
private SettingsFragment settingsFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_activity);
        bottomNavigationView=findViewById(R.id.navigation_view);
        frameLayout=findViewById(R.id.main_frame);
        homeFragment=new HomeFragment();
        setFragment(homeFragment);
        paymentsFragment=new PaymentsFragment();
        settingsFragment=new SettingsFragment();
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
}