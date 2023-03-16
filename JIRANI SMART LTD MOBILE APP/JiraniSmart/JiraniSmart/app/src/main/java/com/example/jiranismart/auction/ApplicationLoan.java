package com.example.jiranismart.auction;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jiranismart.Fermented.ApplicaModel;
import com.example.jiranismart.Fermented.ApplicationAda;
import com.example.jiranismart.Fermented.URls;
import com.example.jiranismart.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ApplicationLoan extends AppCompatActivity {
    ApplicationAda produAda;
    ArrayList<ApplicaModel> SubjectList = new ArrayList<>();
    ApplicaModel viewUsers2;
    ListView listView;
    SearchView searchView;
    ImageView imageView;
    EditText editText, EditPri;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("New Loan Applications");
        setContentView(R.layout.activity_application_loan);
        listView = findViewById(R.id.listedOne);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.search);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            viewUsers2 = (ApplicaModel) parent.getItemAtPosition(position);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(Html.fromHtml("<font><b><u>" + viewUsers2.getName() + "</u></b> Application Details</font>"));
            builder.setMessage("ApplicantID: " + viewUsers2.getId_no() + "\nName: " + viewUsers2.getName() + "\nPhone: " + viewUsers2.getPhone() + "\n\nMaxLoanLimit: KES" + viewUsers2.getMaxloan() + "\n\nLoan: KES" + viewUsers2.getLoan() + "\nTotalExpected: KES" + viewUsers2.getTotal() + "\nRepaymentPeriod: " + viewUsers2.getLoan_period() + " months\nMonthlyPay: KES" + viewUsers2.getExpected_monthly() + "\nApproval :" + viewUsers2.getStatus());
            if (viewUsers2.getStatus().equals("Pending")) {
                builder.setPositiveButton("approve", (dialog, idx) -> {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URls.approveLoan,
                            response -> {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String mess = jsonObject.getString("message");
                                    int Status = jsonObject.getInt("status");
                                    if (Status == 1) {
                                        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), ApplicationLoan.class));
                                        finish();
                                    } else if (Status == 0) {
                                        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show();
                                }
                            }, error -> {
                        Toast.makeText(this, "Connection Error", Toast.LENGTH_SHORT).show();
                    }) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("borrow_id", viewUsers2.getBorrow_id());
                            params.put("status", "Approved");
                            params.put("loan_id", viewUsers2.getLoan_id());
                            params.put("id_no", viewUsers2.getId_no());
                            params.put("name", viewUsers2.getName());
                            params.put("phone", viewUsers2.getPhone());
                            params.put("loan", viewUsers2.getLoan());
                            params.put("amount", viewUsers2.getTotal());
                            return params;
                        }//borrow_id,status,loan_id,id_no,name,phone,loan
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                });
                builder.setNeutralButton("reject", (dialog, idx) -> {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URls.respond,
                            response -> {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String mess = jsonObject.getString("message");
                                    int Status = jsonObject.getInt("success");
                                    if (Status == 1) {
                                        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), ApplicationLoan.class));
                                        finish();
                                    } else if (Status == 0) {
                                        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show();
                                }
                            }, error -> {
                        Toast.makeText(this, "Connection Error", Toast.LENGTH_SHORT).show();
                    }) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("borrow_id", viewUsers2.getBorrow_id());
                            params.put("status", "Rejected");
                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                });
            }
            builder.setNegativeButton("close", (oo, o) -> oo.cancel());
            AlertDialog alertx = builder.create();
            alertx.setCancelable(false);
            alertx.setCanceledOnTouchOutside(false);
            alertx.show();
        });
        practical();
    }

    private void practical() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URls.getloaner,
                response -> {
                    try {
                        ApplicaModel subject;
                        SubjectList = new ArrayList<>();
                        JSONObject jsonObject = new JSONObject(response);
                        Log.e("response ", response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            JSONArray jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                String borrow_id = jsonObject.getString("borrow_id");
                                String loan_id = jsonObject.getString("loan_id");
                                String loan_type = jsonObject.getString("loan_type");
                                String rate = jsonObject.getString("rate");
                                String id_no = jsonObject.getString("id_no");
                                String name = jsonObject.getString("name");
                                String phone = jsonObject.getString("phone");
                                String maxloan = jsonObject.getString("maxloan");
                                String loan = jsonObject.getString("loan");
                                String interest = jsonObject.getString("interest");
                                String loan_period = jsonObject.getString("loan_period");
                                String period = jsonObject.getString("period");
                                String total = jsonObject.getString("total");
                                String expected_monthly = jsonObject.getString("expected_monthly");
                                String balance = jsonObject.getString("balance");
                                String status = jsonObject.getString("status");
                                String reg_date = jsonObject.getString("reg_date");
                                String pay_date = jsonObject.getString("pay_date");
                                String payment_status = jsonObject.getString("payment_status");
                                String fina_status = jsonObject.getString("fina_status");
                                subject = new ApplicaModel(borrow_id, loan_id, loan_type, rate, id_no, name, phone, maxloan, loan, interest, loan_period, period, total, expected_monthly,
                                        balance, status, reg_date, pay_date, payment_status, fina_status);
                                SubjectList.add(subject);
                            }
                            produAda = new ApplicationAda(ApplicationLoan.this, R.layout.attach_agen, SubjectList);
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
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show();
                    }

                }, error -> {
            Toast.makeText(this, "Failed to connect", Toast.LENGTH_SHORT).show();

        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}