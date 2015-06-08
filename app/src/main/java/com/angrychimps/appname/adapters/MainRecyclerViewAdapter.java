package com.angrychimps.appname.adapters;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.angrychimps.appname.MainActivity;
import com.angrychimps.appname.R;
import com.angrychimps.appname.VolleySingleton;
import com.angrychimps.appname.adapters.viewholders.DealItemGridViewHolder;
import com.angrychimps.appname.models.SearchPostResponseResults;

import java.util.List;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<DealItemGridViewHolder> {

    private Fragment fragment;
    private ImageLoader imageLoader;
    private List<SearchPostResponseResults> arrayList;

    public MainRecyclerViewAdapter(Fragment fragment, List<SearchPostResponseResults> arrayList) {
        this.fragment = fragment;
        this.arrayList = arrayList;
        imageLoader = VolleySingleton.getInstance().getImageLoader();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DealItemGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_grid_search_result, parent, false);
        return new DealItemGridViewHolder(v, fragment);
    }

    // Replace the contents of a view (invoked by the layout manager)
    //TODO: precache images
    @Override
    public void onBindViewHolder(DealItemGridViewHolder viewHolder, int position) {
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
}