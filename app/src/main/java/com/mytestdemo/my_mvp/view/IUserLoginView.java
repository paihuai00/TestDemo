package com.mytestdemo.my_mvp.view;

import com.mytestdemo.my_mvp.model.User;

/**
 * Created by cuishuxiang on 2017/5/31.
 * <p>
 * view
 */

public interface IUserLoginView {
    String getUserName();

    String getPassword();

    void clearUserName();

    void clearPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity(User user);

    void showFailedError();
}
