package com.mytestdemo.behavior_md;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author cuishuxiang
 * @date 2017/11/15.
 *
 * behavior 相当于观察者模式，此view 需要依赖  dependency 这个view
 *
 * 在本例中，就是   TextView  依赖   AppBarLayout
 */

public class EasyBehavior extends CoordinatorLayout.Behavior<TextView> {
    private static final String TAG = "EasyBehavior";

    private int height;

    public EasyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, TextView child, View dependency) {
        //告知监听的dependency 是 AppBarLayout
        return dependency instanceof AppBarLayout;
    }

    /**
     * 当 dependency(即 AppBarLayout )，变化的时候可以对 child ( TextView ) 进行操作
     *
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, TextView child, View dependency) {

        height = dependency.getHeight();

        Log.d(TAG, "onDependentViewChanged: dependency.getY()：" + dependency.getY() +
                "\ndependency.getTranslationY()：" + dependency.getTranslationY()+
                "\ndependency.getBottom()：" + dependency.getBottom()+
                "\ndependency.getHeight()：" + dependency.getHeight());

        float targetHeight = dependency.getHeight();

        float scollY = Math.abs(dependency.getY());

        float percent = scollY / 450;


        //child.setY(child.getHeight() * percent);

//        child.setHeight((int) (150 * percent));

        child.setTranslationY((int) (150 * percent));

        Log.d(TAG, "onDependentViewChanged: " +
                "(int) (150 * percent)(int) (150 * percent):" + (int) (150 * percent));

        return false;
    }
}
