package com.angrychimps.citizenvet.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.angrychimps.citizenvet.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AdDetailHeaderViewHolder extends RecyclerView.ViewHolder{
    @Bind(R.id.tvCompanyTagLine) public TextView tvCompanyTagLine;
    @Bind(R.id.tvCompanyDetails) public TextView tvCompanyDetails;

    public AdDetailHeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
