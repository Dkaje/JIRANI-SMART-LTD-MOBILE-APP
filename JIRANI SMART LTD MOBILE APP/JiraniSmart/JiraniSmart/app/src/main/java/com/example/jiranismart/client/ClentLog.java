package com.example.jiranismart.client;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jiranismart.Fermented.ClientMode;
import com.example.jiranismart.Fermented.ClientSession;
import com.example.jiranismart.Fermented.URls;
import com.example.jiranismart.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ClentLog extends AppCompatActivity {
    ClientMode clientMode;
    ClientSession clientSession;
    EditText Email, Password, Repeat;
    Button btLog, resetButton;
    TextView textView, RemindPassword;
    View layer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Client Login");
        setContentView(R.layout.activity_clent_log);
        clientSession = new ClientSession(getApplicationContext());
        clientMode = clientSession.getClientDetails();
        Email = findViewById(R.id.username);
        Password = findViewById(R.id.password);
        btLog = findViewById(R.id.btnLogin);
        textView = findViewById(R.id.btnRegister);
        RemindPassword = findViewById(R.id.btnreset);
        textView.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ClieReg.class));
        });
        RemindPassword.setOnClickListener(c -> {
            AlertDialog.Builder alarmed = new AlertDialog.Builder(ClentLog.this);
            Rect rec = new Rect();
            Window window = ClentLog.this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rec);
            LayoutInflater inflat = (LayoutInflater) ClentLog.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layer = inflat.inflate(R.layout.pasword, null);
            layer.setMinimumWidth((int) (rec.width() * 0.9f));
            layer.setMinimumHeight((int) (rec.height() * 0.3f));
            Email = layer.findViewById(R.id.myEmail);
            Password = layer.findViewById(R.id.newPassword);
            Repeat = layer.findViewById(R.id.passwordRetype);
            resetButton = layer.findViewById(R.id.btnReset);
            resetButton.setOnClickListener(view -> {
                final String UserMail = Email.getText().toString().trim();
                final String UserPass = Password.getText().toString().trim();
                final String NewRepe = Repeat.getText().toString().trim();
                if (UserMail.isEmpty() || UserPass.isEmpty() || NewRepe.isEmpty()) {
                    Toast.makeText(this, "All Fields are required", Toast.LENGTH_SHORT).show();
                } else if (!UserMail.matches(getString(R.string.valid_email2)) & !UserMail.matches(getString(R.string.valid_email))) {
                    Email.setError("invalid email");
                    Email.requestFocus();
                } else if (!UserPass.equals(NewRepe)) {
                    Toast.makeText(this, "Your passwords do not match", Toast.LENGTH_SHORT).show();
                } else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URls.changeCl,
                            response -> {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    Log.e("response ", response);
                                    String msg = jsonObject.getString("message");
                                    int status = jsonObject.getInt("success");
                                    if (status == 1) {
                                        Toast.makeText(ClentLog.this, msg, Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(getApplicationContext(), ClentLog.class));

                                    } else if (status == 0) {
                                        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show();
                                }

                            }, error -> {
                        Toast.makeText(this, "Oops Check your root connection", Toast.LENGTH_SHORT).show();

                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("email", UserMail);
                            params.put("password", UserPass);
                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(this);
                    requestQueue.add(stringRequest);
                }
            });
            alarmed.setTitle("Reset Password");
            alarmed.setView(layer);
            alarmed.setNegativeButton("Close", (dialogInterface, i) -> dialogInterface.cancel());
            AlertDialog alertDialog2 = alarmed.create();
            alertDialog2.show();
            alertDialog2.setCanceledOnTouchOutside(false);
        });
        btLog.setOnClickListener(v -> {
            final String myUser = Email.getText().toString().trim();
            final String myPass = Password.getText().toString().trim();
            if (myUser.isEmpty() || myPass.isEmpty()) {
                Toast.makeText(this, "You an empty field", Toast.LENGTH_SHORT).show();
            } else if (!myUser.matches(getString(R.string.valid_email2)) & !myUser.matches(getString(R.string.valid_email))) {
                Email.setError("invalid email");
                Email.requestFocus();
            } else {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URls.cliLog,
                        response -> {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                Log.e("response", response);
                                String msg = jsonObject.getString("message");
                                String status = jsonObject.getString("success");
                                if (status.equals("1")) {
                                    JSONArray dataArray = jsonObject.getJSONArray("login");
                                    for (int i = 0; i < dataArray.length(); i++) {
                                        JSONObject dataobj = dataArray.getJSONObject(i);
                                        String entry = dataobj.getString("entry");
                                        String id_no = dataobj.getString("id_no");
                                        String account = dataobj.getString("account");
                                        String fname = dataobj.getString("fname");
                                        String lname = dataobj.getString("lname");
                                        String email = dataobj.getString("email");
                                        String phone = dataobj.getString("phone");
                                        String country = dataobj.getString("country");
                                        String agent_email = dataobj.getString("agent_email");
                                        String location = dataobj.getString("location");
                                        String reg_date = dataobj.getString("reg_date");
                                        clientSession.loginClie(entry,id_no,account,fname,lname,email,phone,country,agent_email,location,reg_date);
                                    }
                                    startActivity(new Intent(getApplicationContext(), ClieDash.class));


                                } else if (status.equals("0")) {
                                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

                                } else if (status.equals("2")) {
                                    JSONArray dataArray = jsonObject.getJSONArray("login");
                                    for (int i = 0; i < dataArray.length(); i++) {
                                        JSONObject dataobj = dataArray.getJSONObject(i);
                                        String remarks = dataobj.getString("remarks").trim();
                                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                                        Toast.makeText(getApplicationContext(), remarks, Toast.LENGTH_LONG).show();
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show();

                            }

                        }, error -> {
                    Toast.makeText(this, "Connection error", Toast.LENGTH_SHORT).show();

                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("email", myUser);
                        params.put("password", myPass);

                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            }
        });
    }
}