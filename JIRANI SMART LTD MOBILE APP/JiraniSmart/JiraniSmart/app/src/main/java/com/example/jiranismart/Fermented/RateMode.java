package com.example.jiranismart.Fermented;

public class RateMode {
    //id,cust_id,phone,rate,message,reg_date
    String id = null;
    String sender = null;
    String phone = null;
    String message = null;
    String reg_date = null;
    String reply = null;
    String reply_date = null;

    public RateMode(String id, String sender, String phone, String message, String reg_date, String reply, String reply_date) {
        this.id = id;
        this.sender = sender;
        this.phone = phone;
        this.message = message;
        this.reg_date = reg_date;
        this.reply = reply;
        this.reply_date = reply_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getReply_date() {
        return reply_date;
    }

    public void setReply_date(String reply_date) {
        this.reply_date = reply_date;
    }

    public String toString() {
        return id + " " + phone + " " + message + " " + sender;
    }
}
