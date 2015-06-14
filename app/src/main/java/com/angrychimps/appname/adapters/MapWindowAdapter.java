package com.angrychimps.appname.adapters;

import android.support.v7.util.SortedList;
import android.view.View;
import android.widget.TextView;

import com.angrychimps.appname.R;
import com.angrychimps.appname.models.Deal;
import com.angrychimps.appname.widgets.FlexibleRatingBar;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import butterknife.ButterKnife;
import butterknife.InjectView;

/*
    Displays the marker popup info windows in the map
 */
public class MapWindowAdapter implements GoogleMap.InfoWindowAdapter {

    @InjectView(R.id.tvCompanyDistance) public TextView tvCompanyDistance;
    @InjectView(R.id.tvCompanyTitle) public TextView tvCompanyTitle;
    @InjectView(R.id.tvCompanyServicePrice) public TextView tvCompanyServicePrice;
    @InjectView(R.id.tvCompanyServiceDiscount) public TextView tvCompanyServiceDiscount;
    @InjectView(R.id.tvCompanyServicePriceDecimal) public TextView tvCompanyServicePriceDecimal;
    @InjectView(R.id.ratingBar) public FlexibleRatingBar rbCompany;
    private SortedList<Deal> deals;
    private final View view;

    public MapWindowAdapter(View view, SortedList<Deal> deals) {
        this.deals = deals;
        this.view = view;
    }

    public View getInfoWindow(Marker marker) {
        return null;
    }

    public View getInfoContents(Marker marker) {
        if(marker.getTitle() == null) return null; //The home location marker will not have a window
        int position = Integer.parseInt(marker.getTitle()); //We put the position of the marker as Title because there wasn't a better place for it

        ButterKnife.inject(this, view);
        rbCompany.setRating(deals.get(position).getRating());
        tvCompanyDistance.setText(deals.get(position).getDistanceMiles());
        tvCompanyTitle.setText(deals.get(position).getTitle());
        tvCompanyServicePrice.setText("" + deals.get(position).getDiscounted_price());
        if (deals.get(position).getDiscounted_price_decimal() > 0) tvCompanyServicePriceDecimal.setText("" + deals.get(position).getDiscounted_price_decimal());
        tvCompanyServiceDiscount.setText(deals.get(position).getDiscount_percentage() + "% off");
        return view;
    }
}