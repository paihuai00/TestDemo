package com.mytestdemo.navigationbar_builder;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.ViewGroup;

import com.mytestdemo.R;

import java.util.List;

/**
 * Created by cuishuxaing on 2017/10/11.
 */

public class DefaultNavigationBar extends AbsNavigationBar {
    public DefaultNavigationBar(Builder.AbsNavigationBarParams params) {
        super(params);
    }

    @Override
    public int bindLayoutId() {
        return R.layout.title_bar;
    }

    @Override
    public void applyView() {
        //绑定效果


    }

    public static class Builder extends AbsNavigationBar.Builder {
        DefaultNavigationParams p ;
        @Override
        public DefaultNavigationBar builder() {
            DefaultNavigationBar defaultNavigationBar = new DefaultNavigationBar(p);
            return defaultNavigationBar;
        }

        /**
         * @param context
         * @param parent  添加到那个父布局
         */
        public Builder(Context context, ViewGroup parent) {
            super(context, parent);
            p = new DefaultNavigationParams(context, parent);
        }

        //1，设置所有效果

        public static class DefaultNavigationParams
                extends AbsNavigationBarParams {

            //2，放置所有的效果

            public DefaultNavigationParams(Context context, ViewGroup parent) {
                super(context, parent);
            }
        }
    }
}
