package com.mytestdemo.mvp_demo2.model;

/**
 * Created by cuishuxiang on 2017/10/19.
 *
 * Model
 *
 * 提供我们想要展示在 View 层的数据
 *
 * 提供具体登陆业务逻辑处理的实现
 */

public interface ILoginModel {
    /**
     * ILoginModel 模拟登陆操作的接口，实现类为   LoginModel
     * @param userName
     * @param passWord
     * @param onLoginListenter
     */
    void login(String userName, String passWord, OnLoginListenter onLoginListenter);

}
