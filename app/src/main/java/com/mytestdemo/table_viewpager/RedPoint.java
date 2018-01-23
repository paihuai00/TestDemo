package com.mytestdemo.table_viewpager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * @Created by cuishuxiang
 * @date 2018/1/22.
 *
 * 红点 TextView
 */

public class RedPoint extends View {
    private static final String TAG = "RedPoint";
    private String middleText = "";

    private Paint mPaint;

    private float mRadius;
    private float mCenterX, mCenterY;

    //圆的颜色
    private int RED_COLOR = Color.RED;
    //字体颜色 白色
    private int WHITE_COLOR = Color.WHITE;

    public RedPoint(Context context) {
        this(context,null);
    }

    public RedPoint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initView(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mCenterX = getWidth() / 2;
        mCenterY = getHeight() / 2;
        mRadius = Math.max(mCenterX, mCenterY);
        Log.d(TAG, "onDraw:  mRadius = " + mRadius + "  mCenterX = " + mCenterX + " mCenterY = " + mCenterY);

        mPaint.setColor(RED_COLOR);
        canvas.drawCircle(mCenterX, mCenterY, mRadius, mPaint);

        //绘制文字
        if (TextUtils.isEmpty(middleText)) {
            return;
        }
        mPaint.setColor(WHITE_COLOR);

        //计算基线位置，使文字居中
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float baseLine = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom + getHeight() / 2;

        canvas.drawText(middleText, getWidth() / 2, baseLine, mPaint);
    }

    private void initView(Context context) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(30);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int mWidth = MeasureSpec.getSize(widthMeasureSpec);
        int mWidthMode = MeasureSpec.getMode(widthMeasureSpec);

        int mHeight = MeasureSpec.getSize(heightMeasureSpec);
        int mHeightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = Math.max(mWidth, mHeight);

        if (mWidthMode == MeasureSpec.EXACTLY) {
            setMeasuredDimension(width, width);
        }else {
            super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        }
    }


    public void setMiddleText(String middleString) {
        middleText = middleString;
        invalidate();
    }
}
