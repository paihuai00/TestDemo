package com.mytestdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * Created by cuishuxiang on 2017/4/19.
 *
 * 测试path 属性
 */

public class MyPathView  extends View{
    private Paint mPaint;
    private Path mPath;
    public MyPathView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);    // 填充模式 - 描边
        mPaint.setStrokeWidth(10);              // 边框宽度 - 10
        mPath = new Path();

        ViewConfiguration.get(getContext()).getScaledTouchSlop();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //移动坐标系，到中心位置
        canvas.translate(getWidth() / 2, getHeight() / 2);

        /**
         * 测试 moveTo  只改变下次操作的起点
         */
//        mPath.lineTo(200, 200);//此前没有设置开始的位置，默认从 原点-->（200,200）画线
//        mPath.moveTo(200,100);                       // moveTo
//        mPath.lineTo(200,0);                         // lineTo
//        canvas.drawPath(mPath,mPaint);
//        mPath.reset();

        /**
         * setLastPoint 是重置上次操作的最后一个点(对绘制前后都有影响)，
         */
//        mPath.reset();
//        mPath.lineTo(200, 200);
//        mPath.setLastPoint(200,100);                 // setLastPoint
//        mPath.lineTo(200, 0);
//        canvas.drawPath(mPath,mPaint);

        /**
         * 测试close()方法
         */
//        mPath.reset();
//        mPath.lineTo(200, 200);
//        mPath.lineTo(200, 0);
//        mPath.close();
//        canvas.drawPath(mPath,mPaint);

        /**
         * addXxx与arcTo
         * Path中添加基本图形，重点区分addArc与arcTo。
         *
         * Path.Direction.CW:枚举类型，顺时针
         * Path.Direction.CCW:枚举类型，逆时针
         */
//        mPath.reset();
////        mPath.addRect(-200, -200, 200, 200, Path.Direction.CW);
//        mPath.addRect(-200, -200, 200, 200, Path.Direction.CCW);
//        mPath.setLastPoint(-100,100);//改变最后一个点坐标，测试 顺时针 逆时针
//        canvas.drawPath(mPath,mPaint);

        /**
         *  path合并
         */
//        canvas.scale(1, -1); // <-- 注意 翻转y坐标轴
//
//        Path src = new Path();
//        mPath.addRect(-100, -100, 100, 100, Path.Direction.CW);
//        src.addCircle(0, 0, 100, Path.Direction.CW);
//
//        mPath.addPath(src,0,200);//将src位移，加入到mPath
////        mPaint.setColor(Color.BLACK);// 绘制合并后的路径
//        canvas.drawPath(mPath, mPaint);

        /**
         *  addArc
         */
//        canvas.scale(1, -1); // <-- 注意 翻转y坐标轴
//        mPath.reset();
//        mPath.lineTo(100, 100);
//        RectF oval = new RectF(0, 0, 300, 300);
//
////        mPath.addArc(oval, 0, 270);
////        mPath.arcTo(oval,0,270,true);             // <-- 和上面一句作用等价
//
//        mPath.arcTo(oval, 0, 90);
//        canvas.drawPath(mPath,mPaint);

        /**
         * 测试 path 平移
         */
        canvas.scale(1, -1); // <-- 注意 翻转y坐标轴
        mPath.reset();
        mPath.addArc(new RectF(0, 0, 100, 100), 0, 360);
        mPaint.setColor(Color.BLACK);
        canvas.drawPath(mPath,mPaint);

        Path dst = new Path();
        mPath.offset(200, 0, dst);

        mPaint.setColor(Color.RED);//更改画笔的颜色
        canvas.drawPath(dst,mPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);


    }

}
