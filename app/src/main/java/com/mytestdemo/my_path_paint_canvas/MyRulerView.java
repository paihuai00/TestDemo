package com.mytestdemo.my_path_paint_canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by cuishuxiang on 2017/4/19.
 *
 * 绘制一把尺子
 */

public class MyRulerView extends View {

    // 刻度尺高度
    private static final int DIVIDING_RULE_HEIGHT = 70;
    // 距离左右间
    private static final int DIVIDING_RULE_MARGIN_LEFT_RIGHT = 10;

    // 第一条线距离边框距离
    private static final int FIRST_LINE_MARGIN = 5;
    // 打算绘制的厘米数
    private static final int DEFAULT_COUNT = 9;


    private int mDividRuleHeight;

    private int mHalfRuleHeight;

    private int mDividRuleLeftMargin;

    private int mFirstLineMargin;


    public MyRulerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initData();
    }
    //将以上数据转为对应像素值：
    private void initData() {
        mDividRuleHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70, getResources().getDisplayMetrics());

        mHalfRuleHeight = mDividRuleHeight / 2;

        mDividRuleLeftMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());

        mFirstLineMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                5, getResources().getDisplayMetrics());

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawOutRect(canvas);
    }

    //绘制外部的框
    private void drawOutRect(Canvas canvas) {
        RectF rectF = new RectF(mDividRuleLeftMargin, 20, getWidth() - 10, 50);


    }
}
