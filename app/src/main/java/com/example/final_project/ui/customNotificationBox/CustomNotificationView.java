package com.example.final_project.ui.customNotificationBox;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.final_project.R;
import com.example.final_project.RootActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CustomNotificationView {

    private static final String TAG = "CustomNotificationView";
    private Activity view;
    private AppCompatActivity application;

    public CustomNotificationView(AppCompatActivity application, Activity activity){
        this.application = application;
        this.view = activity;
    }

    public void getNotificationBox(){
        View v = LayoutInflater.from(application.getApplicationContext()).inflate(R.layout.notifications,null);
        String[] StringArray = new String[1];
        StringArray[0] = "No Notification";
        ArrayAdapter simpleAdapter = new ArrayAdapter<String>(v.getContext(),R.layout.notifications,StringArray);
        ArrayList<NotificationData> notificationDataList = new ArrayList<>();

        notificationDataList.add(new NotificationData("http://footballplayerpro.com/wp-content/uploads/2016/10/Lionel-Messi-Profile-Biography.png","Leon messi","2000","06/08/2020"));
        NotificationAdapter adapter = new NotificationAdapter(v.getContext(),notificationDataList);

        AlertDialog n = new AlertDialog.Builder(view).create();
        //AlertDialog.Builder dialog = new  AlertDialog.Builder(application.getApplicationContext());

        ListView list = application.findViewById(R.id.notification_list);

        if (adapter == null) {
            list.setAdapter(adapter);
        }else{
            list.setAdapter(simpleAdapter);
            Log.e(TAG,"Null Adapter");
        }


        n.setView(v);
        n.show();
        //AlertDialog alertDialog = dialog.create();
        //alertDialog.show();
    }

}
