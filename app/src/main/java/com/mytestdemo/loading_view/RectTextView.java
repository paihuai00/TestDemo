package com.mytestdemo.loading_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by cuishuxiang on 2017/8/14.
 *
 * 测试 文字 位于 控件的位置。
 */

public class RectTextView extends View {
    private static final String TAG = "RectTextView";
    private Paint paint;

    private Paint bgPaint;

    private String string = "测试文字";

    public RectTextView(Context context) {
        this(context,null);
    }

    public RectTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RectTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setDither(true);
        paint.setAntiAlias(true);
        paint.setTextSize(30);

        bgPaint = new Paint();
        bgPaint.setColor(Color.WHITE);
        bgPaint.setAlpha(230);
        bgPaint.setDither(true);
        bgPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Rect rect = new Rect(0, 0, 300, 300);
        canvas.drawRect(rect, bgPaint);
        Log.d(TAG, "onDraw: rect.left=" + rect.left + " rect.bottom=" + rect.bottom + "  rect.centerY()=" + rect.centerY());
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        paint.setTextAlign(Paint.Align.CENTER);//X居中
        //设置 Y  居中，需要计算 基准点
        canvas.drawText(string, rect.centerX(), rect.centerY() - ((fontMetrics.bottom - fontMetrics.top) / 2 + fontMetrics.top), paint);

    }
}
