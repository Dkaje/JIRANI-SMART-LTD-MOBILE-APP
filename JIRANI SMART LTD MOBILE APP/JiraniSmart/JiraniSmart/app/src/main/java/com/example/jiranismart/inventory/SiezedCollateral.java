
package com.example.jiranismart.inventory;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.jiranismart.Fermented.GreatMatch;
import com.example.jiranismart.Fermented.GreateAda;
import com.example.jiranismart.Fermented.URls;
import com.example.jiranismart.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SiezedCollateral extends AppCompatActivity {
    GreateAda produAda;
    ArrayList<GreatMatch> SubjectList = new ArrayList<>();
    GreatMatch viewUsers2;
    ListView listView;
    SearchView searchView;
    ImageView imageView;
    EditText editText, EditPri;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Seized Collateral");
        setContentView(R.layout.activity_siezed_collateral);
        listView = findViewById(R.id.listedOne);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.search);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            viewUsers2 = (GreatMatch) parent.getItemAtPosition(position);
            if (viewUsers2.getOne_sta().equals("Seized") & viewUsers2.getTwo_sta().equals("Seized") & viewUsers2.getThree_sta().equals("Seized")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(Html.fromHtml("<font><b><u>" + viewUsers2.getName() + "</u></b> Collateral Details</font>"));
                Rect rec = new Rect();
                Window win = this.getWindow();
                win.getDecorView().getWindowVisibleDisplayFrame(rec);
                LayoutInflater lay = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View vol = lay.inflate(R.layout.one_temp, null);
                vol.setMinimumWidth((int) (rec.width() * 0.9));
                vol.setMinimumHeight((int) (rec.height() * 0.01));
                ImageView fan = vol.findViewById(R.id.circleView);
                ImageView fan2 = vol.findViewById(R.id.myImage);
                ImageView fan3 = vol.findViewById(R.id.cartex);
                TextView text1 = vol.findViewById(R.id.mktSec);
                TextView text2 = vol.findViewById(R.id.accRece);
                TextView text3 = vol.findViewById(R.id.machSec);
                text3.setText("AssetCategory: " + viewUsers2.getCategory_three() + "\nType: " + viewUsers2.getType_three() + "\nExistence: " + viewUsers2.getExistence_three() + " yrs\nWorth KES" + viewUsers2.getWorth_three() + "\nTakeOver: Seized\nResume...");
                text2.setText("AssetCategory: " + viewUsers2.getCategory_two() + "\nType: " + viewUsers2.getType_two() + "\nExistence: " + viewUsers2.getExistence_two() + " yrs\nWorth KES" + viewUsers2.getWorth_two() + "\nTakeOver: Seized\nResume...");
                text1.setText("ClientIDno: " + viewUsers2.getId_no() + "\nPhone: " + viewUsers2.getPhone() + "\nCounty: " + viewUsers2.getCounty() + "\nLocation: " + viewUsers2.getLocation() + "\nAgentEmail: " + viewUsers2.getAgent_email() + "\n\nAssetCategory: " + viewUsers2.getCategory_one() + "\nType: " + viewUsers2.getType_one() + "\nExistence: " + viewUsers2.getExistence_one() + " yrs\nWorth KES" + viewUsers2.getWorth_one() + "\nTakeOver: Seized\nResume...");
                Glide.with(this).load(viewUsers2.getImage_one()).into(fan);
                Glide.with(this).load(viewUsers2.getImage_two()).into(fan2);
                Glide.with(this).load(viewUsers2.getImage_three()).into(fan3);
                builder.setView(vol);
                builder.setNegativeButton("close", (oo, o) -> oo.cancel());
                AlertDialog alertx = builder.create();
                alertx.setCancelable(false);
                alertx.setCanceledOnTouchOutside(false);
                alertx.show();
            } else if (viewUsers2.getOne_sta().equals("Seized") & viewUsers2.getTwo_sta().equals("Seized") & viewUsers2.getThree_sta().equals("Pending")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(Html.fromHtml("<font><b><u>" + viewUsers2.getName() + "</u></b> Collateral Details</font>"));
                Rect rec = new Rect();
                Window win = this.getWindow();
                win.getDecorView().getWindowVisibleDisplayFrame(rec);
                LayoutInflater lay = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View vol = lay.inflate(R.layout.two_temp, null);
                vol.setMinimumWidth((int) (rec.width() * 0.9));
                vol.setMinimumHeight((int) (rec.height() * 0.01));
                ImageView fan = vol.findViewById(R.id.circleView);
                ImageView fan2 = vol.findViewById(R.id.myImage);
                TextView text1 = vol.findViewById(R.id.mktSec);
                TextView text2 = vol.findViewById(R.id.accRece);
                text1.setText("AssetCategory: " + viewUsers2.getCategory_two() + "\nType: " + viewUsers2.getType_two() + "\nExistence: " + viewUsers2.getExistence_two() + " yrs\nWorth KES" + viewUsers2.getWorth_two() + "\nTakeOver: Seized\nResume...");
                text2.setText("ClientIDno: " + viewUsers2.getId_no() + "\nPhone: " + viewUsers2.getPhone() + "\nCounty: " + viewUsers2.getCounty() + "\nLocation: " + viewUsers2.getLocation() + "\nAgentEmail: " + viewUsers2.getAgent_email() + "\n\nAssetCategory: " + viewUsers2.getCategory_one() + "\nType: " + viewUsers2.getType_one() + "\nExistence: " + viewUsers2.getExistence_one() + " yrs\nWorth KES" + viewUsers2.getWorth_one() + "\nTakeOver: Seized\nResume...");
                Glide.with(this).load(viewUsers2.getImage_two()).into(fan2);
                Glide.with(this).load(viewUsers2.getImage_one()).into(fan);
                builder.setView(vol);
                builder.setNegativeButton("close", (oo, o) -> oo.cancel());
                AlertDialog alertx = builder.create();
                alertx.setCancelable(false);
                alertx.setCanceledOnTouchOutside(false);
                alertx.show();
            } else if (viewUsers2.getOne_sta().equals("Seized") & viewUsers2.getTwo_sta().equals("Pending") &  viewUsers2.getThree_sta().equals("Seized")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(Html.fromHtml("<font><b><u>" + viewUsers2.getName() + "</u></b> Collateral Details</font>"));
                Rect rec = new Rect();
                Window win = this.getWindow();
                win.getDecorView().getWindowVisibleDisplayFrame(rec);
                LayoutInflater lay = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View vol = lay.inflate(R.layout.two_temp, null);
                vol.setMinimumWidth((int) (rec.width() * 0.9));
                vol.setMinimumHeight((int) (rec.height() * 0.01));
                ImageView fan = vol.findViewById(R.id.circleView);
                ImageView fan2 = vol.findViewById(R.id.myImage);
                TextView text1 = vol.findViewById(R.id.mktSec);
                TextView text2 = vol.findViewById(R.id.accRece);
                text2.setText("AssetCategory: " + viewUsers2.getCategory_three() + "\nType: " + viewUsers2.getType_three() + "\nExistence: " + viewUsers2.getExistence_three() + " yrs\nWorth KES" + viewUsers2.getWorth_three() + "\nTakeOver: Seized\nResume...");
                text1.setText("ClientIDno: " + viewUsers2.getId_no() + "\nPhone: " + viewUsers2.getPhone() + "\nCounty: " + viewUsers2.getCounty() + "\nLocation: " + viewUsers2.getLocation() + "\nAgentEmail: " + viewUsers2.getAgent_email() + "\n\nAssetCategory: " + viewUsers2.getCategory_one() + "\nType: " + viewUsers2.getType_one() + "\nExistence: " + viewUsers2.getExistence_one() + " yrs\nWorth KES" + viewUsers2.getWorth_one() + "\nTakeOver: Seized\nResume...");
                Glide.with(this).load(viewUsers2.getImage_three()).into(fan2);
                Glide.with(this).load(viewUsers2.getImage_one()).into(fan);
                builder.setView(vol);
                builder.setNegativeButton("close", (oo, o) -> oo.cancel());
                AlertDialog alertx = builder.create();
                alertx.setCancelable(false);
                alertx.setCanceledOnTouchOutside(false);
                alertx.show();
            } else if (viewUsers2.getOne_sta().equals("Pending") & viewUsers2.getTwo_sta().equals("Seized") & viewUsers2.getThree_sta().equals("Seized")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(Html.fromHtml("<font><b><u>" + viewUsers2.getName() + "</u></b> Collateral Details</font>"));
                Rect rec = new Rect();
                Window win = this.getWindow();
                win.getDecorView().getWindowVisibleDisplayFrame(rec);
                LayoutInflater lay = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View vol = lay.inflate(R.layout.two_temp, null);
                vol.setMinimumWidth((int) (rec.width() * 0.9));
                vol.setMinimumHeight((int) (rec.height() * 0.01));
                ImageView fan = vol.findViewById(R.id.circleView);
                ImageView fan2 = vol.findViewById(R.id.myImage);
                TextView text1 = vol.findViewById(R.id.mktSec);
                TextView text2 = vol.findViewById(R.id.accRece);
                text2.setText("AssetCategory: " + viewUsers2.getCategory_three() + "\nType: " + viewUsers2.getType_three() + "\nExistence: " + viewUsers2.getExistence_three() + " yrs\nWorth KES" + viewUsers2.getWorth_three() + "\nTakeOver: Seized\nResume...");
                text1.setText("ClientIDno: " + viewUsers2.getId_no() + "\nPhone: " + viewUsers2.getPhone() + "\nCounty: " + viewUsers2.getCounty() + "\nLocation: " + viewUsers2.getLocation() + "\nAgentEmail: " + viewUsers2.getAgent_email() + "\n\nAssetCategory: " + viewUsers2.getCategory_two() + "\nType: " + viewUsers2.getType_two() + "\nExistence: " + viewUsers2.getExistence_two() + " yrs\nWorth KES" + viewUsers2.getWorth_two() + "\nTakeOver: Seized\nResume...");
                Glide.with(this).load(viewUsers2.getImage_three()).into(fan2);
                Glide.with(this).load(viewUsers2.getImage_two()).into(fan);
                builder.setView(vol);
                builder.setNegativeButton("close", (oo, o) -> oo.cancel());
                AlertDialog alertx = builder.create();
                alertx.setCancelable(false);
                alertx.setCanceledOnTouchOutside(false);
                alertx.show();
            } else if (viewUsers2.getOne_sta().equals("Seized") & viewUsers2.getTwo_sta().equals("Pending") & viewUsers2.getThree_sta().equals("Pending")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(Html.fromHtml("<font><b><u>" + viewUsers2.getName() + "</u></b> Collateral Details</font>"));
                builder.setMessage("ClientIDno: " + viewUsers2.getId_no() + "\nPhone: " + viewUsers2.getPhone() + "\nCounty: " + viewUsers2.getCounty() + "\nLocation: " + viewUsers2.getLocation() + "\nAgentEmail: " + viewUsers2.getAgent_email() + "\n\nAssetCategory: " + viewUsers2.getCategory_one() + "\nType: " + viewUsers2.getType_one() + "\nExistence: " + viewUsers2.getExistence_one() + " yrs\nWorth KES" + viewUsers2.getWorth_one() + "\nTakeOver: Seized\nResume...");
                Rect rec = new Rect();
                Window win = this.getWindow();
                win.getDecorView().getWindowVisibleDisplayFrame(rec);
                LayoutInflater lay = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View vol = lay.inflate(R.layout.three_temp, null);
                vol.setMinimumWidth((int) (rec.width() * 0.9));
                vol.setMinimumHeight((int) (rec.height() * 0.01));
                ImageView fan = vol.findViewById(R.id.cartex);
                Glide.with(this).load(viewUsers2.getImage_one()).into(fan);
                builder.setView(vol);
                builder.setNegativeButton("close", (oo, o) -> oo.cancel());
                AlertDialog alertx = builder.create();
                alertx.setCancelable(false);
                alertx.setCanceledOnTouchOutside(false);
                alertx.show();
            } else if (viewUsers2.getOne_sta().equals("Pending") & viewUsers2.getTwo_sta().equals("Seized") & viewUsers2.getThree_sta().equals("Pending")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(Html.fromHtml("<font><b><u>" + viewUsers2.getName() + "</u></b> Collateral Details</font>"));
                builder.setMessage("ClientIDno: " + viewUsers2.getId_no() + "\nPhone: " + viewUsers2.getPhone() + "\nCounty: " + viewUsers2.getCounty() + "\nLocation: " + viewUsers2.getLocation() + "\nAgentEmail: " + viewUsers2.getAgent_email() + "\n\nAssetCategory: " + viewUsers2.getCategory_two() + "\nType: " + viewUsers2.getType_two() + "\nExistence: " + viewUsers2.getExistence_two() + " yrs\nWorth KES" + viewUsers2.getWorth_two() + "\nTakeOver: Seized\nResume...");
                Rect rec = new Rect();
                Window win = this.getWindow();
                win.getDecorView().getWindowVisibleDisplayFrame(rec);
                LayoutInflater lay = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View vol = lay.inflate(R.layout.three_temp, null);
                vol.setMinimumWidth((int) (rec.width() * 0.9));
                vol.setMinimumHeight((int) (rec.height() * 0.01));
                ImageView fan = vol.findViewById(R.id.cartex);
                Glide.with(this).load(viewUsers2.getImage_two()).into(fan);
                builder.setView(vol);
                builder.setNegativeButton("close", (oo, o) -> oo.cancel());
                AlertDialog alertx = builder.create();
                alertx.setCancelable(false);
                alertx.setCanceledOnTouchOutside(false);
                alertx.show();
            } else if (viewUsers2.getOne_sta().equals("Pending") & viewUsers2.getTwo_sta().equals("Pending") & viewUsers2.getThree_sta().equals("Seized")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(Html.fromHtml("<font><b><u>" + viewUsers2.getName() + "</u></b> Collateral Details</font>"));
                builder.setMessage("ClientIDno: " + viewUsers2.getId_no() + "\nPhone: " + viewUsers2.getPhone() + "\nCounty: " + viewUsers2.getCounty() + "\nLocation: " + viewUsers2.getLocation() + "\nAgentEmail: " + viewUsers2.getAgent_email() + "\n\nAssetCategory: " + viewUsers2.getCategory_three() + "\nType: " + viewUsers2.getType_three() + "\nExistence: " + viewUsers2.getExistence_three() + " yrs\nWorth KES" + viewUsers2.getWorth_three() + "\nTakeOver: Seized\nResume...");
                Rect rec = new Rect();
                Window win = this.getWindow();
                win.getDecorView().getWindowVisibleDisplayFrame(rec);
                LayoutInflater lay = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View vol = lay.inflate(R.layout.three_temp, null);
                vol.setMinimumWidth((int) (rec.width() * 0.9));
                vol.setMinimumHeight((int) (rec.height() * 0.01));
                ImageView fan = vol.findViewById(R.id.cartex);
                Glide.with(this).load(viewUsers2.getImage_three()).into(fan);
                builder.setView(vol);
                builder.setNegativeButton("close", (oo, o) -> oo.cancel());
                AlertDialog alertx = builder.create();
                alertx.setCancelable(false);
                alertx.setCanceledOnTouchOutside(false);
                alertx.show();

            }
        });
        practical();
    }

    private void practical() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URls.viewCol,
                response -> {
                    try {
                        GreatMatch subject;
                        SubjectList = new ArrayList<>();
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
                                subject = new GreatMatch(reg_id, id_no, name, phone, email, county, location, agent_email,
                                        category_one, type_one, existence_one, worth_one, imag, one_sta, category_two, type_two, existence_two,
                                        worth_two, image, two_sta, category_three, type_three, existence_three, worth_three, imagery, three_sta, status, status_auc, reg_date);
                                SubjectList.add(subject);
                            }
                            produAda = new GreateAda(SiezedCollateral.this, R.layout.attach_agen, SubjectList);
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