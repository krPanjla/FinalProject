package com.example.final_project.CheckService;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.material.dialog.InsetDialogOnTouchListener;

public class GoogleServices {
    public static boolean  checkGooglePlayServices(AppCompatActivity This) {
        final int status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(This);
        if (status != ConnectionResult.SUCCESS) {
            Log.e("CheckService : ", GoogleApiAvailability.getInstance().getErrorString(status));

            //Google Service are not update or not installed
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(This, 1, 1);
            dialog.show();
            return false;
        } else {
            Log.e("CheckService : ", GoogleApiAvailability.getInstance().getErrorString(status));
            return true;
        }
    }

}
