package com.example.final_project.ui.customNotificationBox;

import android.app.NotificationManager;
import android.widget.Adapter;

public class NotificationData  {

    private String imageUrl;
    private String name;
    private Long amount;
    private Long count;
    private String id;
    private String date;
    private boolean payed;


    public NotificationData(){
        //this for firebase
    }

    public boolean isPayed() {
        return payed;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public void setPayed(boolean payed) {
        this.payed = payed;
    }
}
