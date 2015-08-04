package com.angrychimps.appname.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.angrychimps.appname.R;
import com.angrychimps.appname.events.CallCompanyEvent;
import com.angrychimps.appname.events.FlagListingEvent;
import com.angrychimps.appname.events.ShowReviewsEvent;
import com.angrychimps.appname.events.StartNavigationEvent;
import com.angrychimps.appname.utils.Otto;
import com.angrychimps.appname.widgets.AnimatedNetworkImageView;
import com.angrychimps.appname.widgets.FlexibleRatingBar;

import butterknife.ButterKnife;
import butterknife.Bind;

public class MapCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    @Bind(R.id.map) public AnimatedNetworkImageView map;
    @Bind(R.id.bCallCompany) ImageButton bCallCompany;
    @Bind(R.id.tvCompanyName) public TextView tvCompanyName;
    @Bind(R.id.tvCompanyAddress) public TextView tvCompanyAddress;
    @Bind(R.id.tvCompanyDistance) public TextView tvCompanyDistance;
    @Bind(R.id.ratingBar) public FlexibleRatingBar ratingBar;
    @Bind(R.id.bReviews) public Button bReviews;
    @Bind(R.id.tvFlagListing) TextView tvFlagListing;

    public MapCardViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        map.setOnClickListener(this);
        bCallCompany.setOnClickListener(this);
        tvCompanyName.setOnClickListener(this);
        tvCompanyAddress.setOnClickListener(this);
        bReviews.setOnClickListener(this);
        tvFlagListing.setOnClickListener(this);
    }

    @Override public void onClick(View v) {
        switch(v.getId()){
            case R.id.bCallCompany:
                Otto.BUS.getBus().post(new CallCompanyEvent());
                break;
            case R.id.tvFlagListing:
                Otto.BUS.getBus().post(new FlagListingEvent());
                break;
            case R.id.bReviews:
                Otto.BUS.getBus().post(new ShowReviewsEvent());
                break;
            default:
                Otto.BUS.getBus().post(new StartNavigationEvent());
        }
    }
}
