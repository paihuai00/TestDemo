package com.mytestdemo.my_animator;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.mytestdemo.BaseActivity;
import com.mytestdemo.R;

/**
 * Created by cuishuxiang on 2017/10/17.
 *
 * 属性动画  估值器 的使用
 */

public class ValueAnimatorActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animator);

        ViewGroup parent = (ViewGroup) findViewById(R.id.parent);
        parent.addView(new SingleLine(this));
    }

    private class SingleLine extends View {

        private Paint mPaint;
        private float x = 0;
        private float y = 150;

        public SingleLine(Context context) {
                super(context);
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                mPaint.setColor(Color.RED);
            }

            public SingleLine(Context context, AttributeSet attrs) {
                super(context, attrs);
                mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                mPaint.setColor(Color.RED);
                mPaint.setStrokeWidth(10);
            }

            public SingleLine(Context context, AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
            }

            @Override
            protected void onDraw(Canvas canvas) {
                if (0 == x) {
                    startAnimation();
                }
                canvas.drawLine(0, y, x, x + y, mPaint);
            }


            private void startAnimation() {
                ValueAnimator animator = ValueAnimator.ofObject(new SingleLineEvaluator(), 0, 500);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float i = (float) animation.getAnimatedValue();
                        x = i;
                        //不断的刷新UI
                        invalidate();
                    }
                });
                animator.setDuration(2000);
                animator.start();
            }
        }

        private class SingleLineEvaluator implements TypeEvaluator {

            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                return fraction * (((Number) endValue).floatValue() - ((Number) startValue).floatValue());
            }
        }

}
