package com.mytestdemo.mvp_demo2.persenter;

import com.mytestdemo.mvp_demo2.model.LoginModel;
import com.mytestdemo.mvp_demo2.model.OnLoginListenter;
import com.mytestdemo.mvp_demo2.view.IloginView;
import com.mytestdemo.mvp_demo2.view.Mvp2LoginActivity;

/**
 * Created by cuishuxiang on 2017/10/19.
 *
 * 1 完成presenter的实现。这里面主要是Model层和View层的交互和操作。
 *
 * 2  presenter里面还有个OnLoginListener，
 *    其在Presenter层实现，给Model层回调，更改View层的状态，
 *    确保 Model层不直接操作View层。LoginPersenter，
 *    LoginPersenter只 有View和Model的引用那么Model怎么把结果告诉View呢？
 */

public class LoginPersenter implements IPresenter,OnLoginListenter{
    private LoginModel loginModel;
    private IloginView iloginView;

    public LoginPersenter(IloginView iloginView) {
        this.loginModel = new LoginModel();
        this.iloginView = iloginView;
    }

    @Override
    public void confirmLogin(String userName, String password) {
        if (iloginView != null) {
            iloginView.showLoading();
        }
        /**
         * 这里调用 model 层的登陆方法
         *
         * OnLoginListenter 通过接口，实现回调
         */
        loginModel.login(userName, password, this);
    }

    @Override
    public void onLoginDestroy() {

    }

    @Override
    public void loginSucceed() {
        iloginView.hindLoading();

        iloginView.loginSucceedToView();
    }

    @Override
    public void loginError() {
        iloginView.hindLoading();

        iloginView.showError();
    }
}
