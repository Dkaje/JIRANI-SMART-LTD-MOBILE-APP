package com.example.jiranismart.finance;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Spinner;
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
import com.example.jiranismart.Fermented.LoanAda;
import com.example.jiranismart.Fermented.LoanModel;
import com.example.jiranismart.Fermented.URls;
import com.example.jiranismart.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ManageLoan extends AppCompatActivity {
    SearchView searchView;
    ListView listView;
    Button button, delete;
    TextView textView;
    Spinner spinner;
    Button Upload, exit;
    EditText editamount, textRate, textPeriod;
    RelativeLayout relativeLayout;
    View layout;
    RequestQueue requestQueue;
    ArrayList<LoanModel> loanModels = new ArrayList<>();
    LoanAda loanAda;
    LoanModel loanModel;
    AlertDialog.Builder alert;
    Rect rect;
    Window window;
    LayoutInflater layoutInflater;
    String amount, disburesed, balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Manage Loan");
        setContentView(R.layout.activity_manage_loan);
        listView = findViewById(R.id.listed);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.search);
        button = findViewById(R.id.addNewLoan);
        button.setOnClickListener(v -> {
            alert = new AlertDialog.Builder(ManageLoan.this);
            rect = new Rect();
            window = ManageLoan.this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            layoutInflater = (LayoutInflater) ManageLoan.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = layoutInflater.inflate(R.layout.loaned, null);
            layout.setMinimumWidth((int) (rect.width() * 0.9f));
            layout.setMinimumHeight((int) (rect.height() * 0.04f));
            relativeLayout = layout.findViewById(R.id.relative);
            editamount = layout.findViewById(R.id.amountLoan);
            spinner = layout.findViewById(R.id.sprType);
            Upload = layout.findViewById(R.id.btnUpload);
            textRate = layout.findViewById(R.id.interestRate);
            textPeriod = layout.findViewById(R.id.repayMentP);
            exit = layout.findViewById(R.id.btnClose);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Loan, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String mySpin = spinner.getSelectedItem().toString().trim();
                    if (mySpin.equals("Personal Loan")) {
                        textRate.setText("3");
                        textPeriod.setText("12");
                        relativeLayout.setVisibility(View.VISIBLE);
                    } else if (mySpin.equals("Development Loan")) {
                        textRate.setText("6");
                        textPeriod.setText("24");
                        relativeLayout.setVisibility(View.VISIBLE);
                    } else if (mySpin.equals("Small Business Loan")) {
                        textRate.setText("10");
                        textPeriod.setText("36");
                        relativeLayout.setVisibility(View.VISIBLE);
                    } else if (mySpin.equals("Credit Card Loan")) {
                        textRate.setText("14");
                        textPeriod.setText("48");
                        relativeLayout.setVisibility(View.VISIBLE);
                    } else if (mySpin.equals("Home Equity Loan")) {
                        textRate.setText("18");
                        textPeriod.setText("60");
                        relativeLayout.setVisibility(View.VISIBLE);
                    } else {
                        relativeLayout.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            alert.setView(layout);
            alert.setTitle("Upload ");
            AlertDialog alertDialog = alert.create();
            exit.setOnClickListener(v1 -> {
                alertDialog.cancel();
            });
            Upload.setOnClickListener(v1 -> {
                final String mySpin = spinner.getSelectedItem().toString().trim();
                if (mySpin.equals("Select Loan Type")) {
                    Toast.makeText(this, "Please select Loan Type", Toast.LENGTH_SHORT).show();
                } else {
                    final String myrate = textRate.getText().toString().trim();
                    final String myperiod = textPeriod.getText().toString().trim();
                    final String myAmount = editamount.getText().toString().trim();
                    if (myrate.isEmpty()) {
                        textRate.setError("??");
                        textRate.requestFocus();
                    } else if (myperiod.isEmpty()) {
                        textPeriod.setError("??");
                        textPeriod.requestFocus();
                    } else if (myAmount.isEmpty()) {
                        editamount.setError("??");
                        editamount.requestFocus();
                    } else {
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
                                                if (Float.parseFloat(balance) < Float.parseFloat(myAmount)) {
                                                    AlertDialog.Builder yes = new AlertDialog.Builder(this);
                                                    yes.setTitle("Insufficient Funds");
                                                    yes.setMessage("Failed!! Look\nCurrent Balance @" + balance + "\nLoanAmount @" + myAmount + "\nBalance is insufficient\nReduce Amount or Navigate to Home to Manage Flow");
                                                    AlertDialog yo = yes.create();
                                                    yo.getWindow().setGravity(Gravity.BOTTOM);
                                                    yo.show();
                                                } else {
                                                    requestQueue = Volley.newRequestQueue(getApplicationContext());
                                                    requestQueue.add(new StringRequest(Request.Method.POST, URls.add_loan,
                                                            responser -> {
                                                                try {
                                                                    JSONObject jsonObje = new JSONObject(responser);
                                                                    int st = jsonObje.getInt("status");
                                                                    String msg = jsonObje.getString("message");
                                                                    if (st == 1) {
                                                                        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                                                                        alertDialog.cancel();
                                                                        startActivity(new Intent(getApplicationContext(), ManageLoan.class));
                                                                        finish();
                                                                    } else if (st == 8) {
                                                                        AlertDialog.Builder yes = new AlertDialog.Builder(this);
                                                                        yes.setTitle("Insufficient Funds");
                                                                        yes.setMessage(msg);
                                                                        AlertDialog yo = yes.create();
                                                                        yo.getWindow().setGravity(Gravity.BOTTOM);
                                                                        yo.show();
                                                                    } else {
                                                                        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                                                                    }
                                                                } catch (Exception e) {
                                                                    e.printStackTrace();
                                                                    Toast.makeText(this, "an error occurred", Toast.LENGTH_SHORT).show();
                                                                }
                                                            }, error -> {
                                                        Toast.makeText(this, "connection error", Toast.LENGTH_SHORT).show();
                                                    }) {
                                                        @Nullable
                                                        @Override
                                                        protected Map<String, String> getParams() throws AuthFailureError {
                                                            Map<String, String> para = new HashMap<>();
                                                            para.put("loan_type", mySpin);
                                                            para.put("interest", myrate);
                                                            para.put("repayment", myperiod);
                                                            para.put("amount", myAmount);
                                                            para.put("balance", balance);
                                                            return para;//loan_type,interest,repayment,amount
                                                        }
                                                    });
                                                }
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
                        requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(stringRequest);
                    }
                }
            });
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
            alertDialog.getWindow().setGravity(Gravity.TOP);
        });
        listView.setOnItemClickListener((parent, view, position, id) -> {
            loanModel = (LoanModel) parent.getItemAtPosition(position);
            alert = new AlertDialog.Builder(ManageLoan.this);
            rect = new Rect();
            window = ManageLoan.this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            layoutInflater = (LayoutInflater) ManageLoan.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = layoutInflater.inflate(R.layout.update_loan, null);
            layout.setMinimumWidth((int) (rect.width() * 0.9f));
            layout.setMinimumHeight((int) (rect.height() * 0.04f));
            editamount = layout.findViewById(R.id.amountLoan);
            textView = layout.findViewById(R.id.textLoan);
            delete = layout.findViewById(R.id.btnDelete);
            Upload = layout.findViewById(R.id.btnUpload);
            textRate = layout.findViewById(R.id.interestRate);
            textPeriod = layout.findViewById(R.id.repayMentP);
            exit = layout.findViewById(R.id.btnClose);
            alert.setView(layout);
            alert.setTitle("Update Record");
            textView.setText(loanModel.getLoan_type());
            textRate.setText(loanModel.getRate());
            textPeriod.setText(loanModel.getR_period());
            editamount.setText(loanModel.getAmount());
            AlertDialog alertDialog = alert.create();
            exit.setOnClickListener(v1 -> {
                alertDialog.cancel();
            });
            Upload.setOnClickListener(v1 -> {
                final String myrate = textRate.getText().toString().trim();
                final String myperiod = textPeriod.getText().toString().trim();
                final String myAmount = editamount.getText().toString().trim();
                if (myrate.isEmpty()) {
                    textRate.setError("??");
                    textRate.requestFocus();
                } else if (myperiod.isEmpty()) {
                    textPeriod.setError("??");
                    textPeriod.requestFocus();
                } else if (myAmount.isEmpty()) {
                    editamount.setError("??");
                    editamount.requestFocus();
                } else if (myAmount.equals(loanModel.getAmount()) & myperiod.equals(loanModel.getR_period()) & myrate.equals(loanModel.getRate())) {
                    Toast.makeText(this, "You have edited nothing\nFor update", Toast.LENGTH_SHORT).show();
                } else {
                    requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(new StringRequest(Request.Method.POST, URls.updateLo,
                            response -> {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    int st = jsonObject.getInt("status");
                                    String msg = jsonObject.getString("message");
                                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                    if (st == 1) {
                                        alertDialog.cancel();
                                        startActivity(new Intent(getApplicationContext(), ManageLoan.class));
                                        finish();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Toast.makeText(this, "an error occurred", Toast.LENGTH_SHORT).show();
                                }
                            }, error -> {
                        Toast.makeText(this, "connection error", Toast.LENGTH_SHORT).show();
                    }) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> para = new HashMap<>();
                            para.put("id", loanModel.getId());
                            para.put("interest", myrate);
                            para.put("repayment", myperiod);
                            para.put("amount", myAmount);
                            return para;//loan_type,interest,repayment,amount
                        }
                    });
                }
            });
            delete.setOnClickListener(v1 -> {
                requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(new StringRequest(Request.Method.POST, URls.deleteLoa,
                        response -> {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int st = jsonObject.getInt("status");
                                String msg = jsonObject.getString("message");
                                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                if (st == 1) {
                                    alertDialog.cancel();
                                    startActivity(new Intent(getApplicationContext(), ManageLoan.class));
                                    finish();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(this, "an error occurred", Toast.LENGTH_SHORT).show();
                            }
                        }, error -> {
                    Toast.makeText(this, "connection error", Toast.LENGTH_SHORT).show();
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> para = new HashMap<>();
                        para.put("id", loanModel.getId());
                        return para;//loan_type,interest,repayment,amount
                    }
                });
            });
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
        });
        getLoan();
    }

    private void getLoan() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(new StringRequest(Request.Method.POST, URls.view_loan,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        Log.e("response ", response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            JSONArray jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                String id = jsonObject.getString("id");
                                String loan_type = jsonObject.getString("loan_type");
                                String r_period = jsonObject.getString("r_period");
                                String rate = jsonObject.getString("rate");
                                String amount = jsonObject.getString("amount");
                                String reg_date = jsonObject.getString("reg_date");
                                loanModel = new LoanModel(id, loan_type, r_period, rate, amount, reg_date);
                                loanModels.add(loanModel);
                            }
                            loanAda = new LoanAda(ManageLoan.this, R.layout.attach_agen, loanModels);
                            listView.setAdapter(loanAda);
                            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String text) {
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String newText) {
                                    loanAda.getFilter().filter(newText);
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
                        Toast.makeText(this, "an error occurred", Toast.LENGTH_SHORT).show();
                    }
                }, error -> {
            Toast.makeText(this, "connection error", Toast.LENGTH_SHORT).show();
        }));
    }
}