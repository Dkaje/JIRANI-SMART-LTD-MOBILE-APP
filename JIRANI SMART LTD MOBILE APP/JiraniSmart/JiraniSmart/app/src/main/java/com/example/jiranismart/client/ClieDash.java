package com.example.jiranismart.client;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jiranismart.Fermented.ClientMode;
import com.example.jiranismart.Fermented.ClientSession;
import com.example.jiranismart.Fermented.RateMode;
import com.example.jiranismart.Fermented.RatedOne;
import com.example.jiranismart.Fermented.URls;
import com.example.jiranismart.MainActivity;
import com.example.jiranismart.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ClieDash extends AppCompatActivity {
    ClientMode clientMode;
    ClientSession clientSession;
    CardView Loaned, Borrow, deposit;
    Button manage, cleared, uncleared, defaulter, exit, disburse, issued, leave;
    int w, h;
    View view2;
    Dialog dialog;
    RecyclerView recyclerView;
    ImageButton imageButton;
    View layer;
    EditText message;
    private List<RateMode> list;
    private RatedOne vccAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clientSession = new ClientSession(getApplicationContext());
        clientMode = clientSession.getClientDetails();
        Objects.requireNonNull(getSupportActionBar()).setTitle("Welcome " + clientMode.getFname());
        setContentView(R.layout.activity_clie_dash);
        Loaned = findViewById(R.id.borrowLoan);
        Borrow = findViewById(R.id.collateralInfo);
        deposit = findViewById(R.id.issuedLoan);
        Loaned.setOnClickListener(v -> {
            dialog = new Dialog(this);
            dialog.setContentView(R.layout.cust_loan);
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
                startActivity(new Intent(getApplicationContext(), Uncleared.class));
            });
            disburse.setOnClickListener(v1 -> {
                startActivity(new Intent(getApplicationContext(), ApplicationHis.class));
            });
            issued.setOnClickListener(v1 -> {
                startActivity(new Intent(getApplicationContext(), Issued.class));
            });
            defaulter.setOnClickListener(v1 -> {
                startActivity(new Intent(getApplicationContext(), DefaultLoan.class));
            });
            cleared.setOnClickListener(v1 -> {
                startActivity(new Intent(getApplicationContext(), SettledL.class));
            });
            manage.setOnClickListener(v1 -> {
                startActivity(new Intent(getApplicationContext(), BorrowLoan.class));
            });
            dialog.show();
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.getWindow().setGravity(Gravity.TOP);
        });
        Borrow.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), MyCollateral.class));
        });
        deposit.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), Deposit.class));
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.custed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.myProfile:
                AlertDialog.Builder builder = new AlertDialog.Builder(ClieDash.this);
                builder.setTitle("Registration Information");
                builder.setMessage("EntryID :" + clientMode.getEntry() + "\nBankAcc. " + clientMode.getAccount() + "\nIDNo. " + clientMode.getId_no() + "\nName :" + clientMode.getFname() + " " + clientMode.getLname() + "\nEmail :" + clientMode.getEmail() + "\nPhone :" + clientMode.getPhone() + "\nCounty :" + clientMode.getCountry() + "\nLocation :" + clientMode.getLocation() + "\nEntryDate :" + clientMode.getReg_date());
                builder.setIcon(R.drawable.profile);
                builder.setPositiveButton("Logout", (dialogt, idd) -> {
                    clientSession.logoutClient();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                });
                builder.setNeutralButton("exit", (dialogt, idd) -> {
                    clientSession.logoutClient();
                    finishAffinity();
                });
                builder.setNegativeButton("close", (dialog1t, itemmt) -> dialog1t.cancel());
                final AlertDialog alertDialogm = builder.create();
                alertDialogm.getWindow().setGravity(Gravity.TOP);
                alertDialogm.show();
                break;
            case R.id.notif:
                startActivity(new Intent(getApplicationContext(), SeizeMe.class));
                break;
            case R.id.dme:
                leaveMessage(this);
                ViewReply();
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    private void leaveMessage(ClieDash custDash) {
        AlertDialog.Builder alarmed = new AlertDialog.Builder(custDash);
        Rect rec = new Rect();
        Window window = custDash.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rec);
        LayoutInflater inflat = (LayoutInflater) custDash.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layer = inflat.inflate(R.layout.demo_mess, null);
        layer.setMinimumWidth((int) (rec.width() * 0.9f));
        layer.setMinimumHeight((int) (rec.height() * 0.19f));
        imageButton = layer.findViewById(R.id.submit_button1);
        message = layer.findViewById(R.id.reviewing);
        leave = layer.findViewById(R.id.btnExit);
        recyclerView = layer.findViewById(R.id.britLitovsk);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(layoutManager);
        list = new ArrayList<>();
        imageButton.setOnClickListener(v -> {
            final String myMessage = message.getText().toString().trim();
            if (myMessage.isEmpty()) {
                Toast.makeText(custDash, "Please type some message", Toast.LENGTH_SHORT).show();
            } else {
                StringRequest stringRequesting = new StringRequest(Request.Method.POST, URls.send,
                        respon -> {
                            try {
                                JSONObject jsonOb = new JSONObject(respon);
                                Log.e("response ", respon);
                                String msg = jsonOb.getString("message");
                                int statuses = jsonOb.getInt("success");
                                if (statuses == 1) {
                                    Toast.makeText(custDash, msg, Toast.LENGTH_LONG).show();
                                    message.setText("");
                                    ViewReply();
                                } else if (statuses == 0) {
                                    Toast.makeText(custDash, msg, Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(custDash, "An error occurred", Toast.LENGTH_SHORT).show();
                            }

                        }, error -> {
                    Toast.makeText(custDash, "Failed to connect", Toast.LENGTH_SHORT).show();

                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("name", clientMode.getFname());
                        params.put("message", myMessage);
                        params.put("sender", clientMode.getId_no());
                        params.put("phone", clientMode.getPhone());
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequesting);
            }
        });
        alarmed.setView(layer);
        alarmed.setTitle("Messages");
        AlertDialog alertDialog = alarmed.create();
        leave.setOnClickListener(view -> {
            alertDialog.cancel();
        });
        alertDialog.show();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
    }

    private void ViewReply() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URls.getReply,
                response -> {
                    try {
                        Log.e("RESPONSE ", response);
                        JSONObject jsonObject = new JSONObject(response);
                        int status = jsonObject.getInt("trust");
                        if (status == 1) {
                            list.clear();
                            JSONArray jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                String id = jsonObject.getString("id");
                                String cust_id = jsonObject.getString("sender");
                                String phone = jsonObject.getString("phone");
                                String message = jsonObject.getString("message");
                                String reg_date = jsonObject.getString("reg_date");
                                String reply = jsonObject.getString("reply");
                                String reply_date = jsonObject.getString("reply_date");
                                RateMode subject = new RateMode(id, cust_id, phone, message, reg_date, reply, reply_date);
                                list.add(subject);

                            }
                            vccAd = new RatedOne(getApplicationContext(), list);
                            recyclerView.setAdapter(vccAd);
                            recyclerView.scrollToPosition(list.size() - 1);

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }, error -> {
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("sender", clientMode.getId_no());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}