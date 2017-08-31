package com.mytestdemo.my_path_paint_canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by cuishuxiang on 2017/4/19.
 */

public class RadarView extends View {
    private int count = 6;
    private Paint paint;
    private Path path;
    private String[] strings = {"a","b","c","d","e","f"};

    public RadarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth() / 2, getHeight() / 2);
        //绘制正六边形
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);    // 填充模式 - 描边
        paint.setStrokeWidth(2);              // 边框宽度 - 10

        path = new Path();

//        path.moveTo(160, 0);
//        path.lineTo(80, 80);
//        path.lineTo(-80, 80);
//        path.lineTo(-160, 00);
//        path.lineTo(-80, -80);
//        path.lineTo(80, -80);
//        path.close();
//
//        canvas.drawPath(path,paint);

        drawPolygon(canvas);//绘制正多边形

        drawLines(canvas);//绘制直线

        drawText(canvas);

    }
    //绘制文字
    private void drawText(Canvas canvas) {
        Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.RED);
        textPaint.setTextSize(50);

        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;
        for (int i=0;i<6;i++) {
            float x=(float) (300 * Math.cos(Math.toRadians(60*i)));
            float y = (float) (300 * Math.sin(Math.toRadians(60*i)));
            canvas.drawText(strings[i], x , y , textPaint);
        }

    }

    private void drawLines(Canvas canvas) {
        Path path = new Path();
        for (int i=0;i<6;i++) {
            path.reset();
            float x=(float) (250 * Math.cos(Math.toRadians(60*i)));
            float y = (float) (250 * Math.sin(Math.toRadians(60*i)));

            path.lineTo(x,y);
            canvas.drawPath(path,paint);
        }

    }

    //绘制正多边形
    private void drawPolygon(Canvas canvas) {
        float r = 50;//每个正六边形之间的距离
        float pi = (float) (2 * Math.PI / 360);

        for (int i=1;i<6;i++) {//0为中心点，不绘制
            float currR = r * i;//当前正六边形距离中点距离
            path.reset();//清空path

            for (int j=0;j<6;j++) {
                if (j ==0) {
                    path.moveTo(currR, 0);//将绘图点，位移
                }else {
                    float x=(float) (currR * Math.cos(Math.toRadians(60*j)));
                    float y = (float) (currR * Math.sin(pi*60*j));

                    path.lineTo(x, y);
                }
            }
            path.close();
            canvas.drawPath(path,paint);
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }


}
