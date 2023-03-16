package com.example.jiranismart.inventory;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jiranismart.Fermented.GreatMatch;
import com.example.jiranismart.Fermented.GreateAda;
import com.example.jiranismart.Fermented.URls;
import com.example.jiranismart.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class AgentHist extends AppCompatActivity {
    ListView listView;
    SearchView searchView;
    ArrayList<GreatMatch> viewUsers = new ArrayList<>();
    GreateAda viewAda;
    GreatMatch viewUsers2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Attachment History");
        setContentView(R.layout.activity_agent_hist);
        listView = findViewById(R.id.listedOne);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.search);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            viewUsers2 = (GreatMatch) parent.getItemAtPosition(position);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Assessment Progress");
            builder.setMessage("AgentEmail: " + viewUsers2.getAgent_email() + "\nClientID: " + viewUsers2.getId_no() + "\nClientName: " + viewUsers2.getName() + "\nPhone: " + viewUsers2.getPhone() + "\nLocation: " + viewUsers2.getLocation() + " " + viewUsers2.getCounty() + "\nAssessment: " + viewUsers2.getStatus());
            builder.show();
        });
        practical();
    }

    private void practical() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URls.agen,
                response -> {
                    try {
                        GreatMatch subject;
                        viewUsers = new ArrayList<>();
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
                                viewUsers.add(subject);
                            }//reg_id,id_no,name,phone,email,county,location,agent_email//agentReview
                            viewAda = new GreateAda(AgentHist.this, R.layout.attach_agen, viewUsers);
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
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setTitle(msg);
                            builder.show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "an error", Toast.LENGTH_SHORT).show();
                    }

                }, error -> {
            Toast.makeText(this, "error in connection", Toast.LENGTH_SHORT).show();

        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}