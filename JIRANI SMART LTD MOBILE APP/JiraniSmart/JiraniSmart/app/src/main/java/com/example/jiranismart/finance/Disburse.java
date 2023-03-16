package com.example.jiranismart.finance;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.print.PrintManager;
import android.text.Html;
import android.text.InputFilter;
import android.text.InputType;
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
import com.example.jiranismart.Fermented.AppModel;
import com.example.jiranismart.Fermented.Appad;
import com.example.jiranismart.Fermented.PrintAll;
import com.example.jiranismart.Fermented.URls;
import com.example.jiranismart.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Disburse extends AppCompatActivity {
    Appad produAda;
    ArrayList<AppModel> SubjectList = new ArrayList<>();
    AppModel viewUsers2;
    ListView listView;
    SearchView searchView;
    View view2, viewer;
    TextView textView;
    Dialog dialog;
    Button btnSu, btnEx, print, issue;
    String amount, disburesed, balance;
    Rect rect;
    Window window;
    LayoutInflater layoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Disburse Loan");
        setContentView(R.layout.activity_disburse);
        listView = findViewById(R.id.listedOne);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.search);
        issue = findViewById(R.id.btnBal);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            viewUsers2 = (AppModel) parent.getItemAtPosition(position);
            AlertDialog.Builder meja = new AlertDialog.Builder(this);
            meja.setTitle("Disbursement");
            rect = new Rect();
            window = this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view2 = layoutInflater.inflate(R.layout.armison, null);
            textView = view2.findViewById(R.id.texter);
            btnSu = view2.findViewById(R.id.btnDis);
            btnEx = view2.findViewById(R.id.btnClose);
            print = view2.findViewById(R.id.btnPrint);
            meja.setView(view2);
            textView.setText(Html.fromHtml("<font>JIRANI SMART LIMITED<br>P.O.Box 25-80305,<br>Mwatate-Kenya<br><b><u>Applicant Details</u></b><br>IDNo: " + viewUsers2.getId_no() + "<br>Name: " + viewUsers2.getName() + "<br>Phone: " + viewUsers2.getPhone() + "<br><br><b><u>Loan Details</u></b><br>LoanID: " + viewUsers2.getLoan_id() + "<br>LoanAmount: KES" + viewUsers2.getLoan() + "<br>BorrowRef: " + viewUsers2.getBorrow_id() + "<br>AuctioneerStatus: " + viewUsers2.getAuction() + "<br><br>Disbursement: " + viewUsers2.getStatus() + "</font>"));
            print.setOnClickListener(view1 -> {
                printMe();
            });//borrow_id,loan_id,id_no,name,phone,loan,status,fina_status,reg_date
            dialog = meja.create();
            btnSu.setOnClickListener(view1 -> {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URls.loaned,
                        response -> {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                Log.e("response ", response);
                                int success = jsonObject.getInt("trust");
                                if (success == 1) {
                                    JSONArray jsonArray = jsonObject.getJSONArray("victory");
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        jsonObject = jsonArray.getJSONObject(i);
                                        amount = jsonObject.getString("amount");
                                        disburesed = jsonObject.getString("disbursed");
                                        balance = jsonObject.getString("balance");
                                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                        builder.setTitle("Confirm Disbursement");
                                        rect = new Rect();
                                        window = this.getWindow();
                                        window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                        layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                        viewer = layoutInflater.inflate(R.layout.armison, null);
                                        textView = viewer.findViewById(R.id.texter);
                                        btnSu = viewer.findViewById(R.id.btnDis);
                                        btnEx = viewer.findViewById(R.id.btnClose);
                                        print = viewer.findViewById(R.id.btnPrint);
                                        textView.setText(Html.fromHtml("<font>JIRANI SMART LIMITED<br>P.O.Box 25-80305,<br>Mwatate-Kenya<br>ApplicantID: " + viewUsers2.getId_no() + "<br><b><u>Loan</u></b> KES" + viewUsers2.getLoan() + "<br><hr><u><b>System Account Details</b></u><br>TotalDisbursement: KES" + disburesed + "<br><br>CurrentBalance: KES" + balance + "</font>"));
                                        print.setOnClickListener(vie -> {
                                            printUs();
                                        });
                                        builder.setView(viewer);
                                        AlertDialog ded = builder.create();
                                        btnEx.setOnClickListener(vie -> {
                                            ded.cancel();
                                        });
                                        btnSu.setOnClickListener(vie -> {
                                            //float floater = Float.parseFloat(balance);
                                            //float inner = Float.parseFloat(viewUsers2.getLoan());
                                            if (Float.parseFloat(balance) < Float.parseFloat(viewUsers2.getLoan())) {
                                                Toast.makeText(this, "Failed!! Look\nCurrent Balance @" + balance + "\nLoan @" + viewUsers2.getLoan() + "\nBalance is insufficient", Toast.LENGTH_SHORT).show();
                                            } else {
                                                AlertDialog.Builder alex = new AlertDialog.Builder(this);
                                                alex.setTitle("Confirm Details");
                                                alex.setMessage("Applicant Phone: " + viewUsers2.getPhone() + "\nLoan KES" + viewUsers2.getLoan() + "\nDisburse Via MPESA:\nPhone: " + viewUsers2.getPhone() + "\n\nEnter the MPESA Code in the next Screen");
                                                alex.setPositiveButton("Next", (uk, kl) -> {
                                                    AlertDialog.Builder meal = new AlertDialog.Builder(this);
                                                    final EditText editText = new EditText(this);
                                                    editText.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                                                    editText.setHint("MPESA CODE");
                                                    InputFilter[] filters = new InputFilter[1];
                                                    filters[0] = new InputFilter.LengthFilter(10); //Filter to 10 characters
                                                    editText.setFilters(filters);
                                                    meal.setView(editText);
                                                    meal.setPositiveButton("submit", (yoom, ou) -> {
                                                        final String mPe = editText.getText().toString().trim();
                                                        if (mPe.isEmpty()) {
                                                            Toast.makeText(this, "MPESA Required", Toast.LENGTH_SHORT).show();
                                                        } else {
                                                            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                                                            requestQueue.add(new StringRequest(Request.Method.POST, URls.disbu,
                                                                    response1 -> {
                                                                        try {
                                                                            JSONObject jsonObject1 = new JSONObject(response1);
                                                                            int st = jsonObject1.getInt("status");
                                                                            String msg = jsonObject1.getString("message");
                                                                            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                                                            if (st == 1) {
                                                                                startActivity(new Intent(getApplicationContext(), Disburse.class));
                                                                                finish();
                                                                            }
                                                                        } catch (Exception e) {
                                                                            e.printStackTrace();
                                                                            Toast.makeText(this, "error occurred", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    }, error -> {
                                                                Toast.makeText(this, "connection error", Toast.LENGTH_SHORT).show();
                                                            }) {
                                                                @Nullable
                                                                @Override
                                                                protected Map<String, String> getParams() throws AuthFailureError {
                                                                    Map<String, String> para = new HashMap<>();
                                                                    para.put("mpesa", mPe);
                                                                    para.put("id", viewUsers2.getId());
                                                                    para.put("borrow_id", viewUsers2.getBorrow_id());
                                                                    para.put("loan", viewUsers2.getLoan());
                                                                    para.put("amount", viewUsers2.getAmount());
                                                                    return para;
                                                                }
                                                            });
                                                        }
                                                    });
                                                    AlertDialog foo = meal.create();
                                                    foo.setCancelable(false);
                                                    foo.setCanceledOnTouchOutside(false);
                                                    foo.show();
                                                    foo.getWindow().setGravity(Gravity.TOP | Gravity.RIGHT);
                                                });
                                                alex.setNegativeButton("close", (uk, kl) -> uk.cancel());
                                                AlertDialog dada = alex.create();
                                                dada.setCancelable(false);
                                                dada.setCanceledOnTouchOutside(false);
                                                dada.show();
                                                dada.getWindow().setGravity(Gravity.CENTER);
                                            }
                                        });
                                        ded.setCanceledOnTouchOutside(false);
                                        ded.setCancelable(false);
                                        ded.getWindow().setGravity(Gravity.BOTTOM);
                                        ded.show();
                                    }
                                } else if (success == 0) {
                                    AlertDialog.Builder yes = new AlertDialog.Builder(this);
                                    yes.setTitle("Insufficient Funds");
                                    yes.setMessage("Failed!! The system Account is currently 0\n\nPlease Click manage Flow from your Home Page.");
                                    AlertDialog yo = yes.create();
                                    yo.getWindow().setGravity(Gravity.BOTTOM);
                                    yo.show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();

                            }

                        }, error -> {
                    Toast.makeText(this, "error with your connection", Toast.LENGTH_SHORT).show();
                });
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            });
            btnEx.setOnClickListener(view1 -> {
                dialog.cancel();
            });
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.getWindow().setGravity(Gravity.TOP);
            dialog.show();
        });
        practical();
    }

    private void printMe() {
        PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
        printManager.print(getString(R.string.app_name), new PrintAll(this, view2.findViewById(R.id.relative)), null);

    }

    private void printUs() {
        PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
        printManager.print(getString(R.string.app_name), new PrintAll(this, viewer.findViewById(R.id.relative)), null);

    }

    private void practical() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URls.getApp,
                response -> {
                    try {
                        AppModel subject;
                        SubjectList = new ArrayList<>();
                        JSONObject jsonObject = new JSONObject(response);
                        Log.e("response ", response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            JSONArray jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                String id = jsonObject.getString("id");
                                String borrow_id = jsonObject.getString("borrow_id");
                                String loan_id = jsonObject.getString("loan_id");
                                String id_no = jsonObject.getString("id_no");
                                String name = jsonObject.getString("name");
                                String phone = jsonObject.getString("phone");
                                String loan = jsonObject.getString("loan");
                                String status = jsonObject.getString("status");
                                String auction = jsonObject.getString("auction");
                                String total = jsonObject.getString("total");
                                String amount = jsonObject.getString("amount");
                                String balance = jsonObject.getString("balance");
                                String mpesa = jsonObject.getString("mpesa");
                                String fina_status = jsonObject.getString("fina_status");
                                String reg_date = jsonObject.getString("reg_date");
                                subject = new AppModel(id, borrow_id, loan_id, id_no, name, phone, loan, status, auction, total, amount, balance, mpesa, fina_status, reg_date);
                                SubjectList.add(subject);
                                issue.setText("Total Borrowed KES " + total);
                            }
                            produAda = new Appad(Disburse.this, R.layout.loaner, SubjectList);
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