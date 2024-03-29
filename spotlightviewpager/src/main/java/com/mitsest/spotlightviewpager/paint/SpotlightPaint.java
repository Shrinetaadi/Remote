package com.mitsest.spotlightviewpager.paint;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.core.content.ContextCompat;


import com.mitsest.spotlightviewpager.Commons;
import com.mitsest.spotlightviewpager.R;

public class SpotlightPaint {
    @Px
    private final int borderSize;
    @ColorInt
    private final int borderColor;
    @NonNull
    private final Paint paint; // used to draw spotlight

    @Px
    private int radius;
    @Px
    private int borderGradientRadius;
    @Nullable
    private Paint borderGradientPaint; // used to draw spotlight border
    @Nullable
    private Paint borderPaint; // used to draw spotlight border

    public SpotlightPaint(@NonNull Context context) {
        paint = new Paint();
        borderPaint = new Paint();
        borderGradientPaint = new Paint();


        borderColor = ContextCompat.getColor(context, R.color.spotlight_border_color);
        borderSize = Commons.getDimenInPixels(context, R.dimen.spotlight_border_size);
        setRadius(context);

        initSpotlightPaint();
        initBorderPaint();
        initBorderGradientPaint();

    }

    public void setRadius(@Nullable Context context) {
        if (context == null) {
            return;
        }

        radius = Commons.getDimenInPixels(context, R.dimen.spotlight_border_radius);
        borderGradientRadius = Commons.getDimenInPixels(context, R.dimen.spotlight_border_gradient_radius);
    }

    private void initBorderGradientPaint() {
        borderGradientPaint = new Paint();

        borderGradientPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        borderGradientPaint.setAntiAlias(true);
        borderGradientPaint.setDither(true);
        borderGradientPaint.setStrokeWidth((float) (borderSize));
        if (borderGradientRadius > 0) {
            borderGradientPaint.setMaskFilter(new BlurMaskFilter(borderGradientRadius, BlurMaskFilter.Blur.OUTER));
        }

        borderGradientPaint.setColor(borderColor);

    }

    public void setBorderGradientPaint(@Nullable Paint paint) {
        borderGradientPaint = paint;
    }

    private void initSpotlightPaint() {
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        paint.setAntiAlias(true);
        paint.setDither(true);
    }

    private void initBorderPaint() {
        borderPaint = new Paint();

        borderPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        borderPaint.setAntiAlias(true);
        borderPaint.setDither(true);
        borderPaint.setStrokeWidth(borderSize);
        borderPaint.setColor(borderColor);
    }

    public void setBorderPaint(@Nullable Paint paint) {
        borderPaint = paint;
    }

    public void drawSpotlight(Canvas canvas, RectF animatingRectangle) {
        if (animatingRectangle != null) {
            canvas.drawRoundRect(animatingRectangle, radius, radius, paint);
        }
    }

    public void drawSpotlightBorder(Canvas canvas, RectF animatingRectangle) {
        if (animatingRectangle != null) {

            if (borderPaint != null) {
                canvas.drawRoundRect(animatingRectangle, radius, radius, borderPaint);
            }

            if (borderGradientPaint != null) {
                canvas.drawRoundRect(animatingRectangle, radius, radius, borderGradientPaint);
            }
        }
    }

    @Nullable
    public Paint getBorderPaint() {
        return borderPaint;
    }

    @Nullable
    public Paint getBorderGradientPaint() {
        return borderGradientPaint;
    }


    public int getRadius() {
        return radius;
    }

    public void setRadius(@Px int radius) {
        this.radius = radius;
    }
}
