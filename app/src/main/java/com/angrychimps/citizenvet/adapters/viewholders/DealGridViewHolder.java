package com.angrychimps.citizenvet.adapters.viewholders;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.angrychimps.citizenvet.R;
import com.angrychimps.citizenvet.events.DealClickedEvent;
import com.angrychimps.citizenvet.utils.Otto;
import com.angrychimps.citizenvet.widgets.AnimatedFixedNetworkImageView;
import com.angrychimps.citizenvet.widgets.FlexibleRatingBar;

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