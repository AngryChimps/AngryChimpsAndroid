package com.angrychimps.appname.customer;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.angrychimps.appname.AnimatedNetworkImageView;
import com.angrychimps.appname.MainActivity;
import com.angrychimps.appname.R;
import com.angrychimps.appname.VolleySingleton;
import com.angrychimps.appname.models.SearchPostResponseResults;

import java.util.ArrayList;

/*
    This adapter handles the StaggeredGridView in Provider Mode
 */

public class StaggeredGridViewAdapter extends ArrayAdapter<SearchPostResponseResults> {

    private final LayoutInflater layoutInflater;
    private ArrayList<SearchPostResponseResults> arrayList;
    private ImageLoader imageLoader = VolleySingleton.getInstance().getImageLoader();

    public StaggeredGridViewAdapter(Context context, ArrayList<SearchPostResponseResults> arrayList) {
        super(context, android.R.layout.simple_list_item_1, arrayList);
        this.layoutInflater = LayoutInflater.from(context);
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
            convertView = layoutInflater.inflate(R.layout.gridlayout_search_result, parent, false);

            // Configure ViewHolder
            viewHolder = new ViewHolder();
            viewHolder.imageCompanyMain = (AnimatedNetworkImageView) convertView.findViewById(R.id.imageCompanyMain);
            viewHolder.rbCompany = (RatingBar) convertView.findViewById(R.id.ratingBarCompany);
            viewHolder.tvCompanyDistance = (TextView) convertView.findViewById(R.id.tvCompanyDistance);
            viewHolder.tvCompanyTitle = (TextView) convertView.findViewById(R.id.tvCompanyTitle);
            viewHolder.tvCompanyServicePrice = (TextView) convertView.findViewById(R.id.tvCompanyServicePrice);
            viewHolder.tvCompanyServiceDiscount = (TextView) convertView.findViewById(R.id.tvCompanyServiceDiscount);
            viewHolder.tvCompanyServicePriceDecimal = (TextView) convertView.findViewById(R.id.tvCompanyServicePriceDecimal);
            viewHolder.tvCompanyServicePriceDecimal.setPaintFlags(viewHolder.tvCompanyServicePriceDecimal.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imageCompanyMain.setImageUrl(MainActivity.mediaUrl + arrayList.get(position).getPhoto(), imageLoader);
        viewHolder.rbCompany.setRating(arrayList.get(position).getRating());
        viewHolder.tvCompanyDistance.setText(arrayList.get(position).getDistance() + "mi");
        viewHolder.tvCompanyTitle.setText(arrayList.get(position).getTitle());
        viewHolder.tvCompanyServicePrice.setText("" + arrayList.get(position).getDiscounted_price());
        if (arrayList.get(position).getDiscounted_price_decimal() > 0) viewHolder.tvCompanyServicePriceDecimal.setText("" + arrayList.get(position).getDiscounted_price_decimal());
        viewHolder.tvCompanyServiceDiscount.setText(arrayList.get(position).getDiscount_percentage() + "% off");
        return convertView;
    }

    // ViewHolder increases speed and efficiency by recycling views rather than doing many findViewByIds, which are expensive.
    static class ViewHolder {
        AnimatedNetworkImageView imageCompanyMain;
        TextView tvCompanyDistance, tvCompanyTitle, tvCompanyServicePrice, tvCompanyServiceDiscount, tvCompanyServicePriceDecimal;
        RatingBar rbCompany;
    }
}