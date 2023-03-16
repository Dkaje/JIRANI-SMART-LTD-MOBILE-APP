package com.example.jiranismart.client;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jiranismart.Fermented.AppModel;
import com.example.jiranismart.Fermented.Appad;
import com.example.jiranismart.Fermented.ClientMode;
import com.example.jiranismart.Fermented.ClientSession;
import com.example.jiranismart.Fermented.PrintAll;
import com.example.jiranismart.Fermented.URls;
import com.example.jiranismart.R;
import com.example.jiranismart.finance.Disburse;
import com.example.jiranismart.finance.UnclearedLoan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Uncleared extends AppCompatActivity {
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
    ClientMode clientMode;
    ClientSession clientSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("UnSettled Loans");
        setContentView(R.layout.activity_uncleared);
        clientSession = new ClientSession(getApplicationContext());
        clientMode = clientSession.getClientDetails();
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
            btnSu.setVisibility(View.INVISIBLE);
            textView.setText(Html.fromHtml("<font>JIRANI SMART LIMITED<br>P.O.Box 25-80305,<br>Mwatate-Kenya<br><b><u>Applicant Details</u></b><br>IDNo: " + viewUsers2.getId_no() + "<br>Name: " + viewUsers2.getName() + "<br>Phone: " + viewUsers2.getPhone() + "<br><br><b><u>Loan Details</u></b><br>LoanID: " + viewUsers2.getLoan_id() + "<br>LoanAmount: KES" + viewUsers2.getLoan() + "<br>BorrowRef: " + viewUsers2.getBorrow_id() + "<br>AuctioneerStatus: " + viewUsers2.getAuction() + "<br><br>Disbursement: " + viewUsers2.getStatus() + "<br>Clearance: " + viewUsers2.getFina_status() + "</font>"));
            print.setOnClickListener(view1 -> {
                printMe();
            });//borrow_id,loan_id,id_no,name,phone,loan,status,fina_status,reg_date
            dialog = meja.create();
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
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URls.custUnc,
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
                                issue.setText("Total Issued KES " + total);
                            }
                            produAda = new Appad(Uncleared.this, R.layout.loaner, SubjectList);
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

        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> para = new HashMap<>();
                para.put("id_no", clientMode.getId_no());
                return para;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}