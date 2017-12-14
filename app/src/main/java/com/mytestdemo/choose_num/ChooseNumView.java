package com.mytestdemo.choose_num;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.mytestdemo.R;

/**
 * @author cuishuxiang
 * @date 2017/12/14.
 *
 * 自定义  打分View
 */

public class ChooseNumView extends View {
    private static final String TAG = ChooseNumView.class.getSimpleName().toString();


    private Paint mPaint;
    private Paint.FontMetrics fontMetrics;//用于文字绘制时的测量

    private int mScreenWidth,mScreenHeight;

    private int strokeWidth = 30;//画笔宽度
    private int mTextSize = 60;//文字大小

    private Bitmap bgBitmap;//背景图

    private int num; //绘制到View上的数字


    private Bitmap numsBitmap;

    public ChooseNumView(Context context) {
        this(context,null);
    }

    public ChooseNumView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChooseNumView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    /**
     * 设置绘制的背景图片
     * @param bgBitmap
     */
    public void setBgBitmap(Bitmap bgBitmap) {
        this.bgBitmap = bgBitmap;
    }

    public void setNum(int num) {
        this.num=num;
        invalidate();//刷新一下
    }

    private void init() {
        if (mPaint == null) {
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaint.setDither(true);
            mPaint.setColor(Color.RED);//默认红色
            mPaint.setStrokeWidth(strokeWidth);
            mPaint.setDither(true);
            mPaint.setAntiAlias(true);
            mPaint.setTextSize(mTextSize);

            mPaint.setTextAlign(Paint.Align.CENTER);

            fontMetrics= mPaint.getFontMetrics();
        }

        if (bgBitmap == null) {
            //从资源中获取Bitmap , 设置一张默认图
            bgBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_bg_img);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float drawBitmapLeft = (getWidth() - bgBitmap.getWidth()) / 2;
        float drawBitmapTop = (getHeight() - bgBitmap.getHeight()) / 2;

        canvas.drawBitmap(bgBitmap,drawBitmapLeft,drawBitmapTop,mPaint);

        //计算基线的位置
        float startY = getHeight() / 2 + (Math.abs(fontMetrics.ascent) + fontMetrics.descent) / 2
                - fontMetrics.descent;

        canvas.drawText(num + "", getWidth() / 2, startY, mPaint);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mScreenWidth = MeasureSpec.getSize(widthMeasureSpec);

        mScreenHeight = MeasureSpec.getSize(heightMeasureSpec);

        if (bgBitmap != null) {
            //确保宽高 能包裹  背景图片
            mScreenWidth = mScreenWidth > bgBitmap.getWidth() ? mScreenWidth : bgBitmap.getWidth();
            mScreenHeight = mScreenHeight > bgBitmap.getHeight() ? mScreenHeight : bgBitmap.getHeight();
        }

        setMeasuredDimension(mScreenWidth,mScreenWidth);

        Log.d(TAG, "onMeasure: mScreenWidth = " + mScreenWidth + "\nmScreenHeight = " + mScreenHeight);
    }


    public Bitmap getNumsBitmap() {
        if (numsBitmap != null) {
            return numsBitmap;
        }
        return null;
    }
}
