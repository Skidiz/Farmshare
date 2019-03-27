package com.example.empire.farmshare;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Merchant extends AppCompatActivity implements View.OnClickListener {

    LinearLayout agentLayout, merchantLayout, investorLayout,
            resourcesLayout, profileLayout;
    private Dialog profileDialog;

    private ImageView home_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant);

        agentLayout = (LinearLayout) findViewById(R.id.agent_btn);
        merchantLayout = (LinearLayout) findViewById(R.id.merchant_btn);
        investorLayout = (LinearLayout) findViewById(R.id.investor_btn);
        resourcesLayout = (LinearLayout) findViewById(R.id.resources_btn);
        profileLayout = (LinearLayout) findViewById(R.id.profile_btn);
        home_btn = (ImageView) findViewById(R.id.home_btn);

        agentLayout.setOnClickListener(this);
        merchantLayout.setOnClickListener(this);
        investorLayout.setOnClickListener(this);
        resourcesLayout.setOnClickListener(this);
        profileLayout.setOnClickListener(this);
        home_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.agent_btn:
//                toaster("Agent");
                navToNext(Agent.class);
                break;
            case R.id.merchant_btn:
//                toaster("Merchant");
//                navToNext(Merchant.class);
                break;
            case R.id.investor_btn:
//                toaster("Investor");
                navToNext(Investor.class);
                break;
            case R.id.resources_btn:
                toaster("Resources");
                break;
            case R.id.profile_btn:
                profile();
                break;
            case R.id.home_btn:
                navToNext(Home2.class);
                break;
            default:
                break;
        }
    }

    public void toaster(String message) {
        Toast.makeText(Merchant.this, message, Toast.LENGTH_SHORT).show();
    }

    public void navToNext(Class newIntent) {
        Intent intent = new Intent(Merchant.this, newIntent);
        startActivity(intent);
    }

    private void profile(){
        profileDialog = new Dialog(Merchant.this);
        profileDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        profileDialog.setContentView(R.layout.profile_layout);

//        messageDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        profileDialog.getWindow().setGravity(Gravity.CENTER);
        profileDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        profileDialog.setCanceledOnTouchOutside(true);
        profileDialog.show();

//        TextView farmNameTv = (TextView) farmDialog.findViewById(R.id.farmNameTv);
//        TextView investedAmountTv = (TextView) farmDialog.findViewById(R.id.investedAmountTv);
//        TextView expectedYieldTv = (TextView) farmDialog.findViewById(R.id.expectedYeildTv);
//        TextView dueDateTv = (TextView) farmDialog.findViewById(R.id.dueDateTv);
//        TextView statusTv = (TextView) farmDialog.findViewById(R.id.farmStatusTv);
        Button closeBtn = (Button) profileDialog.findViewById(R.id.closeBtn);

//        farmNameTv.setText(farmName);
//        investedAmountTv.setText(investedAmount);
//        expectedYieldTv.setText(expectedYield);
//        dueDateTv.setText(dueDate);
//        statusTv.setText(farmStatus);



        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileDialog.cancel();
            }
        });
    }

}
