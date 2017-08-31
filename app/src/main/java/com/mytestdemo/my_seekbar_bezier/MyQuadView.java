package com.mytestdemo.my_seekbar_bezier;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by cuishuxiang on 2017/8/2.
 * 利用二阶贝塞尔曲线，画波浪线
 * 思路是：利用sin图形，将与Y轴对称的两半都画出来。
 */

public class MyQuadView extends View{
    private static final String TAG = "MyQuadView";
    private Paint mPaint;
    private Path mPath;

    private int width;
    private int height;

    private int mOffset;//动画时，偏移量

    /**
     * 构造方法,View初始化的时候调用,
     * 在这里是无法获取其子控件的引用的.更加无法获取宽高了
     * @param context
     * @param attrs
     */
    public MyQuadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//抗锯齿
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(10);

        mPath = new Path();

//        startViewAnimator();//不能再此处开启动画，以为初始化的时候，width=0

        Log.d(TAG, "MyQuadView: width = " + getWidth() + "\nheight = " + getHeight()+mPaint.getStyle());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //测量当前view的长宽
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);

        startViewAnimator();

        Log.d(TAG, "onMeasure: width = " + getWidth() + "\nheight = " + getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPath.reset();//首先重置path
        Log.d(TAG, "onDraw: ");

        mPath.moveTo(-width + mOffset, height / 2);
        //sin图形，Y轴左侧部分
//        mPath.quadTo(-width * 3 / 4, height / 2 - 200, -width / 2 + mOffset, height / 2);
//        mPath.quadTo(-width / 2, height / 2 + 200, 0 + mOffset, height / 2);
//        //sin图形，Y轴右侧部分
//        mPath.quadTo(width / 4 + mOffset, height / 2 - 200, width / 2 + mOffset, height / 2);
//        mPath.quadTo(width * 3 / 4 + mOffset, height / 2 + 200, width + mOffset, height / 2);

        //简化写法
        for (int i = 0; i < 2; i++) {
            mPath.quadTo(-width * 3 / 4 + (width * i) + mOffset, height / 2 - 100, -width / 2 + (width * i) + mOffset, height / 2);
            mPath.quadTo(-width / 4 + (width * i) + mOffset, height / 2 + 100, +(width * i) + mOffset, height / 2);
        }

        //将下面的空白 填满
        mPath.lineTo(width, height);
        mPath.lineTo(0, height);

        canvas.drawPath(mPath,mPaint);

    }

    /**
     * 设置动画效果
     */
    private void startViewAnimator() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, width);
        valueAnimator.setDuration(1200);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);//设置无限循环
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                mOffset = (int) animation.getAnimatedValue();//获取当前平移的值
                Log.d(TAG, "onAnimationUpdate: mOffset= " + mOffset + "\nwidth= " + width);
                invalidate();//重绘
            }
        });
        valueAnimator.start();
    }
}
