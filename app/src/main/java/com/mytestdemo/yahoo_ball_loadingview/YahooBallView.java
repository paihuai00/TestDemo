package com.mytestdemo.yahoo_ball_loadingview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

/**
 * Created by cuishuxiang on 2017/8/15.
 *
 * 仿雅虎，加载动画
 */

public class YahooBallView extends View {
    private static final String TAG = "YahooBallView";

    private int width, height;//宽高。
    private Paint paint;

    private float distance;//屏幕中心点到小球之间的距离
    private float radius;//小球的半径

    //设置小球颜色的集合(多少种颜色，多少个小球)
    private int[] colors = new int[]{Color.RED, Color.BLACK, Color.BLUE, Color.GREEN, Color.YELLOW, Color.CYAN};
    private float currentAngle;// 当前旋转的角度
    private ValueAnimator valueRotateAnimator;//旋转动画
    private ValueAnimator valueScaleAnimator;//收缩动画
    private AnimatorSet animatorSet;

    private Paint bgPaint;//背景画笔
    private int bgRadius = 0;

    public YahooBallView(Context context) {
        this(context, null);
    }

    public YahooBallView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public YahooBallView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        distance = 200;
        radius = 30;

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setDither(true);
        paint.setAntiAlias(true);

        bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bgPaint.setColor(Color.RED);
        bgPaint.setDither(true);
        bgPaint.setAntiAlias(true);
        bgPaint.setStrokeWidth(20);

        initAnimator();

        //使用set开启动画
        animatorSet = new AnimatorSet();
        animatorSet.playTogether(valueRotateAnimator, valueScaleAnimator);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                Toast.makeText(getContext(), "动画结束", Toast.LENGTH_LONG).show();
            }
        });
        animatorSet.start();

    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (distance > 0) {
            /**
             * 绘制六个 小球
             */
            float preAngle = (float) (2 * Math.PI / colors.length);//有多少个球，就平分当前的弧度
            for (int i = 0; i < colors.length; i++) {
                paint.setColor(colors[i]);

                //初始角度 + 旋转角度
                double angle = i * preAngle + currentAngle;
                float circle_x = (float) (width / 2 + distance * Math.cos(angle));
                float circle_y = (float) (height / 2 + distance * Math.sin(angle));

                canvas.drawCircle(circle_x,circle_y,radius,paint);
            }
        }else {
            //距离小于0，就不再绘制 停止动画
            Log.d(TAG, "onDraw: distance=" + distance);
            animatorSet.cancel();

//            setBackground(ContextCompat.getDrawable(getContext(), R.drawable.icon_bg_img));
//            Path path = new Path();
//            path.addCircle(width / 2, height / 2, 100, Path.Direction.CCW);
//
//            canvas.clipPath(path);

//            Rect rect = new Rect(0, height, width, 0);

//            canvas.drawRect(rect,bgPaint);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        Log.d(TAG, "onMeasure: width=" + width + "  height=" + height);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    //背景 动画
    private void initBgAnimator() {
        ValueAnimator bgAnimator = ValueAnimator.ofInt(0, height / 2);
        bgAnimator.setRepeatCount(0);
        bgAnimator.setDuration(1300);
        bgAnimator.setInterpolator(new AccelerateInterpolator());
        bgAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                bgRadius = (int) animation.getAnimatedValue();

                invalidate();
            }
        });
        bgAnimator.start();
    }

    //初始化旋转动画 + 收缩动画
    private void initAnimator() {
        valueRotateAnimator = ValueAnimator.ofFloat(0, (float) Math.PI * 2);//旋转角度 0--2π
        valueRotateAnimator.setDuration(1200);
        valueRotateAnimator.setInterpolator(new LinearInterpolator());
        valueRotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueRotateAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                //旋转动画结束
            }
        });
        valueRotateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentAngle = (float) animation.getAnimatedValue();
                Log.d(TAG, "onAnimationUpdate: currentAngle=" + currentAngle);

                invalidate();

            }
        });

        valueScaleAnimator = ValueAnimator.ofInt((int) distance, 0);
        valueScaleAnimator.setInterpolator(new AnticipateOvershootInterpolator(5f));
        valueScaleAnimator.setDuration(3500);
        valueScaleAnimator.setRepeatCount(0);//参数repeatCount为重复执行的次数。如果设置为n，则动画将执行n+1次。
        valueScaleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int scaleDistance = (int) animation.getAnimatedValue();
                distance = scaleDistance;
                Log.d(TAG, "onAnimationUpdate: distance =" + distance + "  scaleDistance=" + scaleDistance);
                invalidate();

                if (0 == distance) {
                    initBgAnimator();
                }
            }
        });

    }

    /**
     * 停止 动画
     */
    public void stopAnimator() {
        if (animatorSet.isRunning()) {
            animatorSet.cancel();

            animatorSet = null;
            valueRotateAnimator = null;
            valueScaleAnimator = null;
        }
    }


    private int px2dip(Context context, float pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5f);
    }
}
