package com.mytestdemo.city_loadview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by cuishuxiang on 2017/8/16.
 *
 * 三角形，正方形 ，圆形 不断变化的View
 */

public class ShapeLoadView extends View {
    private static final String TAG = "ShapeLoadView";
    private Paint circlePaint;
    private Paint trianglePaint;
    private Path trianglePath;

    private int width, height;

    private Shape shape;

    public ShapeLoadView(Context context) {
        this(context, null);
    }

    public ShapeLoadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeLoadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        circlePaint = new Paint();
        circlePaint.setColor(Color.BLUE);
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setAntiAlias(true);
        circlePaint.setDither(true);

        trianglePaint = new Paint();
        trianglePaint.setColor(Color.RED);
        trianglePaint.setStyle(Paint.Style.FILL);
        trianglePaint.setAntiAlias(true);
        trianglePaint.setDither(true);
        //开始的时候，是圆
        shape = Shape.oval_shape;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        switch (shape) {
            //绘制圆
            case oval_shape:
                canvas.drawOval(width / 2 - 20, height / 2 - 20 , width / 2 + 20, height / 2 + 20, circlePaint);
                break;
            //绘制三角形
            case triangle_shape:
                trianglePath = getTrianglePath(width / 2, height / 2, 35);
                canvas.drawPath(trianglePath, trianglePaint);
                break;

            //绘制正方形
            case rect_shape:
                drawRect(canvas);
                break;
        }

    }

    /**
     * 绘制 矩形
     * @param canvas
     */
    private void drawRect(Canvas canvas) {
        Rect rect = new Rect(width / 2 - 20, height / 2 - 20, width / 2 + 20, height / 2 + 20);
        canvas.drawRect(rect, trianglePaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        Log.d(TAG, "onMeasure: width=" + width + "  height=" + height);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 得到三角形的path
     * @param centerX 三角形 中心点 x 坐标
     * @param centerY 三角形 中心点 y 坐标
     * @param side_length  三角形 边长
     * @return
     */
    private Path getTrianglePath(float centerX, float centerY, float side_length) {
        Path path = new Path();

        //对应的 1那段长度
        float length_1 = (float) (side_length / 2 * Math.tan(Math.toRadians(30)));
        //√3 那段的长度
        float length_3 = (float) (side_length * Math.cos(Math.toRadians(30))) - length_1;

        //三角形顶点
        PointF topPoint = new PointF(centerX, centerY - length_3);
        //左下角
        PointF leftPoint = new PointF(centerX - side_length / 2, centerY + length_1);
        //右下角
        PointF rightPoing = new PointF(centerX + side_length / 2, centerY + length_1);

        //首先移动到顶点
        path.moveTo(topPoint.x, topPoint.y);
        //连线
        path.lineTo(rightPoing.x, rightPoing.y);
        path.lineTo(leftPoint.x, leftPoint.y);

        path.close();

        return path;
    }

    /**
     * 提供给外部方法，以便变换图形
     */
    public void changeShape() {
        switch (shape) {
            case oval_shape:
                shape = Shape.triangle_shape;
                break;

            case triangle_shape:
                shape = Shape.rect_shape;
                break;

            case rect_shape:
                shape = Shape.oval_shape;
                break;
        }

        invalidate();

    }

    private enum Shape {
        triangle_shape,oval_shape, rect_shape;
    }

}
