package com.example.final_project.ui.customNotificationBox;

import android.app.NotificationManager;
import android.widget.Adapter;

public class NotificationData  {

    private String imageUrl;
    private String name;
    private String amount;
    private int count;
    private String id;
    private String date;
    private boolean payed;
    private String uniqueId;

    public NotificationData(){
        //this for firebase
    }
    public NotificationData(String imageUrl, String name, String amount, String date, boolean status) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.payed = status;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public NotificationData(String imageUrl, String name, String amount, String date) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.amount = amount;
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
