package com.angrychimps.appname.adapters.viewholders;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.angrychimps.appname.R;
import com.angrychimps.appname.events.DealClickedEvent;
import com.angrychimps.appname.utils.Otto;
import com.angrychimps.appname.widgets.AnimatedFixedNetworkImageView;
import com.angrychimps.appname.widgets.FlexibleRatingBar;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DealGridViewHolder extends RecyclerView.ViewHolder {

    @InjectView(R.id.imageCompanyMain) public AnimatedFixedNetworkImageView imageCompanyMain;
    @InjectView(R.id.tvCompanyDistance) public TextView tvCompanyDistance;
    @InjectView(R.id.tvCompanyTitle) public TextView tvCompanyTitle;
    @InjectView(R.id.tvCompanyServicePrice) public TextView tvCompanyServicePrice;
    @InjectView(R.id.tvCompanyServiceDiscount) public TextView tvCompanyServiceDiscount;
    @InjectView(R.id.tvCompanyServicePriceDecimal) public TextView tvCompanyServicePriceDecimal;
    @InjectView(R.id.ratingBar) public FlexibleRatingBar rbCompany;

    public DealGridViewHolder(View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);

        tvCompanyServicePriceDecimal.setPaintFlags(tvCompanyServicePriceDecimal.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Otto.BUS.getBus().post(new DealClickedEvent(getAdapterPosition()));
            }
        });
    }
}