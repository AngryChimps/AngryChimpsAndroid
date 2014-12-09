package com.angrychimps.appname.consumer.search;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.angrychimps.appname.R;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchResultArrayAdapter extends ArrayAdapter<SearchResultItem> {

    // This is the Adapter that handles the data for the MissionPlanner listview.

    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();
    private final Activity context;
    private final ArrayList<SearchResultItem> searchResultItems;

    // ViewHolder increases speed and efficiency by recycling views rather than doing many findViewByIds, which are expensive.
    static class ViewHolder {
        public ImageView imageCompanyMain;
        public TextView tvCompanyTitle;
        public TextView tvCompanyAddress;
        public TextView tvCompanyTimeAvailable;
    }

    public SearchResultArrayAdapter(Activity context, ArrayList<SearchResultItem> searchResultItems) {
        super(context, R.layout.rowlayout_search_result, searchResultItems);
        this.context = context;
        this.searchResultItems = searchResultItems;
    }

    @Override
    public SearchResultItem getItem(int position) {
        return searchResultItems.get(position);
    }

    //check if necessary
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    //check if necessary
    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        // Reuse views
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(R.layout.rowlayout_search_result, null);

            // Configure ViewHolder
            viewHolder = new ViewHolder();
            viewHolder.imageCompanyMain = (ImageView) convertView.findViewById(R.id.imageCompanyMain);
            viewHolder.tvCompanyTitle = (TextView) convertView.findViewById(R.id.tvCompanyTitle);
            viewHolder.tvCompanyAddress = (TextView) convertView.findViewById(R.id.tvCompanyAddress);
            viewHolder.tvCompanyTimeAvailable = (TextView) convertView.findViewById(R.id.tvCompanyTimeAvailable);
            convertView.setTag(viewHolder);
        }
        // Fill data
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.imageCompanyMain.setImageBitmap(searchResultItems.get(position).getImageCompanyMain());
        viewHolder.tvCompanyTitle.setText(searchResultItems.get(position).getCompanyTitle());
        viewHolder.tvCompanyAddress.setText(searchResultItems.get(position).getCompanyAddress());
        viewHolder.tvCompanyTimeAvailable.setText(searchResultItems.get(position).getCompanyTimeAvailable());
        return convertView;
        }
}