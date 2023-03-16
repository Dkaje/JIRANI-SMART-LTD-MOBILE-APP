package com.example.jiranismart.finance;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jiranismart.Fermented.AliAda;
import com.example.jiranismart.Fermented.AliModel;
import com.example.jiranismart.Fermented.URls;
import com.example.jiranismart.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ClientRepayment extends AppCompatActivity {
    ListView listView;
    SearchView searchView;
    View view2, viewer;
    TextView textView;
    Dialog dialog;
    Button btnSu, btnEx, hist, issue;
    Rect rect;
    Window window;
    LayoutInflater layoutInflater;
    AliAda produAda;
    ArrayList<AliModel> SubjectList = new ArrayList<>();
    AliModel viewUsers2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("New Client Deposits");
        setContentView(R.layout.activity_client_repayment);
        listView = findViewById(R.id.listedOne);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.search);
        issue = findViewById(R.id.btnBal);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            viewUsers2 = (AliModel) parent.getItemAtPosition(position);
            double inter = (Float.parseFloat(viewUsers2.getAmount()) / Float.parseFloat(viewUsers2.getExpected())) * (Float.parseFloat(viewUsers2.getInterest()) / Float.parseFloat(viewUsers2.getPeriod())) * (Float.parseFloat(viewUsers2.getCurrent_period()));
            String interes = String.format("%.0f", inter);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(Html.fromHtml("<font><b><u>" + viewUsers2.getName() + "</u></b> Payment Details</font>"));
            builder.setMessage("ApplicantID: " + viewUsers2.getId_no() + "\nName: " + viewUsers2.getName() + "\nPhone: " + viewUsers2.getPhone() + "\n\nMPESA " + viewUsers2.getMpesa() + "\nAmountPaid: KES" + viewUsers2.getAmount() + "\n\nPeriod : " + viewUsers2.getCurrent_period() + " month(s)\nSummedPayment: KES" + viewUsers2.getTotal() + "\n\nStatus :" + viewUsers2.getStatus());
            builder.setPositiveButton("approve", (oo, o) -> {
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(new StringRequest(Request.Method.POST, URls.updatePa,
                        response -> {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int st = jsonObject.getInt("status");
                                String msg = jsonObject.getString("message");
                                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                if (st == 1) {
                                    startActivity(new Intent(getApplicationContext(), ClientRepayment.class));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
                            }
                        }, error -> {
                    Toast.makeText(this, "connection error", Toast.LENGTH_SHORT).show();
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> para = new HashMap<>();
                        para.put("reg_id", viewUsers2.getReg_id());
                        para.put("borrow_id", viewUsers2.getBorrow_id());
                        para.put("amount", viewUsers2.getTotal());
                        para.put("interest", interes);
                        para.put("status", "Approved");
                        return para;
                    }
                });
            });
            builder.setNeutralButton("reject", (oo, o) -> {
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(new StringRequest(Request.Method.POST, URls.updatePa,
                        response -> {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int st = jsonObject.getInt("status");
                                String msg = jsonObject.getString("message");
                                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                if (st == 1) {
                                    startActivity(new Intent(getApplicationContext(), ClientRepayment.class));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
                            }
                        }, error -> {
                    Toast.makeText(this, "connection error", Toast.LENGTH_SHORT).show();
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> para = new HashMap<>();
                        para.put("reg_id", viewUsers2.getReg_id());
                        para.put("borrow_id", viewUsers2.getBorrow_id());
                        para.put("amount", viewUsers2.getTotal());
                        para.put("interest", interes);
                        para.put("status", "Rejected");
                        return para;
                    }
                });//reg_id,borrow_id,amount,status
            });
            builder.setNegativeButton("exit", (oo, o) -> oo.cancel());
            AlertDialog alertDialog = builder.create();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.getWindow().setGravity(Gravity.BOTTOM);
            alertDialog.show();
        });
        practical();
    }

    private void practical() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URls.pendingp,
                response -> {
                    try {
                        AliModel subject;
                        SubjectList = new ArrayList<>();
                        JSONObject jsonObject = new JSONObject(response);
                        Log.e("response ", response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            JSONArray jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                String reg_id = jsonObject.getString("reg_id");
                                String borrow_id = jsonObject.getString("borrow_id");
                                String mpesa = jsonObject.getString("mpesa");
                                String id_no = jsonObject.getString("id_no");
                                String name = jsonObject.getString("name");
                                String phone = jsonObject.getString("phone");
                                String interest = jsonObject.getString("interest");
                                String period = jsonObject.getString("period");
                                String expected = jsonObject.getString("expected");
                                String amount = jsonObject.getString("amount");
                                String current_period = jsonObject.getString("current_period");
                                String total = jsonObject.getString("total");
                                String summed = jsonObject.getString("summed");
                                String status = jsonObject.getString("status");
                                String remarks = jsonObject.getString("remarks");
                                String reg_date = jsonObject.getString("reg_date");
                                subject = new AliModel(reg_id, borrow_id, mpesa, id_no, name, phone, interest, period, expected, amount, current_period, total, summed, status, remarks, reg_date);
                                SubjectList.add(subject);
                                issue.setText("Total Paid KES " + summed);
                            }
                            produAda = new AliAda(ClientRepayment.this, R.layout.loaner, SubjectList);
                            listView.setAdapter(produAda);
                            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String text) {
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String newText) {
                                    produAda.getFilter().filter(newText);
                                    return false;
                                }
                            });
                        } else if (success == 0) {
                            String msg = jsonObject.getString("mine");
                            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setTitle(msg);
                            builder.show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "an error", Toast.LENGTH_SHORT).show();
                    }

                }, error -> {
            Toast.makeText(this, "error in connection", Toast.LENGTH_SHORT).show();

        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}