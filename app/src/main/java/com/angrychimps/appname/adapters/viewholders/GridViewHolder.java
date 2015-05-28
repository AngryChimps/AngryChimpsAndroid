package com.angrychimps.appname.adapters.viewholders;

import android.graphics.Paint;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.angrychimps.appname.R;
import com.angrychimps.appname.interfaces.OnItemClickedListener;
import com.angrychimps.appname.widgets.FlexibleRatingBar;

public class GridViewHolder extends RecyclerView.ViewHolder {

    private OnItemClickedListener listener;
    public NetworkImageView imageCompanyMain;
    public TextView tvCompanyDistance;
    public TextView tvCompanyTitle;
    public TextView tvCompanyServicePrice;
    public TextView tvCompanyServiceDiscount;
    public TextView tvCompanyServicePriceDecimal;
    public FlexibleRatingBar rbCompany;

    public GridViewHolder(View itemView, Fragment fragment) {
        super(itemView);

        listener = (OnItemClickedListener) fragment;

        imageCompanyMain = (NetworkImageView) itemView.findViewById(R.id.imageCompanyMain);
        rbCompany = (FlexibleRatingBar) itemView.findViewById(R.id.ratingBar);
        tvCompanyDistance = (TextView) itemView.findViewById(R.id.tvCompanyDistance);
        tvCompanyTitle = (TextView) itemView.findViewById(R.id.tvCompanyTitle);
        tvCompanyServicePrice = (TextView) itemView.findViewById(R.id.tvCompanyServicePrice);
        tvCompanyServiceDiscount = (TextView) itemView.findViewById(R.id.tvCompanyServiceDiscount);
        tvCompanyServicePriceDecimal = (TextView) itemView.findViewById(R.id.tvCompanyServicePriceDecimal);
        tvCompanyServicePriceDecimal.setPaintFlags(tvCompanyServicePriceDecimal.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(getPosition());
            }
        });
    }
}