package com.mytestdemo.my_seekbar_bezier;

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
 * Created by cuishuxiang on 2017/7/27.
 *
 * 二阶 贝塞尔  曲线
 */

public class QuadBezierView extends View {
    private static final String TAG = "QuadBezierView";
    
    private Paint mPaint;//画笔
    private int centerX, centerY;

    private PointF start, end, control;

    private Path path;

    public QuadBezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //初始化操作
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);//画笔颜色
        mPaint.setStrokeWidth(8);//宽度
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(60);

        start = new PointF(0,0);
        end = new PointF(0,0);
        control = new PointF(0,0);

        path = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //得到当前view的长宽 700， 1080
        Log.d(TAG, "onSizeChanged: " + "CurrentW: " + w + "CurrentH: " + h + "\n oldW: " + oldw + " oldh : " + oldh);
        
        centerX = w / 2;//540
        centerY = h / 2;//350

        // 初始化数据点和控制点的位置
        start.x = centerX-200;//340
        start.y = centerY;//350
        end.x = centerX+200;//740
        end.y = centerY;//350
        control.x = centerX;//540
        control.y = centerY-100;//250
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 根据触摸位置更新控制点，并提示重绘
        control.x = event.getX();
        control.y = event.getY();
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制数据点和控制点
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(start.x,start.y,mPaint);//画起始点
        canvas.drawPoint(end.x,end.y,mPaint);//画终止点
        canvas.drawPoint(control.x,control.y,mPaint);//画初始时候，控制点

        // 绘制辅助线
        mPaint.setStrokeWidth(4);
        canvas.drawLine(start.x,start.y,control.x,control.y,mPaint);
        canvas.drawLine(end.x,end.y,control.x,control.y,mPaint);

        // 绘制贝塞尔曲线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);

        path.moveTo(start.x,start.y);
        path.quadTo(control.x,control.y,end.x,end.y);

        canvas.drawPath(path, mPaint);
        path.reset();

//        path.moveTo(100, 100);
//        path.quadTo(150, 150, 150, 150);
//
//        canvas.drawPath(path,mPaint);

    }
}
