package com.mytestdemo.view_check;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

/**
 * Created by cuishuxiang on 2017/10/24.
 *
 * 绘制一个，自定义勾选状态  实现思路如下
 * 1，首先绘制未选中的状态
 * 2，绘制外圈的loading状态，
 * 3，绘制缩进圆动画
 * 4，绘制  白色 对号
 *
 * http://www.jianshu.com/p/1b2cdba03d23
 */

public class CheckView extends View {
    private static final String TAG = CheckView.class.getSimpleName();

    /**
     * 未选中  对号  外框的画笔
     * 未选中画笔颜色(浅灰色)
     */
    private Paint unPaint;
    private int unCheckColor = Color.parseColor("#F3F3F3");

    /**
     * 选中状态
     * 画笔
     */
    private Paint checkedPaint;
    private Paint whitePaint;
    private int checkedColor = Color.parseColor("#FE3F81");//红色
    private float currentOutAngle = 0;//外圈当前的角度

    /**
     * 背景色，实心圆的画笔
     */
    private Paint backgroundPaint;
    private float shrinkWidth = 0;//收缩的宽度

    private ValueAnimator shinkAnimator;//收缩动画
    private ValueAnimator outLineAnimator;//外圈动画

    /**
     * 默认画笔宽度
     */
    private int defaultPaintWidth = 10;

    /**
     * view的宽高，和圆的中心点，半径
     */
    private int mWidth;
    private int mHeight;
    private PointF circleCenterPoint = new PointF();
    private float mRadius = 100;

    /**
     * 计算 √  三个点的坐标
     */
    private PointF startTickPoint,centerTickPoint, endTickPoint;
    /**
     * 持续时间
     */
    private int mDuration = 1500;

    /**
     * 是否选中
     */
    private boolean isChecked = false;


    public CheckView(Context context) {
        this(context, null);
    }

    public CheckView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        unPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//消除锯齿
        unPaint.setStrokeWidth(defaultPaintWidth);
        unPaint.setColor(unCheckColor);
        unPaint.setStyle(Paint.Style.STROKE);
        unPaint.setStrokeJoin(Paint.Join.ROUND);
        unPaint.setStrokeCap(Paint.Cap.ROUND);

        whitePaint = new Paint(Paint.ANTI_ALIAS_FLAG);//消除锯齿
        whitePaint.setStrokeWidth(defaultPaintWidth);
        whitePaint.setColor(Color.WHITE);
        whitePaint.setStyle(Paint.Style.FILL);
        whitePaint.setStrokeJoin(Paint.Join.ROUND);
        whitePaint.setStrokeCap(Paint.Cap.ROUND);

        checkedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//消除锯齿
        checkedPaint.setStrokeWidth(defaultPaintWidth);
        checkedPaint.setColor(checkedColor);
        checkedPaint.setStyle(Paint.Style.STROKE);

        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//消除锯齿
        backgroundPaint.setStrokeWidth(defaultPaintWidth);
        backgroundPaint.setColor(checkedColor);//背景色，跟外圈颜色相同
        backgroundPaint.setStyle(Paint.Style.FILL);

        initAnimator();
    }

    /**
     * 初始化 收缩动画  外圈动画
     */
    private void initAnimator() {
        shinkAnimator = ValueAnimator.ofFloat(mRadius);
        shinkAnimator.setDuration(mDuration);
        shinkAnimator.setInterpolator(new AnticipateOvershootInterpolator());
        shinkAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentShink = (float) animation.getAnimatedValue();

                shrinkWidth = currentShink;

                invalidate();
            }
        });
        shinkAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                Toast.makeText(getContext(), "收缩动画完成", Toast.LENGTH_LONG).show();
            }
        });

        /**
         * 外圈动画
         */
        outLineAnimator= ValueAnimator.ofFloat(360);
        outLineAnimator.setDuration(mDuration);
        outLineAnimator.setInterpolator(new LinearInterpolator());
        outLineAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float current = (float) animation.getAnimatedValue();
                Log.d(TAG, "onAnimationUpdate: current = " + current);
                currentOutAngle = current;

                invalidate();
            }
        });
        //添加外圈动画完成的监听
        outLineAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                Toast.makeText(getContext(), "外圈动画完成", Toast.LENGTH_LONG).show();
                shinkAnimator.start();
            }
        });

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawUnChecked(canvas);

        drawChecked(canvas);

        drawShrink(canvas);

        drawWhiteTink(canvas);
    }

    /**
     * 当收缩的宽度 = 半径的时候，表面收缩动画完成
     * 开始绘制  白色对号
     */
    private void drawWhiteTink(Canvas canvas) {
        if (mRadius != shrinkWidth) {
            return;
        }

        canvas.drawLine(startTickPoint.x, startTickPoint.y, centerTickPoint.x, centerTickPoint.y, whitePaint);

        canvas.drawLine(centerTickPoint.x, centerTickPoint.y, endTickPoint.x, endTickPoint.y, whitePaint);

    }

    /**
     * 绘制 收缩动画
     * @param canvas
     */
    private void drawShrink(Canvas canvas) {
        if (360 != currentOutAngle) {
            //不等于360 ,表面圆圈动画未完成
            return;
        }

        //绘制 背景
        canvas.drawCircle(circleCenterPoint.x, circleCenterPoint.y, mRadius, backgroundPaint);

        //绘制半径不断缩小的圆
        canvas.drawCircle(circleCenterPoint.x, circleCenterPoint.y, mRadius - shrinkWidth, whitePaint);

    }

    /**
     * 绘制选中状态
     * @param canvas
     */
    private void drawChecked(Canvas canvas) {
        /**
         * 1,绘制 外圈的 圆环 动画
         */
        RectF rectF = new RectF(circleCenterPoint.x - mRadius, circleCenterPoint.y - mRadius,
                circleCenterPoint.x + mRadius, circleCenterPoint.y + mRadius);
        canvas.drawArc(rectF, 90, currentOutAngle, false, checkedPaint);

    }

    /**
     * 绘制未选中状态
     * @param canvas
     */
    private void drawUnChecked(Canvas canvas) {
        canvas.drawCircle(circleCenterPoint.x, circleCenterPoint.y, mRadius, unPaint);
        canvas.drawLine(startTickPoint.x, startTickPoint.y, centerTickPoint.x, centerTickPoint.y, unPaint);
        canvas.drawLine(centerTickPoint.x, centerTickPoint.y, endTickPoint.x, endTickPoint.y, unPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        Log.d(TAG, "onMeasure: mWidth = " + mWidth + "\n mHeight = " + mHeight);
        circleCenterPoint.x = mWidth / 2;
        circleCenterPoint.y = mHeight / 2;

        /**
         * 这里还需要计算 √ 三个点的位置坐标，以便使用drawLine绘制
         */
        startTickPoint = new PointF(circleCenterPoint.x - 60, circleCenterPoint.y + 10);
        centerTickPoint = new PointF(circleCenterPoint.x , circleCenterPoint.y + 50);
        endTickPoint = new PointF(circleCenterPoint.x + 60, circleCenterPoint.y - 40);
    }

    /**
     * 供外部调用的方法
     */
    public void clickCheckView() {
        if (!isChecked) {
            outLineAnimator.start();
        }
    }
}
