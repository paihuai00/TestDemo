package com.mytestdemo.my_message_bubble;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by cuishuxiang on 2017/8/7.
 * <p>
 * 仿QQ 拖拽气泡
 */

public class MessageBubbleView extends View {
    private final String TAG = MessageBubbleView.class.getSimpleName();
    //拖拽圆心点
    private PointF dragCircleCenterPoint;
    private float dragRadius = 40;//半径

    //固定圆心点
    private PointF fixCircleCenterPoint;
    private float maxFixRadius = 40;//固定圆的最大半径(初始半径)
    private float changeFixRadius;  //手指滑动，两个距离越远，半径越小

    //画笔
    private Paint mPaint;

    //第一条贝塞尔曲线的起始点
    private PointF p0, p1;
    //第二条贝塞尔曲线的起始点
    private PointF p2, p3;


    public MessageBubbleView(Context context) {
        this(context, null);
    }

    public MessageBubbleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MessageBubbleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//抗锯齿
        mPaint.setColor(Color.RED);
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (dragCircleCenterPoint == null || fixCircleCenterPoint == null) {
            return;
        }

        if (changeFixRadius > 0) {
            canvas.drawCircle(fixCircleCenterPoint.x, fixCircleCenterPoint.y, changeFixRadius, mPaint);
        }

        canvas.drawCircle(dragCircleCenterPoint.x, dragCircleCenterPoint.y, dragRadius, mPaint);

        if (getBeizerPath() != null) {
            canvas.drawPath(getBeizerPath(), mPaint);
        }

    }

    //初始化2个 圆心点 坐标
    private void initPoint(float downX, float downY) {

        dragCircleCenterPoint = new PointF();
        fixCircleCenterPoint = new PointF();

        fixCircleCenterPoint.x = downX;
        fixCircleCenterPoint.y = downY;

        dragCircleCenterPoint.x = downX;
        dragCircleCenterPoint.y = downY;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                float downX = event.getX();
                float downY = event.getY();
                initPoint(downX, downY);

                break;

            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                float moveY = event.getY();

                updateDragPoint(moveX, moveY);

                //动态改变  固定圆 的半径
                changeFixCircle();
                break;

            case MotionEvent.ACTION_POINTER_UP:
                break;
        }

        invalidate();//更新
        return true;
    }

    /**
      * 动态改变 当前固定圆的 半径大小
      */
    private void changeFixCircle() {
        if (dragCircleCenterPoint == null || fixCircleCenterPoint == null) {
            Log.d(TAG, "changeFixCircle: null");
            return;
        }
        float distance = getPointDistance(dragCircleCenterPoint, fixCircleCenterPoint);

        changeFixRadius = maxFixRadius - distance /10;

        if (changeFixRadius <= 0) {
            changeFixRadius = 0;
        }
    }

    /**
     * 更新dragPoint位置
     * @param moveX
     * @param moveY
     */
    private void updateDragPoint(float moveX, float moveY) {
        dragCircleCenterPoint.x = moveX;
        dragCircleCenterPoint.y = moveY;
    }

    /**
     * 得到 两点圆心 距离
     * @return float distance
     */
    private float getPointDistance(PointF dragPoint, PointF fixPoint) {
        float distanceX = dragPoint.x - fixPoint.x;
        float distanceY = dragPoint.y - fixPoint.y;

        return (float) Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
    }

    //获取到 贝塞尔曲线的path
    private Path getBeizerPath() {
        Path beizerPath = new Path();
        if (fixCircleCenterPoint ==null || dragCircleCenterPoint == null) {
            Log.d(TAG, "getBeizerPath:  fixCircleCenterPoint or fixCircleCenterPoint == null");
            return null;
        }
        if (changeFixRadius <= 0) {
            //当半径小，曲线就不画了
            return null;
        }

        //计算斜率
        float dx = dragCircleCenterPoint.x - fixCircleCenterPoint.x;
        float dy = dragCircleCenterPoint.y - fixCircleCenterPoint.y;

        //tan比值
        float tan = dy / dx;

        //根据tan获取角度值
        double angle_A = (float) Math.atan(tan);

        //依次计算，p0 p1 p2 p3 的值
        p0 = new PointF();
        p0.x = (float) (fixCircleCenterPoint.x + changeFixRadius * Math.sin(angle_A));
        p0.y = (float) (fixCircleCenterPoint.y - changeFixRadius * Math.cos(angle_A));

        p1 = new PointF();
        p1.x = (float) (dragCircleCenterPoint.x + dragRadius * Math.sin(angle_A));
        p1.y = (float) (dragCircleCenterPoint.y - dragRadius * Math.cos(angle_A));

        p2 = new PointF();
        p2.x = (float) (dragCircleCenterPoint.x - dragRadius * Math.sin(angle_A));
        p2.y = (float) (dragCircleCenterPoint.y + dragRadius * Math.cos(angle_A));

        p3 = new PointF();
        p3.x = (float) (fixCircleCenterPoint.x - changeFixRadius * Math.sin(angle_A));
        p3.y = (float) (fixCircleCenterPoint.y + changeFixRadius * Math.cos(angle_A));

        //将两个圆心的中间点作为  控制点
        PointF controlPointF = new PointF();
        controlPointF.x = (fixCircleCenterPoint.x + dragCircleCenterPoint.x) / 2;
        controlPointF.y = (fixCircleCenterPoint.y + dragCircleCenterPoint.y) / 2;

        //整合 贝塞尔曲线  路径
        beizerPath.moveTo(p0.x, p0.y);
        beizerPath.quadTo(controlPointF.x, controlPointF.y, p1.x, p1.y);
        beizerPath.lineTo(p2.x, p2.y);
        beizerPath.quadTo(controlPointF.x, controlPointF.y, p3.x, p3.y);
        beizerPath.close();

        return beizerPath;
    }
}
