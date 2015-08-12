package com.angrychimps.citizenvet.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.angrychimps.citizenvet.R;
import com.angrychimps.citizenvet.events.CallCompanyEvent;
import com.angrychimps.citizenvet.events.FlagListingEvent;
import com.angrychimps.citizenvet.events.ShowReviewsEvent;
import com.angrychimps.citizenvet.events.StartNavigationEvent;
import com.angrychimps.citizenvet.utils.Otto;
import com.angrychimps.citizenvet.widgets.FlexibleRatingBar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MapCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    //@Bind(R.id.map) public AnimatedNetworkImageView map;
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

        //map.setOnClickListener(this);
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
