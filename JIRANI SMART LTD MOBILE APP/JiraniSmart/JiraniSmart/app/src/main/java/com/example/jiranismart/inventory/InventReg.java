package com.example.jiranismart.inventory;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
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

public class InventReg extends AppCompatActivity {
    EditText fname, lname, email, password;
    MaskEditText Phone;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Inventory Registration");
        setContentView(R.layout.activity_invent_reg);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        email = findViewById(R.id.Email);
        password = findViewById(R.id.password);
        Phone = findViewById(R.id.phone);
        btn = findViewById(R.id.btnRegister);
        btn.setOnClickListener(v -> {
            final String myName = fname.getText().toString().trim();
            final String myLname = lname.getText().toString().trim();
            final String myEmail = email.getText().toString().trim();
            final String mypas = password.getText().toString().trim();
            final String myPhon = Phone.getText().toString().trim();
            int lent = mypas.length();
            int phn = myPhon.length();
            String emailPattern = "[a-z0-9._%+-]+@gmail+\\.[a-z]{2,4}";
            String emailPattern2 = "[a-z0-9._%+-]+@yahoo+\\.[a-z]{2,4}";
            if (myName.isEmpty() || myLname.isEmpty() || myEmail.isEmpty() || mypas.isEmpty() || myPhon.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            } else if (!myEmail.matches(emailPattern) & !myEmail.matches(emailPattern2)) {
                Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show();
            } else if (lent < 8) {
                Toast.makeText(this, "Weak Password", Toast.LENGTH_SHORT).show();
            } else if (phn < 10) {
                Toast.makeText(this, "invalid Phone", Toast.LENGTH_SHORT).show();
            } else {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URls.stafReg,
                        response -> {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                Log.e("response ", response);
                                String msg = jsonObject.getString("message");
                                int status = jsonObject.getInt("success");
                                if (status == 1) {
                                    Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(getApplicationContext(), InventLog.class));

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
                        params.put("email", myEmail);
                        params.put("phone", myPhon);
                        params.put("role", "Inventory");
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