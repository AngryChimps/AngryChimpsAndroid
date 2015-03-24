package com.angrychimps.appname;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.angrychimps.appname.models.SearchPostResponseResults;

import java.util.ArrayList;

public class CompanyAdFlowGridArrayAdapter extends ArrayAdapter<SearchPostResponseResults> {

    private final LayoutInflater mLayoutInflater;
    private ArrayList<SearchPostResponseResults> arrayList;
    private ImageLoader imageLoader = VolleySingleton.getInstance().getImageLoader();

    public CompanyAdFlowGridArrayAdapter(Context context, ArrayList<SearchPostResponseResults> arrayList) {
        super(context, android.R.layout.simple_list_item_1, arrayList);
        this.mLayoutInflater = LayoutInflater.from(context);
        this.arrayList = arrayList;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final ViewHolder viewHolder;

        //TODO
        //if(close to end && !loading data){
        //load more data;
        //}

        // Reuse views
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.gridlayout_search_result, parent, false);

            // Configure ViewHolder
            viewHolder = new ViewHolder();
            viewHolder.imageCompanyMain = (NetworkImageView) convertView.findViewById(R.id.imageCompanyMain);
            viewHolder.ratingBarCompany = (RatingBar) convertView.findViewById(R.id.ratingBarCompany);
            viewHolder.textViewCompanyDistance = (TextView) convertView.findViewById(R.id.tvCompanyDistance);
            viewHolder.textViewCompanyTitle = (TextView) convertView.findViewById(R.id.tvCompanyTitle);
            viewHolder.textViewCompanyServicePrice = (TextView) convertView.findViewById(R.id.tvCompanyServicePrice);
            viewHolder.textViewCompanyServiceDiscount = (TextView) convertView.findViewById(R.id.tvCompanyServiceDiscount);
            viewHolder.textViewCompanyServicePriceDecimal = (TextView) convertView.findViewById(R.id.tvCompanyServicePriceDecimal);
            viewHolder.textViewCompanyServicePriceDecimal.setPaintFlags(viewHolder.textViewCompanyServicePriceDecimal.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imageCompanyMain.setImageUrl("http://devvy3.angrychimps.com/media/" + arrayList.get(position).getPhoto(), imageLoader);
        viewHolder.ratingBarCompany.setRating(arrayList.get(position).getRating());
        viewHolder.textViewCompanyDistance.setText(arrayList.get(position).getDistance() + "mi");
        viewHolder.textViewCompanyTitle.setText(arrayList.get(position).getTitle());
        viewHolder.textViewCompanyServicePrice.setText("" + arrayList.get(position).getDiscounted_price());
        if (arrayList.get(position).getDiscounted_price_decimal() > 0) viewHolder.textViewCompanyServicePriceDecimal.setText("" + arrayList.get(position).getDiscounted_price_decimal());
        viewHolder.textViewCompanyServiceDiscount.setText(arrayList.get(position).getDiscount_percentage() + "% off");

        return convertView;
    }

    // ViewHolder increases speed and efficiency by recycling views rather than doing many findViewByIds, which are expensive.
    static class ViewHolder {
        NetworkImageView imageCompanyMain;
        TextView textViewCompanyDistance, textViewCompanyTitle, textViewCompanyServicePrice, textViewCompanyServiceDiscount, textViewCompanyServicePriceDecimal;
        RatingBar ratingBarCompany;
    }

}