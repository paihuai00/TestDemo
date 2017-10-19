package com.mytestdemo.my_mvp.model;

/**
 * Created by cuishuxiang on 2017/5/31.
 *
 * model
 */

public interface OnLoginListener {
    void loginSuccess(User user);

    void loginFailed();
}
