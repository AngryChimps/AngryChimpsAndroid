package com.angrychimps.citizenvet.adapters;

public class MainRecyclerViewAdapter  {

//    private final ImageLoader imageLoader;
//    private final SortedList<Deal> deals;
    private int previousPosition = 0;
    private String imageSize;

//    public MainRecyclerViewAdapter(Context context, SortedList<Deal> deals) {
//        this.deals = deals;
//        imageLoader = VolleySingleton.VOLLEY.getImageLoader();
//
//        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        Display display = wm.getDefaultDisplay();
//        Point size = new Point();
//        display.getSize(size);
//        int imageWidth = size.x /240 * 113;
//        int imageHeight = imageWidth / 4 * 3;
//        imageSize = "?height="+imageHeight+"&width="+imageWidth;
//    }

//    // Create new views (invoked by the layout manager)
//    @Override
//    public DealGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_deal, parent, false);
//        return new DealGridViewHolder(v);
//    }
//
//    // Replace the contents of a view (invoked by the layout manager)
//    //TODO: get image height and width from the server and adjust images to appropriate sizes before loading. Set the imageView dimensions to match
//    @Override
//    public void onBindViewHolder(DealGridViewHolder vh, int position) {
//        vh.imageCompanyMain.setImageUrl(App.MEDIA_URL + deals.get(position).getPhoto()+imageSize, imageLoader);
//        vh.rbCompany.setRating(deals.get(position).getRating());
//        vh.tvCompanyDistance.setText(deals.get(position).getDistanceMiles());
//        vh.tvCompanyTitle.setText(deals.get(position).getTitle());
//        vh.tvCompanyServicePrice.setText("" + deals.get(position).getDiscounted_price());
//        if (deals.get(position).getDiscounted_price_decimal() > 0) vh.tvCompanyServicePriceDecimal.setText("" + deals.get(position).getDiscounted_price_decimal());
//        vh.tvCompanyServiceDiscount.setText(deals.get(position).getDiscount_percentage() + "% off");
//
//        AnimationUtils.animateIn(vh, position >= previousPosition);
//        previousPosition = position;
//    }
//
//    // Return the size of your dataset (invoked by the layout manager)
//    @Override
//    public int getItemCount() {
//        return deals.size();
//    }
}