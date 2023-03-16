package com.example.jiranismart.client;

import android.app.Dialog;
import android.content.Context;
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
import android.widget.EditText;
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
import com.example.jiranismart.Fermented.AppWeb;
import com.example.jiranismart.Fermented.ApplicaModel;
import com.example.jiranismart.Fermented.ClientMode;
import com.example.jiranismart.Fermented.ClientSession;
import com.example.jiranismart.Fermented.URls;
import com.example.jiranismart.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Deposit extends AppCompatActivity {
    ListView listView;
    SearchView searchView;
    View view2, viewer;
    TextView textView;
    Dialog dialog;
    Button btnSu, btnEx, hist, issue;
    Rect rect;
    Window window;
    LayoutInflater layoutInflater;
    ClientMode clientMode;
    ClientSession clientSession;
    AppWeb produAda;
    ArrayList<ApplicaModel> SubjectList = new ArrayList<>();
    ApplicaModel viewUsers2;
    EditText mpesa, amount, period;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Deposits");
        setContentView(R.layout.activity_deposit);
        clientSession = new ClientSession(getApplicationContext());
        clientMode = clientSession.getClientDetails();
        listView = findViewById(R.id.listedOne);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.search);
        hist = findViewById(R.id.btnHis);
        issue = findViewById(R.id.btnBal);
        hist.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), HistPay.class));
        });
        listView.setOnItemClickListener((parent, view, position, id) -> {
            viewUsers2 = (ApplicaModel) parent.getItemAtPosition(position);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(Html.fromHtml("<font><b><u>" + viewUsers2.getName() + "</u></b> Application Details</font>"));
            if (viewUsers2.getBalance().equals("0")) {
                builder.setMessage("ApplicantID: " + viewUsers2.getId_no() + "\nName: " + viewUsers2.getName() + "\nPhone: " + viewUsers2.getPhone() + "\n\nIssuedLoan: KES" + viewUsers2.getLoan() + "\nAmountExpected: KES" + viewUsers2.getTotal() + "\n\nYour Loan is fully Settled");
            } else if ((Float.parseFloat(viewUsers2.getExpected_monthly()) * 2) > Float.parseFloat(viewUsers2.getBalance())) {
                builder.setMessage("ApplicantID: " + viewUsers2.getId_no() + "\nName: " + viewUsers2.getName() + "\nPhone: " + viewUsers2.getPhone() + "\n\nIssuedLoan: KES" + viewUsers2.getLoan() + "\nAmountExpected: KES" + viewUsers2.getTotal() + "\n\nRepaymentPeriod: " + viewUsers2.getPeriod() + " month\nMonthlyPay: KES" + viewUsers2.getBalance() + "\n\nCurrentBalance :" + viewUsers2.getBalance() + "\n\n" + Html.fromHtml("<font><b><i><u>How do you want to PAY?</u></i></b><br>Select One_Period below</font>"));
                builder.setPositiveButton("1-Period", (oo, o) -> {
                    AlertDialog.Builder alert = new AlertDialog.Builder(this);
                    alert.setTitle("MPESA payment for One Period");
                    if ((Float.parseFloat(viewUsers2.getExpected_monthly()) * 2) > Float.parseFloat(viewUsers2.getBalance())) {
                        alert.setMessage("Hello " + clientMode.getFname() + ",\nyou will be expected to Pay KES" + viewUsers2.getBalance() + ".\nMPESA code generated will be required\n\nJIRANI SMART Pay Bill no:830409\nAccount: " + clientMode.getId_no());
                    } else if (Float.parseFloat(viewUsers2.getExpected_monthly()) > Float.parseFloat(viewUsers2.getBalance())) {
                        alert.setMessage("Hello " + clientMode.getFname() + ",\nyou will be expected to Pay KES" + viewUsers2.getBalance() + ".\nMPESA code generated will be required\n\nJIRANI SMART Pay Bill no:830409\nAccount: " + clientMode.getId_no());
                    } else {
                        alert.setMessage("Hello " + clientMode.getFname() + ",\nyou will be expected to Pay KES" + viewUsers2.getExpected_monthly() + ".\nMPESA code generated will be required\n\nJIRANI SMART Pay Bill no:830409\nAccount: " + clientMode.getId_no());
                    }
                    alert.setPositiveButton("submit", (gt, dd) -> {
                        AlertDialog.Builder my = new AlertDialog.Builder(this);
                        my.setTitle("One Month MPESA Payment");
                        rect = new Rect();
                        window = this.getWindow();
                        window.getDecorView().getWindowVisibleDisplayFrame(rect);
                        layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        viewer = layoutInflater.inflate(R.layout.period_entry, null);
                        mpesa = viewer.findViewById(R.id.amountLoan);
                        amount = viewer.findViewById(R.id.interestRate);
                        period = viewer.findViewById(R.id.repayMentP);
                        btnEx = viewer.findViewById(R.id.btnClose);
                        btnSu = viewer.findViewById(R.id.btnSub);
                        if ((Float.parseFloat(viewUsers2.getExpected_monthly()) * 2) > Float.parseFloat(viewUsers2.getBalance())) {
                            amount.setText(viewUsers2.getBalance());
                        } else if (Float.parseFloat(viewUsers2.getExpected_monthly()) > Float.parseFloat(viewUsers2.getBalance())) {
                            amount.setText(viewUsers2.getBalance());
                        } else {
                            amount.setText(viewUsers2.getExpected_monthly());
                        }
                        period.setText("1");
                        period.setEnabled(false);
                        amount.setEnabled(false);
                        my.setView(viewer);
                        Dialog demo = my.create();
                        btnEx.setOnClickListener(view1 -> {
                            demo.cancel();
                        });
                        btnSu.setOnClickListener(view1 -> {
                            final String mPesa = mpesa.getText().toString().trim();
                            final String mAmo = amount.getText().toString().trim();
                            final String mPer = period.getText().toString().trim();
                            if (mPesa.isEmpty()) {
                                mpesa.setError("??");
                                mpesa.requestFocus();
                            } else if (mPesa.length() < 10) {
                                mpesa.setError("10 Characters??");
                                mpesa.requestFocus();
                            } else {
                                double sum = Float.parseFloat(mAmo) * Float.parseFloat(mPer);
                                String monthly = String.format("%.0f", sum);
                                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                                requestQueue.add(new StringRequest(Request.Method.POST, URls.clientPa,
                                        response -> {
                                            try {
                                                JSONObject jsonObject = new JSONObject(response);
                                                int st = jsonObject.getInt("status");
                                                String msg = jsonObject.getString("message");
                                                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                                if (st == 1) {
                                                    startActivity(new Intent(getApplicationContext(), HistPay.class));
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
                                        para.put("mpesa", mPesa);
                                        para.put("amount", mAmo);
                                        para.put("current_period", mPer);
                                        para.put("borrow_id", viewUsers2.getBorrow_id());
                                        para.put("id_no", clientMode.getId_no());
                                        para.put("name", viewUsers2.getName());
                                        para.put("phone", clientMode.getPhone());
                                        para.put("interest", viewUsers2.getInterest());
                                        para.put("period", viewUsers2.getLoan_period());
                                        para.put("expected", viewUsers2.getExpected_monthly());
                                        para.put("total", monthly);
                                        return para;
                                    }
                                });
                            }//borrow_id,mpesa,id_no,phone,amount,current_period,total

                        });
                        demo.getWindow().setGravity(Gravity.BOTTOM);
                        demo.setCancelable(false);
                        demo.setCanceledOnTouchOutside(false);
                        demo.show();
                    });
                    alert.setNegativeButton("close", (gt, dd) -> gt.cancel());
                    AlertDialog doo = alert.create();
                    doo.setCancelable(false);
                    doo.setCanceledOnTouchOutside(false);
                    doo.show();
                });
            } else if (Float.parseFloat(viewUsers2.getExpected_monthly()) > Float.parseFloat(viewUsers2.getBalance())) {
                builder.setPositiveButton("1-Period", (oo, o) -> {
                    AlertDialog.Builder alert = new AlertDialog.Builder(this);
                    alert.setTitle("MPESA payment for One Period");
                    if ((Float.parseFloat(viewUsers2.getExpected_monthly()) * 2) > Float.parseFloat(viewUsers2.getBalance())) {
                        alert.setMessage("Hello " + clientMode.getFname() + ",\nyou will be expected to Pay KES" + viewUsers2.getBalance() + ".\nMPESA code generated will be required\n\nJIRANI SMART Pay Bill no:830409\nAccount: " + clientMode.getId_no());
                    } else if (Float.parseFloat(viewUsers2.getExpected_monthly()) > Float.parseFloat(viewUsers2.getBalance())) {
                        alert.setMessage("Hello " + clientMode.getFname() + ",\nyou will be expected to Pay KES" + viewUsers2.getBalance() + ".\nMPESA code generated will be required\n\nJIRANI SMART Pay Bill no:830409\nAccount: " + clientMode.getId_no());
                    } else {
                        alert.setMessage("Hello " + clientMode.getFname() + ",\nyou will be expected to Pay KES" + viewUsers2.getExpected_monthly() + ".\nMPESA code generated will be required\n\nJIRANI SMART Pay Bill no:830409\nAccount: " + clientMode.getId_no());
                    }
                    alert.setPositiveButton("submit", (gt, dd) -> {
                        AlertDialog.Builder my = new AlertDialog.Builder(this);
                        my.setTitle("One Month MPESA Payment");
                        rect = new Rect();
                        window = this.getWindow();
                        window.getDecorView().getWindowVisibleDisplayFrame(rect);
                        layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        viewer = layoutInflater.inflate(R.layout.period_entry, null);
                        mpesa = viewer.findViewById(R.id.amountLoan);
                        amount = viewer.findViewById(R.id.interestRate);
                        period = viewer.findViewById(R.id.repayMentP);
                        btnEx = viewer.findViewById(R.id.btnClose);
                        btnSu = viewer.findViewById(R.id.btnSub);
                        if ((Float.parseFloat(viewUsers2.getExpected_monthly()) * 2) > Float.parseFloat(viewUsers2.getBalance())) {
                            amount.setText(viewUsers2.getBalance());
                        } else if (Float.parseFloat(viewUsers2.getExpected_monthly()) > Float.parseFloat(viewUsers2.getBalance())) {
                            amount.setText(viewUsers2.getBalance());
                        } else {
                            amount.setText(viewUsers2.getExpected_monthly());
                        }
                        period.setText("1");
                        period.setEnabled(false);
                        amount.setEnabled(false);
                        my.setView(viewer);
                        Dialog demo = my.create();
                        btnEx.setOnClickListener(view1 -> {
                            demo.cancel();
                        });
                        btnSu.setOnClickListener(view1 -> {
                            final String mPesa = mpesa.getText().toString().trim();
                            final String mAmo = amount.getText().toString().trim();
                            final String mPer = period.getText().toString().trim();
                            if (mPesa.isEmpty()) {
                                mpesa.setError("??");
                                mpesa.requestFocus();
                            } else if (mPesa.length() < 10) {
                                mpesa.setError("10 Characters??");
                                mpesa.requestFocus();
                            } else {
                                double sum = Float.parseFloat(mAmo) * Float.parseFloat(mPer);
                                String monthly = String.format("%.0f", sum);
                                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                                requestQueue.add(new StringRequest(Request.Method.POST, URls.clientPa,
                                        response -> {
                                            try {
                                                JSONObject jsonObject = new JSONObject(response);
                                                int st = jsonObject.getInt("status");
                                                String msg = jsonObject.getString("message");
                                                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                                if (st == 1) {
                                                    startActivity(new Intent(getApplicationContext(), HistPay.class));
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
                                        para.put("mpesa", mPesa);
                                        para.put("amount", mAmo);
                                        para.put("current_period", mPer);
                                        para.put("borrow_id", viewUsers2.getBorrow_id());
                                        para.put("id_no", clientMode.getId_no());
                                        para.put("name", viewUsers2.getName());
                                        para.put("phone", clientMode.getPhone());
                                        para.put("interest", viewUsers2.getInterest());
                                        para.put("period", viewUsers2.getLoan_period());
                                        para.put("expected", viewUsers2.getExpected_monthly());
                                        para.put("total", monthly);
                                        return para;
                                    }
                                });
                            }//borrow_id,mpesa,id_no,phone,amount,current_period,total

                        });
                        demo.getWindow().setGravity(Gravity.BOTTOM);
                        demo.setCancelable(false);
                        demo.setCanceledOnTouchOutside(false);
                        demo.show();
                    });
                    alert.setNegativeButton("close", (gt, dd) -> gt.cancel());
                    AlertDialog doo = alert.create();
                    doo.setCancelable(false);
                    doo.setCanceledOnTouchOutside(false);
                    doo.show();
                });
                builder.setMessage("ApplicantID: " + viewUsers2.getId_no() + "\nName: " + viewUsers2.getName() + "\nPhone: " + viewUsers2.getPhone() + "\n\nIssuedLoan: KES" + viewUsers2.getLoan() + "\nAmountExpected: KES" + viewUsers2.getTotal() + "\n\nRepaymentPeriod: " + viewUsers2.getPeriod() + " month\nMonthlyPay: KES" + viewUsers2.getBalance() + "\n\nCurrentBalance :" + viewUsers2.getBalance() + "\n\n" + Html.fromHtml("<font><b><i><u>How do you want to PAY?</u></i></b><br>Select One_Period</font>"));
            } else {
                builder.setMessage("ApplicantID: " + viewUsers2.getId_no() + "\nName: " + viewUsers2.getName() + "\nPhone: " + viewUsers2.getPhone() + "\n\nIssuedLoan: KES" + viewUsers2.getLoan() + "\nAmountExpected: KES" + viewUsers2.getTotal() + "\n\nRepaymentPeriod: " + viewUsers2.getPeriod() + " months\nMonthlyPay: KES" + viewUsers2.getExpected_monthly() + "\n\nCurrentBalance :" + viewUsers2.getBalance() + "\n\n" + Html.fromHtml("<font><b><i><u>How do you want to PAY?</u></i></b><br>Select One_Period or Multi_Period below</font>"));
                builder.setPositiveButton("1-Period", (oo, o) -> {
                    AlertDialog.Builder alert = new AlertDialog.Builder(this);
                    alert.setTitle("MPESA payment for One Period");
                    if ((Float.parseFloat(viewUsers2.getExpected_monthly()) * 2) > Float.parseFloat(viewUsers2.getBalance())) {
                        alert.setMessage("Hello " + clientMode.getFname() + ",\nyou will be expected to Pay KES" + viewUsers2.getBalance() + ".\nMPESA code generated will be required\n\nJIRANI SMART Pay Bill no:830409\nAccount: " + clientMode.getId_no());
                    } else if (Float.parseFloat(viewUsers2.getExpected_monthly()) > Float.parseFloat(viewUsers2.getBalance())) {
                        alert.setMessage("Hello " + clientMode.getFname() + ",\nyou will be expected to Pay KES" + viewUsers2.getBalance() + ".\nMPESA code generated will be required\n\nJIRANI SMART Pay Bill no:830409\nAccount: " + clientMode.getId_no());
                    } else {
                        alert.setMessage("Hello " + clientMode.getFname() + ",\nyou will be expected to Pay KES" + viewUsers2.getExpected_monthly() + ".\nMPESA code generated will be required\n\nJIRANI SMART Pay Bill no:830409\nAccount: " + clientMode.getId_no());
                    }
                    alert.setPositiveButton("submit", (gt, dd) -> {
                        AlertDialog.Builder my = new AlertDialog.Builder(this);
                        my.setTitle("One Month MPESA Payment");
                        rect = new Rect();
                        window = this.getWindow();
                        window.getDecorView().getWindowVisibleDisplayFrame(rect);
                        layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        viewer = layoutInflater.inflate(R.layout.period_entry, null);
                        mpesa = viewer.findViewById(R.id.amountLoan);
                        amount = viewer.findViewById(R.id.interestRate);
                        period = viewer.findViewById(R.id.repayMentP);
                        btnEx = viewer.findViewById(R.id.btnClose);
                        btnSu = viewer.findViewById(R.id.btnSub);
                        if ((Float.parseFloat(viewUsers2.getExpected_monthly()) * 2) > Float.parseFloat(viewUsers2.getBalance())) {
                            amount.setText(viewUsers2.getBalance());
                        } else if (Float.parseFloat(viewUsers2.getExpected_monthly()) > Float.parseFloat(viewUsers2.getBalance())) {
                            amount.setText(viewUsers2.getBalance());
                        } else {
                            amount.setText(viewUsers2.getExpected_monthly());
                        }
                        period.setText("1");
                        period.setEnabled(false);
                        amount.setEnabled(false);
                        my.setView(viewer);
                        Dialog demo = my.create();
                        btnEx.setOnClickListener(view1 -> {
                            demo.cancel();
                        });
                        btnSu.setOnClickListener(view1 -> {
                            final String mPesa = mpesa.getText().toString().trim();
                            final String mAmo = amount.getText().toString().trim();
                            final String mPer = period.getText().toString().trim();
                            if (mPesa.isEmpty()) {
                                mpesa.setError("??");
                                mpesa.requestFocus();
                            } else if (mPesa.length() < 10) {
                                mpesa.setError("10 Characters??");
                                mpesa.requestFocus();
                            } else {
                                double sum = Float.parseFloat(mAmo) * Float.parseFloat(mPer);
                                String monthly = String.format("%.0f", sum);
                                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                                requestQueue.add(new StringRequest(Request.Method.POST, URls.clientPa,
                                        response -> {
                                            try {
                                                JSONObject jsonObject = new JSONObject(response);
                                                int st = jsonObject.getInt("status");
                                                String msg = jsonObject.getString("message");
                                                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                                if (st == 1) {
                                                    startActivity(new Intent(getApplicationContext(), HistPay.class));
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
                                        para.put("mpesa", mPesa);
                                        para.put("amount", mAmo);
                                        para.put("current_period", mPer);
                                        para.put("borrow_id", viewUsers2.getBorrow_id());
                                        para.put("id_no", clientMode.getId_no());
                                        para.put("name", viewUsers2.getName());
                                        para.put("phone", clientMode.getPhone());
                                        para.put("interest", viewUsers2.getInterest());
                                        para.put("period", viewUsers2.getLoan_period());
                                        para.put("expected", viewUsers2.getExpected_monthly());
                                        para.put("total", monthly);
                                        return para;
                                    }
                                });
                            }//borrow_id,mpesa,id_no,phone,amount,current_period,total

                        });
                        demo.getWindow().setGravity(Gravity.BOTTOM);
                        demo.setCancelable(false);
                        demo.setCanceledOnTouchOutside(false);
                        demo.show();
                    });
                    alert.setNegativeButton("close", (gt, dd) -> gt.cancel());
                    AlertDialog doo = alert.create();
                    doo.setCancelable(false);
                    doo.setCanceledOnTouchOutside(false);
                    doo.show();
                });
                builder.setNeutralButton("Multi-Period", (oo, o) -> {
                    AlertDialog.Builder alert = new AlertDialog.Builder(this);
                    alert.setTitle("MPESA payment for many Periods");
                    alert.setMessage("Hello " + clientMode.getFname() + ",\nyou will be expected to enter Amount paid and and number of periods.\nMPESA code generated will be required\n\nJIRANI SMART Pay Bill no:830409\nAccount: " + clientMode.getId_no());
                    alert.setPositiveButton("submit", (gt, dd) -> {
                        AlertDialog.Builder my = new AlertDialog.Builder(this);
                        my.setTitle("Many Months MPESA Payment");
                        rect = new Rect();
                        window = this.getWindow();
                        window.getDecorView().getWindowVisibleDisplayFrame(rect);
                        layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        viewer = layoutInflater.inflate(R.layout.period_entry, null);
                        mpesa = viewer.findViewById(R.id.amountLoan);
                        amount = viewer.findViewById(R.id.interestRate);
                        period = viewer.findViewById(R.id.repayMentP);
                        btnEx = viewer.findViewById(R.id.btnClose);
                        btnSu = viewer.findViewById(R.id.btnSub);
                        amount.setHint("not Below " + viewUsers2.getExpected_monthly());
                        my.setView(viewer);
                        Dialog demo = my.create();
                        btnEx.setOnClickListener(view1 -> {
                            demo.cancel();
                        });
                        btnSu.setOnClickListener(view1 -> {
                            final String mPesa = mpesa.getText().toString().trim();
                            final String mAmo = amount.getText().toString().trim();
                            final String mPer = period.getText().toString().trim();
                            if (mAmo.isEmpty()) {
                                amount.setError("??");
                                amount.requestFocus();
                            } else if (Float.parseFloat(mAmo) < Float.parseFloat(viewUsers2.getExpected_monthly())) {
                                amount.setError("Must be above KES" + viewUsers2.getExpected_monthly());
                                amount.requestFocus();
                            } else if (Float.parseFloat(mAmo) > Float.parseFloat(viewUsers2.getBalance())) {
                                amount.setError("Beyond Balance KES" + viewUsers2.getBalance());
                                amount.requestFocus();
                            } else if (mPer.isEmpty()) {
                                period.setError("??");
                                period.requestFocus();
                            } else if (Float.parseFloat(mPer) < 2) {
                                period.setError("must be more than 1");
                                period.requestFocus();
                            } else if (Float.parseFloat(mPer) > Float.parseFloat(viewUsers2.getPeriod())) {
                                period.setError("Why Above " + viewUsers2.getPeriod() + " months??");
                                period.requestFocus();
                            } else if (mPesa.isEmpty()) {
                                mpesa.setError("??");
                                mpesa.requestFocus();
                            } else if (mPesa.length() < 10) {
                                mpesa.setError("10 Characters??");
                                mpesa.requestFocus();
                            } else {
                                double sum = Float.parseFloat(mAmo) * Float.parseFloat(mPer);
                                String monthly = String.format("%.0f", sum);
                                if (sum > Float.parseFloat(viewUsers2.getBalance())) {
                                    AlertDialog.Builder yee = new AlertDialog.Builder(this);
                                    yee.setTitle("Beyond Limit");
                                    yee.setMessage("Oops!! Your CurrentBalance\nis KES " + viewUsers2.getBalance() + "\nYour Summed Amount is Beyond\n\n" + mAmo + " X " + mPer + " = " + monthly + " ??");
                                    AlertDialog mn = yee.create();
                                    mn.getWindow().setGravity(Gravity.TOP);
                                    mn.show();
                                } else {
                                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                                    requestQueue.add(new StringRequest(Request.Method.POST, URls.clientPa,
                                            response -> {
                                                try {
                                                    JSONObject jsonObject = new JSONObject(response);
                                                    int st = jsonObject.getInt("status");
                                                    String msg = jsonObject.getString("message");
                                                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                                    if (st == 1) {
                                                        startActivity(new Intent(getApplicationContext(), HistPay.class));
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
                                            para.put("mpesa", mPesa);
                                            para.put("amount", mAmo);
                                            para.put("current_period", mPer);
                                            para.put("borrow_id", viewUsers2.getBorrow_id());
                                            para.put("id_no", clientMode.getId_no());
                                            para.put("name", viewUsers2.getName());
                                            para.put("phone", clientMode.getPhone());
                                            para.put("interest", viewUsers2.getInterest());
                                            para.put("period", viewUsers2.getLoan_period());
                                            para.put("expected", viewUsers2.getExpected_monthly());
                                            para.put("total", monthly);
                                            return para;
                                        }
                                    });
                                }//borrow_id,mpesa,id_no,phone,amount,current_period,total
                            }
                        });
                        demo.getWindow().setGravity(Gravity.BOTTOM);
                        demo.setCancelable(false);
                        demo.setCanceledOnTouchOutside(false);
                        demo.show();
                    });
                    alert.setNegativeButton("close", (gt, dd) -> gt.cancel());
                    AlertDialog doo = alert.create();
                    doo.setCancelable(false);
                    doo.setCanceledOnTouchOutside(false);
                    doo.show();
                });
            }
            builder.setNegativeButton("exit", (oo, o) -> oo.cancel());
            AlertDialog alertx = builder.create();
            alertx.setCancelable(false);
            alertx.setCanceledOnTouchOutside(false);
            alertx.show();
        });
        practical();
    }

    private void practical() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URls.code,
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
                                issue.setText("Total Balance KES " + balance);
                            }
                            produAda = new AppWeb(Deposit.this, R.layout.loaner, SubjectList);
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
                        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                    }

                }, error -> {
            Toast.makeText(this, "error in connection", Toast.LENGTH_SHORT).show();

        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id_no", clientMode.getId_no());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}