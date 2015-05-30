package com.angrychimps.appname.adapters;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.angrychimps.appname.R;
import com.angrychimps.appname.VolleySingleton;
import com.angrychimps.appname.adapters.viewholders.GridViewHolder;
import com.angrychimps.appname.models.SearchPostResponseResults;
import com.angrychimps.appname.models.Service;

import java.util.List;

public class CustomerAdDetailRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Fragment fragment;
    private ImageLoader imageLoader;
    private List<Service> listService;
    private List<SearchPostResponseResults> listGrid;

    public CustomerAdDetailRecyclerViewAdapter(Fragment fragment, List<Service> listService, List<SearchPostResponseResults> listGrid) {
        this.fragment = fragment;
        this.listService = listService;
        this.listGrid = listGrid;
        imageLoader = VolleySingleton.getInstance().getImageLoader();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_grid_search_result, parent, false);
        return new GridViewHolder(v, fragment);
    }

    // Replace the contents of a view (invoked by the layout manager)
    //TODO: precache images
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
//        GridViewHolder holder = (GridViewHolder) viewHolder;
//        holder.imageCompanyMain.setImageUrl(MainActivity.mediaUrl + listGrid.get(position).getPhoto(), imageLoader);
//        viewHolder.rbCompany.setRating(listGrid.get(position).getRating());
//        viewHolder.tvCompanyDistance.setText(listGrid.get(position).getDistance() + " miles");
//        viewHolder.tvCompanyTitle.setText(listGrid.get(position).getTitle());
//        viewHolder.tvCompanyServicePrice.setText("" + listGrid.get(position).getDiscounted_price());
//        if (listGrid.get(position).getDiscounted_price_decimal() > 0) viewHolder.tvCompanyServicePriceDecimal.setText("" + listGrid.get(position).getDiscounted_price_decimal());
//        viewHolder.tvCompanyServiceDiscount.setText(listGrid.get(position).getDiscount_percentage() + "% off");
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listGrid.size();
    }

}