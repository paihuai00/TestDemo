package com.mytestdemo.my_path_paint_canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by cuishuxiang on 2017/4/20.
 */

public class MyPathText extends View {
    private Paint paint;
    private Path path;

    public MyPathText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
//        paint.setStyle(Paint.Style.FILL_AND_STROKE);
//        paint.setStrokeWidth(5);

        path = new Path();
//        path.lineTo(100, 100);
//        path.moveTo(100, 0);//moveTo 是
//        path.lineTo(300,300);

//        path.cubicTo(100,50,200,300, 100, 500);

//        path.reset();
//        path.moveTo(100,500);
//        path.rQuadTo(600 / 2, 100, 600, 0);//r的意思是，以变换之后的点(100,500)为坐标原点，进行绘制
////        path.rQuadTo(600 / 2, 100, 600, 0);
//        path.cubicTo();

        /**
         * 测试填充模式
         */
//        path.addCircle(100, 100, 50, Path.Direction.CW);//顺时针，原点100,100 半径50
//
//        path.addRect(100, 100, 200, 200, Path.Direction.CW);
//        path.setFillType(Path.FillType.INVERSE_WINDING);
//
//        canvas.drawPath(path, paint);
        paint.setStrokeWidth(5);

        RectF rect = new RectF(0, 0, 980, 150);

        canvas.drawRoundRect(rect, 90, 90, paint);
    }
}
