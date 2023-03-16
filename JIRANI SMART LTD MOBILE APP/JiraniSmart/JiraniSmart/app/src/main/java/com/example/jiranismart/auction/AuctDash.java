package com.example.jiranismart.auction;

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
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.example.jiranismart.Fermented.AttachAgen;
import com.example.jiranismart.Fermented.AttachAgenAda;
import com.example.jiranismart.Fermented.AuctionSession;
import com.example.jiranismart.Fermented.RateAda;
import com.example.jiranismart.Fermented.RateMode;
import com.example.jiranismart.Fermented.RatedOne;
import com.example.jiranismart.Fermented.StaffMode;
import com.example.jiranismart.Fermented.URls;
import com.example.jiranismart.MainActivity;
import com.example.jiranismart.R;
import com.example.jiranismart.inventory.AgentNew;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AuctDash extends AppCompatActivity {
    AuctionSession auctionSession;
    StaffMode staffMode;
    CardView Assess, Aplications, defalt;
    Dialog dialog;
    Button leave;
    SearchView searchView;
    View layer;
    ListView listView;
    EditText message;
    ArrayList<RateMode> viewUsers = new ArrayList<>();
    RateAda viewAda;
    RateMode product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auctionSession = new AuctionSession(getApplicationContext());
        staffMode = auctionSession.getAuctionDetails();
        Objects.requireNonNull(getSupportActionBar()).setTitle("Welcome " + staffMode.getFname());
        setContentView(R.layout.activity_auct_dash);
        Assess = findViewById(R.id.newAssessment);
        Aplications = findViewById(R.id.LOANaPPS);
        defalt = findViewById(R.id.hisAssessment);
        Assess.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), AssessPending.class));
        });
        defalt.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ManageDefa.class));
        });
        Aplications.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ApplicationLoan.class));
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.auction, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.myProfile:
                AlertDialog.Builder builder = new AlertDialog.Builder(AuctDash.this);
                builder.setTitle("Registration Information");
                builder.setMessage("EntryID :" + staffMode.getEntry() + "\nName :" + staffMode.getFname() + " " + staffMode.getLname() + "\nEmail :" + staffMode.getEmail() + "\nPhone :" + staffMode.getPhone() + "\nRole :" + staffMode.getRole() + "\nEntryDate :" + staffMode.getReg_date());
                builder.setIcon(R.drawable.profile);
                builder.setPositiveButton("Logout", (dialogt, idd) -> {
                    auctionSession.logoutAuction();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                });
                builder.setNeutralButton("exit", (dialogt, idd) -> {
                    auctionSession.logoutAuction();
                    finishAffinity();
                });
                builder.setNegativeButton("close", (dialog1t, itemmt) -> dialog1t.cancel());
                final AlertDialog alertDialogm = builder.create();
                alertDialogm.getWindow().setGravity(Gravity.TOP);
                alertDialogm.show();
                break;
            case R.id.dme:
                leaveMessage(this);
                ViewReply();
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    private void ViewReply() {
    }

    private void leaveMessage(AuctDash custDash) {
        AlertDialog.Builder alarmed = new AlertDialog.Builder(custDash);
        Rect rec = new Rect();
        Window window = custDash.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rec);
        LayoutInflater inflat = (LayoutInflater) custDash.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layer = inflat.inflate(R.layout.messages, null);
        layer.setMinimumWidth((int) (rec.width() * 0.9f));
        layer.setMinimumHeight((int) (rec.height() * 0.19f));
        searchView = layer.findViewById(R.id.search);
        listView = layer.findViewById(R.id.listMess);
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Message Details");
            product = (RateMode) parent.getItemAtPosition(position);
            if (product.getReply().equals("Pending")) {
                builder.setMessage("SenderID " + product.getSender() + "\nPhone :" + product.getPhone() + "\nMessage :" + product.getMessage() + "\nDateSent :" + product.getReg_date());
                builder.setPositiveButton("reply", (dialog1, item) -> {
                    AlertDialog.Builder alert = new AlertDialog.Builder(this);
                    alert.setTitle("Send Your Reply");
                    final EditText editText = new EditText(this);
                    editText.setHint("type some reply message");
                    alert.setView(editText);
                    alert.setPositiveButton("send", (dialog, ite) -> {
                        String myMess = editText.getText().toString().trim();
                        if (myMess.isEmpty()) {
                            Toast.makeText(this, "Some Message required", Toast.LENGTH_SHORT).show();
                        } else {
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, URls.sendReply,
                                    response -> {
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            String msg = jsonObject.getString("message");
                                            int status = jsonObject.getInt("success");
                                            if (status == 1) {
                                                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(), AuctDash.class));
                                                finish();
                                            } else if (status == 0) {
                                                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show();
                                        }
                                    }, error -> {
                                Toast.makeText(this, "There was a connection error", Toast.LENGTH_SHORT).show();

                            }) {
                                @Nullable
                                @Override
                                protected Map<String, String> getParams() {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("id", product.getId());
                                    params.put("reply", myMess);
                                    return params;
                                }
                            };
                            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                            requestQueue.add(stringRequest);
                        }
                    });
                    alert.setNegativeButton("close", (dialog, ite) -> dialog.cancel());
                    AlertDialog alertDialog = alert.create();
                    alertDialog.setCancelable(false);
                    alertDialog.setCanceledOnTouchOutside(false);
                    alertDialog.show();
                });
            } else {
                builder.setMessage("SenderID " + product.getSender() + "\nPhone :" + product.getPhone() + "\nMessage :" + product.getMessage() + "\nDateSent :" + product.getReg_date() + "\nReply :" + product.getReply() + "\nReplyDate :" + product.getReply_date());
            }
            builder.setNegativeButton("close", (dialog1, item) -> dialog1.cancel());
            AlertDialog alertDialog = builder.create();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
        });
        practicalPra();
        leave = layer.findViewById(R.id.btnExit);
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

    private void practicalPra() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URls.getMess,
                response -> {
                    try {
                        RateMode subject;
                        viewUsers = new ArrayList<>();
                        JSONObject jsonObject = new JSONObject(response);
                        Log.e("response ", response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
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
                                subject = new RateMode(id, cust_id, phone, message, reg_date, reply, reply_date);
                                viewUsers.add(subject);
                            }
                            viewAda = new RateAda(AuctDash.this, R.layout.rate_us, viewUsers);
                            listView.setAdapter(viewAda);
                            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String text) {
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String newText) {
                                    viewAda.getFilter().filter(newText);
                                    return false;
                                }
                            });
                        } else if (success == 0) {
                            String msg = jsonObject.getString("mine");
                            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "an error", Toast.LENGTH_SHORT).show();
                    }

                }, error -> {
            Toast.makeText(this, "error in connection", Toast.LENGTH_SHORT).show();

        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}