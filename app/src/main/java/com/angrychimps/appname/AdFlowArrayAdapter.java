package com.angrychimps.appname;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdFlowArrayAdapter extends ArrayAdapter<AdFlowCompanyListing> {

    private final Activity mContext;
    private final ArrayList<AdFlowCompanyListing> mSearchResultItems;

    // ViewHolder increases speed and efficiency by recycling views rather than doing many findViewByIds, which are expensive.
    static class ViewHolder {
        public ImageView mImageCompanyMain;
        public TextView mTextViewCompanyTitle, mTextViewCompanyAddress, mTextViewCompanyTimeAvailable;
    }

    public AdFlowArrayAdapter(Activity context, ArrayList<AdFlowCompanyListing> searchResultItems) {
        super(context, R.layout.rowlayout_search_result, searchResultItems);
        this.mContext = context;
        this.mSearchResultItems = searchResultItems;
    }

    @Override
    public AdFlowCompanyListing getItem(int position) {
        return mSearchResultItems.get(position);
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

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder;

        // Reuse views
        if (convertView == null) {
            LayoutInflater inflater = mContext.getLayoutInflater();
            convertView = inflater.inflate(R.layout.rowlayout_search_result, null);

            // Configure ViewHolder
            mViewHolder = new ViewHolder();
            mViewHolder.mImageCompanyMain = (ImageView) convertView.findViewById(R.id.imageCompanyMain);
            mViewHolder.mTextViewCompanyTitle = (TextView) convertView.findViewById(R.id.tvCompanyTitle);
            mViewHolder.mTextViewCompanyAddress = (TextView) convertView.findViewById(R.id.tvCompanyAddress);
            mViewHolder.mTextViewCompanyTimeAvailable = (TextView) convertView.findViewById(R.id.tvCompanyTimeAvailable);
            convertView.setTag(mViewHolder);
        }
        // Fill data
        mViewHolder = (ViewHolder) convertView.getTag();
        mViewHolder.mImageCompanyMain.setImageBitmap(mSearchResultItems.get(position).getImageCompanyMain());
        mViewHolder.mTextViewCompanyTitle.setText(mSearchResultItems.get(position).getCompanyTitle());
        mViewHolder.mTextViewCompanyAddress.setText(mSearchResultItems.get(position).getCompanyAddress());
        mViewHolder.mTextViewCompanyTimeAvailable.setText(mSearchResultItems.get(position).getCompanyTimeAvailable());
        return convertView;
        }
}