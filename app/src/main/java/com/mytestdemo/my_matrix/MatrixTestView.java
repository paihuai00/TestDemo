package com.mytestdemo.my_matrix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.mytestdemo.R;

/**
 * Created by cuishuxiang on 2017/8/1.
 * matrix  基本操作
 */

public class MatrixTestView extends View {
    private static final String TAG = "MatrixTestView";
    private Paint mPaint;
    private Bitmap bitmap;
    private Matrix matrix;
    public MatrixTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.big_img);
        //初始化 画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#FF4081"));

        //矩阵
        matrix = new Matrix();
        matrix.setTranslate(100f, 100f);//平移变换
        matrix.setRotate(27, 100, 100);//旋转角度27°，指定中心点(100,100)

        matrix.setScale(0.2f,0.5f);//默认缩放，左上角
        matrix.setScale(0.5f, 0.5f, 500, 500);//缩放，以(500,500)中心点

        //错位，分为水平 垂直
        matrix.setSkew(0.5f, 0);//水平错位
        matrix.setSkew(0, 0.5f);

        /**
         * setPolyToPoly() 通过改变参数，可以实现 平移,旋转,缩放,错位,透视
         * 利用确定矩形的4个顶点，根据顶点变化，来对bitmap进行变换。
         * 通过指定的0-4个点，原始坐标以及变化后的坐标，来得到一个变换矩阵。
         * 如果指定0个点则没有效果。
         * setPolyToPoly(float[] src, int srcIndex,float[] dst, int dstIndex,int pointCount)
         * 最终效果由src 和 dst 两个数组控制，两个数组控制4个顶点的坐标
         * srcIndex dstIndex 分别是src和dst第一个值的角标
         * pointCount 是四个顶点中要使用的个数。最大4，如果为0表示不进行操作变换
         */
        //仅指定一个点，达到平移效果
//        float[] src = {0, 0};
//        int DX = 300;
//        float[] dst = {0 + DX, 0 + DX};
//        matrix.setPolyToPoly(src, 0, dst, 0, 1);

        //指定2个点，旋转
        int bw = bitmap.getWidth();//1080
        int bh = bitmap.getHeight();//600
        Log.d(TAG, "init: bitmap: Width" + bw + "\nHeight:" + bh);
        float[] src = {bw / 2, bh / 2,
                        bw, 0};
        float[] dst = {bw / 2, bh / 2,
                bw / 2 + bh / 2, bw / 2 + bh / 2};
        matrix.setPolyToPoly(src, 0, dst, 0, 2);

        matrix.mapRadius(100f);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.YELLOW);
//        canvas.drawBitmap(bitmap, 0, 0, null);//没有使用matrix

        canvas.drawBitmap(bitmap, matrix, null);
        //在(100，100)处 画一个圆
        //可以看出，matrix的平移，对canvas坐标不影响。因为matrix是对bitmap操作
        canvas.drawCircle(100, 100, 30, mPaint);
    }
}
