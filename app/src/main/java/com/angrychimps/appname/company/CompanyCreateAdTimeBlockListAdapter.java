package com.angrychimps.appname.company;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.angrychimps.appname.R;

public class CompanyCreateAdTimeBlockListAdapter extends ArrayAdapter<String>{

    private final LayoutInflater mLayoutInflater;


    public CompanyCreateAdTimeBlockListAdapter(Context context) {
        super(context, android.R.layout.simple_list_item_1);
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder;

        // Reuse views
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.company_create_ad_list_item, parent, false);

            // Configure ViewHolder
            viewHolder = new ViewHolder();
            viewHolder.tvCompanyCreateAdAvailableTimeBlocks = (TextView) convertView.findViewById(R.id.tvCompanyCreateAdAvailableTimeBlocks);
            viewHolder.bCompanyCreateAdStartDate = (Button) convertView.findViewById(R.id.bCompanyCreateAdStartDate);
            viewHolder.bCompanyCreateAdStartTime = (Button) convertView.findViewById(R.id.bCompanyCreateAdStartTime);
            viewHolder.bCompanyCreateAdEndDate = (Button) convertView.findViewById(R.id.bCompanyCreateAdEndDate);
            viewHolder.bCompanyCreateAdEndTime = (Button) convertView.findViewById(R.id.bCompanyCreateAdEndTime);
            convertView.setTag(viewHolder);
        }

        //TODO - save inputted data

        return convertView;
    }

    // ViewHolder increases speed and efficiency by recycling views rather than doing many findViewByIds, which are expensive.
    static class ViewHolder {
        TextView tvCompanyCreateAdAvailableTimeBlocks;
        Button bCompanyCreateAdStartDate, bCompanyCreateAdStartTime, bCompanyCreateAdEndDate, bCompanyCreateAdEndTime;
    }


}
