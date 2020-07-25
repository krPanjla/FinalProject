package com.example.final_project.firebaseConnection;

public class UserNotification {
    private String SenderName;
    private String ReviverName;
    private double Amount;
    private String date;
    private boolean flag;

    public UserNotification() {
    }

    public UserNotification(String SenderName,String ReviverName,double Amount,String date,boolean flag){
        this.Amount = Amount;
        this.date = date;
        this.flag =flag;
        this.SenderName = SenderName;
        this.ReviverName = ReviverName;
    }

    public String getSenderName() {
        return SenderName;
    }

    public void setSenderName(String senderName) {
        SenderName = senderName;
    }

    public String getReviverName() {
        return ReviverName;
    }

    public void setReviverName(String reviverName) {
        ReviverName = reviverName;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


}
