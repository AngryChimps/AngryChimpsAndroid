package com.angrychimps.appname.adapters;

import android.graphics.Paint;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.angrychimps.appname.MainActivity;
import com.angrychimps.appname.R;
import com.angrychimps.appname.VolleySingleton;
import com.angrychimps.appname.interfaces.OnItemClickedListener;
import com.angrychimps.appname.models.SearchPostResponseResults;
import com.angrychimps.appname.widgets.FlexibleRatingBar;

import java.util.ArrayList;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {

    private static OnItemClickedListener listener;
    private ImageLoader imageLoader;
    private ArrayList<SearchPostResponseResults> arrayList;

    public MainRecyclerViewAdapter(Fragment fragment, ArrayList<SearchPostResponseResults> arrayList) {
        listener = (OnItemClickedListener) fragment;
        this.arrayList = arrayList;
        imageLoader = VolleySingleton.getInstance().getImageLoader();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MainRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_grid_search_result, parent, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    //TODO: precache images
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.imageCompanyMain.setImageUrl(MainActivity.mediaUrl + arrayList.get(position).getPhoto(), imageLoader);
        viewHolder.rbCompany.setRating(arrayList.get(position).getRating());
        viewHolder.tvCompanyDistance.setText(arrayList.get(position).getDistance() + " miles");
        viewHolder.tvCompanyTitle.setText(arrayList.get(position).getTitle());
        viewHolder.tvCompanyServicePrice.setText("" + arrayList.get(position).getDiscounted_price());
        if (arrayList.get(position).getDiscounted_price_decimal() > 0) viewHolder.tvCompanyServicePriceDecimal.setText("" + arrayList.get(position).getDiscounted_price_decimal());
        viewHolder.tvCompanyServiceDiscount.setText(arrayList.get(position).getDiscount_percentage() + "% off");
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    // Provide a reference to the views for each data item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        NetworkImageView imageCompanyMain;
        TextView tvCompanyDistance;
        TextView tvCompanyTitle;
        TextView tvCompanyServicePrice;
        TextView tvCompanyServiceDiscount;
        TextView tvCompanyServicePriceDecimal;
        FlexibleRatingBar rbCompany;

        public ViewHolder(View itemView) {
            super(itemView);

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
}