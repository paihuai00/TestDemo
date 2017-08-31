package com.mytestdemo.my_seekbar_bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by cuishuxiang on 2017/7/27.
 */

public class CubicBezierView extends View {
    private Paint mPaint;
    private Path mPath;

    private PointF startPoint,endPoint,control_1, control_2;

    private int mode;

    public void setMode(int mode) {
        this.mode = mode;
    }

    public CubicBezierView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        startPoint = new PointF(w / 2 - 200, h / 2);
        endPoint = new PointF(w / 2 + 200, h / 2);

        control_1 = new PointF(w / 2 - 200, h / 2 - 200);
        control_2 = new PointF(w / 2 + 200, h / 2 - 200);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (1 == mode) {
            //改变控制点1
            control_1.x = event.getX();
            control_1.y = event.getY();

        } else {
            control_2.x = event.getX();
            control_2.y = event.getY();
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStrokeWidth(20);
        mPaint.setColor(Color.GRAY);
        canvas.drawPoint(startPoint.x, startPoint.y, mPaint);// 画初始点
        canvas.drawPoint(endPoint.x, endPoint.y, mPaint);//画结束点
        canvas.drawPoint(control_1.x, control_1.y, mPaint);
        canvas.drawPoint(control_2.x, control_2.y, mPaint);

        //画辅助线
        mPaint.setStrokeWidth(5);
        canvas.drawLine(startPoint.x, startPoint.y, control_1.x, control_1.y, mPaint);
        canvas.drawLine(control_1.x, control_1.y, control_2.x, control_2.y, mPaint);
        canvas.drawLine(control_2.x, control_2.y, endPoint.x, endPoint.y, mPaint);

        //绘制贝塞尔曲线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);
        mPath = new Path();
        mPath.moveTo(startPoint.x,startPoint.y);

        mPath.cubicTo(control_1.x, control_1.y, control_2.x, control_2.y, endPoint.x, endPoint.y);
        canvas.drawPath(mPath, mPaint);
    }
}
