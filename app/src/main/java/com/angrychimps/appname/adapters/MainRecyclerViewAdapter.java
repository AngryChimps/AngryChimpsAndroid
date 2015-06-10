package com.angrychimps.appname.adapters;

import android.support.v4.app.Fragment;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.angrychimps.appname.App;
import com.angrychimps.appname.R;
import com.angrychimps.appname.VolleySingleton;
import com.angrychimps.appname.adapters.viewholders.DealItemGridViewHolder;
import com.angrychimps.appname.models.SearchPostResponseResults;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<DealItemGridViewHolder> {

    private Fragment fragment;
    private ImageLoader imageLoader;
    private SortedList<SearchPostResponseResults> searchResults;

    public MainRecyclerViewAdapter(Fragment fragment, SortedList<SearchPostResponseResults> searchResults) {
        this.fragment = fragment;
        this.searchResults = searchResults;
        imageLoader = VolleySingleton.INSTANCE.getImageLoader();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DealItemGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_deal, parent, false);
        return new DealItemGridViewHolder(v, fragment);
    }

    // Replace the contents of a view (invoked by the layout manager)
    //TODO: precache images
    @Override
    public void onBindViewHolder(DealItemGridViewHolder vh, int position) {
        vh.imageCompanyMain.setImageUrl(App.mediaUrl + searchResults.get(position).getPhoto(), imageLoader);
        vh.rbCompany.setRating(searchResults.get(position).getRating());
        vh.tvCompanyDistance.setText(searchResults.get(position).getDistance() + " miles");
        vh.tvCompanyTitle.setText(searchResults.get(position).getTitle());
        vh.tvCompanyServicePrice.setText("" + searchResults.get(position).getDiscounted_price());
        if (searchResults.get(position).getDiscounted_price_decimal() > 0) vh.tvCompanyServicePriceDecimal.setText("" + searchResults.get(position).getDiscounted_price_decimal());
        vh.tvCompanyServiceDiscount.setText(searchResults.get(position).getDiscount_percentage() + "% off");
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return searchResults.size();
    }
}