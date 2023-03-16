package com.example.jiranismart;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.jiranismart.Fermented.AgentMode;
import com.example.jiranismart.Fermented.AgentSession;
import com.example.jiranismart.Fermented.AuctionSession;
import com.example.jiranismart.Fermented.ClientMode;
import com.example.jiranismart.Fermented.ClientSession;
import com.example.jiranismart.Fermented.FinaSessions;
import com.example.jiranismart.Fermented.InventSession;
import com.example.jiranismart.Fermented.StaffMode;
import com.example.jiranismart.agent.AgemntLog;
import com.example.jiranismart.agent.AgentDash;
import com.example.jiranismart.auction.AuctDash;
import com.example.jiranismart.auction.AuctLog;
import com.example.jiranismart.client.ClentLog;
import com.example.jiranismart.client.ClieDash;
import com.example.jiranismart.finance.FinaDash;
import com.example.jiranismart.finance.FinaLog;
import com.example.jiranismart.inventory.InventDash;
import com.example.jiranismart.inventory.InventLog;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    CardView customer, agent, finance, auction, inventory;
    ClientMode clientMode;
    ClientSession clientSession;
    AgentMode agentMode;
    AgentSession agentSession;
    StaffMode staffMode;
    FinaSessions finaSessions;
    InventSession inventSession;
    AuctionSession auctionSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Dashboard");
        setContentView(R.layout.activity_main);
        clientSession = new ClientSession(getApplicationContext());
        clientMode = clientSession.getClientDetails();
        agentSession = new AgentSession(getApplicationContext());
        agentMode = agentSession.getAgentDetails();
        finaSessions = new FinaSessions(getApplicationContext());
        staffMode = finaSessions.getFinaDetails();
        inventSession = new InventSession(getApplicationContext());
        staffMode = inventSession.getInventDetails();
        auctionSession = new AuctionSession(getApplicationContext());
        staffMode = auctionSession.getAuctionDetails();
        customer = findViewById(R.id.Customer);
        agent = findViewById(R.id.Agent);
        auction = findViewById(R.id.Auction);
        inventory = findViewById(R.id.Inventory);
        finance = findViewById(R.id.Finance);
        customer.setOnClickListener(v -> {
            /*if (clientSession.isClientIn()) {
                startActivity(new Intent(getApplicationContext(), ClieDash.class));
            } else {*/
                startActivity(new Intent(getApplicationContext(), ClentLog.class));
            //}
        });
        agent.setOnClickListener(v -> {
            /*if (agentSession.isAgent()) {
                startActivity(new Intent(getApplicationContext(), AgentDash.class));
            } else {*/
                startActivity(new Intent(getApplicationContext(), AgemntLog.class));
            //}
        });
        auction.setOnClickListener(v -> {
            /*if (auctionSession.isAcution()) {
                startActivity(new Intent(getApplicationContext(), AuctDash.class));
            } else {*/
                startActivity(new Intent(getApplicationContext(), AuctLog.class));
           //}
        });
        finance.setOnClickListener(v -> {
            /*if (finaSessions.isFina()) {
                startActivity(new Intent(getApplicationContext(), FinaDash.class));
            } else {*/
                startActivity(new Intent(getApplicationContext(), FinaLog.class));

            //}
        });
        inventory.setOnClickListener(v -> {
            /*if (inventSession.isInvent()) {
                startActivity(new Intent(getApplicationContext(), InventDash.class));
            } else {*/
                startActivity(new Intent(getApplicationContext(), InventLog.class));
            //}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.dashAbout:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("About us");
                builder.setMessage(getString(R.string.about));
                builder.setNegativeButton("close", (dialo, idm) -> dialo.cancel());
                AlertDialog alertDialog = builder.create();
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.show();
                break;
            case R.id.dashhelp:
                AlertDialog.Builder builderr = new AlertDialog.Builder(MainActivity.this);
                builderr.setTitle("Help");
                builderr.setMessage(getString(R.string.help));
                builderr.setNegativeButton("close", (dialo, idm) -> dialo.cancel());
                AlertDialog alertDialogr = builderr.create();
                alertDialogr.setCanceledOnTouchOutside(false);
                alertDialogr.show();
                break;
            case R.id.dashExit:
                finishAffinity();
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}