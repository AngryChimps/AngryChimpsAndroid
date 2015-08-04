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
import butterknife.Bind;

public class DealGridViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.imageCompanyMain) public AnimatedFixedNetworkImageView imageCompanyMain;
    @Bind(R.id.tvCompanyDistance) public TextView tvCompanyDistance;
    @Bind(R.id.tvCompanyTitle) public TextView tvCompanyTitle;
    @Bind(R.id.tvCompanyServicePrice) public TextView tvCompanyServicePrice;
    @Bind(R.id.tvCompanyServiceDiscount) public TextView tvCompanyServiceDiscount;
    @Bind(R.id.tvCompanyServicePriceDecimal) public TextView tvCompanyServicePriceDecimal;
    @Bind(R.id.ratingBar) public FlexibleRatingBar rbCompany;

    public DealGridViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        tvCompanyServicePriceDecimal.setPaintFlags(tvCompanyServicePriceDecimal.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Otto.BUS.getBus().post(new DealClickedEvent(getAdapterPosition()));
            }
        });
    }
}