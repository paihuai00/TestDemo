package com.mytestdemo.mvp_demo;

/**
 * Created by cuishuxiang on 2017/10/19.
 *
 * 定义Presenter的顶级父接口，并定义attach和detach方法，
 * 这里用到泛型中extends来规定传入的数据类型
 */

public interface IBasePresenter<V extends IBaseView> {

    /**
     * 保存 View 的引用
     * @param view
     */
    void attach(V view);

    /**
     * 清除View
     */
    void detach();
}
