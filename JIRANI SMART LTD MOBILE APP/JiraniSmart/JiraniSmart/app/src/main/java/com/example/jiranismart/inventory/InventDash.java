package com.example.jiranismart.inventory;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.jiranismart.Fermented.InventSession;
import com.example.jiranismart.Fermented.StaffMode;
import com.example.jiranismart.MainActivity;
import com.example.jiranismart.R;

import java.util.Objects;

public class InventDash extends AppCompatActivity {
    InventSession inventSession;
    StaffMode staffMode;
    CardView Agent, Collateral, CollHist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inventSession = new InventSession(getApplicationContext());
        staffMode = inventSession.getInventDetails();
        Objects.requireNonNull(getSupportActionBar()).setTitle("Welcome " + staffMode.getFname());
        setContentView(R.layout.activity_invent_dash);
        Agent = findViewById(R.id.agentAssign);
        Collateral = findViewById(R.id.LOANaPPS);
        CollHist = findViewById(R.id.CLIENTdEPOSITS);
        Agent.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(),AgentNew.class));
        });
        Collateral.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(),CollateralNew.class));
        });
        CollHist.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(),SiezedCollateral.class));
        });
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
                AlertDialog.Builder builder = new AlertDialog.Builder(InventDash.this);
                builder.setTitle("Registration Information");
                builder.setMessage("EntryID :" + staffMode.getEntry() + "\nName :" + staffMode.getFname() + " " + staffMode.getLname() + "\nEmail :" + staffMode.getEmail() + "\nPhone :" + staffMode.getPhone() + "\nRole :" + staffMode.getRole() + "\nEntryDate :" + staffMode.getReg_date());
                builder.setIcon(R.drawable.profile);
                builder.setPositiveButton("Logout", (dialogt, idd) -> {
                    inventSession.logoutInvent();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                });
                builder.setNeutralButton("exit", (dialogt, idd) -> {
                    inventSession.logoutInvent();
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