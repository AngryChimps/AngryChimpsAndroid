package com.angrychimps.appname.adapters.viewholders;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.angrychimps.appname.R;
import com.angrychimps.appname.events.ServiceClickedEvent;
import com.angrychimps.appname.utils.Otto;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ServiceItemViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.tvAdDetailTitle) public TextView tvAdDetailTitle;
    @Bind(R.id.tvAdDetailDescription) public TextView tvAdDetailDescription;
    @Bind(R.id.tvAdDetailDiscount) public TextView tvAdDetailDiscount;
    @Bind(R.id.tvAdDetailPrice) public TextView tvAdDetailPrice;
    @Bind(R.id.tvAdDetailPriceDecimal) public TextView tvAdDetailPriceDecimal;

    public ServiceItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        tvAdDetailPriceDecimal.setPaintFlags(tvAdDetailPriceDecimal.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Otto.BUS.getBus().post(new ServiceClickedEvent(getAdapterPosition()));
            }
        });
    }
}