package com.example.empire.farmshare;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Investor_portfolio extends AppCompatActivity implements View.OnClickListener {

    ListView LISTVIEW;
    InvestmentAdapter ADAPTER;
    private Dialog farmDialog;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investor_portfolio);

        LISTVIEW = (ListView) findViewById(R.id.InvestmentList);
        backBtn = (ImageView) findViewById(R.id.backBtn);
        getInvestments();

        backBtn.setOnClickListener(this);
    }


    private void getInvestments() {

        final ArrayList<String> FARM_NAME_ArrayList = new ArrayList<String>();
        final ArrayList<String> INVESTED_AMOUNT_ArrayList = new ArrayList<String>();
        final ArrayList<String> EXPECTED_YEILD_ArrayList = new ArrayList<String>();
        final ArrayList<String> DUE_DATE_ArrayList = new ArrayList<String>();
        final ArrayList<String> STATUS_ArrayList = new ArrayList<String>();

        try {
            FARM_NAME_ArrayList.clear();
            INVESTED_AMOUNT_ArrayList.clear();
            EXPECTED_YEILD_ArrayList.clear();
            DUE_DATE_ArrayList.clear();
            STATUS_ArrayList.clear();

            FARM_NAME_ArrayList.add("Nasarawa Rice Farm 2");
            INVESTED_AMOUNT_ArrayList.add("350,000");
            EXPECTED_YEILD_ArrayList.add("400,000");
            DUE_DATE_ArrayList.add("31/12/18");
            STATUS_ArrayList.add("Growing");

            FARM_NAME_ArrayList.add("Owena Cassava Farm");
            INVESTED_AMOUNT_ArrayList.add("150,000");
            EXPECTED_YEILD_ArrayList.add("300,000");
            DUE_DATE_ArrayList.add("30/03/19");
            STATUS_ArrayList.add("Growing");

            FARM_NAME_ArrayList.add("Ota Poultry Farm");
            INVESTED_AMOUNT_ArrayList.add("400,000");
            EXPECTED_YEILD_ArrayList.add("600,000");
            DUE_DATE_ArrayList.add("20/01/19");
            STATUS_ArrayList.add("Growing");

            ADAPTER = new InvestmentAdapter(Investor_portfolio.this, FARM_NAME_ArrayList,
                    INVESTED_AMOUNT_ArrayList, EXPECTED_YEILD_ArrayList, DUE_DATE_ArrayList, STATUS_ArrayList);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    LISTVIEW.setAdapter(ADAPTER);

                    LISTVIEW.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {

                            String farm_name = FARM_NAME_ArrayList.get(position).toString();
                            String invested_amount = INVESTED_AMOUNT_ArrayList.get(position).toString();
                            String expected_yeild = EXPECTED_YEILD_ArrayList.get(position).toString();
                            String due_date = DUE_DATE_ArrayList.get(position).toString();
                            String status = STATUS_ArrayList.get(position).toString();

                            showMore(farm_name, invested_amount, expected_yeild, due_date, status);


                        }

                    });
                }


            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void showMore(String farmName, String investedAmount, String expectedYield, String dueDate, String farmStatus) {
        farmDialog = new Dialog(Investor_portfolio.this);
        farmDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        farmDialog.setContentView(R.layout.invest_farm_info_layout);

//        messageDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        farmDialog.getWindow().setGravity(Gravity.CENTER);
        farmDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        farmDialog.setCanceledOnTouchOutside(true);
        farmDialog.show();

        TextView farmNameTv = (TextView) farmDialog.findViewById(R.id.farmNameTv);
        TextView investedAmountTv = (TextView) farmDialog.findViewById(R.id.investedAmountTv);
        TextView expectedYieldTv = (TextView) farmDialog.findViewById(R.id.expectedYeildTv);
        TextView dueDateTv = (TextView) farmDialog.findViewById(R.id.dueDateTv);
        TextView statusTv = (TextView) farmDialog.findViewById(R.id.farmStatusTv);
        Button closeBtn = (Button) farmDialog.findViewById(R.id.closeBtn);

        farmNameTv.setText(farmName);
        investedAmountTv.setText(investedAmount);
        expectedYieldTv.setText(expectedYield);
        dueDateTv.setText(dueDate);
        statusTv.setText(farmStatus);


        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                farmDialog.cancel();
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backBtn:
                onBackPressed();
                break;
            default:
                break;
        }
    }
}
