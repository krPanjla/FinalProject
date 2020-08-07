package com.example.final_project.ui.customNotificationBox;

import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.final_project.R;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CustomNotificationView {
    private static final String TAG = "CustomNotificationView";
    private Activity application;
    public CustomNotificationView(Activity activity){
        this.application= activity;
    }

    public void getNotificationBox(ArrayList<NotificationData> notificationDataList){

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(application);
        builderSingle.setTitle("Notification");


        NotificationAdapter arrayAdapter = new NotificationAdapter(application,notificationDataList);


        builderSingle.setNegativeButton("cancel", (dialog, which) -> dialog.dismiss());

        Log.e(TAG,"Total notification : "+arrayAdapter.getCount());
        if(arrayAdapter.getCount() != 0){
            Log.e(TAG,"Notification Adapter : "+arrayAdapter.getCount());
            builderSingle.setAdapter(arrayAdapter, (dialog, which) -> {});
        }
        Log.e(TAG,"showing the data");
        builderSingle.show();

    }

}
