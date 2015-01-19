package com.angrychimps.appname;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.etsy.android.grid.util.DynamicHeightImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

public class AdFlowGridArrayAdapter extends ArrayAdapter<String> {

    private ArrayList<CompanyListing> mCompany;
    private Context mContext;
    private static final String TAG = "SampleAdapter";
    private final LayoutInflater mLayoutInflater;
    private final Random mRandom;
    private static final SparseArray<Double> sPositionHeightRatios = new SparseArray<>();

    public AdFlowGridArrayAdapter(Context context, int textViewResourceId, ArrayList<CompanyListing> company) {
        super(context, textViewResourceId);
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mRandom = new Random();
        this.mCompany = company;
    }

    // ViewHolder increases speed and efficiency by recycling views rather than doing many findViewByIds, which are expensive.
    static class ViewHolder {
        DynamicHeightImageView imageCompanyMain;
        TextView textViewCompanyDistance, textViewCompanyTitle, textViewCompanyName, textViewCompanyTimeAvailable;
        RatingBar ratingBarCompany;

    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder;

        // Reuse views
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.gridlayout_search_result, parent, false);

            // Configure ViewHolder
            viewHolder = new ViewHolder();
            viewHolder.imageCompanyMain = (DynamicHeightImageView) convertView.findViewById(R.id.imageCompanyMain);
            viewHolder.ratingBarCompany = (RatingBar) convertView.findViewById(R.id.ratingBarCompany);
            viewHolder.textViewCompanyDistance = (TextView) convertView.findViewById(R.id.tvCompanyDistance);
            viewHolder.textViewCompanyTitle = (TextView) convertView.findViewById(R.id.tvCompanyTitle);
            viewHolder.textViewCompanyName = (TextView) convertView.findViewById(R.id.tvCompanyName);
            viewHolder.textViewCompanyTimeAvailable = (TextView) convertView.findViewById(R.id.tvCompanyTimeAvailable);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Fill data
        double positionHeight = getPositionRatio(position);
        viewHolder.imageCompanyMain.setHeightRatio(positionHeight);
        Picasso.with(mContext).load(getItem(position)).into(viewHolder.imageCompanyMain);
//        viewHolder.ratingBarCompany.setRating(mCompany.get(position).getCompanyRating());
//        viewHolder.textViewCompanyNumberOfReviews.setText("(" + mCompany.get(position).getNumberOfReviews() + ")");
//        viewHolder.textViewCompanyDistance.setText(mCompany.get(position).getCompanyDistance() + units);
//        viewHolder.textViewCompanyTitle.setText(mCompany.get(position).getCompanyAdTitle());
//        viewHolder.textViewCompanyName.setText(mCompany.get(position).getCompanyName());
//        viewHolder.textViewCompanyTimeAvailable.setText(mCompany.get(position).getCompanyAdTimeSlotId());
        return convertView;
    }

    private double getPositionRatio(final int position) {
        double ratio = sPositionHeightRatios.get(position, 0.0);
// if not yet done generate and stash the columns height
// in our real world scenario this will be determined by
// some match based on the known height and width of the image
// and maybe a helpful way to get the column height!
        if (ratio == 0) {
            ratio = getRandomHeightRatio();
            sPositionHeightRatios.append(position, ratio);
            Log.d(TAG, "getPositionRatio:" + position + " ratio:" + ratio);
        }
        return ratio;
    }
    private double getRandomHeightRatio() {
        return (mRandom.nextDouble() / 1.5) + 0.5; // height will be 1.0 - 1.5 of the width
    }
}