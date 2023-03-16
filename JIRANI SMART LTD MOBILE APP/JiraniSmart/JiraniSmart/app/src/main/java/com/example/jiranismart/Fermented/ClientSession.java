package com.example.jiranismart.Fermented;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;

public class ClientSession {
    private static final String PREF_NAME = "ClientSession";
    private static final String kentry = "entry";
    private static final String kid_no = "id_no";
    private static final String kaccount = "account";
    private static final String kfname = "fname";
    private static final String klname = "lname";
    private static final String kemail = "email";
    private static final String kphone = "phone";
    private static final String kcountry = "country";
    private static final String kagent_email = "agent_email";
    private static final String klocation = "location";
    private static final String KEY_DATE = "reg_date";
    private static final String KEY_EXPIRES = "expires";
    private static final String KEY_EMPTY = "";
    private Context mContext;
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mPreferences;

    //entry,id_no,account,fname,lname,email,phone,country,location,reg_date;
    public ClientSession(Context mContext) {
        this.mContext = mContext;
        mPreferences = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        this.mEditor = mPreferences.edit();
    }

    public void loginClie(String entry, String id_no, String account, String fname, String lname, String email, String phone,
                          String country, String agent_email, String location, String reg_date) {
        mEditor.putString(kentry, entry);
        mEditor.putString(kid_no, id_no);
        mEditor.putString(kaccount, account);
        mEditor.putString(kfname, fname);
        mEditor.putString(klname, lname);
        mEditor.putString(kemail, email);
        mEditor.putString(kphone, phone);
        mEditor.putString(kcountry, country);
        mEditor.putString(kagent_email, agent_email);
        mEditor.putString(klocation, location);
        mEditor.putString(KEY_DATE, reg_date);
        //entry,id_no,account,fname,lname,email,phone,country,location,reg_date;
        Date date = new Date();
        long millis = date.getTime() + (7 * 24 * 60 * 60 * 1000);
        mEditor.putLong(KEY_EXPIRES, millis);
        mEditor.commit();
    }

    public boolean isClientIn() {
        Date currentDate = new Date();

        long millis = mPreferences.getLong(KEY_EXPIRES, 0);

        if (millis == 0) {
            return false;
        }
        Date expiryDate = new Date(millis);


        return currentDate.before(expiryDate);
    }

    public ClientMode getClientDetails() {

        if (!isClientIn()) {
            return null;
        }
        ClientMode clientMode = new ClientMode();
        clientMode.setEntry(mPreferences.getString(kentry, KEY_EMPTY));
        clientMode.setId_no(mPreferences.getString(kid_no, KEY_EMPTY));
        clientMode.setAccount(mPreferences.getString(kaccount, KEY_EMPTY));
        clientMode.setFname(mPreferences.getString(kfname, KEY_EMPTY));
        clientMode.setLname(mPreferences.getString(klname, KEY_EMPTY));
        clientMode.setEmail(mPreferences.getString(kemail, KEY_EMPTY));
        clientMode.setPhone(mPreferences.getString(kphone, KEY_EMPTY));
        clientMode.setCountry(mPreferences.getString(kcountry, KEY_EMPTY));
        clientMode.setAgent_email(mPreferences.getString(kagent_email, KEY_EMPTY));
        clientMode.setLocation(mPreferences.getString(klocation, KEY_EMPTY));
        clientMode.setReg_date(mPreferences.getString(KEY_DATE, KEY_EMPTY));
        return clientMode;
    }

    //entry,id_no,account,fname,lname,email,phone,country,location,reg_date;
    public void logoutClient() {
        mEditor.clear();
        mEditor.commit();
    }
}
