package com.mytestdemo.dynamic_add_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;

import com.mytestdemo.R;

/**
 * @Created by cuishuxiang
 * @date 2018/1/30.
 *
 * 自定义一个progresbar，
 * 圆角，灰色底，蓝色进度
 */

public class ProgressView extends ProgressBar {
    private static final String TAG = "ProgressView";

    private int GRAY_COLOR = Color.GRAY;//灰色底
    private int BLUE_COLOR = Color.BLUE;//蓝色进度

    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        Drawable drawable = context.getResources().getDrawable(R.drawable.bg_progress_state);
        setProgressDrawable(drawable);
    }


    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.draw
    }


}
