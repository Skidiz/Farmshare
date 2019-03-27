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
import android.widget.TextView;
import android.widget.Toast;

public class Home2 extends AppCompatActivity implements View.OnClickListener {

    LinearLayout agentLayout, merchantLayout, investorLayout,
            resourcesLayout, profileLayout,cardContainer;
    private Dialog profileDialog;

    private TextView logoutTv, pageNumberTv, newsHeadingTV, newsBodyTv, newsHeadingTV2, newsBodyTv2;

    private ImageView newsIv;

    private String newsHead = "PorkFarming";
    private String newsBody = "The initiative, called \"PorkMoney\", considers the health benefits of pork and the financial benefits related to its sales as a lucrative source that will in the long term contribute immensely to the growth of agriculture in Nigeria, as well as, creating jobs in the process.\n" +
            "\n" +
            "The PorkMoney initiative will be launched on the June 4, 2018.\n" +
            "\n" +
            "Muyiwa, said it is an agribusiness subscription plan where partners can yield profits with a commitment sum of just N250, 000.\n" +
            "\n" +
            "According to him, PorkMoney, with its partners, led by Ghanaian actor and businessman John Dumelo, said to be Africa's largest pig farm have proposed a collaborative plan to grow the agricultural sector through pig farming in Nigeria.";

    private String newsHead2 = "IFC Boost Agribusiness in Nigeria";
    private String newsBody2 = "IFC, a member of the World Bank Group, has signed a cooperation agreement with the Government of Nigeria to implement a $2 million initiative targeting regulatory and institutional reforms that will strengthen Nigeria's agribusiness sector, creating jobs and economic growth.\n" +
            "\n" +
            "Nigeria is one of three African countries (along with Ethiopia and Tanzania) to benefit from the World Bank Group's Livestock and Micro Reforms in Agribusiness (L-MIRA) program, whose overarching objective is to improve competitiveness of the dairy and poultry sectors.\n" +
            "\n" +
            "The four-year initiative is funded by the Bill & Melinda Gates Foundation and was endorsed by key national partners, including Nigeria's Federal Ministry of Agriculture and Rural Development, the National Agency for Food and Drugs Administration and Control, the Standards Organization of Nigeria and the Nigerian Institute of Animal Science.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        agentLayout = (LinearLayout) findViewById(R.id.agent_btn);
        merchantLayout = (LinearLayout) findViewById(R.id.merchant_btn);
        investorLayout = (LinearLayout) findViewById(R.id.investor_btn);
        resourcesLayout = (LinearLayout) findViewById(R.id.resources_btn);
        profileLayout = (LinearLayout) findViewById(R.id.profile_btn);
        logoutTv = (TextView) findViewById(R.id.logoutTv);
        pageNumberTv = (TextView) findViewById(R.id.pageNumberTv);
        newsHeadingTV = (TextView) findViewById(R.id.newsHeadingTv);
        newsBodyTv = (TextView) findViewById(R.id.newsBodyTv);
        cardContainer = (LinearLayout) findViewById(R.id.cardContainer);
        newsIv = (ImageView) findViewById(R.id.newsIv);
//        newsHeadingTV2 = (TextView) findViewById(R.id.newsHeadingTv2);
//        newsBodyTv2 = (TextView) findViewById(R.id.newsBodyTv2);

        newsHeadingTV.setText(newsHead);
        newsBodyTv.setText(newsBody);
        pageNumberTv.setText("1/2");
//        newsHeadingTV2.setText(newsHead2);
//        newsBodyTv2.setText(newsBody2);


        agentLayout.setOnClickListener(this);
        merchantLayout.setOnClickListener(this);
        investorLayout.setOnClickListener(this);
        resourcesLayout.setOnClickListener(this);
        profileLayout.setOnClickListener(this);
        logoutTv.setOnClickListener(this);
        swip();

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
                navToNext(Merchant.class);
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
            case R.id.logoutTv:
                navToNext(Splash.class);
                break;
            default:
                break;
        }
    }

    private void swip(){
        cardContainer.setOnTouchListener(new OnSwipeTouchListener(Home2.this) {
            public void onSwipeTop() {

            }
            public void onSwipeRight() {
                pageNumberTv.setText("1/2");
                newsHeadingTV.setText(newsHead);
                newsBodyTv.setText(newsBody);
                newsIv.setImageResource(R.drawable.porks);
            }
            public void onSwipeLeft() {
                pageNumberTv.setText("2/2");
                newsHeadingTV.setText(newsHead2);
                newsBodyTv.setText(newsBody2);
                newsIv.setImageResource(R.drawable.agriculture);
            }
            public void onSwipeBottom() {

            }

        });
    }

    public void toaster(String message) {
        Toast.makeText(Home2.this, message, Toast.LENGTH_SHORT).show();
    }

    public void navToNext(Class newIntent) {
        Intent intent = new Intent(Home2.this, newIntent);
        startActivity(intent);
    }

    private void profile(){
        profileDialog = new Dialog(Home2.this);
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
