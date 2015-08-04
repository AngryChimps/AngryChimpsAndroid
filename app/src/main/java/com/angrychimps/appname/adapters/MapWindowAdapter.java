package com.angrychimps.appname.adapters;

import android.support.v7.util.SortedList;
import android.view.View;
import android.widget.TextView;

import com.angrychimps.appname.R;
import com.angrychimps.appname.models.Deal;
import com.angrychimps.appname.widgets.FlexibleRatingBar;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import butterknife.Bind;
import butterknife.ButterKnife;

/*
    Displays the marker popup info windows in the map
 */
public class MapWindowAdapter implements GoogleMap.InfoWindowAdapter {

    @Bind(R.id.tvCompanyDistance) TextView tvCompanyDistance;
    @Bind(R.id.tvCompanyTitle) TextView tvCompanyTitle;
    @Bind(R.id.tvCompanyServicePrice) TextView tvCompanyServicePrice;
    @Bind(R.id.tvCompanyServiceDiscount) TextView tvCompanyServiceDiscount;
    @Bind(R.id.tvCompanyServicePriceDecimal) TextView tvCompanyServicePriceDecimal;
    @Bind(R.id.ratingBar) FlexibleRatingBar rbCompany;
    private final SortedList<Deal> deals;
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

        ButterKnife.bind(this, view);
        rbCompany.setRating(deals.get(position).getRating());
        tvCompanyDistance.setText(deals.get(position).getDistanceMiles());
        tvCompanyTitle.setText(deals.get(position).getTitle());
        tvCompanyServicePrice.setText("" + deals.get(position).getDiscounted_price());
        if (deals.get(position).getDiscounted_price_decimal() > 0) tvCompanyServicePriceDecimal.setText("" + deals.get(position).getDiscounted_price_decimal());
        tvCompanyServiceDiscount.setText(deals.get(position).getDiscount_percentage() + "% off");
        return view;
    }
}