package com.mytestdemo.choose_num;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.mytestdemo.R;

/**
 * @author cuishuxiang
 * @date 2017/12/14.
 */

public class ImageUtils {

    public static Bitmap getGradeBitmap(Context context,int drawablePath,int num) {
        Bitmap oldBitmap = BitmapFactory.decodeResource(context.getResources(), drawablePath);

        Bitmap newBitmap = Bitmap.createBitmap(oldBitmap.getWidth(), oldBitmap.getHeight(), Bitmap.Config.ARGB_8888);

        Paint mPaint = new Paint();
        mPaint.setTextSize(70);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(20);
        mPaint.setTextAlign(Paint.Align.CENTER);

        //计算，使数字 位置居中
        Paint.FontMetrics fontMetrics = new Paint.FontMetrics();
        float startX = oldBitmap.getWidth() / 2;
        float startY = oldBitmap.getHeight() / 2 + (Math.abs(fontMetrics.ascent) + fontMetrics.descent) / 2 - fontMetrics.descent;


        Canvas canvas = new Canvas(newBitmap);
        canvas.drawBitmap(oldBitmap, 0, 0, mPaint);
        canvas.drawText("" + num, startX, startY, mPaint);

        return newBitmap;

    }
}
