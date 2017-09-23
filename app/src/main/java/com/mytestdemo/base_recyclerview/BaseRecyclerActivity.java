package com.mytestdemo.base_recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mytestdemo.BaseActivity;
import com.mytestdemo.R;

/**
 * Created by cuishuxiang on 2017/9/23.
 *
 * http://www.jianshu.com/p/b343fcff51b0
 */

public class BaseRecyclerActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_recycler);
    }
}
