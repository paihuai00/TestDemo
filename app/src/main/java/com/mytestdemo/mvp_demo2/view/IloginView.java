package com.mytestdemo.mvp_demo2.view;

/**
 * Created by cuishuxiang on 2017/10/19.
 *
 * View 层接口  实现类是 Mvp2LoginActivity
 */

public interface IloginView {
    void showLoading();

    void hindLoading();

    void showError();

    void loginSucceedToView();


}
