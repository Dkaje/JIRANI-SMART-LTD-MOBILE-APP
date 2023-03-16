
package com.example.jiranismart.client;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jiranismart.Fermented.BorrowAda;
import com.example.jiranismart.Fermented.ClientMode;
import com.example.jiranismart.Fermented.ClientSession;
import com.example.jiranismart.Fermented.LoanModel;
import com.example.jiranismart.Fermented.URls;
import com.example.jiranismart.R;
import com.example.jiranismart.finance.ManageLoan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BorrowLoan extends AppCompatActivity {
    SearchView searchView;
    ListView listView;
    ClientMode clientMode;
    ClientSession clientSession;
    View layout;
    RequestQueue requestQueue;
    ArrayList<LoanModel> loanModels = new ArrayList<>();
    BorrowAda loanAda;
    LoanModel loanModel;
    AlertDialog.Builder alert;
    Rect rect;
    Window window;
    LayoutInflater layoutInflater;
    Button Upload, exit;
    float rate, principal, time, ones, twos, threes;
    double interest, amount, monthly_installment, worth;
    String monthly, summedLoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Borrow Loan");
        setContentView(R.layout.activity_borrow_loan);
        listView = findViewById(R.id.mylister);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.mySearch);
        clientSession = new ClientSession(getApplicationContext());
        clientMode = clientSession.getClientDetails();
        listView.setOnItemClickListener((parent, view, position, id) -> {
            loanModel = (LoanModel) parent.getItemAtPosition(position);
            alert = new AlertDialog.Builder(this);
            rect = new Rect();
            window = this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = layoutInflater.inflate(R.layout.borrow, null);
            layout.setMinimumWidth((int) (rect.width() * 0.9f));
            layout.setMinimumHeight((int) (rect.height() * 0.04f));
            Upload = layout.findViewById(R.id.btnUpload);
            exit = layout.findViewById(R.id.btnClose);
            alert.setView(layout);
            rate = Float.parseFloat(loanModel.getRate());
            principal = Float.parseFloat(loanModel.getAmount());
            time = Float.parseFloat(loanModel.getR_period());
            interest = (principal * rate * time) / (12 * 100);
            amount = interest + principal;
            monthly_installment = amount / time;
            monthly = String.format("%.0f", monthly_installment);
            String tot = String.format("%.0f", amount);
            alert.setMessage(Html.fromHtml("<font>Loan Type: <b>" + loanModel.getLoan_type() + "</b><br>Repayment: KES<b>" + monthly + "</b> per month<br>Period: <b>" + loanModel.getR_period() + "</b>months<br>LoanAmount: KES<b>" + loanModel.getAmount() + "</b><br><br>TotalRepayable: KES<b>" + tot + "</b></font>"));
            alert.setTitle("Loan Details");
            AlertDialog alertDialog = alert.create();
            exit.setOnClickListener(v1 -> {
                alertDialog.cancel();
            });
            Upload.setOnClickListener(v1 -> {
                requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(new StringRequest(Request.Method.POST, URls.getTot,
                        response -> {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                Log.e("response ", response);
                                int success = jsonObject.getInt("trust");
                                if (success == 1) {
                                    JSONArray jsonArray = jsonObject.getJSONArray("victory");
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        jsonObject = jsonArray.getJSONObject(i);
                                        String one = jsonObject.getString("one");
                                        String two = jsonObject.getString("two");
                                        String three = jsonObject.getString("three");
                                        if (!one.equals("0") & !two.equals("0") & !three.equals("0")) {
                                            ones = Float.parseFloat(one);
                                            twos = Float.parseFloat(two);
                                            threes = Float.parseFloat(three);
                                            worth = (ones * 0.85) + (twos * 0.7) + (threes * 0.4);
                                            summedLoan = String.format("%.0f", worth);
                                        } else if (!one.equals("0") & !two.equals("0") & three.equals("0")) {
                                            ones = Float.parseFloat(one);
                                            twos = Float.parseFloat(two);
                                            //threes = Float.parseFloat(three);
                                            worth = (ones * 0.85) + (twos * 0.7)/* + (threes * 0.4)*/;
                                            summedLoan = String.format("%.0f", worth);
                                        } else if (!one.equals("0") & two.equals("0") & !three.equals("0")) {
                                            ones = Float.parseFloat(one);
                                            //twos = Float.parseFloat(two);
                                            threes = Float.parseFloat(three);
                                            worth = (ones * 0.85) + /*(twos * 0.7) +*/ (threes * 0.4);
                                            summedLoan = String.format("%.0f", worth);
                                        } else if (one.equals("0") & !two.equals("0") & !three.equals("0")) {
                                            //ones = Float.parseFloat(one);
                                            twos = Float.parseFloat(two);
                                            threes = Float.parseFloat(three);
                                            worth = /* (ones * 0.85)+ */(twos * 0.7) + (threes * 0.4);
                                            summedLoan = String.format("%.0f", worth);
                                        } else if (!one.equals("0") & two.equals("0") & three.equals("0")) {
                                            ones = Float.parseFloat(one);
                                            //twos = Float.parseFloat(two);
                                            //threes = Float.parseFloat(three);
                                            worth = (ones * 0.85)/* + (twos * 0.7) + (threes * 0.4)*/;
                                            summedLoan = String.format("%.0f", worth);
                                        } else if (!two.equals("0") & three.equals("0")) {
                                            //ones = Float.parseFloat(one);
                                            twos = Float.parseFloat(two);
                                            //threes = Float.parseFloat(three);
                                            worth = /* (ones * 0.85)+ */(twos * 0.7)/* + (threes * 0.4)*/;
                                            summedLoan = String.format("%.0f", worth);
                                        } else if (!three.equals("0")) {
                                            //ones = Float.parseFloat(one);
                                            //twos = Float.parseFloat(two);
                                            threes = Float.parseFloat(three);
                                            worth = /* (ones * 0.85)+ (twos * 0.7) + */(threes * 0.4);
                                            summedLoan = String.format("%.0f", worth);
                                        } else {
                                            Toast.makeText(this, "No Recognized Collateral", Toast.LENGTH_SHORT).show();
                                        }
                                        AlertDialog.Builder based = new AlertDialog.Builder(this);
                                        based.setTitle("Maximum Loan limit");
                                        based.setMessage("MaximumLoan: KES " + summedLoan + "\n\nLoanAmount: KES " + loanModel.getAmount() + "\n\nDo you want to Apply for this Loan?");
                                        based.setPositiveButton("Yes_apply", (oo, kl) -> {
                                            float loaner = Float.parseFloat(loanModel.getAmount());
                                            if (worth < loaner) {
                                                Toast.makeText(BorrowLoan.this, "Oops Look at your maximum loan limit\nToo little for this loan", Toast.LENGTH_LONG).show();
                                            } else {
                                                requestQueue = Volley.newRequestQueue(getApplicationContext());
                                                requestQueue.add(new StringRequest(Request.Method.POST, URls.applyLoan,
                                                        response1 -> {
                                                            try {
                                                                JSONObject jsonObject1 = new JSONObject(response1);
                                                                int st = jsonObject1.getInt("status");
                                                                String msg = jsonObject1.getString("message");
                                                                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                                                if (st == 1) {
                                                                    startActivity(new Intent(getApplicationContext(), ApplicationHis.class));
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
                                                        params.put("id_no", clientMode.getId_no());
                                                        params.put("name", clientMode.getFname() + " " + clientMode.getLname());
                                                        params.put("phone", clientMode.getPhone());
                                                        params.put("maxloan", summedLoan);
                                                        params.put("loan", loanModel.getAmount());
                                                        params.put("interest", String.format("%.2f", interest));
                                                        params.put("expected_monthly", String.format("%.0f", monthly_installment));
                                                        params.put("loan_type", loanModel.getLoan_type());
                                                        params.put("loan_period", loanModel.getR_period());
                                                        params.put("total", String.format("%.0f", amount));
                                                        params.put("loan_id", loanModel.getId());
                                                        params.put("rate", loanModel.getRate());
                                                        return params;
                                                    }
                                                });//id_no,name,phone,maxloan,loan,interest,loan,expected_monthly,loan_period,total,loan_id,rate
                                            }
                                        });
                                        based.setNegativeButton("close", (oo, kl) -> oo.cancel());
                                        AlertDialog alertDialog1 = based.create();
                                        alertDialog1.setCancelable(false);
                                        alertDialog1.setCanceledOnTouchOutside(false);
                                        alertDialog1.show();
                                        based.show();
                                    }
                                } else if (success == 0) {
                                    String msg = jsonObject.getString("mine");
                                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }, error -> {
                    Toast.makeText(this, "connection error", Toast.LENGTH_SHORT).show();
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("id_no", clientMode.getId_no());
                        return params;
                    }
                });

            });
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
        });
        seeLoan();
    }

    private void seeLoan() {
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
                            loanAda = new BorrowAda(BorrowLoan.this, R.layout.lo, loanModels);
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