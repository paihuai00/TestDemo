package com.mytestdemo.mvp_demo2.persenter;

/**
 * Created by cuishuxiang on 2017/10/19.
 * 登陆的Presenter 的接口
 *
 * 实现类为LoginPresenter，完成登陆的验证，以及销毁当前view
 */

public interface IPresenter {

    void confirmLogin(String userName, String password);

    void onLoginDestroy();
}
