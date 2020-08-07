package com.example.final_project.ui.customNotificationBox;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.final_project.CheckService.Formate;
import com.example.final_project.Database.useradate.UserDatadbProvider;
import com.example.final_project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class NotificationAdapter extends ArrayAdapter<NotificationData> {

    private Context mContext;
    private List<NotificationData> notificationDataList;

    /**
     * Constructor
     *
     * @param context The current context.
     */
    public NotificationAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<NotificationData> list) {
        super(context,0,list);
        mContext = context;
        notificationDataList = list;
    }


    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.notification_template,parent,false);

        NotificationData currentNotification = notificationDataList.get(position);

        ImageView image = listItem.findViewById(R.id.notification_image_);
        Glide.with(mContext)
                .load(currentNotification.getImageUrl())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        Log.e(TAG,"nof image load");
                        return false;
                    }
                })
        .into(image);

        TextView name = listItem.findViewById(R.id.nof_name_);
        name.setText(currentNotification.getName());
        Log.e(TAG,"Name loaded :" + currentNotification.getName());


        TextView date = listItem.findViewById(R.id.nof_date_);
        date.setText(currentNotification.getDate());
        Log.e(TAG,"date loaded : "+ currentNotification.getDate());

        currentNotification.isPayed();

        TextView amount =  listItem.findViewById(R.id.nof_amount_);
        amount.setText("Amount : "+currentNotification.getAmount());
        Log.e(TAG,"Amount loaded : "+currentNotification.getAmount());

        ImageButton accept = listItem.findViewById(R.id.buttonA);
        accept.setOnClickListener(v -> {
            DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
            DatabaseReference myRef2 = FirebaseDatabase.getInstance().getReference("Member/"+ Formate.toUsername(new UserDatadbProvider(mContext).getEmail())+"/DueBill");
            myRef2.push().setValue(currentNotification);
            Query query = myRef.child("Member/"+ Formate.toUsername(new UserDatadbProvider(mContext).getEmail())+"/Notification").orderByChild("count").equalTo(currentNotification.getCount());
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot:
                            snapshot.getChildren()) {
                        dataSnapshot.getRef().removeValue();
                        Log.e(TAG,"Data removed");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e(TAG, "onCancelled", error.toException());
                }
            });
        });
        Log.e(TAG,"get buttonA");

        ImageButton denied = listItem.findViewById(R.id.buttonD);
        denied.setOnClickListener(v -> {

            DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
            NotificationData data = new NotificationData();
            Query query = myRef.child("Member/"+ Formate.toUsername(new UserDatadbProvider(mContext).getEmail())+"/Notification").orderByChild("count").equalTo(currentNotification.getCount());
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot:
                            snapshot.getChildren()) {
                        dataSnapshot.getRef().removeValue();
                        Log.e(TAG,"Data removed");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e(TAG, "onCancelled", error.toException());
                }
            });

        });
        Log.e(TAG,"get buttonD");

        return listItem;
    }

}
