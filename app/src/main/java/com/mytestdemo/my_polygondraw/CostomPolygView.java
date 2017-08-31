package com.mytestdemo.my_polygondraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.stkent.polygondrawingutil.PolygonDrawingUtil;

/**
 * Created by cuishuxiang on 2017/6/14.
 *
 *
 * https://github.com/stkent/PolygonDrawingUtil
 */

public class CostomPolygView extends View {

    private PolygonDrawingUtil polygonDrawingUtil = new PolygonDrawingUtil();

    public CostomPolygView(Context context) {
        super(context);
    }

    public CostomPolygView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CostomPolygView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();

        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        polygonDrawingUtil.drawPolygon(
                canvas
                , 3
                , getWidth() / 2
                , getHeight() / 2
                , 300
                , 10
                , 90
                , paint);
    }
}
