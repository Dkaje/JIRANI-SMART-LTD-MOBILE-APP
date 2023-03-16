package com.example.jiranismart.Fermented;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;

public class FinaSessions {
    private static final String PREF_NAME = "FinaSession";
    private static final String kentry = "entry";
    private static final String kfname = "fname";
    private static final String klname = "lname";
    private static final String kemail = "email";
    private static final String kphone = "phone";
    private static final String krole = "role";
    private static final String KEY_DATE = "reg_date";
    private static final String KEY_EXPIRES = "expires";
    private static final String KEY_EMPTY = "";
    private Context mContext;
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mPreferences;

    //entry,id_no,account,fname,lname,email,phone,country,location,reg_date;
    public FinaSessions(Context mContext) {
        this.mContext = mContext;
        mPreferences = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        this.mEditor = mPreferences.edit();
    }

    public void loginFina(String entry, String fname, String lname, String email, String phone,
                           String role, String reg_date) {
        mEditor.putString(kentry, entry);
        mEditor.putString(kfname, fname);
        mEditor.putString(klname, lname);
        mEditor.putString(kemail, email);
        mEditor.putString(kphone, phone);
        mEditor.putString(krole, role);
        mEditor.putString(KEY_DATE, reg_date);
        //entry,id_no,account,fname,lname,email,phone,country,location,reg_date;
        Date date = new Date();
        long millis = date.getTime() + (7 * 24 * 60 * 60 * 1000);
        mEditor.putLong(KEY_EXPIRES, millis);
        mEditor.commit();
    }

    public boolean isFina() {
        Date currentDate = new Date();

        long millis = mPreferences.getLong(KEY_EXPIRES, 0);

        if (millis == 0) {
            return false;
        }
        Date expiryDate = new Date(millis);


        return currentDate.before(expiryDate);
    }

    public StaffMode getFinaDetails() {

        if (!isFina()) {
            return null;
        }
        StaffMode clientMode = new StaffMode();
        clientMode.setEntry(mPreferences.getString(kentry, KEY_EMPTY));
        clientMode.setFname(mPreferences.getString(kfname, KEY_EMPTY));
        clientMode.setLname(mPreferences.getString(klname, KEY_EMPTY));
        clientMode.setEmail(mPreferences.getString(kemail, KEY_EMPTY));
        clientMode.setPhone(mPreferences.getString(kphone, KEY_EMPTY));
        clientMode.setRole(mPreferences.getString(krole, KEY_EMPTY));
        clientMode.setReg_date(mPreferences.getString(KEY_DATE, KEY_EMPTY));
        return clientMode;
    }

    //entry,id_no,account,fname,lname,email,phone,country,location,reg_date;
    public void logoutFina() {
        mEditor.clear();
        mEditor.commit();
    }
}
