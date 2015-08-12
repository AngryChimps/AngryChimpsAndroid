package com.angrychimps.citizenvet.widgets;

/*
    NetworkImageView providing fade-in of images and a fixed 4/3 aspect ratio
 */

public class AnimatedFixedNetworkImageView  {

    private static final int ANIM_DURATION = 200;
    private boolean shouldAnimate = false;

//    public AnimatedFixedNetworkImageView(Context context) {
//        super(context);
//    }
//
//    public AnimatedFixedNetworkImageView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public AnimatedFixedNetworkImageView(Context context, AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int width = MeasureSpec.getSize(widthMeasureSpec);
//        int height = width /4 *3;
//        setMeasuredDimension(width, height);
//    }
//
//    @Override
//    public void setImageBitmap(Bitmap bm) {
//        super.setImageBitmap(bm);
//        if(shouldAnimate) ObjectAnimator.ofFloat(this, "alpha", 0, 1).setDuration(ANIM_DURATION).start();
//    }
//
//    @Override
//    public void setImageUrl(String url, ImageLoader imageLoader) {
//        shouldAnimate = !imageLoader.isCached(url, 0, 0);
//        super.setImageUrl(url, imageLoader);
//    }
}