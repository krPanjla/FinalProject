package com.example.final_project.firebaseConnection;

import android.hardware.usb.UsbDevice;

import com.firebase.ui.auth.data.model.User;

public class UserData {
    private String id;
    private String name;
    private int gender;
    private long phone;
    private byte[] image;
    private String password;

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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
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
}
