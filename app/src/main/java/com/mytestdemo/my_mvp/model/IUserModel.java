package com.mytestdemo.my_mvp.model;

/**
 * Created by cuishuxiang on 2017/5/31.
 *
 * model
 */

public interface IUserModel {
    void login(String username, String pwd, OnLoginListener onLoginListener);
}
