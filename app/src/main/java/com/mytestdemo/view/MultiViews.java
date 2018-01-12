package com.mytestdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * @Created by cuishuxiang
 * @date 2018/1/10.
 *
 * 多边形
 */

public class MultiViews extends ViewGroup {
    private static final String TAG = MultiViews.class.getSimpleName().toString();

    public MultiViews(Context context) {
        this(context,null);
    }

    public MultiViews(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiViews(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
    }

    private void initView() {

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
