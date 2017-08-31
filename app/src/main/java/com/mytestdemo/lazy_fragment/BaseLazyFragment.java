package com.mytestdemo.lazy_fragment;

import android.support.v4.app.Fragment;

/**
 * Created by cuishuxiang on 2017/3/30.
 *
 * 注：懒加载,Fragment生命周期中，setUserVisbleHint先于onCreateView执行
 *
 */

public abstract class BaseLazyFragment extends Fragment {
    // 标志位，标志Fragment已经初始化完成。
    public boolean isPrepared = false;//设置为public
    protected boolean isVisible = false;

    /**
     * 实现Fragment数据的缓加载
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
            lazyLoad();
        }else {
            isVisible = false;
            onInVisible();
        }
    }

    //懒加载
    private void lazyLoad() {
        loadData();
    }
    protected void onInVisible() {

    }
    protected abstract void loadData();
}
