package com.angrychimps.citizenvet.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.angrychimps.citizenvet.App;
import com.angrychimps.citizenvet.VolleySingleton;
import com.angrychimps.citizenvet.widgets.AnimatedNetworkImageView;

import java.util.List;

public class ViewPagerPhotoAdapter extends PagerAdapter {

    private final Context context;
    private final List<String> images;
    private final ImageLoader imageLoader = VolleySingleton.VOLLEY.getImageLoader();

    public ViewPagerPhotoAdapter(Context context, List<String> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        AnimatedNetworkImageView imageView = new AnimatedNetworkImageView(context);
        imageView.setImageUrl(App.MEDIA_URL + images.get(position), imageLoader);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        container.addView(imageView, 0);
        return imageView;
    }
}
