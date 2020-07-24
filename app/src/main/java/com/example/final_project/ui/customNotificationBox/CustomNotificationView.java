package com.example.final_project.ui.customNotificationBox;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.WindowManager;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.final_project.R;

import java.util.ArrayList;

public class CustomNotificationView {

    private static final String TAG = "CustomNotificationView";
    private Activity application;

    public CustomNotificationView(Activity activity){
        this.application= activity;
    }

    public void getNotificationBox(){
        String[] StringArray = new String[]{"No Notification"};
        final ArrayAdapter simpleAdapter = new ArrayAdapter<>(application,R.layout.notifications,StringArray);

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(application);
        //this.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        //builderSingle.setIcon(R.drawable.ic_launcher);
        builderSingle.setTitle("Notification");

        ArrayList<NotificationData> notificationDataList = new ArrayList<>();
        //notificationDataList.add(new NotificationData("http://footballplayerpro.com/wp-content/uploads/2016/10/Lionel-Messi-Profile-Biography.png","Leo messi","2000","06/08/2020"));
        NotificationAdapter arrayAdapter = new NotificationAdapter(application,notificationDataList);


        builderSingle.setNegativeButton("cancel", (dialog, which) -> dialog.dismiss());

        if(arrayAdapter.getCount() == 0)
            builderSingle.setAdapter(arrayAdapter, (dialog, which) -> {});
        else
            builderSingle.setAdapter(simpleAdapter,((dialog, which) -> {}));
        builderSingle.show();

    }

}
