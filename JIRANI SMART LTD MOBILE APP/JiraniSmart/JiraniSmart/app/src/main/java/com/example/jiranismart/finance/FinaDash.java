package com.example.jiranismart.finance;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.print.PrintManager;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jiranismart.Fermented.FinaSessions;
import com.example.jiranismart.Fermented.PrintAll;
import com.example.jiranismart.Fermented.StaffMode;
import com.example.jiranismart.Fermented.URls;
import com.example.jiranismart.MainActivity;
import com.example.jiranismart.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FinaDash extends AppCompatActivity {
    FinaSessions finaSessions;
    StaffMode staffMode;
    CardView loan, cashed, depo;
    Dialog dialog;
    Button manage, cleared, uncleared, defaulter, exit, disburse, issued;
    int w, h;
    View view2;
    TextView textView;
    String amount, disburesed, balance, interest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        finaSessions = new FinaSessions(getApplicationContext());
        staffMode = finaSessions.getFinaDetails();
        Objects.requireNonNull(getSupportActionBar()).setTitle("Welcome " + staffMode.getFname());
        setContentView(R.layout.activity_fina_dash);
        loan = findViewById(R.id.MANAGELoan);
        depo = findViewById(R.id.CLIENTdEPOSITS);
        loan.setOnClickListener(v -> {
            dialog = new Dialog(this);
            dialog.setContentView(R.layout.loan);
            h = (int) (this.getResources().getDisplayMetrics().heightPixels * 0.55);
            w = (int) (this.getResources().getDisplayMetrics().widthPixels * 0.99);
            dialog.getWindow().setLayout(w, h);
            manage = dialog.findViewById(R.id.btnAddNew);
            cleared = dialog.findViewById(R.id.btnSettled);
            disburse = dialog.findViewById(R.id.btnDisb);
            defaulter = dialog.findViewById(R.id.btnDef);
            issued = dialog.findViewById(R.id.btnIssued);
            uncleared = dialog.findViewById(R.id.btnuncle);
            exit = dialog.findViewById(R.id.btnClose);
            exit.setOnClickListener(v1 -> {
                dialog.cancel();
            });
            uncleared.setOnClickListener(v1 -> {
                startActivity(new Intent(getApplicationContext(), UnclearedLoan.class));
            });
            disburse.setOnClickListener(v1 -> {
                startActivity(new Intent(getApplicationContext(), Disburse.class));
            });
            issued.setOnClickListener(v1 -> {
                startActivity(new Intent(getApplicationContext(), IssuedLoan.class));
            });
            defaulter.setOnClickListener(v1 -> {
                startActivity(new Intent(getApplicationContext(), LoanDefaulter.class));
            });
            cleared.setOnClickListener(v1 -> {
                startActivity(new Intent(getApplicationContext(), SettledLoan.class));
            });
            manage.setOnClickListener(v1 -> {
                startActivity(new Intent(getApplicationContext(), ManageLoan.class));
            });
            dialog.show();
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
        });
        cashed = findViewById(R.id.financeBook);
        depo.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setPositiveButton("pending", (oo, ol) -> {
                startActivity(new Intent(getApplicationContext(), ClientRepayment.class));
            });
            builder.setNegativeButton("Approved", (oo, ol) -> {
                startActivity(new Intent(getApplicationContext(), ApprovedPA.class));
            });
            builder.setNeutralButton("Rejected", (oo, ol) -> {
                startActivity(new Intent(getApplicationContext(), RejectedPA.class));
            });
            builder.show();
        });
        cashed.setOnClickListener(view -> {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URls.loaning,
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
                                    interest = jsonObject.getString("interest");
                                    showCashed(this);

                                }
                            } else if (success == 0) {
                                amount = "0";
                                disburesed = "0";
                                balance = "0";
                                interest = "0";
                                showCashed(this);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                    }, error -> {
                Toast.makeText(this, "error with your connection", Toast.LENGTH_SHORT).show();
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        });
    }

    private void showCashed(FinaDash finaDash) {
        AlertDialog.Builder meja = new AlertDialog.Builder(finaDash);
        meja.setTitle("Account Book");
        Rect rect = new Rect();
        Window window = finaDash.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rect);
        LayoutInflater layoutInflater = (LayoutInflater) finaDash.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view2 = layoutInflater.inflate(R.layout.tabulated, null);
        textView = view2.findViewById(R.id.texter);
        meja.setView(view2);
        textView.setText("JIRANI SMART LIMITED\nP.O.Box 25-80305,\n" + "Mwatate-Kenya\n\nLiquid Cash: KES" + amount + "\n\nAmount Borrowed: KES" + disburesed + "\n\nRetained Earnings: KES" + interest + "\n\nCapitalization: KES" + balance);
        meja.setNeutralButton("print", (oo, o) -> {
            printMe();
        });
        meja.setPositiveButton("add", (oo, o) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(finaDash);
            builder.setTitle("Add Cash");
            final EditText editText = new EditText(finaDash);
            editText.setHint("enter amount");
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            builder.setView(editText);

            builder.setPositiveButton("submit", (io, ii) -> {
                final String meme = editText.getText().toString().trim();
                if (meme.isEmpty()) {
                    Toast.makeText(finaDash, "enter some amount", Toast.LENGTH_SHORT).show();
                } else if (Float.parseFloat(meme) < 100) {
                    Toast.makeText(finaDash, "Too little amount", Toast.LENGTH_SHORT).show();
                } else {
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(new StringRequest(Request.Method.POST, URls.addCash,
                            response1 -> {
                                try {
                                    JSONObject jsonObject1 = new JSONObject(response1);
                                    int st = jsonObject1.getInt("status");
                                    String msg = jsonObject1.getString("message");
                                    Toast.makeText(finaDash, msg, Toast.LENGTH_SHORT).show();
                                    if (st == 1) {
                                        startActivity(new Intent(getApplicationContext(), FinaDash.class));
                                        finish();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Toast.makeText(finaDash, "error occurred", Toast.LENGTH_SHORT).show();
                                }
                            }, error -> {
                        Toast.makeText(this, "net error", Toast.LENGTH_SHORT).show();
                    }) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> para = new HashMap<>();
                            para.put("amount", meme);
                            return para;
                        }
                    });
                }
            });
            builder.setNegativeButton("close", (io, ii) -> io.cancel());
            AlertDialog ded = builder.create();
            ded.setCanceledOnTouchOutside(false);
            ded.setCancelable(false);
            ded.getWindow().setGravity(Gravity.TOP);
            ded.show();
        });
        meja.setNegativeButton("close", (oo, o) -> oo.cancel());
        dialog = meja.create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setGravity(Gravity.TOP);
        dialog.show();
    }

    private void printMe() {
        PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
        printManager.print(getString(R.string.app_name), new PrintAll(this, view2.findViewById(R.id.relative)), null);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_dash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.myProfile:
                AlertDialog.Builder builder = new AlertDialog.Builder(FinaDash.this);
                builder.setTitle("Registration Information");
                builder.setMessage("EntryID :" + staffMode.getEntry() + "\nName :" + staffMode.getFname() + " " + staffMode.getLname() + "\nEmail :" + staffMode.getEmail() + "\nPhone :" + staffMode.getPhone() + "\nRole :" + staffMode.getRole() + "\nEntryDate :" + staffMode.getReg_date());
                builder.setIcon(R.drawable.profile);
                builder.setPositiveButton("Logout", (dialogt, idd) -> {
                    finaSessions.logoutFina();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                });
                builder.setNeutralButton("exit", (dialogt, idd) -> {
                    finaSessions.logoutFina();
                    finishAffinity();
                });
                builder.setNegativeButton("close", (dialog1t, itemmt) -> dialog1t.cancel());
                final AlertDialog alertDialogm = builder.create();
                alertDialogm.getWindow().setGravity(Gravity.TOP);
                alertDialogm.show();
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}