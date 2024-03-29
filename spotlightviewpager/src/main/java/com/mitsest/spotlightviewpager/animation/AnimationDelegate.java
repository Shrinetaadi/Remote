package com.mitsest.spotlightviewpager.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
import android.view.animation.Interpolator;

import com.mitsest.spotlightviewpager.Commons;

public class AnimationDelegate {

    @NonNull private final ValueAnimator[] animators;

    public AnimationDelegate(@NonNull ValueAnimator[] animators) {
        this.animators = animators;
    }

    public void animate(@NonNull final View v, final int durationMs, @NonNull Interpolator interpolator) {
        animate(v, null, durationMs, interpolator);
    }

    /**
     *
     * @param v: parent view (the one that draws on canvas)
     * @param listener: an animation listener to attach on the final animator set
     * @param durationMs: animation duration in milliseconds
     * @param interpolator: animation interpolator
     */
    public void animate(@NonNull final View v, @Nullable final Animator.AnimatorListener listener, final int durationMs, @NonNull Interpolator interpolator) {
        final AnimatorSet animatorSet = new AnimatorSet();

        if (animators.length >= 1) {
            animators[animators.length - 1].addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    v.postInvalidate();
                }
            });

            animatorSet.playTogether(animators);
        }

        animatorSet.setInterpolator(interpolator);
        animatorSet.setDuration(durationMs);

        if (listener != null) {
            animatorSet.addListener(listener);
        }

        animatorSet.start();

    }

}
