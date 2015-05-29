package com.angrychimps.appname.adapters.viewholders;

import android.graphics.Paint;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.angrychimps.appname.R;
import com.angrychimps.appname.interfaces.OnItemClickedListener;

public class ServiceItemViewHolder extends RecyclerView.ViewHolder {

    private OnItemClickedListener listener;
    public TextView tvAdDetailTitle;
    public TextView tvAdDetailDescription;
    public TextView tvAdDetailDiscount;
    public TextView tvAdDetailPrice;
    public TextView tvAdDetailPriceDecimal;

    public ServiceItemViewHolder(View itemView, Fragment fragment) {
        super(itemView);

        listener = (OnItemClickedListener) fragment;

        tvAdDetailTitle = (TextView) itemView.findViewById(R.id.tvAdDetailTitle);
        tvAdDetailDescription = (TextView) itemView.findViewById(R.id.tvAdDetailDescription);
        tvAdDetailDiscount = (TextView) itemView.findViewById(R.id.tvAdDetailDiscount);
        tvAdDetailPrice = (TextView) itemView.findViewById(R.id.tvAdDetailPrice);
        tvAdDetailPriceDecimal = (TextView) itemView.findViewById(R.id.tvAdDetailPriceDecimal);
        tvAdDetailPriceDecimal.setPaintFlags(tvAdDetailPriceDecimal.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(getPosition());
            }
        });
    }
}