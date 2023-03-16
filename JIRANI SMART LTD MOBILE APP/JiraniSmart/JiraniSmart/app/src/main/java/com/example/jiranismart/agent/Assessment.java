package com.example.jiranismart.agent;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jiranismart.Fermented.AgentMode;
import com.example.jiranismart.Fermented.AgentSession;
import com.example.jiranismart.Fermented.GreatMatch;
import com.example.jiranismart.Fermented.GreateAda;
import com.example.jiranismart.Fermented.URls;
import com.example.jiranismart.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Assessment extends AppCompatActivity {
    AgentMode agentMode;
    AgentSession agentSession;
    ListView listView;
    SearchView searchView;
    ArrayList<GreatMatch> viewUsers = new ArrayList<>();
    GreateAda viewAda;
    GreatMatch viewUsers2;
    View view;
    TextView texterMk, texterAc, texterMac;
    Button ScanImage, Submit, exit;
    RequestQueue requestQueue;
    private Spinner spinOne, spinTwo, spinThree;
    EditText EditExistence, EditWorth, worth2, worth3, exist2, exist3;
    ImageView imageView, imaging, carte;
    Bitmap bitmapOne, bitmapTwo, bitmapThree;
    String encodedimageOne, encodedimageTwo, encodedimageThree;
    RelativeLayout relaOne, relaTwo, relaThre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Newly Attached Clients");
        setContentView(R.layout.activity_assessment);
        listView = findViewById(R.id.listview);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.search);
        agentSession = new AgentSession(getApplicationContext());
        agentMode = agentSession.getAgentDetails();
        listView.setOnItemClickListener((parent, view, position, id) -> {
            viewUsers2 = (GreatMatch) parent.getItemAtPosition(position);
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            Rect rect = new Rect();
            Window window = Assessment.this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            LayoutInflater layoutInflater = (LayoutInflater) Assessment.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = layoutInflater.inflate(R.layout.asser, null);
            layout.setMinimumWidth((int) (rect.width() * 0.9f));
            layout.setMinimumHeight((int) (rect.height() * 0.04f));
            EditExistence = layout.findViewById(R.id.existence);
            EditWorth = layout.findViewById(R.id.worth);
            exit = layout.findViewById(R.id.btnClose);
            Submit = layout.findViewById(R.id.btnPost);
            spinOne = layout.findViewById(R.id.spnType);//texterMk,EditWorth,EditExistence/imageView
            spinThree = layout.findViewById(R.id.spnTypeThree);//texterMac,worth3,exist3/carte
            spinTwo = layout.findViewById(R.id.spnTypeTwo);//texterAc,worth2,exist2/imaging
            imaging = layout.findViewById(R.id.myImage);
            imageView = layout.findViewById(R.id.circleView);
            carte = layout.findViewById(R.id.cartex);
            relaOne = layout.findViewById(R.id.relative);
            relaTwo = layout.findViewById(R.id.relativeTwo);
            texterMk = layout.findViewById(R.id.mktSec);
            texterAc = layout.findViewById(R.id.accRece);
            texterMac = layout.findViewById(R.id.machSec);
            worth2 = layout.findViewById(R.id.worthOne);
            exist2 = layout.findViewById(R.id.existenceOne);
            worth3 = layout.findViewById(R.id.worthTwo);
            exist3 = layout.findViewById(R.id.existenceTwo);
            relaThre = layout.findViewById(R.id.relativeThree);
            imageView.setOnClickListener(v -> {
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        101);
            });
            imaging.setOnClickListener(v1 -> {
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        102
                );
            });
            carte.setOnClickListener(v1 -> {
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        103
                );
            });
            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(Assessment.this, R.array.Marketable, android.R.layout.simple_spinner_item);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinOne.setAdapter(adapter2);
            ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(Assessment.this, R.array.Accounts, android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinTwo.setAdapter(adapter1);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Assessment.this, R.array.Machinery, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinThree.setAdapter(adapter);
            spinOne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String mSpinner = spinOne.getSelectedItem().toString();
                    if (mSpinner.equals("Select Type")) {
                        relaOne.setVisibility(View.GONE);
                    } else {
                        relaOne.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinTwo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String mSpinner = spinTwo.getSelectedItem().toString();
                    if (mSpinner.equals("Select Type")) {
                        relaTwo.setVisibility(View.GONE);
                    } else {
                        relaTwo.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinThree.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String mSpinner = spinThree.getSelectedItem().toString();
                    if (mSpinner.equals("Select Type")) {
                        relaThre.setVisibility(View.GONE);
                    } else {
                        relaThre.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            Submit.setOnClickListener(v -> {
                final String typeOne = spinOne.getSelectedItem().toString().trim();
                final String typeTwo = spinTwo.getSelectedItem().toString().trim();
                final String typeThree = spinThree.getSelectedItem().toString().trim();
                if (typeOne.equals("Select Type") & typeTwo.equals("Select Type") & typeThree.equals("Select Type")) {
                    Toast.makeText(this, "Please select at least on Group", Toast.LENGTH_SHORT).show();
                } else if (!typeOne.equals("Select Type") & !typeTwo.equals("Select Type") & !typeThree.equals("Select Type")) {
                    final String myQuantOne = EditWorth.getText().toString().trim();
                    final String myDescOne = EditExistence.getText().toString().trim();
                    final String myCategoryOne = texterMk.getText().toString().trim();
                    Drawable myImageOne = imageView.getDrawable();
                    final String myQuantTwo = worth2.getText().toString().trim();
                    final String myDescTwo = exist2.getText().toString().trim();
                    final String myCategoryTwo = texterAc.getText().toString().trim();
                    Drawable myImageTwo = imaging.getDrawable();
                    final String myQuantThre = worth3.getText().toString().trim();
                    final String myDescThre = exist3.getText().toString().trim();
                    final String myCategoryThre = texterMac.getText().toString().trim();
                    Drawable myImageThre = carte.getDrawable();
                    if (myQuantOne.isEmpty()) {
                        EditWorth.setError("Required");
                        EditWorth.requestFocus();
                    } else if (Float.parseFloat(myQuantOne) < 1000) {
                        EditWorth.setError("Cannot Upload an Asset\nLess than KES 1000");
                        EditWorth.requestFocus();
                    } else if (myDescOne.isEmpty()) {
                        EditExistence.setError("required");
                        EditExistence.requestFocus();
                    } else if (Float.parseFloat(myDescOne) < 0.25) {
                        EditExistence.setError("Check Your timing well");
                        EditExistence.requestFocus();
                    } else if (myImageOne == null) {
                        Toast.makeText(this, "Add some Resume image for Marketable Security", Toast.LENGTH_SHORT).show();
                    } else if (myQuantTwo.isEmpty()) {
                        worth2.setError("Required");
                        worth2.requestFocus();
                    } else if (Float.parseFloat(myQuantTwo) < 1000) {
                        worth2.setError("Cannot Upload an Asset\nLess than KES 1000");
                        worth2.requestFocus();
                    } else if (myDescTwo.isEmpty()) {
                        exist2.setError("required");
                        exist2.requestFocus();
                    } else if (Float.parseFloat(myDescTwo) < 0.25) {
                        exist2.setError("Check Your timing well");
                        exist2.requestFocus();
                    } else if (myImageTwo == null) {
                        Toast.makeText(this, "Add some Resume image for Account Receivables", Toast.LENGTH_SHORT).show();
                    } else if (myQuantThre.isEmpty()) {
                        worth3.setError("Required");
                        worth3.requestFocus();
                    } else if (Float.parseFloat(myQuantThre) < 1000) {
                        worth3.setError("Cannot Upload an Asset\nLess than KES 1000");
                        worth3.requestFocus();
                    } else if (myDescThre.isEmpty()) {
                        exist3.setError("required");
                        exist3.requestFocus();
                    } else if (Float.parseFloat(myDescThre) < 0.25) {
                        exist3.setError("Check Your timing well");
                        exist3.requestFocus();
                    } else if (myImageThre == null) {
                        Toast.makeText(this, "Add some Resume image for Machinery Security", Toast.LENGTH_SHORT).show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle(Html.fromHtml("<font>Confirm <b><u>" + viewUsers2.getName() + "</u></b> Details</font>"));
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
                        text3.setText("AssetCategory: " + myCategoryThre + "\nType: " + typeThree + "\nExistence: " + myDescThre + " yrs\nWorth KES" + myQuantThre + "\nResume...");
                        text2.setText("AssetCategory: " + myCategoryTwo + "\nType: " + typeTwo + "\nExistence: " + myDescTwo + " yrs\nWorth KES" + myQuantTwo + "\nResume...");
                        text1.setText("AssetCategory: " + myCategoryOne + "\nType: " + typeOne + "\nExistence: " + myDescOne + " yrs\nWorth KES" + myQuantOne + "\nResume...");
                        fan.setImageBitmap(bitmapOne);
                        fan2.setImageBitmap(bitmapTwo);
                        fan3.setImageBitmap(bitmapThree);
                        builder.setView(vol);
                        builder.setNegativeButton("no_close", (oo, o) -> oo.cancel());
                        builder.setPositiveButton("yes_submit", (oo, o) -> {
                            requestQueue = Volley.newRequestQueue(getApplicationContext());
                            requestQueue.add(new StringRequest(Request.Method.POST, URls.entrustedAss,
                                    response -> {
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            String mess = jsonObject.getString("message");
                                            int Status = jsonObject.getInt("success");
                                            if (Status == 1) {
                                                Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(), Assessment.class));
                                                finish();
                                            } else if (Status == 0) {
                                                Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show();
                                        }
                                    }, error -> {
                                Toast.makeText(this, "Connection Error", Toast.LENGTH_SHORT).show();
                            }) {
                                @Nullable
                                @Override
                                protected Map<String, String> getParams() {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("reg_id", viewUsers2.getReg_id());
                                    params.put("category_one", myCategoryOne);
                                    params.put("type_one", typeOne);
                                    params.put("existence_one", myDescOne);
                                    params.put("worth_one", myQuantOne);
                                    params.put("image_one", encodedimageOne);
                                    params.put("category_two", myCategoryTwo);
                                    params.put("type_two", typeTwo);
                                    params.put("existence_two", myDescTwo);
                                    params.put("worth_two", myQuantTwo);
                                    params.put("image_two", encodedimageTwo);
                                    params.put("category_three", myCategoryThre);
                                    params.put("type_three", typeThree);
                                    params.put("existence_three", myDescThre);
                                    params.put("worth_three", myQuantThre);
                                    params.put("image_three", encodedimageThree);
                                    return params;
                                }
                            });
                        });
                        AlertDialog alertx = builder.create();
                        alertx.setCancelable(false);
                        alertx.setCanceledOnTouchOutside(false);
                        alertx.show();
                    }
                } else if (!typeOne.equals("Select Type") & !typeTwo.equals("Select Type") & typeThree.equals("Select Type")) {
                    final String myQuantOne = EditWorth.getText().toString().trim();
                    final String myDescOne = EditExistence.getText().toString().trim();
                    final String myCategoryOne = texterMk.getText().toString().trim();
                    Drawable myImageOne = imageView.getDrawable();
                    final String myQuantTwo = worth2.getText().toString().trim();
                    final String myDescTwo = exist2.getText().toString().trim();
                    final String myCategoryTwo = texterAc.getText().toString().trim();
                    Drawable myImageTwo = imaging.getDrawable();
                    if (myQuantOne.isEmpty()) {
                        EditWorth.setError("Required");
                        EditWorth.requestFocus();
                    } else if (Float.parseFloat(myQuantOne) < 1000) {
                        EditWorth.setError("Cannot Upload an Asset\nLess than KES 1000");
                        EditWorth.requestFocus();
                    }  else if (myDescOne.isEmpty()) {
                        EditExistence.setError("required");
                        EditExistence.requestFocus();
                    } else if (Float.parseFloat(myDescOne) < 0.25) {
                        EditExistence.setError("Check Your timing well");
                        EditExistence.requestFocus();
                    } else if (myImageOne == null) {
                        Toast.makeText(this, "Add some Resume image for Marketable Security", Toast.LENGTH_SHORT).show();
                    } else if (myQuantTwo.isEmpty()) {
                        worth2.setError("Required");
                        worth2.requestFocus();
                    } else if (Float.parseFloat(myQuantTwo) < 1000) {
                        worth2.setError("Cannot Upload an Asset\nLess than KES 1000");
                        worth2.requestFocus();
                    } else if (myDescTwo.isEmpty()) {
                        exist2.setError("required");
                        exist2.requestFocus();
                    } else if (Float.parseFloat(myDescTwo) < 0.25) {
                        exist2.setError("Check Your timing well");
                        exist2.requestFocus();
                    } else if (myImageTwo == null) {
                        Toast.makeText(this, "Add some Resume image for Account Receivables", Toast.LENGTH_SHORT).show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle(Html.fromHtml("<font>Confirm <b><u>" + viewUsers2.getName() + "</u></b> Details</font>"));
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
                        text1.setText("AssetCategory: " + myCategoryTwo + "\nType: " + typeTwo + "\nExistence: " + myDescTwo + " yrs\nWorth KES" + myQuantTwo + "\nResume...");
                        text2.setText("AssetCategory: " + myCategoryOne + "\nType: " + typeOne + "\nExistence: " + myDescOne + " yrs\nWorth KES" + myQuantOne + "\nResume...");
                        fan2.setImageBitmap(bitmapTwo);
                        fan.setImageBitmap(bitmapOne);
                        builder.setView(vol);
                        builder.setNegativeButton("no_close", (oo, o) -> oo.cancel());
                        builder.setPositiveButton("yes_submit", (oo, o) -> {
                            requestQueue = Volley.newRequestQueue(getApplicationContext());
                            requestQueue.add(new StringRequest(Request.Method.POST, URls.entrustedAss,
                                    response -> {
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            String mess = jsonObject.getString("message");
                                            int Status = jsonObject.getInt("success");
                                            if (Status == 1) {
                                                Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(), Assessment.class));
                                                finish();
                                            } else if (Status == 0) {
                                                Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show();
                                        }
                                    }, error -> {
                                Toast.makeText(this, "Connection Error", Toast.LENGTH_SHORT).show();
                            }) {
                                @Nullable
                                @Override
                                protected Map<String, String> getParams() {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("reg_id", viewUsers2.getReg_id());
                                    params.put("category_one", myCategoryOne);
                                    params.put("type_one", typeOne);
                                    params.put("existence_one", myDescOne);
                                    params.put("worth_one", myQuantOne);
                                    params.put("image_one", encodedimageOne);
                                    params.put("category_two", myCategoryTwo);
                                    params.put("type_two", typeTwo);
                                    params.put("existence_two", myDescTwo);
                                    params.put("worth_two", myQuantTwo);
                                    params.put("image_two", encodedimageTwo);
                                    params.put("category_three", "");
                                    params.put("type_three", "");
                                    params.put("existence_three", "");
                                    params.put("worth_three", "0");
                                    params.put("image_three", "");
                                    return params;
                                }
                            });
                        });
                        AlertDialog alertx = builder.create();
                        alertx.setCancelable(false);
                        alertx.setCanceledOnTouchOutside(false);
                        alertx.show();
                    }
                } else if (!typeOne.equals("Select Type") & typeTwo.equals("Select Type") & !typeThree.equals("Select Type")) {
                    final String myQuantOne = EditWorth.getText().toString().trim();
                    final String myDescOne = EditExistence.getText().toString().trim();
                    final String myCategoryOne = texterMk.getText().toString().trim();
                    Drawable myImageOne = imageView.getDrawable();
                    final String myQuantThre = worth3.getText().toString().trim();
                    final String myDescThre = exist3.getText().toString().trim();
                    final String myCategoryThre = texterMac.getText().toString().trim();
                    Drawable myImageThre = carte.getDrawable();
                    if (myQuantOne.isEmpty()) {
                        EditWorth.setError("Required");
                        EditWorth.requestFocus();
                    } else if (Float.parseFloat(myQuantOne) < 1000) {
                        EditWorth.setError("Cannot Upload an Asset\nLess than KES 1000");
                        EditWorth.requestFocus();
                    }  else if (myDescOne.isEmpty()) {
                        EditExistence.setError("required");
                        EditExistence.requestFocus();
                    } else if (Float.parseFloat(myDescOne) < 0.25) {
                        EditExistence.setError("Check Your timing well");
                        EditExistence.requestFocus();
                    } else if (myImageOne == null) {
                        Toast.makeText(this, "Add some Resume image for Marketable Security", Toast.LENGTH_SHORT).show();
                    } else if (myQuantThre.isEmpty()) {
                        worth3.setError("Required");
                        worth3.requestFocus();
                    } else if (Float.parseFloat(myQuantThre) < 1000) {
                        worth3.setError("Cannot Upload an Asset\nLess than KES 1000");
                        worth3.requestFocus();
                    } else if (myDescThre.isEmpty()) {
                        exist3.setError("required");
                        exist3.requestFocus();
                    } else if (Float.parseFloat(myDescThre) < 0.25) {
                        exist3.setError("Check Your timing well");
                        exist3.requestFocus();
                    } else if (myImageThre == null) {
                        Toast.makeText(this, "Add some Resume image for Machinery Security", Toast.LENGTH_SHORT).show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle(Html.fromHtml("<font>Confirm <b><u>" + viewUsers2.getName() + "</u></b> Details</font>"));
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
                        text2.setText("AssetCategory: " + myCategoryThre + "\nType: " + typeThree + "\nExistence: " + myDescThre + " yrs\nWorth KES" + myQuantThre + "\nResume...");
                        text1.setText("AssetCategory: " + myCategoryOne + "\nType: " + typeOne + "\nExistence: " + myDescOne + " yrs\nWorth KES" + myQuantOne + "\nResume...");
                        fan2.setImageBitmap(bitmapThree);
                        fan.setImageBitmap(bitmapOne);
                        builder.setView(vol);
                        builder.setNegativeButton("no_close", (oo, o) -> oo.cancel());
                        builder.setPositiveButton("yes_submit", (oo, o) -> {
                            requestQueue = Volley.newRequestQueue(getApplicationContext());
                            requestQueue.add(new StringRequest(Request.Method.POST, URls.entrustedAss,
                                    response -> {
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            String mess = jsonObject.getString("message");
                                            int Status = jsonObject.getInt("success");
                                            if (Status == 1) {
                                                Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(), Assessment.class));
                                                finish();
                                            } else if (Status == 0) {
                                                Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show();
                                        }
                                    }, error -> {
                                Toast.makeText(this, "Connection Error", Toast.LENGTH_SHORT).show();
                            }) {
                                @Nullable
                                @Override
                                protected Map<String, String> getParams() {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("reg_id", viewUsers2.getReg_id());
                                    params.put("category_one", myCategoryOne);
                                    params.put("type_one", typeOne);
                                    params.put("existence_one", myDescOne);
                                    params.put("worth_one", myQuantOne);
                                    params.put("image_one", encodedimageOne);
                                    params.put("category_two", "");
                                    params.put("type_two", "");
                                    params.put("existence_two", "");
                                    params.put("worth_two", "0");
                                    params.put("image_two", "");
                                    params.put("category_three", myCategoryThre);
                                    params.put("type_three", typeThree);
                                    params.put("existence_three", myDescThre);
                                    params.put("worth_three", myQuantThre);
                                    params.put("image_three", encodedimageThree);
                                    return params;
                                }
                            });
                        });
                        AlertDialog alertx = builder.create();
                        alertx.setCancelable(false);
                        alertx.setCanceledOnTouchOutside(false);
                        alertx.show();
                    }
                } else if (!typeTwo.equals("Select Type") & !typeThree.equals("Select Type") & typeOne.equals("Select Type")) {
                    final String myQuantTwo = worth2.getText().toString().trim();
                    final String myDescTwo = exist2.getText().toString().trim();
                    final String myCategoryTwo = texterAc.getText().toString().trim();
                    Drawable myImageTwo = imaging.getDrawable();
                    final String myQuantThre = worth3.getText().toString().trim();
                    final String myDescThre = exist3.getText().toString().trim();
                    final String myCategoryThre = texterMac.getText().toString().trim();
                    Drawable myImageThre = carte.getDrawable();
                    if (myQuantTwo.isEmpty()) {
                        worth2.setError("Required");
                        worth2.requestFocus();
                    } else if (Float.parseFloat(myQuantTwo) < 1000) {
                        worth2.setError("Cannot Upload an Asset\nLess than KES 1000");
                        worth2.requestFocus();
                    } else if (myDescTwo.isEmpty()) {
                        exist2.setError("required");
                        exist2.requestFocus();
                    } else if (Float.parseFloat(myDescTwo) < 0.25) {
                        exist2.setError("Check Your timing well");
                        exist2.requestFocus();
                    } else if (myImageTwo == null) {
                        Toast.makeText(this, "Add some Resume image for Account Receivables", Toast.LENGTH_SHORT).show();
                    } else if (myQuantThre.isEmpty()) {
                        worth3.setError("Required");
                        worth3.requestFocus();
                    } else if (Float.parseFloat(myQuantThre) < 1000) {
                        worth3.setError("Cannot Upload an Asset\nLess than KES 1000");
                        worth3.requestFocus();
                    } else if (myDescThre.isEmpty()) {
                        exist3.setError("required");
                        exist3.requestFocus();
                    } else if (Float.parseFloat(myDescThre) < 0.25) {
                        exist3.setError("Check Your timing well");
                        exist3.requestFocus();
                    } else if (myImageThre == null) {
                        Toast.makeText(this, "Add some Resume image for Machinery Security", Toast.LENGTH_SHORT).show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle(Html.fromHtml("<font>Confirm <b><u>" + viewUsers2.getName() + "</u></b> Details</font>"));
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
                        text2.setText("AssetCategory: " + myCategoryThre + "\nType: " + typeThree + "\nExistence: " + myDescThre + " yrs\nWorth KES" + myQuantThre + "\nResume...");
                        text1.setText("AssetCategory: " + myCategoryTwo + "\nType: " + typeTwo + "\nExistence: " + myDescTwo + " yrs\nWorth KES" + myQuantTwo + "\nResume...");
                        fan2.setImageBitmap(bitmapThree);
                        fan.setImageBitmap(bitmapTwo);
                        builder.setView(vol);
                        builder.setNegativeButton("no_close", (oo, o) -> oo.cancel());
                        builder.setPositiveButton("yes_submit", (oo, o) -> {
                            requestQueue = Volley.newRequestQueue(getApplicationContext());
                            requestQueue.add(new StringRequest(Request.Method.POST, URls.entrustedAss,
                                    response -> {
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            String mess = jsonObject.getString("message");
                                            int Status = jsonObject.getInt("success");
                                            if (Status == 1) {
                                                Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(), Assessment.class));
                                                finish();
                                            } else if (Status == 0) {
                                                Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show();
                                        }
                                    }, error -> {
                                Toast.makeText(this, "Connection Error", Toast.LENGTH_SHORT).show();
                            }) {
                                @Nullable
                                @Override
                                protected Map<String, String> getParams() {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("reg_id", viewUsers2.getReg_id());
                                    params.put("category_one", "");
                                    params.put("type_one", "");
                                    params.put("existence_one", "");
                                    params.put("worth_one", "0");
                                    params.put("image_one", "");
                                    params.put("category_two", myCategoryTwo);
                                    params.put("type_two", typeTwo);
                                    params.put("existence_two", myDescTwo);
                                    params.put("worth_two", myQuantTwo);
                                    params.put("image_two", encodedimageTwo);
                                    params.put("category_three", myCategoryThre);
                                    params.put("type_three", typeThree);
                                    params.put("existence_three", myDescThre);
                                    params.put("worth_three", myQuantThre);
                                    params.put("image_three", encodedimageThree);
                                    return params;
                                }
                            });
                        });
                        AlertDialog alertx = builder.create();
                        alertx.setCancelable(false);
                        alertx.setCanceledOnTouchOutside(false);
                        alertx.show();
                    }
                } else if (!typeOne.equals("Select Type") & typeTwo.equals("Select Type") & typeThree.equals("Select Type")) {
                    final String myQuantOne = EditWorth.getText().toString().trim();
                    final String myDescOne = EditExistence.getText().toString().trim();
                    final String myCategoryOne = texterMk.getText().toString().trim();
                    Drawable myImageOne = imageView.getDrawable();
                    if (myQuantOne.isEmpty()) {
                        EditWorth.setError("Required");
                        EditWorth.requestFocus();
                    } else if (Float.parseFloat(myQuantOne) < 1000) {
                        EditWorth.setError("Cannot Upload an Asset\nLess than KES 1000");
                        EditWorth.requestFocus();
                    } else if (myDescOne.isEmpty()) {
                        EditExistence.setError("required");
                        EditExistence.requestFocus();
                    } else if (Float.parseFloat(myDescOne) < 0.25) {
                        EditExistence.setError("Check Your timing well");
                        EditExistence.requestFocus();
                    } else if (myImageOne == null) {
                        Toast.makeText(this, "Add some Resume image for Marketable Security", Toast.LENGTH_SHORT).show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle(Html.fromHtml("<font>Confirm <b><u>" + viewUsers2.getName() + "</u></b> Details</font>"));
                        builder.setMessage("AssetCategory: " + myCategoryOne + "\nType: " + typeOne + "\nExistence: " + myDescOne + " yrs\nWorth KES" + myQuantOne + "\nResume...");
                        Rect rec = new Rect();
                        Window win = this.getWindow();
                        win.getDecorView().getWindowVisibleDisplayFrame(rec);
                        LayoutInflater lay = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View vol = lay.inflate(R.layout.three_temp, null);
                        vol.setMinimumWidth((int) (rec.width() * 0.9));
                        vol.setMinimumHeight((int) (rec.height() * 0.01));
                        ImageView fan = vol.findViewById(R.id.cartex);
                        fan.setImageBitmap(bitmapOne);
                        builder.setView(vol);
                        builder.setNegativeButton("no_close", (oo, o) -> oo.cancel());
                        builder.setPositiveButton("yes_submit", (oo, o) -> {
                            requestQueue = Volley.newRequestQueue(getApplicationContext());
                            requestQueue.add(new StringRequest(Request.Method.POST, URls.entrustedAss,
                                    response -> {
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            String mess = jsonObject.getString("message");
                                            int Status = jsonObject.getInt("success");
                                            if (Status == 1) {
                                                Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(), Assessment.class));
                                                finish();
                                            } else if (Status == 0) {
                                                Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show();
                                        }
                                    }, error -> {
                                Toast.makeText(this, "Connection Error", Toast.LENGTH_SHORT).show();
                            }) {
                                @Nullable
                                @Override
                                protected Map<String, String> getParams() {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("reg_id", viewUsers2.getReg_id());
                                    params.put("category_one", myCategoryOne);
                                    params.put("type_one", typeOne);
                                    params.put("existence_one", myDescOne);
                                    params.put("worth_one", myQuantOne);
                                    params.put("image_one", encodedimageOne);
                                    params.put("category_two", "");
                                    params.put("type_two", "");
                                    params.put("existence_two", "");
                                    params.put("worth_two", "0");
                                    params.put("image_two", "");
                                    params.put("category_three", "");
                                    params.put("type_three", "");
                                    params.put("existence_three", "");
                                    params.put("worth_three", "0");
                                    params.put("image_three", "");
                                    return params;
                                }
                            });
                        });
                        AlertDialog alertx = builder.create();
                        alertx.setCancelable(false);
                        alertx.setCanceledOnTouchOutside(false);
                        alertx.show();
                    }
                } else if (!typeTwo.equals("Select Type") & typeOne.equals("Select Type") & typeThree.equals("Select Type")) {
                    final String myQuantTwo = worth2.getText().toString().trim();
                    final String myDescTwo = exist2.getText().toString().trim();
                    final String myCategoryTwo = texterAc.getText().toString().trim();
                    Drawable myImageTwo = imaging.getDrawable();
                    if (myQuantTwo.isEmpty()) {
                        worth2.setError("Required");
                        worth2.requestFocus();
                    } else if (Float.parseFloat(myQuantTwo) < 1000) {
                        worth2.setError("Cannot Upload an Asset\nLess than KES 1000");
                        worth2.requestFocus();
                    } else if (myDescTwo.isEmpty()) {
                        exist2.setError("required");
                        exist2.requestFocus();
                    } else if (Float.parseFloat(myDescTwo) < 0.25) {
                        exist2.setError("Check Your timing well");
                        exist2.requestFocus();
                    } else if (myImageTwo == null) {
                        Toast.makeText(this, "Add some Resume image for Account Receivables", Toast.LENGTH_SHORT).show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle(Html.fromHtml("<font>Confirm <b><u>" + viewUsers2.getName() + "</u></b> Details</font>"));
                        builder.setMessage("AssetCategory: " + myCategoryTwo + "\nType: " + typeTwo + "\nExistence: " + myDescTwo + " yrs\nWorth KES" + myQuantTwo + "\nResume...");
                        Rect rec = new Rect();
                        Window win = this.getWindow();
                        win.getDecorView().getWindowVisibleDisplayFrame(rec);
                        LayoutInflater lay = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View vol = lay.inflate(R.layout.three_temp, null);
                        vol.setMinimumWidth((int) (rec.width() * 0.9));
                        vol.setMinimumHeight((int) (rec.height() * 0.01));
                        ImageView fan = vol.findViewById(R.id.cartex);
                        fan.setImageBitmap(bitmapTwo);
                        builder.setView(vol);
                        builder.setNegativeButton("no_close", (oo, o) -> oo.cancel());
                        builder.setPositiveButton("yes_submit", (oo, o) -> {
                            requestQueue = Volley.newRequestQueue(getApplicationContext());
                            requestQueue.add(new StringRequest(Request.Method.POST, URls.entrustedAss,
                                    response -> {
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            String mess = jsonObject.getString("message");
                                            int Status = jsonObject.getInt("success");
                                            if (Status == 1) {
                                                Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(), Assessment.class));
                                                finish();
                                            } else if (Status == 0) {
                                                Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show();
                                        }
                                    }, error -> {
                                Toast.makeText(this, "Connection Error", Toast.LENGTH_SHORT).show();
                            }) {
                                @Nullable
                                @Override
                                protected Map<String, String> getParams() {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("reg_id", viewUsers2.getReg_id());
                                    params.put("category_one", "");
                                    params.put("type_one", "");
                                    params.put("existence_one", "");
                                    params.put("worth_one", "0");
                                    params.put("image_one", "");
                                    params.put("category_two", myCategoryTwo);
                                    params.put("type_two", typeTwo);
                                    params.put("existence_two", myDescTwo);
                                    params.put("worth_two", myQuantTwo);
                                    params.put("image_two", encodedimageTwo);
                                    params.put("category_three", "");
                                    params.put("type_three", "");
                                    params.put("existence_three", "");
                                    params.put("worth_three", "0");
                                    params.put("image_three", "");
                                    return params;
                                }
                            });
                        });
                        AlertDialog alertx = builder.create();
                        alertx.setCancelable(false);
                        alertx.setCanceledOnTouchOutside(false);
                        alertx.show();
                    }
                } else if (!typeThree.equals("Select Type") & typeOne.equals("Select Type") & typeTwo.equals("Select Type")) {
                    final String myQuantThre = worth3.getText().toString().trim();
                    final String myDescThre = exist3.getText().toString().trim();
                    final String myCategoryThre = texterMac.getText().toString().trim();
                    Drawable myImageThre = carte.getDrawable();
                    if (myQuantThre.isEmpty()) {
                        worth3.setError("Required");
                        worth3.requestFocus();
                    } else if (Float.parseFloat(myQuantThre) < 1000) {
                        worth3.setError("Cannot Upload an Asset\nLess than KES 1000");
                        worth3.requestFocus();
                    } else if (myDescThre.isEmpty()) {
                        exist3.setError("required");
                        exist3.requestFocus();
                    } else if (Float.parseFloat(myDescThre) < 0.25) {
                        exist3.setError("Check Your timing well");
                        exist3.requestFocus();
                    } else if (myImageThre == null) {
                        Toast.makeText(this, "Add some Resume image for Machinery Security", Toast.LENGTH_SHORT).show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle(Html.fromHtml("<font>Confirm <b><u>" + viewUsers2.getName() + "</u></b> Details</font>"));
                        builder.setMessage("AssetCategory: " + myCategoryThre + "\nType: " + typeThree + "\nExistence: " + myDescThre + " yrs\nWorth KES" + myQuantThre + "\nResume...");
                        Rect rec = new Rect();
                        Window win = this.getWindow();
                        win.getDecorView().getWindowVisibleDisplayFrame(rec);
                        LayoutInflater lay = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View vol = lay.inflate(R.layout.three_temp, null);
                        vol.setMinimumWidth((int) (rec.width() * 0.9));
                        vol.setMinimumHeight((int) (rec.height() * 0.01));
                        ImageView fan = vol.findViewById(R.id.cartex);
                        fan.setImageBitmap(bitmapThree);
                        builder.setView(vol);
                        builder.setNegativeButton("no_close", (oo, o) -> oo.cancel());
                        builder.setPositiveButton("yes_submit", (oo, o) -> {
                            requestQueue = Volley.newRequestQueue(getApplicationContext());
                            requestQueue.add(new StringRequest(Request.Method.POST, URls.entrustedAss,
                                    response -> {
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            String mess = jsonObject.getString("message");
                                            int Status = jsonObject.getInt("success");
                                            if (Status == 1) {
                                                Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(), Assessment.class));
                                                finish();
                                            } else if (Status == 0) {
                                                Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show();
                                        }
                                    }, error -> {
                                Toast.makeText(this, "Connection Error", Toast.LENGTH_SHORT).show();
                            }) {
                                @Nullable
                                @Override
                                protected Map<String, String> getParams() {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("reg_id", viewUsers2.getReg_id());
                                    params.put("category_one", "");
                                    params.put("type_one", "");
                                    params.put("existence_one", "");
                                    params.put("worth_one", "0");
                                    params.put("image_one", "");
                                    params.put("category_two", "");
                                    params.put("type_two", typeTwo);
                                    params.put("existence_two", "");
                                    params.put("worth_two", "0");
                                    params.put("image_two", "");
                                    params.put("category_three", myCategoryThre);
                                    params.put("type_three", typeThree);
                                    params.put("existence_three", myDescThre);
                                    params.put("worth_three", myQuantThre);
                                    params.put("image_three", encodedimageThree);
                                    return params;
                                }
                            });    //reg_id,category_one,type_one,existence_one,worth_one,image_one
                        });//category_two,type_two,existence_two,worth_two,image_two
                        //category_three,type_three,existence_three,worth_three,image_three
                        AlertDialog alertx = builder.create();
                        alertx.setCancelable(false);
                        alertx.setCanceledOnTouchOutside(false);
                        alertx.show();
                    }
                }
            });
            alert.setTitle("Upload " + viewUsers2.getName() + " Assets");
            alert.setView(layout);
            AlertDialog alertDialog = alert.create();
            exit.setOnClickListener(v -> {
                alertDialog.cancel();
            });
            alertDialog.show();
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.setCancelable(false);
        });

        practical();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 101) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 101);
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        if (requestCode == 102) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 102);
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        if (requestCode == 103) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 103);
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 101 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                bitmapOne = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmapOne);
                encodedBitmapOne(bitmapOne);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == 102 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                bitmapTwo = BitmapFactory.decodeStream(inputStream);
                imaging.setImageBitmap(bitmapTwo);
                encodedBitmapTwo(bitmapTwo);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == 103 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                bitmapThree = BitmapFactory.decodeStream(inputStream);
                carte.setImageBitmap(bitmapThree);
                encodedBitmapThree(bitmapThree);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void encodedBitmapOne(Bitmap bitmapOne) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapOne.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        byte[] bytesofimages = byteArrayOutputStream.toByteArray();
        encodedimageOne = Base64.encodeToString(bytesofimages, Base64.DEFAULT);
    }

    private void encodedBitmapThree(Bitmap bitmapThree) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapThree.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        byte[] bytesofimages = byteArrayOutputStream.toByteArray();
        encodedimageThree = Base64.encodeToString(bytesofimages, Base64.DEFAULT);
    }

    private void encodedBitmapTwo(Bitmap bitmapTwo) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapTwo.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        byte[] bytesofimages = byteArrayOutputStream.toByteArray();
        encodedimageTwo = Base64.encodeToString(bytesofimages, Base64.DEFAULT);
    }

    private void practical() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URls.agentReview,
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
                            viewAda = new GreateAda(Assessment.this, R.layout.attach_agen, viewUsers);
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
                            AlertDialog.Builder builder = new AlertDialog.Builder(Assessment.this);
                            builder.setTitle(msg);
                            builder.show();
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
                params.put("email", agentMode.getEmail());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}