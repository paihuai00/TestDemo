package com.mytestdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by cuishuxiang on 2017/4/18.
 * 实现一个简单的计数器，每点击它一次，计数值就加1并显示出来。
 */

public class MyCounterView extends View implements View.OnClickListener{
    private Paint mPaint;//自定义画笔
    private Rect mRect;//用于获取文字的宽和高

    private int mCount;

    public MyCounterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //初始化画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRect = new Rect();

        //文本的点击事件
        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLUE);
        // 绘制一个填充色为蓝色的矩形
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(50);
        String text = String.valueOf(mCount);

        // 获取文字的宽和高
        mPaint.getTextBounds(text, 0, text.length(), mRect);
        float textWidth = mRect.width();
        float textHeight = mRect.height();

        // 绘制字符串
        canvas.drawText(text, getWidth() / 2-textWidth/2, getHeight() / 2+textHeight/2
                , mPaint);
    }

    @Override
    public void onClick(View view) {
        mCount ++;

        // 重绘
        invalidate();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int wSize = MeasureSpec.getSize(widthMeasureSpec);//获取宽度的size
        int wMode = MeasureSpec.getMode(widthMeasureSpec);//宽度测量的mode

        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);

    }

}
