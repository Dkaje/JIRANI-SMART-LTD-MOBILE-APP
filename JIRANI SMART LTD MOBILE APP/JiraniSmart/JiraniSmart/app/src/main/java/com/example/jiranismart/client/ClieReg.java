package com.example.jiranismart.client;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jiranismart.Fermented.URls;
import com.example.jiranismart.R;
import com.santalu.maskedittext.MaskEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ClieReg extends AppCompatActivity {
    EditText fname, lname, email, password, Iden, Acc, Town, Village;
    MaskEditText Phone;
    Spinner county;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Client Registration");
        setContentView(R.layout.activity_clie_reg);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        email = findViewById(R.id.Email);
        password = findViewById(R.id.password);
        Iden = findViewById(R.id.myIdentity);
        Acc = findViewById(R.id.myAccount);
        Phone = findViewById(R.id.phone);
        county = findViewById(R.id.myCounty);
        btn = findViewById(R.id.btnRegister);
        Town = findViewById(R.id.myTown);
        Village = findViewById(R.id.myVillage);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.City, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        county.setAdapter(adapter1);
        btn.setOnClickListener(v -> {
            final String myName = fname.getText().toString().trim();
            final String myLname = lname.getText().toString().trim();
            final String myID = Iden.getText().toString().trim();
            final String myAcc = Acc.getText().toString().trim();
            final String myEmail = email.getText().toString().trim();
            final String mypas = password.getText().toString().trim();
            final String myTow = Town.getText().toString().trim();
            final String myVil = Village.getText().toString().trim();
            final String myPhon = Phone.getText().toString().trim();
            final String myrole = county.getSelectedItem().toString().trim();
            int lent = mypas.length();
            int phn = myPhon.length();
            int id = myID.length();
            int ac = myAcc.length();
            String emailPattern = "[a-z0-9._%+-]+@gmail+\\.[a-z]{2,4}";
            String emailPattern2 = "[a-z0-9._%+-]+@yahoo+\\.[a-z]{2,4}";
            if (myName.isEmpty() || myTow.isEmpty() || myVil.isEmpty() || myLname.isEmpty() || myID.isEmpty() || myAcc.isEmpty() || myEmail.isEmpty() || mypas.isEmpty() || myPhon.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            } else if (!myEmail.matches(emailPattern) & !myEmail.matches(emailPattern2)) {
                Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show();
            } else if (lent < 8) {
                Toast.makeText(this, "Weak Password", Toast.LENGTH_SHORT).show();
            } else if (phn < 10) {
                Toast.makeText(this, "invalid Phone", Toast.LENGTH_SHORT).show();
            } else if (id < 8) {
                Toast.makeText(this, "invalid Identity", Toast.LENGTH_SHORT).show();
            } else if (ac < 10) {
                Toast.makeText(this, "invalid Bank Acc", Toast.LENGTH_SHORT).show();
            } else if (myrole.equals("Select County")) {
                Toast.makeText(this, "County required", Toast.LENGTH_SHORT).show();
            } else {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URls.cliReg,
                        response -> {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                Log.e("response ", response);
                                String msg = jsonObject.getString("message");
                                int status = jsonObject.getInt("success");
                                if (status == 1) {
                                    Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(getApplicationContext(), ClentLog.class));

                                } else if (status == 0) {
                                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(this, "An error Occurred", Toast.LENGTH_SHORT).show();
                            }

                        }, error -> {
                    Toast.makeText(this, "Connection Error", Toast.LENGTH_SHORT).show();

                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("fname", myName);
                        params.put("lname", myLname);
                        params.put("id_no", myID);
                        params.put("account", myAcc);
                        params.put("email", myEmail);
                        params.put("phone", myPhon);
                        params.put("county", myrole);
                        params.put("location", myTow + ", " + myVil);
                        params.put("password", mypas);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);
            }
        });
    }
}