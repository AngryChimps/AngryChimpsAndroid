package com.angrychimps.appname.adapters;

import android.view.View;
import android.widget.TextView;

import com.angrychimps.appname.R;
import com.angrychimps.appname.widgets.FlexibleRatingBar;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import butterknife.InjectView;

public class MapWindowAdapter implements GoogleMap.InfoWindowAdapter {

    @InjectView(R.id.tvCompanyDistance) public TextView tvCompanyDistance;
    @InjectView(R.id.tvCompanyTitle) public TextView tvCompanyTitle;
    @InjectView(R.id.tvCompanyServicePrice) public TextView tvCompanyServicePrice;
    @InjectView(R.id.tvCompanyServiceDiscount) public TextView tvCompanyServiceDiscount;
    @InjectView(R.id.tvCompanyServicePriceDecimal) public TextView tvCompanyServicePriceDecimal;
    @InjectView(R.id.ratingBar) public FlexibleRatingBar rbCompany;
    private final View view;

    MapWindowAdapter(View view) {
        this.view = view;
    }

    public View getInfoWindow(Marker marker) {
        v
        return v;
    }

    public View getInfoContents(Marker marker) {
        return null;
    }

}