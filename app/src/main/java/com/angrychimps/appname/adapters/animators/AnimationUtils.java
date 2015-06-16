package com.angrychimps.appname.adapters.animators;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

public class AnimationUtils {

    public static void animateIn(RecyclerView.ViewHolder holder, boolean scrollDown){

        Interpolator decelInterpolator = new DecelerateInterpolator();

        Animator translateY=ObjectAnimator.ofFloat(holder.itemView, "translationY", scrollDown? 900:-400,0).setDuration(scrollDown ? 600 : 200);
        translateY.setInterpolator(decelInterpolator);

        Animator rotateY = ObjectAnimator.ofFloat(holder.itemView,"rotation",scrollDown? -10 : 0 ,0).setDuration(450);
        rotateY.setInterpolator(decelInterpolator);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translateY, rotateY);
        animatorSet.start();
    }
}
