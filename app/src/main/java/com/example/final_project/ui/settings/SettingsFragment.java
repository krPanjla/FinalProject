package com.example.final_project.ui.settings;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceFragmentCompat;

import com.example.final_project.Database.useradate.UserDatadbHelper;
import com.example.final_project.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

public class SettingsFragment extends Fragment {

    private AppCompatActivity This;
    private TextView sign_out;
    public SettingsFragment(AppCompatActivity activity){
        This = activity;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.fragment_settings, container, false);
        sign_out = view.findViewById(R.id.signout);
        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance()
                        .signOut(container.getContext())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                Snackbar.make(view,"Delete all user info from device?",Snackbar.LENGTH_INDEFINITE)
                                       .setAction("no", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                This.finish();
                                            }
                                        })
                                        .setAction("yes", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        SQLiteDatabase sqLiteDatabase = new UserDatadbHelper(getContext()).getWritableDatabase();
                                        sqLiteDatabase.execSQL(UserDatadbHelper.DROP_LOGIN_TABLE);
                                        sqLiteDatabase.execSQL(UserDatadbHelper.CREATE_LOGIN_USER);
                                        This.finish();
                                    }
                                }).show();
                            }
                        });
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

}