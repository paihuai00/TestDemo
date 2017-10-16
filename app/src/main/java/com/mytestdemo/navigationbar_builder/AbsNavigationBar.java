package com.mytestdemo.navigationbar_builder;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by cuishuxaing on 2017/10/11.
 * <p>
 * 使用Builder构建  通用的NavigationBar
 * 由于是base类，接口方法需要在具体类中去实现
 */

public abstract class AbsNavigationBar implements INavigationBar {

    private static final String TAG = "AbsNavigationBar";

    private Builder.AbsNavigationBarParams mParams;

    public AbsNavigationBar(Builder.AbsNavigationBarParams params) {
        this.mParams = params;

        createAndBindView();
    }

    /**
     * 绑定 创建View
     */
    private void createAndBindView() {
        //1.创建View
        View navigationView = LayoutInflater.from(mParams.mContext).
                inflate(bindLayoutId(), mParams.mParent, false);//这里需要传false

        //2.添加 父布局第0位
        mParams.mParent.addView(navigationView,0);

        applyView();

    }



    /***
     * 仿照 AlertDialog 套路
     *
     * AbsNavigationBar  Builder params
     */
    public static abstract class Builder {
        AbsNavigationBarParams params;

        public abstract AbsNavigationBar builder();

        /**
         * @param context
         * @param parent  添加到那个父布局
         */
        public Builder(Context context, ViewGroup parent) {
            //创建
            params = new AbsNavigationBarParams(context, parent);
        }


        /**
         * 参数
         */
        public static class AbsNavigationBarParams {
            public Context mContext;
            public ViewGroup mParent;

            public AbsNavigationBarParams(Context context, ViewGroup parent) {
                this.mContext = context;
                this.mParent = parent;
            }

        }

    }
}
