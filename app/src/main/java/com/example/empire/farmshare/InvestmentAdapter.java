package com.example.empire.farmshare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class InvestmentAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> farmName;
    ArrayList<String> investedAmount;
    ArrayList<String> expectedYeild;
    ArrayList<String> dueDate;
    ArrayList<String> status;

    public InvestmentAdapter(Context context2, ArrayList<String> farmName2, ArrayList<String> investedAmount2,
                        ArrayList<String> expectedYeild2, ArrayList<String> dueDate2, ArrayList<String> status2) {
        this.context = context2;
        this.farmName = farmName2;
        this.investedAmount = investedAmount2;
        this.expectedYeild = expectedYeild2;
        this.dueDate = dueDate2;
        this.status = status2;
    }


    @Override
    public int getCount() {
        return farmName.size();

    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public View getView(int position, View child, ViewGroup parent) {
        Holder holder;
        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = layoutInflater.inflate(R.layout.investment_list_layout, null);

            holder = new Holder();

            holder.textFarmName = (TextView) child.findViewById(R.id.farmNameTv);
            holder.textInvestAmount = (TextView) child.findViewById(R.id.investedAmountTv);
            holder.textExpectedYeil = (TextView) child.findViewById(R.id.expectedYeildTv);
            holder.textDueDate = (TextView) child.findViewById(R.id.dueDateTv);
            holder.textStatus = (TextView) child.findViewById(R.id.farmStatusTv);
            child.setTag(holder);
        } else {
            holder = (Holder) child.getTag();
        }

        holder.textFarmName.setText(farmName.get(position));
        holder.textInvestAmount.setText(investedAmount.get(position));
        holder.textExpectedYeil.setText(expectedYeild.get(position));
        holder.textDueDate.setText(dueDate.get(position));
        holder.textStatus.setText(status.get(position));

        return child;
    }

    private class Holder {
        TextView textFarmName;
        TextView textInvestAmount;
        TextView textExpectedYeil;
        TextView textDueDate;
        TextView textStatus;
    }
}
