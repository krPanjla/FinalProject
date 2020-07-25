package com.example.final_project.firebaseConnection;

import android.hardware.usb.UsbDevice;
import android.net.Uri;

import com.firebase.ui.auth.data.model.User;

import java.util.ArrayList;

public class UserData {
    private String id;
    private String name;
    private int gender;
    private long phone;
    private String image;
    private String password;
    private ArrayList<String> notification = new ArrayList<>();

    public UserData(){
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public UserData(String id,String name){
        this.id = id;
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNotification(ArrayList<String> notification) {
        this.notification = notification;
    }
}
