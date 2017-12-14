package com.mytestdemo.mvp_demo2.model;

import android.os.Handler;
import android.text.TextUtils;

/**
 * Created by cuishuxiang on 2017/10/19.
 *
 * 延时模拟登陆（2s），如果名字或者密码为空则登陆失败，否则登陆成功
 */

public class LoginModel implements ILoginModel {
    @Override
    public void login(final String userName,final String passWord, final OnLoginListenter onLoginListenter) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean isError = false;
                if (TextUtils.isEmpty(userName)){
                    onLoginListenter.loginError();//model层里面回调listener

                    isError = true;
                }

                if (TextUtils.isEmpty(passWord)){
                    onLoginListenter.loginError();//model层里面回调listener

                    isError = true;
                }

                if (!isError) {
                    onLoginListenter.loginSucceed();
                }
            }
        }, 2000);
    }
}
