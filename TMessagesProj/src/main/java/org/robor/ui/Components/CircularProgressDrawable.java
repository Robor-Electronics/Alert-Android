package org.robor.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import org.robor.messenger.AndroidUtilities;

public class CircularProgressDrawable extends Drawable {

    private float size = AndroidUtilities.dp(18);
    private float thickness = AndroidUtilities.dp(2.25f);

    public CircularProgressDrawable() {
        this(0xffffffff);
    }
    public CircularProgressDrawable(int color) {
        setColor(color);
    }
    public CircularProgressDrawable(float size, float thickness, int color) {
        this.size = size;
        this.thickness = thickness;
        setColor(color);
    }

    private long start = -1;
    private final FastOutSlowInInterpolator interpolator = new FastOutSlowInInterpolator();
    private float segmentFrom, segmentTo;
    private void updateSegment() {
        final long now = SystemClock.elapsedRealtime();
        final long t = (now - start) % 5400;
        segmentFrom = 1520 * t / 5400f - 20;
        segmentTo = 1520 * t / 5400f;
        float fraction;
        for (int i = 0; i < 4; ++i) {
            fraction = (t - i * 1350) / 667f;
            segmentTo += interpolator.getInterpolation(fraction) * 250;
            fraction = (t - (667 + i * 1350)) / 667f;
            segmentFrom += interpolator.getInterpolation(fraction) * 250;
        }
    }

    private final Paint paint = new Paint(); {
        paint.setStyle(Paint.Style.STROKE);
    }

    private final RectF bounds = new RectF();
    @Override
    public void draw(@NonNull Canvas canvas) {
        if (start < 0) {
            start = SystemClock.elapsedRealtime();
        }
        updateSegment();
        canvas.drawArc(
            bounds,
            segmentFrom,
            segmentTo - segmentFrom,
            false,
            paint
        );
        invalidateSelf();
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        int width = right - left, height = bottom - top;
        bounds.set(
            left + (width - thickness / 2f - size) / 2f,
            top + (height - thickness / 2f - size) / 2f,
            left + (width + thickness / 2f + size) / 2f,
            top + (height + thickness / 2f + size) / 2f
        );
        super.setBounds(left, top, right, bottom);
        paint.setStrokeWidth(thickness);
    }

    public void setColor(int color) {
        paint.setColor(color);
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {}

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSPARENT;
    }
}