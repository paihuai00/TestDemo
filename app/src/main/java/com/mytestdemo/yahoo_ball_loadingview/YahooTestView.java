package com.mytestdemo.yahoo_ball_loadingview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by cuishuxiang on 2017/8/15.
 */

public class YahooTestView extends View {
    private static final String TAG = "YahooTestView";

    private Paint paint;

    public YahooTestView(Context context) {
        this(context, null);
    }

    public YahooTestView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public YahooTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //初始化画笔
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.clipRect(0, 0, 1080, 600);

        paint.setColor(Color.RED);
        paint.setStrokeWidth(20);
        Rect rect = new Rect(100, 100, 500, 500);
        canvas.drawRect(rect, paint);

        canvas.drawCircle(300, 300, 10, paint);

//        paint.setStrokeWidth(0);
//        paint.setStyle(Paint.Style.FILL);
//        paint.setColor(Color.BLACK);
//        canvas.drawRect(rect, paint);

        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(0);
        canvas.drawLine(500,0,500,1000,paint);
        canvas.drawLine(0,500,1000,500,paint);
    }
}