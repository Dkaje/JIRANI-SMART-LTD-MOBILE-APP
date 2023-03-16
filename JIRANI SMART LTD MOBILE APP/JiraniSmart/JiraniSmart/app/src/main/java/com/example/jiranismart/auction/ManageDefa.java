package com.example.jiranismart.auction;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.print.PrintManager;
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
import com.example.jiranismart.Fermented.ApplicaModel;
import com.example.jiranismart.Fermented.ApplicationAda;
import com.example.jiranismart.Fermented.GreatMatch;
import com.example.jiranismart.Fermented.GreateAda;
import com.example.jiranismart.Fermented.PrintAll;
import com.example.jiranismart.Fermented.URls;
import com.example.jiranismart.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ManageDefa extends AppCompatActivity {
    ApplicationAda produAda;
    ArrayList<ApplicaModel> SubjectList = new ArrayList<>();
    ApplicaModel viewUsers2;
    ListView listView;
    SearchView searchView;
    View view2, viewer;
    TextView textView;
    Dialog dialog, dail;
    Button btnSu, btnEx, print, issue;
    Rect rect;
    Window window;
    LayoutInflater layoutInflater;
    ArrayList<GreatMatch> greatMatches = new ArrayList<>();
    GreateAda viewAda;
    GreatMatch greatMatch;
    RequestQueue requestQueue;
    String messed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_defa);
        listView = findViewById(R.id.listedOne);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.search);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            viewUsers2 = (ApplicaModel) parent.getItemAtPosition(position);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(Html.fromHtml("<font><b><u>" + viewUsers2.getName() + "</u></b> Loan Details</font>"));
            rect = new Rect();
            window = this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view2 = layoutInflater.inflate(R.layout.armison, null);
            textView = view2.findViewById(R.id.texter);
            btnSu = view2.findViewById(R.id.btnDis);
            btnEx = view2.findViewById(R.id.btnClose);
            print = view2.findViewById(R.id.btnPrint);
            btnSu.setText("Collateral");
            if (viewUsers2.getTotal().equals(viewUsers2.getBalance())) {
                textView.setText("ApplicantID: " + viewUsers2.getId_no() + "\nName: " + viewUsers2.getName() + "\nPhone: " + viewUsers2.getPhone() + "\n\nMaxLoanLimit: KES" + viewUsers2.getMaxloan() + "\n\nLoan: KES" + viewUsers2.getLoan() + "\nTotalExpected: KES" + viewUsers2.getTotal() + "\nRepaymentPeriod: " + viewUsers2.getLoan_period() + " months\nMonthlyPay: KES" + viewUsers2.getExpected_monthly() + "\nApproval :" + viewUsers2.getStatus() + "\nClearance :" + viewUsers2.getFina_status());
            } else {
                if (viewUsers2.getBalance().equals("0")) {
                    textView.setText("ApplicantID: " + viewUsers2.getId_no() + "\nName: " + viewUsers2.getName() + "\nPhone: " + viewUsers2.getPhone() + "\n\nMaxLoanLimit: KES" + viewUsers2.getMaxloan() + "\n\nLoan: KES" + viewUsers2.getLoan() + "\nTotalExpected: KES" + viewUsers2.getBalance() + "\nApproval :" + viewUsers2.getStatus() + "\nRepayment: " + viewUsers2.getPayment_status() + "\nClearance :" + viewUsers2.getFina_status());
                } else {
                    textView.setText("ApplicantID: " + viewUsers2.getId_no() + "\nName: " + viewUsers2.getName() + "\nPhone: " + viewUsers2.getPhone() + "\n\nMaxLoanLimit: KES" + viewUsers2.getMaxloan() + "\n\nLoan: KES" + viewUsers2.getLoan() + "\nTotalExpected: KES" + viewUsers2.getBalance() + "\nRepaymentPeriod: " + viewUsers2.getPeriod() + " months\nMonthlyPay: KES" + viewUsers2.getExpected_monthly() + "\nApproval :" + viewUsers2.getStatus() + "\nClearance :" + viewUsers2.getFina_status());
                }
            }
            builder.setView(view2);
            print.setOnClickListener(view1 -> {
                printMe();
            });
            dialog = builder.create();
            btnEx.setOnClickListener(view1 -> {
                dialog.cancel();
            });
            btnSu.setOnClickListener(view1 -> {
                requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(new StringRequest(Request.Method.POST, URls.mycolla,
                        response -> {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                Log.e("response ", response);
                                int success = jsonObject.getInt("trust");
                                if (success == 1) {
                                    JSONArray jsonArray = jsonObject.getJSONArray("victory");
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        jsonObject = jsonArray.getJSONObject(i);
                                        String reg_id = jsonObject.getString("reg_id");
                                        String id_no = jsonObject.getString("id_no");
                                        String name = jsonObject.getString("name");
                                        String phone = jsonObject.getString("phone");
                                        String email = jsonObject.getString("email");
                                        String county = jsonObject.getString("county");
                                        String location = jsonObject.getString("location");
                                        String agent_email = jsonObject.getString("agent_email");
                                        String category_one = jsonObject.getString("category_one");
                                        String type_one = jsonObject.getString("type_one");
                                        String existence_one = jsonObject.getString("existence_one");
                                        String worth_one = jsonObject.getString("worth_one");
                                        String image_one = jsonObject.getString("image_one");
                                        String imag = URls.img + image_one;
                                        String one_sta = jsonObject.getString("one_sta");
                                        String category_two = jsonObject.getString("category_two");
                                        String type_two = jsonObject.getString("type_two");
                                        String existence_two = jsonObject.getString("existence_two");
                                        String worth_two = jsonObject.getString("worth_two");
                                        String image_two = jsonObject.getString("image_two");
                                        String image = URls.img + image_two;
                                        String two_sta = jsonObject.getString("two_sta");
                                        String category_three = jsonObject.getString("category_three");
                                        String type_three = jsonObject.getString("type_three");
                                        String existence_three = jsonObject.getString("existence_three");
                                        String worth_three = jsonObject.getString("worth_three");
                                        String image_three = jsonObject.getString("image_three");
                                        String imagery = URls.img + image_three;
                                        String three_sta = jsonObject.getString("three_sta");
                                        String status = jsonObject.getString("status");
                                        String status_auc = jsonObject.getString("status_auc");
                                        String reg_date = jsonObject.getString("reg_date");
                                        greatMatch = new GreatMatch(reg_id, id_no, name, phone, email, county, location, agent_email,
                                                category_one, type_one, existence_one, worth_one, imag, one_sta, category_two, type_two, existence_two,
                                                worth_two, image, two_sta, category_three, type_three, existence_three, worth_three, imagery, three_sta, status, status_auc, reg_date);
                                        greatMatches.add(greatMatch);
                                        AlertDialog.Builder alert = new AlertDialog.Builder(this);
                                        alert.setTitle(Html.fromHtml("<font><b><u>" + name + "</u></b> Collateral Details</font>"));
                                        rect = new Rect();
                                        window = this.getWindow();
                                        window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                        layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                        viewer = layoutInflater.inflate(R.layout.armison, null);
                                        textView = viewer.findViewById(R.id.texter);
                                        btnSu = viewer.findViewById(R.id.btnDis);
                                        btnEx = viewer.findViewById(R.id.btnClose);
                                        print = viewer.findViewById(R.id.btnPrint);
                                        if (!worth_one.equals("0") & !worth_two.equals("0") & !worth_three.equals("0")) {
                                            double summed = Float.parseFloat(worth_one) + Float.parseFloat(worth_two) + Float.parseFloat(worth_three);
                                            messed = String.format("%.0f", summed);
                                            textView.setText("Collateral Details\n\nCustomer: " + name + "\nIDno: " + id_no + "\nPhone: " + phone + "\n\n" + category_one + ": KES" + worth_one + "\n" + category_two + ": KES" + worth_two + "\n" + category_three + ": KES" + worth_three + "\n\nTotalCost KES" + messed);
                                        } else if (!worth_one.equals("0") & !worth_two.equals("0") & worth_three.equals("0")) {
                                            double summed = Float.parseFloat(worth_one) + Float.parseFloat(worth_two);
                                            messed = String.format("%.0f", summed);
                                            textView.setText("Collateral Details\n\nCustomer: " + name + "\nIDno: " + id_no + "\nPhone: " + phone + "\n\n" + category_one + ": KES" + worth_one + "\n" + category_two + ": KES" + worth_two + "\n\nTotalCost KES" + messed);
                                        } else if (!worth_one.equals("0") & worth_two.equals("0") & !worth_three.equals("0")) {
                                            double summed = Float.parseFloat(worth_one) + Float.parseFloat(worth_three);
                                            messed = String.format("%.0f", summed);
                                            textView.setText("Collateral Details\n\nCustomer: " + name + "\nIDno: " + id_no + "\nPhone: " + phone + "\n\n" + category_one + ": KES" + worth_one + "\n" + category_three + ": KES" + worth_three + "\n\nTotalCost KES" + messed);
                                        } else if (worth_one.equals("0") & !worth_two.equals("0") & !worth_three.equals("0")) {
                                            double summed = Float.parseFloat(worth_two) + Float.parseFloat(worth_three);
                                            messed = String.format("%.0f", summed);
                                            textView.setText("Collateral Details\n\nCustomer: " + name + "\nIDno: " + id_no + "\nPhone: " + phone + "\n\n" + category_two + ": KES" + worth_two + "\n" + category_three + ": KES" + worth_three + "\n\nTotalCost KES" + messed);
                                        } else if (!worth_one.equals("0") & worth_two.equals("0") & worth_three.equals("0")) {
                                            double summed = Float.parseFloat(worth_one);
                                            messed = String.format("%.0f", summed);
                                            textView.setText("Collateral Details\n\nCustomer: " + name + "\nIDno: " + id_no + "\nPhone: " + phone + "\n\n" + category_one + ": KES" + worth_one + "\n\nTotalCost KES" + messed);
                                        } else if (worth_one.equals("0") & !worth_two.equals("0") & worth_three.equals("0")) {
                                            double summed = Float.parseFloat(worth_two);
                                            messed = String.format("%.0f", summed);
                                            textView.setText("Collateral Details\n\nCustomer: " + name + "\nIDno: " + id_no + "\nPhone: " + phone + "\n\n" + category_two + ": KES" + worth_two + "\n\nTotalCost KES" + messed);
                                        } else if (worth_one.equals("0") & worth_two.equals("0") & !worth_three.equals("0")) {
                                            double summed = Float.parseFloat(worth_three);
                                            messed = String.format("%.0f", summed);
                                            textView.setText("Collateral Details\n\nCustomer: " + name + "\nIDno: " + id_no + "\nPhone: " + phone + "\n\n" + category_three + ": KES" + worth_three + "\n\nTotalCost KES" + messed);
                                        }
                                        btnSu.setText("Action");
                                        alert.setView(viewer);
                                        print.setOnClickListener(ve -> {
                                            prittif();
                                        });
                                        dail = alert.create();
                                        btnEx.setOnClickListener(ve -> {
                                            dail.cancel();//@@@2022Nimro
                                        });
                                        btnSu.setOnClickListener(view3 -> {
                                            AlertDialog.Builder ded = new AlertDialog.Builder(this);
                                            ded.setTitle("Collateral TakeOver");
                                            if (Float.parseFloat(worth_one) >= Float.parseFloat(viewUsers2.getBalance())) {
                                                if (viewUsers2.getPayment_status().equals("Pending")) {
                                                    ded.setMessage("Seizing " + category_one + "\nWorth KES" + worth_one + "\nFor defaulted KES" + viewUsers2.getBalance() + "\nClick Submit Below below");
                                                    ded.setPositiveButton("submit", (uj, jj) -> {
                                                        requestQueue = Volley.newRequestQueue(getApplicationContext());
                                                        requestQueue.add(new StringRequest(Request.Method.POST, URls.lipaColl,
                                                                response1 -> {
                                                                    try {
                                                                        JSONObject jsonObject1 = new JSONObject(response1);
                                                                        int st = jsonObject1.getInt("status");
                                                                        String msg = jsonObject1.getString("message");
                                                                        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                                                        if (st == 1) {
                                                                            startActivity(new Intent(getApplicationContext(), ManageDefa.class));
                                                                            finish();
                                                                        }
                                                                    } catch (Exception e) {
                                                                        e.printStackTrace();
                                                                        Toast.makeText(this, "error occurred", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                }, error -> {
                                                            Toast.makeText(this, "connection error", Toast.LENGTH_SHORT).show();
                                                        }) {
                                                            @Override
                                                            protected Map<String, String> getParams() {
                                                                Map<String, String> params = new HashMap<>();
                                                                params.put("reg_id", reg_id);
                                                                params.put("one_sta", "Seized");
                                                                params.put("two_sta", "Pending");
                                                                params.put("three_sta", "Pending");
                                                                params.put("borrow_id", viewUsers2.getBorrow_id());
                                                                return params;
                                                            }
                                                        });
                                                    });
                                                } else {
                                                    ded.setMessage("Seized " + category_one + "\nWorth KES" + worth_one + "\nFor defaulted KES" + viewUsers2.getBalance() + "\n\nTakeOver: " + viewUsers2.getPayment_status());
                                                }
                                                //reg_id,one_sta,two_sta,three_sta
                                            } else if (Float.parseFloat(worth_two) >= Float.parseFloat(viewUsers2.getBalance())) {
                                                if (viewUsers2.getPayment_status().equals("Pending")) {
                                                    ded.setMessage("Seizing " + category_two + "\nWorth KES" + worth_two + "\nFor defaulted KES" + viewUsers2.getBalance());
                                                    ded.setPositiveButton("submit", (uj, jj) -> {
                                                        requestQueue = Volley.newRequestQueue(getApplicationContext());
                                                        requestQueue.add(new StringRequest(Request.Method.POST, URls.lipaColl,
                                                                response1 -> {
                                                                    try {
                                                                        JSONObject jsonObject1 = new JSONObject(response1);
                                                                        int st = jsonObject1.getInt("status");
                                                                        String msg = jsonObject1.getString("message");
                                                                        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                                                        if (st == 1) {
                                                                            startActivity(new Intent(getApplicationContext(), ManageDefa.class));
                                                                            finish();
                                                                        }
                                                                    } catch (Exception e) {
                                                                        e.printStackTrace();
                                                                        Toast.makeText(this, "error occurred", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                }, error -> {
                                                            Toast.makeText(this, "connection error", Toast.LENGTH_SHORT).show();
                                                        }) {
                                                            @Override
                                                            protected Map<String, String> getParams() {
                                                                Map<String, String> params = new HashMap<>();
                                                                params.put("reg_id", reg_id);
                                                                params.put("one_sta", "Pending");
                                                                params.put("two_sta", "Seized");
                                                                params.put("three_sta", "Pending");
                                                                params.put("borrow_id", viewUsers2.getBorrow_id());
                                                                return params;
                                                            }
                                                        });
                                                    });
                                                } else {
                                                    ded.setMessage("Seized " + category_two + "\nWorth KES" + worth_two + "\nFor defaulted KES" + viewUsers2.getBalance() + "\n\nTakeOver: " + viewUsers2.getPayment_status());
                                                }

                                            } else if (Float.parseFloat(worth_three) >= Float.parseFloat(viewUsers2.getBalance())) {
                                                if (viewUsers2.getPayment_status().equals("Pending")) {
                                                    ded.setMessage("Seizing " + category_three + "\nWorth KES" + worth_three + "\nFor defaulted KES" + viewUsers2.getBalance() + "\nClick Submit Below below");
                                                    ded.setPositiveButton("submit", (uj, jj) -> {
                                                        requestQueue = Volley.newRequestQueue(getApplicationContext());
                                                        requestQueue.add(new StringRequest(Request.Method.POST, URls.lipaColl,
                                                                response1 -> {
                                                                    try {
                                                                        JSONObject jsonObject1 = new JSONObject(response1);
                                                                        int st = jsonObject1.getInt("status");
                                                                        String msg = jsonObject1.getString("message");
                                                                        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                                                        if (st == 1) {
                                                                            startActivity(new Intent(getApplicationContext(), ManageDefa.class));
                                                                            finish();
                                                                        }
                                                                    } catch (Exception e) {
                                                                        e.printStackTrace();
                                                                        Toast.makeText(this, "error occurred", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                }, error -> {
                                                            Toast.makeText(this, "connection error", Toast.LENGTH_SHORT).show();
                                                        }) {
                                                            @Override
                                                            protected Map<String, String> getParams() {
                                                                Map<String, String> params = new HashMap<>();
                                                                params.put("reg_id", reg_id);
                                                                params.put("one_sta", "Pending");
                                                                params.put("two_sta", "Pending");
                                                                params.put("three_sta", "Seized");
                                                                params.put("borrow_id", viewUsers2.getBorrow_id());
                                                                return params;
                                                            }
                                                        });
                                                    });
                                                } else {
                                                    ded.setMessage("Seized " + category_three + "\nWorth KES" + worth_three + "\nFor defaulted KES" + viewUsers2.getBalance() + "\n\nTakeOver: " + viewUsers2.getPayment_status());
                                                }

                                            } else if ((Float.parseFloat(worth_two) + Float.parseFloat(worth_one)) >= Float.parseFloat(viewUsers2.getBalance())) {
                                                if (viewUsers2.getPayment_status().equals("Pending")) {
                                                    ded.setMessage("Seizing: (i) " + category_one + "\nWorth KES" + worth_one + "\n(ii)" + category_two + "\nWorth KES" + worth_two + "\nFor defaulted KES" + viewUsers2.getBalance() + "\nClick Submit Below below");
                                                    ded.setPositiveButton("submit", (uj, jj) -> {
                                                        requestQueue = Volley.newRequestQueue(getApplicationContext());
                                                        requestQueue.add(new StringRequest(Request.Method.POST, URls.lipaColl,
                                                                response1 -> {
                                                                    try {
                                                                        JSONObject jsonObject1 = new JSONObject(response1);
                                                                        int st = jsonObject1.getInt("status");
                                                                        String msg = jsonObject1.getString("message");
                                                                        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                                                        if (st == 1) {
                                                                            startActivity(new Intent(getApplicationContext(), ManageDefa.class));
                                                                            finish();
                                                                        }
                                                                    } catch (Exception e) {
                                                                        e.printStackTrace();
                                                                        Toast.makeText(this, "error occurred", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                }, error -> {
                                                            Toast.makeText(this, "connection error", Toast.LENGTH_SHORT).show();
                                                        }) {
                                                            @Override
                                                            protected Map<String, String> getParams() {
                                                                Map<String, String> params = new HashMap<>();
                                                                params.put("reg_id", reg_id);
                                                                params.put("one_sta", "Seized");
                                                                params.put("two_sta", "Seized");
                                                                params.put("three_sta", "Pending");
                                                                params.put("borrow_id", viewUsers2.getBorrow_id());
                                                                return params;
                                                            }
                                                        });
                                                    });
                                                } else {
                                                    ded.setMessage("Seized: (i) " + category_one + "\nWorth KES" + worth_one + "\n(ii)" + category_two + "\nWorth KES" + worth_two + "\nFor defaulted KES" + viewUsers2.getBalance() + "\n\nTakeOver: " + viewUsers2.getPayment_status());
                                                }

                                            } else if ((Float.parseFloat(worth_three) + Float.parseFloat(worth_one)) >= Float.parseFloat(viewUsers2.getBalance())) {
                                                if (viewUsers2.getPayment_status().equals("Pending")) {
                                                    ded.setMessage("Seizing: (i) " + category_one + "\nWorth KES" + worth_one + "\n(ii)" + category_three + "\nWorth KES" + worth_three + "\nFor defaulted KES" + viewUsers2.getBalance() + "\nClick Submit Below below");
                                                    ded.setPositiveButton("submit", (uj, jj) -> {
                                                        requestQueue = Volley.newRequestQueue(getApplicationContext());
                                                        requestQueue.add(new StringRequest(Request.Method.POST, URls.lipaColl,
                                                                response1 -> {
                                                                    try {
                                                                        JSONObject jsonObject1 = new JSONObject(response1);
                                                                        int st = jsonObject1.getInt("status");
                                                                        String msg = jsonObject1.getString("message");
                                                                        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                                                        if (st == 1) {
                                                                            startActivity(new Intent(getApplicationContext(), ManageDefa.class));
                                                                            finish();
                                                                        }
                                                                    } catch (Exception e) {
                                                                        e.printStackTrace();
                                                                        Toast.makeText(this, "error occurred", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                }, error -> {
                                                            Toast.makeText(this, "connection error", Toast.LENGTH_SHORT).show();
                                                        }) {
                                                            @Override
                                                            protected Map<String, String> getParams() {
                                                                Map<String, String> params = new HashMap<>();
                                                                params.put("reg_id", reg_id);
                                                                params.put("one_sta", "Seized");
                                                                params.put("two_sta", "Pending");
                                                                params.put("three_sta", "Seized");
                                                                params.put("borrow_id", viewUsers2.getBorrow_id());
                                                                return params;
                                                            }
                                                        });
                                                    });
                                                } else {
                                                    ded.setMessage("Seized: (i) " + category_one + "\nWorth KES" + worth_one + "\n(ii)" + category_three + "\nWorth KES" + worth_three + "\nFor defaulted KES" + viewUsers2.getBalance() + "\n\nTakeOver: " + viewUsers2.getPayment_status());
                                                }

                                            } else if ((Float.parseFloat(worth_three) + Float.parseFloat(worth_two)) >= Float.parseFloat(viewUsers2.getBalance())) {
                                                if (viewUsers2.getPayment_status().equals("Pending")) {
                                                    ded.setMessage("Seizing: (i) " + category_two + "\nWorth KES" + worth_two + "\n(ii)" + category_three + "\nWorth KES" + worth_three + "\nFor defaulted KES" + viewUsers2.getBalance() + "\nClick Submit Below below");
                                                    ded.setPositiveButton("submit", (uj, jj) -> {
                                                        requestQueue = Volley.newRequestQueue(getApplicationContext());
                                                        requestQueue.add(new StringRequest(Request.Method.POST, URls.lipaColl,
                                                                response1 -> {
                                                                    try {
                                                                        JSONObject jsonObject1 = new JSONObject(response1);
                                                                        int st = jsonObject1.getInt("status");
                                                                        String msg = jsonObject1.getString("message");
                                                                        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                                                        if (st == 1) {
                                                                            startActivity(new Intent(getApplicationContext(), ManageDefa.class));
                                                                            finish();
                                                                        }
                                                                    } catch (Exception e) {
                                                                        e.printStackTrace();
                                                                        Toast.makeText(this, "error occurred", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                }, error -> {
                                                            Toast.makeText(this, "connection error", Toast.LENGTH_SHORT).show();
                                                        }) {
                                                            @Override
                                                            protected Map<String, String> getParams() {
                                                                Map<String, String> params = new HashMap<>();
                                                                params.put("reg_id", reg_id);
                                                                params.put("one_sta", "Pending");
                                                                params.put("two_sta", "Seized");
                                                                params.put("three_sta", "Seized");
                                                                params.put("borrow_id", viewUsers2.getBorrow_id());
                                                                return params;
                                                            }
                                                        });
                                                    });
                                                } else {
                                                    ded.setMessage("Seized: (i) " + category_two + "\nWorth KES" + worth_two + "\n(ii)" + category_three + "\nWorth KES" + worth_three + "\nFor defaulted KES" + viewUsers2.getBalance() + "\n\nTakeOver: " + viewUsers2.getPayment_status());
                                                }

                                            } else if ((Float.parseFloat(worth_three) + Float.parseFloat(worth_two) + Float.parseFloat(worth_two)) >= Float.parseFloat(viewUsers2.getBalance())) {
                                                if (viewUsers2.getPayment_status().equals("Pending")) {
                                                    ded.setMessage("Seizing\n(i)" + category_one + "\nWorth KES" + worth_one + "\n(ii) " + category_two + "\nWorth KES" + worth_two + "\n(iii)" + category_three + "\nWorth KES" + worth_three + "\nFor defaulted KES" + viewUsers2.getBalance() + "\nClick Submit Below below");
                                                    ded.setPositiveButton("submit", (uj, jj) -> {
                                                        requestQueue = Volley.newRequestQueue(getApplicationContext());
                                                        requestQueue.add(new StringRequest(Request.Method.POST, URls.lipaColl,
                                                                response1 -> {
                                                                    try {
                                                                        JSONObject jsonObject1 = new JSONObject(response1);
                                                                        int st = jsonObject1.getInt("status");
                                                                        String msg = jsonObject1.getString("message");
                                                                        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                                                        if (st == 1) {
                                                                            startActivity(new Intent(getApplicationContext(), ManageDefa.class));
                                                                            finish();
                                                                        }
                                                                    } catch (Exception e) {
                                                                        e.printStackTrace();
                                                                        Toast.makeText(this, "error occurred", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                }, error -> {
                                                            Toast.makeText(this, "connection error", Toast.LENGTH_SHORT).show();
                                                        }) {
                                                            @Override
                                                            protected Map<String, String> getParams() {
                                                                Map<String, String> params = new HashMap<>();
                                                                params.put("reg_id", reg_id);
                                                                params.put("one_sta", "Seized");
                                                                params.put("two_sta", "Seized");
                                                                params.put("three_sta", "Seized");
                                                                params.put("borrow_id", viewUsers2.getBorrow_id());
                                                                return params;
                                                            }
                                                        });
                                                    });
                                                } else {
                                                    ded.setMessage("Seized\n(i)" + category_one + "\nWorth KES" + worth_one + "\n(ii) " + category_two + "\nWorth KES" + worth_two + "\n(iii)" + category_three + "\nWorth KES" + worth_three + "\nFor defaulted KES" + viewUsers2.getBalance() + "\n\nTakeOver: " + viewUsers2.getPayment_status());
                                                }

                                            }
                                            ded.setNegativeButton("close", (uj, jj) -> uj.cancel());
                                            AlertDialog som = ded.create();
                                            som.setCanceledOnTouchOutside(false);
                                            som.setCancelable(false);
                                            som.show();
                                        });
                                        dail.setCancelable(false);
                                        dail.setCanceledOnTouchOutside(false);
                                        dail.getWindow().setGravity(Gravity.BOTTOM);
                                        dail.show();
                                    }
                                } else if (success == 0) {
                                    String msg = jsonObject.getString("mine");
                                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
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
                        para.put("id_no", viewUsers2.getId_no());
                        return para;
                    }
                });
            });
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.getWindow().setGravity(Gravity.TOP);
            dialog.show();
        });
        practical();
    }

    private void prittif() {
        PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
        printManager.print(getString(R.string.app_name), new PrintAll(this, viewer.findViewById(R.id.relative)), null);
    }

    private void printMe() {
        PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
        printManager.print(getString(R.string.app_name), new PrintAll(this, view2.findViewById(R.id.relative)), null);

    }

    private void practical() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URls.getAllD,
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
                            produAda = new ApplicationAda(ManageDefa.this, R.layout.attach_agen, SubjectList);
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