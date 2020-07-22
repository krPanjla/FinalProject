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
import com.example.final_project.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class NotificationAdapter extends ArrayAdapter<NotificationData> {

    private Context mContext;
    private List<NotificationData> notificationDataList = new ArrayList<>();

    /**
     * Constructor
     *
     * @param context            The current context.
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
            listItem = LayoutInflater.from(mContext).inflate(R.layout.notification_teplate,parent,false);

        NotificationData currentNotification = notificationDataList.get(position);

        ImageView image = (ImageView)listItem.findViewById(R.id.notification_image);
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

        TextView name = (TextView) listItem.findViewById(R.id.nof_name);
        name.setText(currentNotification.getName());
        Log.e(TAG,"Name loaded");


        TextView date = (TextView) listItem.findViewById(R.id.nof_date);
        date.setText(currentNotification.getData());
        Log.e(TAG,"date loaded");
        
        TextView amount = (TextView) listItem.findViewById(R.id.nof_amount);
        amount.setText("Amount : "+currentNotification.getAmount());
        Log.e(TAG,"Amount loaded");
        
        Button accpect = listItem.findViewById(R.id.buttonA);
        accpect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
        Log.e(TAG,"get buttonA");
        
        Button dinied = listItem.findViewById(R.id.buttonD);
        dinied.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
        Log.e(TAG,"get buttonD");
        return listItem;
    }

}
