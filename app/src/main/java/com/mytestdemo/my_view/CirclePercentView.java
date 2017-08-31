package com.mytestdemo.my_view;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import com.mytestdemo.DisplayUtil;
import com.mytestdemo.R;

/**
 * Created by cuishuxiang on 2017/3/31.
 */

public class CirclePercentView extends View{
    private int mCircleColor,mArcColor,mArcWidth,mPercentTextColor,mPercentTextSize,mRadius;
    public CirclePercentView(Context context) {
        super(context);
    }

    public CirclePercentView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CirclePercentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CirclePercentView, defStyleAttr, 0);
        mCircleColor = ta.getColor(R.styleable.CirclePercentView_circleBg, 0xff8e29fa);
        mArcColor = ta.getColor(R.styleable.CirclePercentView_arcColor, 0xffffee00);
        mArcWidth = ta.getDimensionPixelSize(R.styleable.CirclePercentView_arcWidth, dip2px(context, 16));
        mPercentTextColor = ta.getColor(R.styleable.CirclePercentView_arcColor, 0xffffee00);
        mPercentTextSize = ta.getDimensionPixelSize(R.styleable.CirclePercentView_percentTextSize, DisplayUtil.sp2px(context, 16));
        mRadius = ta.getDimensionPixelSize(R.styleable.CirclePercentView_radius, dip2px(context, 100));
        ta.recycle();
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
