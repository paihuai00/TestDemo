package com.mytestdemo.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by Xuebin He on 2017/3/16.
 * e-mail:learnice.he@gmail.com.
 */

public class QuadView extends View {
    private int mItemWaveLength = 1200;
    int dx, dy;
    Paint paint;
    Path path;

    public QuadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(5);
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        path.reset();
        int originY = 300;
        int halfWaveLen = 1200 / 2;
        path.moveTo(-1200 + dx, originY + dy);
        for (int i = -1200; i <= getWidth() + 1200; i += 1200) {
            path.rQuadTo(600 / 2, -100, 600, 0);
            path.rQuadTo(600 / 2, 100, 600, 0);
        }
        path.lineTo(getWidth(), getHeight());
        path.lineTo(0, getHeight());
        path.close();
        canvas.drawPath(path, paint);
    }

    public void startAnim() {
        ValueAnimator animator = ValueAnimator.ofInt(0, mItemWaveLength);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();

        ValueAnimator animator1 = ValueAnimator.ofInt(0, mItemWaveLength);
        animator1.setDuration(5000);
        animator1.setRepeatMode(ValueAnimator.REVERSE);
        animator1.setRepeatCount(ValueAnimator.INFINITE);
        animator1.setInterpolator(new LinearInterpolator());
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                dy = (int) valueAnimator.getAnimatedValue() / 2;
                postInvalidate();
            }
        });
        animator1.start();
    }

}
