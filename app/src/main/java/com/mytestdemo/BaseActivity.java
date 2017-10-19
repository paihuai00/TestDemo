package com.mytestdemo;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by cuishuxiang on 2017/5/9.
 */

public class BaseActivity extends AppCompatActivity {


    protected <T extends View> T getView(@IdRes int resId) {

        return (T) findViewById(resId);
    }
}
