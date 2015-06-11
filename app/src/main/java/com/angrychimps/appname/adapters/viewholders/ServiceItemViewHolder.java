package com.angrychimps.appname.adapters.viewholders;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.angrychimps.appname.R;
import com.angrychimps.appname.events.ServiceClickedEvent;
import com.angrychimps.appname.utils.Otto;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ServiceItemViewHolder extends RecyclerView.ViewHolder {

    @InjectView(R.id.tvAdDetailTitle) public TextView tvAdDetailTitle;
    @InjectView(R.id.tvAdDetailDescription) public TextView tvAdDetailDescription;
    @InjectView(R.id.tvAdDetailDiscount) public TextView tvAdDetailDiscount;
    @InjectView(R.id.tvAdDetailPrice) public TextView tvAdDetailPrice;
    @InjectView(R.id.tvAdDetailPriceDecimal) public TextView tvAdDetailPriceDecimal;

    public ServiceItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);

        tvAdDetailPriceDecimal.setPaintFlags(tvAdDetailPriceDecimal.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Otto.BUS.getBus().post(new ServiceClickedEvent(getAdapterPosition()));
            }
        });
    }
}