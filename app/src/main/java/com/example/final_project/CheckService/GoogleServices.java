package com.example.final_project.CheckService;

import android.app.Dialog;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class GoogleServices {
    public static boolean  checkGooglePlayServices(AppCompatActivity This) {
        final int status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(This);
        if (status != ConnectionResult.SUCCESS) {
            Log.e("CheckService", GoogleApiAvailability.getInstance().getErrorString(status));

            //Google Service are not update or not installed
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(This, 1, 1);
            dialog.show();
            return false;
        } else {
            Log.i("CheckService", GoogleApiAvailability.getInstance().getErrorString(status));
            // google play services is updated.
            return true;
        }
    }

}
