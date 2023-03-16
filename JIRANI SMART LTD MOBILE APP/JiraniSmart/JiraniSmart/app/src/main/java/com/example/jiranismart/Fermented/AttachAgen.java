package com.example.jiranismart.Fermented;

public class AttachAgen {
    //entry,id_no,fname,lname,email,phone,country,agent_email,location
    String entry = null;
    String id_no = null;
    String fname = null;
    String lname = null;
    String email = null;
    String phone = null;
    String country = null;
    String agent_email = null;
    String location = null;

    public AttachAgen(String entry, String id_no, String fname, String lname, String email, String phone, String country, String agent_email, String location) {
        this.entry = entry;
        this.id_no = id_no;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.country = country;
        this.agent_email = agent_email;
        this.location = location;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getId_no() {
        return id_no;
    }

    public void setId_no(String id_no) {
        this.id_no = id_no;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAgent_email() {
        return agent_email;
    }

    public void setAgent_email(String agent_email) {
        this.agent_email = agent_email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String toString() {
        return entry + " " + id_no + " " + fname + " " + lname + " " + email + " " + phone + " " + country + " " + agent_email + " " + location;
    }
}
