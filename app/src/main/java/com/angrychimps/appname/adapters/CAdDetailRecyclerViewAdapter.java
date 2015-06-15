package com.angrychimps.appname.adapters;

import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.angrychimps.appname.App;
import com.angrychimps.appname.R;
import com.angrychimps.appname.VolleySingleton;
import com.angrychimps.appname.adapters.viewholders.AdDetailHeaderViewHolder;
import com.angrychimps.appname.adapters.viewholders.DealGridViewHolder;
import com.angrychimps.appname.adapters.viewholders.MapCardViewHolder;
import com.angrychimps.appname.adapters.viewholders.ServiceItemViewHolder;
import com.angrychimps.appname.models.Address;
import com.angrychimps.appname.models.CompanyDetails;
import com.angrychimps.appname.models.Deal;
import com.angrychimps.appname.models.Service;

public class CAdDetailRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final CompanyDetails companyDetails;
    private final SortedList<Service> services;
    private final SortedList<Deal> deals;
    private final ImageLoader imageLoader;
    private final Resources resources;

    public CAdDetailRecyclerViewAdapter(Resources resources, CompanyDetails companyDetails, SortedList<Service> services, SortedList<Deal> deals) {
        this.companyDetails = companyDetails;
        this.services = services;
        this.deals = deals;
        this.resources = resources;
        imageLoader = VolleySingleton.INSTANCE.getImageLoader();
    }

    @Override public int getItemViewType(int position) {
        if (position == 0) return 0;
        if (position < services.size() +1) return 1;
        if (position == services.size() +1) return 2;
        else return 3;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                return new AdDetailHeaderViewHolder(inflate(R.layout.header_c_ad_detail, parent));
            case 1:
                return new ServiceItemViewHolder(inflate(R.layout.card_service, parent));
            case 2:
                return new MapCardViewHolder(inflate(R.layout.card_map_with_footer, parent));
            default:
                return new DealGridViewHolder(inflate(R.layout.card_deal, parent));
        }
    }

    private View inflate(int layoutId, ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vh, int position) {
        StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) vh.itemView.getLayoutParams();
        switch(vh.getItemViewType()){
            case 0:
                layoutParams.setFullSpan(true);
                bind((AdDetailHeaderViewHolder) vh);
                break;
            case 1:
                layoutParams.setFullSpan(true);
                bind((ServiceItemViewHolder) vh, position - 1);
                break;
            case 2:
                layoutParams.setFullSpan(true);
                bind((MapCardViewHolder) vh);
                break;
            default:
                //Margins on grid cannot be set on the RecyclerView because it forces the header to have margins. Set them manually here
                //Edit: this doesn't actually work once the staggered grid moves items. Disable for now. Item Decorator?
//                boolean isLeft = (position - services.size() - 2) % 2 == 0;
//                layoutParams.setMargins(getDimen(isLeft? R.dimen.card_margin_medium : R.dimen.card_margin_small),getDimen(R.dimen.card_margin_small),
//                        getDimen(isLeft? R.dimen.card_margin_small : R.dimen.card_margin_medium),getDimen(R.dimen.card_margin_small));
                bind((DealGridViewHolder) vh, position - services.size() - 2);
        }
    }

    private int getDimen(int dimenId){
        return resources.getDimensionPixelSize(dimenId);
    }

    private void bind(final AdDetailHeaderViewHolder vh) {
        vh.tvCompanyTagLine.setText(companyDetails.getCompanyTagline());
        vh.tvCompanyDetails.setText(companyDetails.getCompanyDescription());

        //Make text fade out if too long. Click to make visible
        if (vh.tvCompanyDetails.length() > 280) {
            //Shader makes the text fade out toward the bottom
            final Shader textShader = new LinearGradient(0, vh.tvCompanyDetails.getLineHeight() * 4, 0, 0, new int[]{Color.TRANSPARENT, Color.BLACK},
                    new float[]{0, 1}, Shader.TileMode.CLAMP);
            vh.tvCompanyDetails.getPaint().setShader(textShader);
            vh.tvCompanyDetails.setMaxLines(4);
            vh.tvCompanyDetails.setOnClickListener(new View.OnClickListener() {
                boolean isExpanded = false;
                ObjectAnimator animator;
                @Override
                public void onClick(View v) {
                    animator = ObjectAnimator.ofInt(vh.tvCompanyDetails, "maxLines", isExpanded? 30 : 4).setDuration(100);
                    if (isExpanded) {
                        vh.tvCompanyDetails.getPaint().setShader(null);
                        animator.start();
                    } else {
                        animator.start();
                        vh.tvCompanyDetails.getPaint().setShader(textShader);
                    }
                    isExpanded = !isExpanded;
                }
            });
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
        Address address = companyDetails.getAddress();
        String street2 = "";
        String coordinates = address.getLat()+","+ address.getLon();

        vh.map.setImageUrl("https://maps.googleapis.com/maps/api/staticmap?center=" + coordinates + "&zoom=15&size=340x200" + "&markers=color:"
                + companyDetails.getMarkerColor() + "%7C" + coordinates + "&scale=2&format=jpeg", VolleySingleton.INSTANCE.getImageLoader());
        vh.tvCompanyName.setText(companyDetails.getCompanyName());
        if (address.getStreet2() != null) street2 = address.getStreet2() + "\n";
        vh.tvCompanyAddress.setText(address.getStreet1() + "\n" + street2 + address.getCity() + ", " + address.getState() + " " + address.getZip());
        vh.tvCompanyDistance.setText(companyDetails.getDistance());
        vh.ratingBar.setRating(companyDetails.getRating());
        vh.bReviews.setText(companyDetails.getRatingCount() + " Reviews");
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
        return services.size() + deals.size() + 2;
    }
}