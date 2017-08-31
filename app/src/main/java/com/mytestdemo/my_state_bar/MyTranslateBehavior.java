package com.mytestdemo.my_state_bar;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.mytestdemo.R;

/**
 * Created by cuishuxiang on 2017/8/22.
 *
 * 自定义 Behavior
 */

public class MyTranslateBehavior extends FloatingActionButton.Behavior {
    /**
     * 当前是否显示状态
     */
    private boolean isShow = false;

    //关联底部的Tab view
    private TextView tab_view;

    public MyTranslateBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 当coordinatorLayout 的子View试图开始嵌套滑动的时候被调用。当返回值为true的时候表明
     * coordinatorLayout 充当nested scroll parent 处理这次滑动，需要注意的是只有当返回值为true
     * 的时候，Behavior 才能收到后面的一些nested scroll 事件回调（如：onNestedPreScroll、onNestedScroll等）
     * 这个方法有个重要的参数nestedScrollAxes，表明处理的滑动的方向。
     *
     * @param coordinatorLayout 和Behavior 绑定的View的父CoordinatorLayout
     * @param child             和Behavior 绑定的View
     * @param directTargetChild
     * @param target
     * @param nestedScrollAxes  嵌套滑动 应用的滑动方向，看 {@link ViewCompat#SCROLL_AXIS_HORIZONTAL},
     *                          {@link ViewCompat#SCROLL_AXIS_VERTICAL}
     * @return
     */
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        //初始化，底部tab 控件
        tab_view = (TextView) coordinatorLayout.findViewById(R.id.tab_view);

        // nestedScrollAxes 滑动关联的轴，我们只关心垂直的滑动
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL ||
                super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    /**
     * 进行嵌套滚动时，被调用
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dxConsumed 已经消费x方向距离
     * @param dyConsumed 已经消费y方向距离
     * @param dxUnconsumed  x 方向剩下的滚动距离
     * @param dyUnconsumed  y 方向剩下的滚动距离
     */
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                               View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);

        //根据实际情况执行动画  一个显示 一个隐藏
        if (dyConsumed > 0) {
            if (!isShow) {
                CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
                child.animate().translationY(params.bottomMargin + child.getMeasuredHeight()).setDuration(400).start();

                //底部tab
                if (tab_view != null) {
                    tab_view.animate().translationY(target.getMeasuredHeight()).setDuration(400).start();
                }
                isShow = !isShow;
            }
        }

        if (dyConsumed < 0) {
            if (isShow) {
                child.animate().translationY(0).setDuration(400).start();
                //底部tab
                if (tab_view != null) {
                    tab_view.animate().translationY(0).setDuration(400).start();
                }

                isShow = !isShow;
            }
        }
    }
}
