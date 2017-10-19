package com.mytestdemo.my_mvp.model;

/**
 * Created by cuishuxiang on 2017/5/31.
 *
 * model
 */

public class UserModel implements IUserModel {

    @Override
    public void login(final String username, final String pwd, final OnLoginListener onLoginListener) {
        new Thread(){
            @Override
            public void run() {
                //休眠2秒
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //模拟登陆成功
                if ("csx".equals(username) && "123456".equals(pwd)) {
                    User user = new User();
                    user.setUser_name(username);
                    user.setUser_pwd(pwd);
                    onLoginListener.loginSuccess(user);
                } else {
                    onLoginListener.loginFailed();
                }

            }
        }.start();
    }
}
