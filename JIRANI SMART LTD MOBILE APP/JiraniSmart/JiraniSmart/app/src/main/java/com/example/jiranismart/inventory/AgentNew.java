package com.example.jiranismart.inventory;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jiranismart.Fermented.AttachAgen;
import com.example.jiranismart.Fermented.AttachAgenAda;
import com.example.jiranismart.Fermented.URls;
import com.example.jiranismart.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AgentNew extends AppCompatActivity {
    ListView listView;
    SearchView searchView;
    ArrayList<AttachAgen> viewUsers = new ArrayList<>();
    AttachAgenAda viewAda;
    AttachAgen viewUsers2;
    View view;
    TextView texter, texter2;
    Button ScanImage, Submit;
    Spinner Category;
    EditText EditWeight;
    String encodedimg;
    ArrayList<String> drivers = new ArrayList<>();
    ArrayAdapter<String> driverAdapter;
    RequestQueue requestQueue;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Attach Agent");
        setContentView(R.layout.activity_agent_new);
        listView = findViewById(R.id.listview);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.search);
        Submit = findViewById(R.id.btnHi);
        Submit.setOnClickListener(view1 -> {
            startActivity(new Intent(getApplicationContext(), AgentHist.class));
        });
        listView.setOnItemClickListener((parent, view, position, id) -> {
            viewUsers2 = (AttachAgen) parent.getItemAtPosition(position);
            AlertDialog.Builder builder = new AlertDialog.Builder(AgentNew.this);
            builder.setTitle("Client Details");
            builder.setMessage("EntryID :" + viewUsers2.getEntry() + "\nIDNo. " + viewUsers2.getId_no() + "\nName :" + viewUsers2.getFname() + " " + viewUsers2.getLname() + "\nEmail :" + viewUsers2.getEmail() + "\nPhone :" + viewUsers2.getPhone() + "\nCounty :" + viewUsers2.getCountry() + "\nLocation :" + viewUsers2.getLocation() + "\nAgent :" + viewUsers2.getAgent_email());
            if (viewUsers2.getAgent_email().equals("Pending")) {
                builder.setPositiveButton("attach_agent", (dialo, idn) -> {
                    requestQueue = Volley.newRequestQueue(this);
                    AlertDialog.Builder buildere = new AlertDialog.Builder(AgentNew.this);
                    buildere.setTitle("Attach Agent");
                    buildere.setMessage("EntryID :" + viewUsers2.getEntry() + "\nIDNo. " + viewUsers2.getId_no() + "\nName :" + viewUsers2.getFname() + " " + viewUsers2.getLname() + "\nEmail :" + viewUsers2.getEmail() + "\nPhone :" + viewUsers2.getPhone() + "\nCounty :" + viewUsers2.getCountry() + "\nLocation :" + viewUsers2.getLocation() + "\nAgent :" + viewUsers2.getAgent_email() + "\nSelect Agent Email Below");
                    final Spinner spinned = new Spinner(AgentNew.this);
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URls.getAgent, null,
                            response -> {
                                try {
                                    JSONArray jsonArray = response.getJSONArray("driver");
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        String driverEmail = jsonObject.optString("driver_id");
                                        drivers.add(driverEmail);
                                        driverAdapter = new ArrayAdapter<>(AgentNew.this,
                                                android.R.layout.simple_spinner_item, drivers);
                                        driverAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                        spinned.setAdapter(driverAdapter);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }, error -> {
                    })/* {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("driver", viewUsers2.getCountry());
                        return params;
                    }
                }*/;
                    requestQueue.add(jsonObjectRequest);
                    buildere.setView(spinned);
                    buildere.setPositiveButton("Assign", (dilo, di) -> {
                        final String Agen = spinned.getSelectedItem().toString().trim();
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, URls.vileTuIko,
                                response -> {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        String msg = jsonObject.getString("message");
                                        int status = jsonObject.getInt("success");
                                        if (status == 1) {
                                            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), AgentNew.class));
                                            finish();
                                        } else if (status == 0) {
                                            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show();
                                    }
                                }, error -> {
                            Toast.makeText(this, "There was a connection error", Toast.LENGTH_SHORT).show();

                        }) {
                            @Nullable
                            @Override
                            protected Map<String, String> getParams() {
                                Map<String, String> params = new HashMap<>();
                                params.put("agent_email", Agen);
                                params.put("entry", viewUsers2.getEntry());
                                params.put("id_no", viewUsers2.getId_no());
                                params.put("county", viewUsers2.getCountry());
                                params.put("location", viewUsers2.getLocation());
                                params.put("name", viewUsers2.getFname() + " " + viewUsers2.getLname());
                                params.put("phone", viewUsers2.getPhone());
                                params.put("email", viewUsers2.getEmail());
                                return params;
                            }
                        };//agent_email,entry,id_no,county,location,name,phone,email
                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(stringRequest);
                    });
                    buildere.setNegativeButton("close", (dilo, o9) -> {
                        startActivity(new Intent(getApplicationContext(), AgentNew.class));
                    });
                    AlertDialog alertDialog = buildere.create();
                    alertDialog.setCanceledOnTouchOutside(false);
                    alertDialog.show();
                });
            }

            builder.setNegativeButton("close", (dialo, idn) -> dialo.cancel());
            AlertDialog alertDialog = builder.create();
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
        });
        practicalPra();
    }

    private void practicalPra() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URls.viewClient,
                response -> {
                    try {
                        AttachAgen subject;
                        viewUsers = new ArrayList<>();
                        JSONObject jsonObject = new JSONObject(response);
                        Log.e("response ", response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            JSONArray jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                String entry = jsonObject.getString("entry");
                                String id_no = jsonObject.getString("id_no");
                                String fname = jsonObject.getString("fname");
                                String lname = jsonObject.getString("lname");
                                String email = jsonObject.getString("email");
                                String phone = jsonObject.getString("phone");
                                String country = jsonObject.getString("country");
                                String agent_email = jsonObject.getString("agent_email");
                                String location = jsonObject.getString("location");
                                subject = new AttachAgen(entry, id_no, fname, lname, email, phone, country, agent_email, location);
                                viewUsers.add(subject);
                            }
                            viewAda = new AttachAgenAda(AgentNew.this, R.layout.attach_agen, viewUsers);
                            listView.setAdapter(viewAda);
                            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String text) {
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String newText) {
                                    viewAda.getFilter().filter(newText);
                                    return false;
                                }
                            });
                        } else if (success == 0) {
                            String msg = jsonObject.getString("mine");
                            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                            AlertDialog.Builder builder = new AlertDialog.Builder(AgentNew.this);
                            builder.setTitle(msg);
                            builder.show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "an error", Toast.LENGTH_SHORT).show();
                    }

                }, error -> {
            Toast.makeText(this, "error in connection", Toast.LENGTH_SHORT).show();

        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}