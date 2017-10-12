package com.mytestdemo.layoutinflater;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.mytestdemo.BaseActivity;
import com.mytestdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cuishuxiang on 2017/10/12.
 * <p>
 * LayoutInflater  理解
 */

public class LayoutInflaterActivity extends BaseActivity {

    @BindView(R.id.root_view)
    LinearLayout rootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layoutinflater);
        ButterKnife.bind(this);

        initTest1();

//        initTest2();

//        initTest3();

//        initTest4();
    }

    /**
     * 4，测试 LayoutInflater inflate两个参数方法
     *  inflate(@LayoutRes int resource, @Nullable ViewGroup root)
     *
     *  root 为null
     *
     */
    private void initTest4() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View childView=layoutInflater.inflate(R.layout.layoutinflater_add_layout, null);

        rootView.addView(childView);
    }

    /**
     * 3，测试 LayoutInflater inflate三个参数方法
     *  inflate(@LayoutRes int resource, @Nullable ViewGroup root, boolean attachToRoot)
     *
     *  root 为null   attachToRoot =false / true  都无效
     *
     */
    private void initTest3() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View childView=layoutInflater.inflate(R.layout.layoutinflater_add_layout, null, false);

        rootView.addView(childView);

    }

    /**
     * 2，测试 LayoutInflater inflate三个参数方法
     *  inflate(@LayoutRes int resource, @Nullable ViewGroup root, boolean attachToRoot)
     *
     *  root 不为null   attachToRoot =  true 为True时候，自动添加到root 布局中了已经
     */
    private void initTest2() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View childView=layoutInflater.inflate(R.layout.layoutinflater_add_layout, rootView, true);
        /**
         * 第三个参数，true
         * 不需要调用addView，否则会报如下异常。
         */
        //java.lang.IllegalStateException: The specified child already has a parent.
        // You must call removeView() on the child's parent first
        //rootView.addView(childView);

    }

    /**
     * 1，测试 LayoutInflater inflate三个参数方法
     *  inflate(@LayoutRes int resource, @Nullable ViewGroup root, boolean attachToRoot)
     *
     *  root 不为null   attachToRoot =false
     */
    private void initTest1() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View childView=layoutInflater.inflate(R.layout.layoutinflater_add_layout, rootView, false);

        rootView.addView(childView);

    }
}
