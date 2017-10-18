package com.mytestdemo.my_animator;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
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
    private static final String TAG = "ValueAnimatorActivity";
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
            this(context, null);
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
            PointF startPointF = new PointF(0, 0);
            PointF endPointF = new PointF(300, 300);
            ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(), startPointF, endPointF);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    //此处得到的返回值类型，由PointEvaluator 决定，为 PointF
                    PointF pointF = (PointF) animation.getAnimatedValue();
                    Log.d(TAG, "onAnimationUpdate: " + pointF.x);
                    x = pointF.x;
                    //不断的刷新UI
                    invalidate();
                }
            });
            animator.setDuration(2000);
            animator.start();
        }
    }
    /**
     * 自定义 ，估值器
     */
    private class PointEvaluator implements TypeEvaluator<PointF> {
        @Override
        public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
            float resultX = startValue.x + fraction * (endValue.x - startValue.x);
            float rexultY = startValue.y + fraction * (endValue.y - startValue.y);

            Log.d(TAG, "evaluate: resultX= " + resultX + "\nrexultY=" + rexultY);
            return new PointF(resultX, rexultY);
        }
    }
}
