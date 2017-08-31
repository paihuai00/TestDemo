package com.mytestdemo.my_mvp.biz;

import com.mytestdemo.my_mvp.bean.User;

/**
 * Created by cuishuxiang on 2017/5/31.
 *
 * model
 */

public interface OnLoginListener {
    void loginSuccess(User user);

    void loginFailed();
}
