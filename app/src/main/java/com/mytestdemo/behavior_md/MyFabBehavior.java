package com.mytestdemo.behavior_md;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @author cuishuxiang
 * @date 2017/11/16.
 * <p>
 * 自定义 FAB 按钮的 Behavior
 */

public class MyFabBehavior extends FloatingActionButton.Behavior {

    private static final String TAG = "MyFabBehavior";

    public MyFabBehavior() {
    }

    public MyFabBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                                       View directTargetChild, View target, int nestedScrollAxes) {
        //设置监听的方向 ：竖直
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    /**
     * 方法在滑动期间被调用
     *
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dxConsumed
     * @param dyConsumed
     * @param dxUnconsumed
     * @param dyUnconsumed
     */
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout,
                               FloatingActionButton child, View target,
                               int dxConsumed, int dyConsumed, int dxUnconsumed,
                               int dyUnconsumed) {
        if (dyConsumed > 0 && dyUnconsumed == 0) {
            Log.d(TAG, "onNestedScroll: 正在上滑");

            if (child.getVisibility() == View.GONE || child.getVisibility() == View.INVISIBLE) {
                child.setVisibility(View.VISIBLE);
            }

        }else {
            child.setVisibility(View.INVISIBLE);
        }

        if (dyConsumed == 0 && dyUnconsumed > 0) {
            Log.d(TAG, "onNestedScroll: 到了底部，但是还在上滑");
        }

        if (dyConsumed < 0 && dyUnconsumed == 0) {
            Log.d(TAG, "onNestedScroll: 下滑....");
        }

        if (dyConsumed == 0 && dyUnconsumed < 0) {
            Log.d(TAG, "onNestedScroll: 到顶部了，还在下滑");
        }

    }

}
