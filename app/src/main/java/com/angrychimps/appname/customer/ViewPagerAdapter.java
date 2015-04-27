package com.angrychimps.appname.customer;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.angrychimps.appname.MainActivity;
import com.angrychimps.appname.VolleySingleton;
import com.angrychimps.appname.widgets.AnimatedNetworkImageView;

import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<String> images;
    private ImageLoader imageLoader = VolleySingleton.getInstance().getImageLoader();

    public ViewPagerAdapter(Context context, ArrayList<String> images) {
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
        imageView.setImageUrl(MainActivity.mediaUrl + images.get(position), imageLoader);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        container.addView(imageView, 0);
        return imageView;
    }
}
