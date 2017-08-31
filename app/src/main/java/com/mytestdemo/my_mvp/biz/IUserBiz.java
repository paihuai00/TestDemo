package com.mytestdemo.my_mvp.biz;

/**
 * Created by cuishuxiang on 2017/5/31.
 *
 * model
 */

public interface IUserBiz {
    void login(String username, String pwd, OnLoginListener onLoginListener);
}
