package com.angrychimps.appname.adapters;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.angrychimps.appname.App;
import com.angrychimps.appname.R;
import com.angrychimps.appname.VolleySingleton;
import com.angrychimps.appname.adapters.viewholders.DealGridViewHolder;
import com.angrychimps.appname.adapters.viewholders.MapCardViewHolder;
import com.angrychimps.appname.adapters.viewholders.ServiceItemViewHolder;
import com.angrychimps.appname.models.Address;
import com.angrychimps.appname.models.Deal;
import com.angrychimps.appname.models.MapCard;
import com.angrychimps.appname.models.Service;

public class CAdDetailRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private SortedList<Service> services;
    private MapCard mapCard;
    private SortedList<Deal> deals;
    private ImageLoader imageLoader;

    public CAdDetailRecyclerViewAdapter(SortedList<Service> services, MapCard mapCard, SortedList<Deal> deals) {
        this.services = services;
        this.mapCard = mapCard;
        this.deals = deals;
        imageLoader = VolleySingleton.INSTANCE.getImageLoader();
    }

    @Override public int getItemViewType(int position) {
        if (position < services.size()) return 0;
        if (position == services.size()) return 1;
        else return 2;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                return new ServiceItemViewHolder(inflate(R.layout.card_service, parent));
            case 1:
                return new MapCardViewHolder(inflate(R.layout.card_map_with_footer, parent));
            default:
                return new DealGridViewHolder(inflate(R.layout.card_deal, parent));
        }
    }

    private View inflate(int layoutId, ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
    }

    // Replace the contents of a view (invoked by the layout manager)
    //TODO: precache images
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vh, int position) {
        switch(vh.getItemViewType()){
            case 0:
                bind((ServiceItemViewHolder) vh, position);
                break;
            case 1:
                bind((MapCardViewHolder) vh);
                break;
            default:
                bind((DealGridViewHolder) vh, position);
        }
    }

    private void bind(ServiceItemViewHolder vh, int position) {
        vh.tvAdDetailDescription.setText(services.get(position).getDescription());
        vh.tvAdDetailTitle.setText(services.get(position).getName());
        vh.tvAdDetailPrice.setText("" + (int) services.get(position).getDiscounted_price());
        if (services.get(position).getDiscounted_price_decimal() > 0) vh.tvAdDetailPriceDecimal.setText(""
                + services.get(position).getDiscounted_price_decimal());
        vh.tvAdDetailDiscount.setText("     " + services.get(position).getDiscount() + "%\ndiscount");
    }

    private void bind(MapCardViewHolder vh) {
        Address address = mapCard.getAddress();
        String street2 = "";
        String coordinates = address.getLat()+","+ address.getLon();

        vh.map.setImageUrl("https://maps.googleapis.com/maps/api/staticmap?center=" + coordinates + "&zoom=15&size=340x200" + "&markers=color:"
                + mapCard.getMarkerColor() + "%7C" + coordinates + "&scale=2&format=jpeg", VolleySingleton.INSTANCE.getImageLoader());
        vh.tvCompanyName.setText(mapCard.getCompanyName());
        if (address.getStreet2() != null) street2 = address.getStreet2() + "\n";
        vh.tvCompanyAddress.setText(address.getStreet1() + "\n" + street2 + address.getCity() + ", " + address.getState() + " " + address.getZip());
        vh.tvCompanyDistance.setText(mapCard.getDistance());
        vh.ratingBar.setRating(mapCard.getRating());
        vh.bReviews.setText(mapCard.getRatingCount() + " Reviews");
    }

    private void bind(DealGridViewHolder vh, int position) {
        vh.imageCompanyMain.setImageUrl(App.mediaUrl + deals.get(position).getPhoto(), imageLoader);
        vh.rbCompany.setRating(deals.get(position).getRating());
        vh.tvCompanyDistance.setText(deals.get(position).getDistanceMiles());
        vh.tvCompanyTitle.setText(deals.get(position).getTitle());
        vh.tvCompanyServicePrice.setText("" + deals.get(position).getDiscounted_price());
        if (deals.get(position).getDiscounted_price_decimal() > 0)
            vh.tvCompanyServicePriceDecimal.setText("" + deals.get(position).getDiscounted_price_decimal());
        vh.tvCompanyServiceDiscount.setText(deals.get(position).getDiscount_percentage() + "% off");
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return services.size() + deals.size() + 1;
    }

}