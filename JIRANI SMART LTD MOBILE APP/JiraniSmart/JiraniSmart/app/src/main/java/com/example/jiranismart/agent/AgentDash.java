package com.example.jiranismart.agent;

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

import com.example.jiranismart.Fermented.AgentMode;
import com.example.jiranismart.Fermented.AgentSession;
import com.example.jiranismart.MainActivity;
import com.example.jiranismart.R;

import java.util.Objects;

public class AgentDash extends AppCompatActivity {
    AgentMode agentMode;
    AgentSession agentSession;
    CardView Attached, hist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        agentSession = new AgentSession(getApplicationContext());
        agentMode = agentSession.getAgentDetails();
        Objects.requireNonNull(getSupportActionBar()).setTitle("Welcome " + agentMode.getFname());
        setContentView(R.layout.activity_agent_dash);
        Attached = findViewById(R.id.newAssessment);
        hist = findViewById(R.id.hisAssessment);
        Attached.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), Assessment.class));
        });
        hist.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), AsessHist.class));
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
                AlertDialog.Builder builder = new AlertDialog.Builder(AgentDash.this);
                builder.setTitle("Registration Information");
                builder.setMessage("EntryID :" + agentMode.getEntry() + "\nIDNo. " + agentMode.getId_no() + "\nName :" + agentMode.getFname() + " " + agentMode.getLname() + "\nEmail :" + agentMode.getEmail() + "\nPhone :" + agentMode.getPhone() + "\nCounty :" + agentMode.getCountry() + "\nEntryDate :" + agentMode.getReg_date());
                builder.setIcon(R.drawable.profile);
                builder.setPositiveButton("Logout", (dialogt, idd) -> {
                    agentSession.logoutAgent();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                });
                builder.setNeutralButton("exit", (dialogt, idd) -> {
                    agentSession.logoutAgent();
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