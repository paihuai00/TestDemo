package com.mytestdemo.my_seekbar_bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by cuishuxiang on 2017/8/3.
 * 贝塞尔曲线，实现气泡拖拽效果
 */

public class DragMsgPointView extends View {
    private static final String TAG = "DragMsgPointView";
    //初始化固定圆
    private PointF mFixPoint;//固定圆的圆心
    private float fixRadius = 60;//固定圆的半径
    private final float maxFixRadius = 60;

    //拖拽圆
    private PointF mDragPoint;//拖拽圆的圆心
    private float dragRadius = 60;//拖拽圆的半径

    private Paint mPaint;

    private Path mPath;

    public DragMsgPointView(Context context) {
        this(context, null);
    }

    public DragMsgPointView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragMsgPointView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**
         * 在这里进行初始化操作
         */
        mPaint = new Paint();//初始化画笔
        mPaint.setColor(Color.RED);
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw: ");
        if (mDragPoint == null || mFixPoint == null) {
            return;
        }
        if (dragRadius <= 0) {
            return;
        }
        //绘制 固定圆
        canvas.drawCircle(mFixPoint.x, mFixPoint.y, maxFixRadius, mPaint);

        //计算俩圆直接距离
        float circleDistance = getPointDistance(mDragPoint, mFixPoint);
        Log.d(TAG, "onDraw: 两圆距离 " + circleDistance);

        //距离越大，圆越小
        if (dragRadius > 0) {
            dragRadius = dragRadius - 5;//每次圆半径是之前的3/4
        }

        Log.d(TAG, "拖拽圆半径radius：" + dragRadius + "\nX：" + mDragPoint.x + " Y: " + mDragPoint.y);
        canvas.drawCircle(mDragPoint.x, mDragPoint.y, dragRadius, mPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float downX = event.getX();
        float downY = event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                initTwoPoint(downX,downY);
                break;

            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onTouchEvent: ACTION_MOVE: x " + event.getX() + "\ny " + event.getY());
                updateDragPoint(event.getX(), event.getY());
                break;

            case MotionEvent.ACTION_UP:
                break;
        }
        invalidate();//重绘
        return true;
    }

    //初始化 俩个圆心
    private void initTwoPoint(float x, float y) {
        dragRadius = 60;
        mFixPoint = new PointF(x, y);
        mDragPoint = new PointF(x, y);
    }

    /**
     * 更新 拖拽圆的位置
     */
    private void updateDragPoint(float x, float y) {
        mDragPoint.x = x;
        mDragPoint.y = y;
    }
    
    //dp-->px
    private int dip2px(int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dip,getResources().getDisplayMetrics());
    }

    //计算两个圆心，直接距离
    private float getPointDistance(PointF dragPoint, PointF mFixPoint) {
        float x = (float) Math.pow((double) dragPoint.x - (double)mFixPoint.x, 2);
        float y = (float) Math.pow((double) dragPoint.y - (double) mFixPoint.y, 2);
        Log.d(TAG, "getPointDistance: 圆心距离：" + (float) Math.sqrt(x + y));
        return (float) Math.sqrt(x + y);
    }
}
