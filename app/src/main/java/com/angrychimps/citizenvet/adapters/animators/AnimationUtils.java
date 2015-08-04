package com.angrychimps.citizenvet.adapters.animators;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

public class AnimationUtils {

    public static void animateIn(RecyclerView.ViewHolder holder, boolean scrollDown) {

        Interpolator decelInterpolator = new DecelerateInterpolator();
        Animator translateY;

        if (scrollDown) {
            translateY = ObjectAnimator.ofFloat(holder.itemView, "translationY", 800, 0).setDuration(500);
            translateY.setInterpolator(decelInterpolator);

            Animator rotateY = ObjectAnimator.ofFloat(holder.itemView, "rotation", -8, 0).setDuration(400);
            rotateY.setInterpolator(decelInterpolator);

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(translateY, rotateY);
            animatorSet.start();
        } else {
            translateY = ObjectAnimator.ofFloat(holder.itemView, "translationY", -400, 0).setDuration(300);
            translateY.setInterpolator(decelInterpolator);
            translateY.start();
        }
    }
}
