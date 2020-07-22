package com.example.final_project.ui.customNotificationBox;

import android.widget.Adapter;

public class NotificationData  {

    private String imageUrl;
    private String name;
    private String amount;
    private String data;
    private boolean status;

    public NotificationData(String imageUrl, String name, String amount, String data, boolean status) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.amount = amount;
        this.data = data;
        this.status = status;
    }

    public NotificationData(String imageUrl, String name, String amount, String data) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.amount = amount;
        this.data = data;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
