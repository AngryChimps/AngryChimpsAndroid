package com.angrychimps.appname.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.angrychimps.appname.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class AdDetailHeaderViewHolder extends RecyclerView.ViewHolder{
    @InjectView(R.id.tvCompanyTagLine) public TextView tvCompanyTagLine;
    @InjectView(R.id.tvCompanyDetails) public TextView tvCompanyDetails;

    public AdDetailHeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
    }
}
